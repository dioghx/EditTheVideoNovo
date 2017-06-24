package com.video.startup.editthevideodio.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
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
//teste
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
        editTelefoneUsuario.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public void cadastrarUsuario(View v){
        Usuario usuario = new Usuario();
        usuario.setNome(editNomeUsuario.getText().toString());
        usuario.setNascimento(editDataUsuario.getText().toString());
        usuario.setTelefone(editTelefoneUsuario.getText().toString());
        usuario.setEmail(editEmailUsuario.getText().toString());
        usuario.setSenha(editSenhaUsuario.getText().toString());
        usuario.getEndereco().setCidade(editCidadeUsuario.getText().toString());


    }


    public void btnVoltar(View v)
    {
        Intent intent = new Intent(v.getContext(),EscolherCadastroActivity.class);
        startActivity(intent);
        finish();
    }

}
