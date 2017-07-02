package com.video.startup.editthevideodio.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.video.startup.editthevideodio.connection.ConnectionManager;
import com.video.startup.editthevideodio.connection.dto.GenericDTO;
import com.video.startup.editthevideodio.constantes.ConstantesApp;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.util.Util;
import static com.video.startup.editthevideodio.util.Util.toastShort;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.video.startup.editthevideodio.util.Util.toastShort;


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
                                    finish();
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


    public void logarUsuario(View v)
    {
        Usuario usuario  = new Usuario();
        usuario.setEmail(editTextEmail.getText().toString());
        usuario.setSenha(editTextSenha.getText().toString());
        try {
            ConnectionManager.executePOSTAsync(usuario, this, ConstantesApp.APP_CRIAR_USUARIO, (GenericDTO dto) -> {
                        Intent telaPrincipal = null;
                        toastShort("Usuário cadastrado no sistema...!", this);
                        JSONObject jsonValidação = null;
                        Usuario user = (Usuario) dto.getObjeto();

                        switch (user.getPermissao()){
                            case 'p':
                                //          telaPrincipal = new Intent(TelaLoginActivity.this, PrincipalActivity.class);
                                startActivity(telaPrincipal);
                                break;
                            case 'u':
                                //        telaPrincipal = new Intent(TelaLoginActivity.this, PrincipalActivity.class);
                                startActivity(telaPrincipal);
                                break;
                            case 'e':
                                //    telaPrincipal = new Intent(TelaLoginActivity.this, PrincipalActivity.class);
                                startActivity(telaPrincipal);
                                break;
                        }

                    }
            );
        } catch (Exception e) {
            Log.e("Error", "Error ", e);
            toastShort("Erro ao logar!",this);
        }
    }


    private void redirect(String nomeUsuario,String emailUsuario,String aniversarioUsuario,String fotoUsuario){
        SharedPreferences.Editor editor = getSharedPreferences("pref", MODE_PRIVATE).edit();
        editor.putString("nomeUsuario",nomeUsuario);
        editor.putString("fotoUsuario", fotoUsuario);
        editor.putString("emailUsuario", emailUsuario);
        editor.putString("aniversarioUsuario", aniversarioUsuario);
        editor.commit();
        Intent intent = new Intent(TelaLoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
