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
import com.video.startup.editthevideodio.model.Profissional;
import com.video.startup.editthevideodio.util.Util;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;


/**
 * Created by Kelvyn on 04/06/2017.
 */

public class CriarFreeActivity extends  Activity{

    private EditText editNomeFree;
    private EditText editDataFree;
    private EditText editTelefoneFree;
    private EditText editEmailFree;
    private EditText editSenhaFree;
    private EditText editCidadeFree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_free);
        EditText editNomeFree = (EditText)findViewById(R.id.editNomeFree);
        EditText editDataFree = (EditText)findViewById(R.id.editDataFree);
        EditText editTelefoneFree = (EditText)findViewById(R.id.editTelefoneFree);
        EditText editEmailFree = (EditText)findViewById(R.id.editEmailFree);
        EditText editSenhaFree = (EditText)findViewById(R.id.editSenhaFree);
        EditText editCidadeFree = (EditText)findViewById(R.id.editCidadeFree);

    }

    public void cadastrarFree(View v){
        Profissional profissional = new Profissional();
        profissional.getUsuario().setNome(editNomeFree.getText().toString());
        profissional.getUsuario().setNascimento(editDataFree.getText().toString());
        profissional.getUsuario().setTelefone(editTelefoneFree.getText().toString());
        profissional.getUsuario().setEmail(editEmailFree.getText().toString());
        profissional.getUsuario().setSenha(editSenhaFree.getText().toString());
        profissional.getUsuario().getEndereco().setCidade(editCidadeFree.getText().toString());
        if(isConnected()) {
            new cadastrarProfissional().execute(profissional);
            Intent paginaPrincipal = new Intent(CriarFreeActivity.this,MainActivity.class);
            startActivity(paginaPrincipal);
            finish();
        }
        else
            Toast.makeText(this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
    }

    private class cadastrarProfissional extends AsyncTask<Profissional, Void, String> {

        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected String doInBackground(Profissional... params) {
            HttpURLConnection urlConnection = null;
            try {
                //Utilizar url do webservice de logar
                //URL url = new URL("");
                //urlConnection = (HttpURLConnection) url.openConnection();
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
                Toast.makeText(CriarFreeActivity.this, "Profissional cadastrada no sistema...!", Toast.LENGTH_SHORT).show();
                telaPrincipal = new Intent(CriarFreeActivity.this, MainActivity.class);
                startActivity(telaPrincipal);
            }else{
                Toast.makeText(CriarFreeActivity.this, "Falha ao cadastrar o profissional.", Toast.LENGTH_SHORT).show();
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
