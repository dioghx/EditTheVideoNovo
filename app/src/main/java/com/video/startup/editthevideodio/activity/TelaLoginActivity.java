package com.video.startup.editthevideodio.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * Created by Kelvyn on 04/06/2017.
 */

public class TelaLoginActivity extends Activity
{

    private EditText editTextEmail;
    private EditText editTextSenha;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Button btnLogar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.tela_login);
        EditText editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        EditText editTextSenha = (EditText)findViewById(R.id.editTextSenha);
        Button btnLogar = (Button)findViewById(R.id.btnLogar);
        Button btnCadastrar = (Button)findViewById(R.id.btnCadastrar);

        loginButton = (LoginButton) findViewById(R.id.logar);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try{
                                    String id = object.getString("id");
                                 String name = object.getString("name");
                                 String email = object.getString("email");
                                 String birthday = object.getString("birthday");
                                    String foto = "https://graph.facebook.com/"+id+"/picture?height=120&width=120";
                                    redirect(name,email,birthday,foto);
                                }
                            catch (JSONException e)
                            {
                               e.printStackTrace();
                            }


                        }});
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday,picture.width(120).height(120)");
                request.setParameters(parameters);
                request.executeAsync();
            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
    private void redirect(String nomeUsuario,String emailUsuario,String aniversarioUsuario,String fotoUsuario){
        Intent intent = new Intent(TelaLoginActivity.this,MainActivity.class);
        intent.putExtra("nomeUsuario",nomeUsuario);
        intent.putExtra("fotoUsuario",fotoUsuario);
        intent.putExtra("emailUsuario",emailUsuario);
        intent.putExtra("aniversarioUsuario",aniversarioUsuario);
        startActivity(intent);
    }


}
