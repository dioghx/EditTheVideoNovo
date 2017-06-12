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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.util.Util;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;



/**
 * Created by Kelvyn on 04/06/2017.
 */

public class TelaLoginActivity extends Activity
{

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button btnLogar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        EditText editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        EditText editTextSenha = (EditText)findViewById(R.id.editTextSenha);
        Button btnLogar = (Button)findViewById(R.id.btnLogar);
        Button btnCadastrar = (Button)findViewById(R.id.btnCadastrar);

    }

    public void logarUsuario(View v)
    {
        Usuario usuario  = new Usuario();
        usuario.setEmail(editTextEmail.getText().toString());
        usuario.setSenha(editTextSenha.getText().toString());
        if(isConnected()) {
            new logarUsuario().execute(usuario);
        }
    }

    private class logarUsuario extends AsyncTask<Usuario, Void, String> {

        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected String doInBackground(Usuario... params) {
            HttpURLConnection urlConnection = null;
            try {
                //Utilizar url do webservice de logar
                //URL url = new URL("");
               // urlConnection = (HttpURLConnection) url.openConnection();
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
                Toast.makeText(TelaLoginActivity.this, "Usuário cadastrado no sistema...!", Toast.LENGTH_SHORT).show();
                JSONObject jsonValidação = null;
                Usuario usuario =null;
                usuario = Util.permissaoLogar(s);
                if(usuario.getPermissao()=='p')
                {
          //          telaPrincipal = new Intent(TelaLoginActivity.this, PrincipalActivity.class);
                    startActivity(telaPrincipal);
                }
                else if(usuario.getPermissao() =='u')
                {
            //        telaPrincipal = new Intent(TelaLoginActivity.this, PrincipalActivity.class);
                    startActivity(telaPrincipal);
                }
                else if (usuario.getPermissao()=='e')
                {
                //    telaPrincipal = new Intent(TelaLoginActivity.this, PrincipalActivity.class);
                    startActivity(telaPrincipal);
                }
            }else{
                Toast.makeText(TelaLoginActivity.this, "Falha ao cadastrar o clube.", Toast.LENGTH_SHORT).show();
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


}
