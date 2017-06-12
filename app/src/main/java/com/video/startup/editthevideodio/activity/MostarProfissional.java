package com.video.startup.editthevideodio.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.video.startup.editthevideodio.R;

/**
 * Created by Diogo on 10/06/2017.
 */

public class MostarProfissional extends AppCompatActivity {

    ImageView imageViewMostrarProfissional;
    TextView  editTextNomeMostrarProfissional;
    TextView  editTextEmailMostrarProfissional;
    TextView  editTextTelefoneMostrarProfissional;
    TextView editTextDescricaoMostrarProfissional;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_profissional);
        Bundle extras = getIntent().getExtras();

        imageViewMostrarProfissional = (ImageView)findViewById(R.id.imageViewMostrarProfissional);
        editTextNomeMostrarProfissional=(TextView)findViewById(R.id.editNomeMostrarProfissional);
        editTextEmailMostrarProfissional=(TextView)findViewById(R.id.editEmailMostrarProfissional);
        editTextTelefoneMostrarProfissional=(TextView)findViewById(R.id.editTelefoneMostrarProfissional);
        editTextDescricaoMostrarProfissional=(TextView)findViewById(R.id.editDescricaoMostrarProfissional);

        imageViewMostrarProfissional.setImageResource(R.drawable.ic_menu_camera);
        editTextNomeMostrarProfissional.setText(extras.getString("nomeProfissional"));
        editTextDescricaoMostrarProfissional.setText(extras.getString("descricaoProfissional"));


    }
}
