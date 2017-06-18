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
import com.video.startup.editthevideodio.model.Empresa;
import com.video.startup.editthevideodio.util.Util;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;


/**
 * Created by Kelvyn on 04/06/2017.
 */

public class CriarEmpresaActivity extends Activity {

    private EditText editNomeEmpresa;
    private EditText editSocialEmpresa;
    private EditText editTelefoneEmpresa;
    private EditText editEmailEmpresa;
    private EditText editSenhaEmpresa;
    private EditText editCidadeEmpresa;
    private EditText editCnpjEmpresa;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_empresa);
        EditText editNomeEmpresa = (EditText) findViewById(R.id.editNomeEmpresa);
        EditText editDataEmpresa = (EditText) findViewById(R.id.editSocialEmpresa);
        EditText editTelefoneEmpresa = (EditText) findViewById(R.id.editTelefoneEmpresa);
        EditText editEmailEmpresa = (EditText) findViewById(R.id.editEmailEmpresa);
        EditText editSenhaEmpresa = (EditText) findViewById(R.id.editTextSenhaEmpresa);
        EditText editCidadeEmpresa = (EditText) findViewById(R.id.editTextCidadeEmpresa);
        EditText editCnpjEmpresa = (EditText) findViewById(R.id.editCnpjEmpresa);

    }

    public void cadastrarEmpresa(View v){
        Empresa empresa = new Empresa();
        empresa.setNome_Curto(editNomeEmpresa.getText().toString());
        empresa.setRazao_Social(editSocialEmpresa.getText().toString());
        empresa.getUsuario().setTelefone(editTelefoneEmpresa.getText().toString());
        empresa.getUsuario().setEmail(editEmailEmpresa.getText().toString());
        empresa.getUsuario().setSenha(editSenhaEmpresa.getText().toString());
        empresa.getUsuario().getEndereco().setCidade(editCidadeEmpresa.getText().toString());
        empresa.setCnpj(editCnpjEmpresa.getText().toString());

        if(isConnected()) {
            new cadastrarEmpresa().execute(empresa);
            Intent paginaPrincipal = new Intent(CriarEmpresaActivity.this, MainActivity.class);
            startActivity(paginaPrincipal);
            finish();
        }
        else
            Toast.makeText(this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
    }
    private class cadastrarEmpresa extends AsyncTask<Empresa, Void, String> {

        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected String doInBackground(Empresa... params) {
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
                Toast.makeText(CriarEmpresaActivity.this, "Empresa cadastrada no sistema...!", Toast.LENGTH_SHORT).show();
                telaPrincipal = new Intent(CriarEmpresaActivity.this, MainActivity.class);
                startActivity(telaPrincipal);
            }else{
                Toast.makeText(CriarEmpresaActivity.this, "Falha ao cadastrar o clube.", Toast.LENGTH_SHORT).show();
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