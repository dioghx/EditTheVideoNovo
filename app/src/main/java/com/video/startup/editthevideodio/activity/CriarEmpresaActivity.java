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
import com.video.startup.editthevideodio.connection.dto.GenericDTO;
import com.video.startup.editthevideodio.constantes.ConstantesApp;
import com.video.startup.editthevideodio.model.Empresa;
import com.video.startup.editthevideodio.util.Util;
import static com.video.startup.editthevideodio.util.Util.toastShort;

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

        try {
            ConnectionManager.executePOSTAsync(empresa, this, ConstantesApp.APP_CRIAR_EMPRESA, (GenericDTO dto) -> {
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