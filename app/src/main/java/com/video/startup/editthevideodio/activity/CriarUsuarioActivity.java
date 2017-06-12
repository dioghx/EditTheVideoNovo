package com.video.startup.editthevideodio.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.util.Util;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;



/**
 * Created by Kelvyn on 04/06/2017.
 */

public class CriarUsuarioActivity extends Activity {

    private EditText editNomeUsuario;
    private EditText editDataUsuario;
    private EditText editTelefoneUsuario;
    private EditText editEmailUsuario;
    private EditText editSenhaUsuario;
    private EditText editCidadeUsuario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_usuario);
        EditText editNomeUsuario = (EditText) findViewById(R.id.editNomeUsuario);
        EditText editDataUsuario = (EditText) findViewById(R.id.editDataUsuario);
        EditText editTelefoneUsuario = (EditText) findViewById(R.id.editTelefoneUsuario);
        EditText editEmailUsuario = (EditText) findViewById(R.id.editEmailUsuario);
        EditText editSenhaUsuario = (EditText) findViewById(R.id.editSenhaUsuario);
        EditText editCidadeUsuario = (EditText) findViewById(R.id.editCidadeUsuario);
    }

    public void cadastrarUsuario(View v){
        Usuario usuario = new Usuario();
        usuario.setNome(editNomeUsuario.getText().toString());
        usuario.setNascimento(editDataUsuario.getText().toString());
        usuario.setTelefone(editTelefoneUsuario.getText().toString());
        usuario.setEmail(editEmailUsuario.getText().toString());
        usuario.setSenha(editSenhaUsuario.getText().toString());
        usuario.getEndereco().setCidade(editCidadeUsuario.getText().toString());

        if(isConnected()) {
            new cadastrarUsuario().execute(usuario);
            Intent paginaPrincipal = new Intent(CriarUsuarioActivity.this,MainActivity.class);
            startActivity(paginaPrincipal);
            finish();
        }
        else
            Toast.makeText(this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
    }
    private class cadastrarUsuario extends AsyncTask<Usuario, Void, String> {

        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected String doInBackground(Usuario... params) {
            HttpURLConnection urlConnection = null;
            try {
                //Utilizar url do webservice de logar
                //URL url = new URL("");
     //           urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                String result = Util.convertObjectJSON(params[0]);
                outputStream.writeBytes(result);

                serverResponseCode = urlConnection.getResponseCode();
                serverResponseMessage = Util.webToString(urlConnection.getInputStream());

                outputStream.flush();
                outputStream.close();

                return result ;
            } catch (Exception e) {
                Log.e("Error", "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent telaPrincipal = null;
            if(Util.getStatusFromJSON(serverResponseMessage).equals("true")) {
                Toast.makeText(CriarUsuarioActivity.this, "Usuário cadastrada no sistema...!", Toast.LENGTH_SHORT).show();
                telaPrincipal = new Intent(CriarUsuarioActivity.this, MainActivity.class);
                startActivity(telaPrincipal);
            }else{
                Toast.makeText(CriarUsuarioActivity.this, "Falha ao cadastrar o usuário.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    public void btnVoltar(View v)
    {
        Intent intent = new Intent(v.getContext(),EscolherCadastroActivity.class);
        startActivity(intent);
        finish();
    }

}
