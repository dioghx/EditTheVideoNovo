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
import com.video.startup.editthevideodio.connection.ConnectionManager;
import com.video.startup.editthevideodio.constantes.ConstantesApp;
import com.video.startup.editthevideodio.model.Profissional;
import com.video.startup.editthevideodio.util.Util;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;

import static com.video.startup.editthevideodio.util.Util.toastShort;


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

        try {
            ConnectionManager.executePOSTAsync(profissional, this, ConstantesApp.APP_CRIAR_FREE, () -> {
                        toastShort("Empresa cadastrada!",this);
                        startActivity(new Intent(this, MainActivity.class));
                    }
            );
        } catch (Exception e) {
            Log.e("Error", "Error ", e);
            toastShort("Erro ao cadastrar!",this);
        }

    }
}
