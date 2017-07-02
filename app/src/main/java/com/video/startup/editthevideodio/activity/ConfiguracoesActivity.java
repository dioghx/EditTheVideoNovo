package com.video.startup.editthevideodio.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Video;

public class ConfiguracoesActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textViewNome;
    TextView textViewTelefone;
    TextView textViewEstado;
    TextView textViewCidade;
    Button  adicionarVideo;

    Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        toolbar = (Toolbar)findViewById(R.id.toolbar_configuracoes);
        setSupportActionBar(toolbar);

        textViewNome =(TextView)findViewById(R.id.perfil_edit_mostrar_nome);
        textViewTelefone =(TextView)findViewById(R.id.perfil_edit_mostrar_telefone);
        textViewEstado =(TextView)findViewById(R.id.perfil_edit_mostrar_estado);
        textViewCidade =(TextView)findViewById(R.id.perfil_edit_mostrar_cidade);
        adicionarVideo = (Button)findViewById(R.id.buttonAdicionarVideo);

        adicionarVideo.setOnClickListener((View v)->
        {
            AdicionarVideoFragment fragment = new AdicionarVideoFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.container, fragment);
            transaction.commit();
        });
        //textViewNome.setText();
        //textViewTelefone.setText();
        //textViewEstado.setText();
        //textViewCidade.setText()
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_configuracoes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.configuraçoes_modificar_perfil)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
