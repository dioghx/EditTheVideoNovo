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
import com.video.startup.editthevideodio.connection.ConnectionManager;
import com.video.startup.editthevideodio.connection.dto.GenericDTO;
import com.video.startup.editthevideodio.constantes.ConstantesApp;
import com.video.startup.editthevideodio.model.Endereco;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.util.Util;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;

import static com.video.startup.editthevideodio.util.Util.toastShort;


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
    Usuario usuario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_usuario);
        editNomeUsuario = (EditText) findViewById(R.id.editNomeUsuario);
        editDataUsuario = (EditText) findViewById(R.id.editDataUsuario);
        editTelefoneUsuario = (EditText) findViewById(R.id.editTelefoneUsuario);
        editEmailUsuario = (EditText) findViewById(R.id.editEmailUsuario);
        editSenhaUsuario = (EditText) findViewById(R.id.editSenhaUsuario);
        editCidadeUsuario = (EditText) findViewById(R.id.editCidadeUsuario);
        editTelefoneUsuario.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public void cadastrarUsuario(View v){

        usuario = new Usuario();
        String s = editEmailUsuario.getText().toString();
        usuario.setNome(editNomeUsuario.getText().toString());
        usuario.setNascimento(editDataUsuario.getText().toString());
        usuario.setTelefone(editTelefoneUsuario.getText().toString());
        usuario.setEmail(editEmailUsuario.getText().toString());
        usuario.setSenha(editSenhaUsuario.getText().toString());
        Endereco endereco  = new Endereco();
        endereco.setCidade(editCidadeUsuario.getText().toString());
        usuario.setEndereco(endereco);

        try {
            ConnectionManager.executePOSTAsync(usuario, this, ConstantesApp.APP_CRIAR_USUARIO, (GenericDTO dto) -> {
                        toastShort("Usuário cadastrado!",this);
                        startActivity(new Intent(this, TelaLoginActivity.class));
                    }
            );
        } catch (Exception e) {
            Log.e("Error", "Error ", e);
            toastShort("Erro ao cadastrar!",this);
        }

    }


    public void btnVoltar(View v)
    {
        Intent intent = new Intent(v.getContext(),EscolherCadastroActivity.class);
        startActivity(intent);
        finish();
    }

}
