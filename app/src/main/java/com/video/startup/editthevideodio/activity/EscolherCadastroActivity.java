package com.video.startup.editthevideodio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.video.startup.editthevideodio.R;

/**
 * Created by Diogo on 11/06/2017.
 */

public class EscolherCadastroActivity extends AppCompatActivity {

    ImageButton cadastrarUsuario;
    ImageButton cadastrarFreelancer;
    ImageButton cadastrarEmpresa;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escolher_cadastro);
        cadastrarUsuario = (ImageButton)findViewById(R.id.imageButtonCadastrarUsuario);
        cadastrarFreelancer = (ImageButton)findViewById(R.id.imageButtonCadastrarFreelancer);
        cadastrarEmpresa = (ImageButton)findViewById(R.id.imageButtonCadastrarEmpresa);

        cadastrarUsuario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CriarUsuarioActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cadastrarFreelancer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CriarFreeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cadastrarEmpresa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CriarEmpresaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
