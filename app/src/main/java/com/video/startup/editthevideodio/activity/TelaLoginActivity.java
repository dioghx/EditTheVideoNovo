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
import com.video.startup.editthevideodio.connection.ConnectionManager;
import com.video.startup.editthevideodio.constantes.ConstantesApp;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.util.Util;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;

import static com.video.startup.editthevideodio.util.Util.toastShort;


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

        try {
            ConnectionManager.executePOSTAsync(usuario, this, ConstantesApp.APP_CRIAR_USUARIO, () -> {
                        toastShort("Usuario cadastrado!",this);
                        startActivity(new Intent(this, MainActivity.class));
                    }
            );
        } catch (Exception e) {
            Log.e("Error", "Error ", e);
            toastShort("Erro ao cadastrar!",this);
        }
    }



}
