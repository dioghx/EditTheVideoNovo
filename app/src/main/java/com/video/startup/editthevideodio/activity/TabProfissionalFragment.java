package com.video.startup.editthevideodio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.adapter.OnClick_RecyclerView;
import com.video.startup.editthevideodio.adapter.RecyclerViewProfissional;
import com.video.startup.editthevideodio.model.Profissional;
import com.video.startup.editthevideodio.model.Usuario;

import java.util.ArrayList;

/**
 * Created by Diogo on 09/06/2017.
 */

public class TabProfissionalFragment extends Fragment implements SearchView.OnQueryTextListener,OnClick_RecyclerView {



    private static final int SPAN_COUNT = 2;

    public static final String TAG = "TabProfissionalFragment";
    private ArrayList<Profissional> profissionalsList;
    private ListView listView;

    protected RecyclerView mRecyclerView;
    protected RecyclerViewProfissional recyclerViewProfissional;
    protected RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_profissionais_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewProfissional);
        mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),SPAN_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);
        profissionalsList =getData();
        recyclerViewProfissional = new RecyclerViewProfissional(getActivity().getApplicationContext(),profissionalsList,this);
        mRecyclerView.setAdapter(recyclerViewProfissional);
        return rootView;

    }

    public static ArrayList<Profissional> getData() {
        ArrayList<Profissional> profissionals = new ArrayList<>();

        int[] avaliacoes = {1, 2, 3, 4, 5, 3};
        String[] nomes = {"Pedro", "Lalala", "Lalala", "Lalala", "Lalala", "Lalala"};
        String[] descricao = {"Um cara muito gente boa", "Uma mulher finisima", "Corintiano", "Corintiano", "Corintiano", "Corintiano"};
        for (int i = 0; i < nomes.length; i++) {
            Profissional current = new Profissional();
            Usuario usuario = new Usuario();
            current.setAvaliacao(avaliacoes[i]);
            usuario.setNome(nomes[i]);
            current.setUsuario(usuario);
            current.setDescricao(descricao[i]);
            profissionals.add(current);
        }
        return profissionals;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {  //procurando layout de procura no menu
        MenuItem item = menu.findItem(R.id.barraProcurar);
        //Setando objeto como searchview,passando como parametros o contexto da Main,como isso é um fragment depende do contexto da main
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        //Metodos estaticos da classe menuItemCompat,setando valores para ação passando como parâmetro o objeto a ser utilizado e enumerados
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
      //Setando valores da classe serch view que foi instanciada e objeto para ter um contexto,utilizando novamente a classe
        //estatica MenuItemCompat
        MenuItemCompat.setActionView(item, sv);
        //utilizando método OnTextListener que foi implementado na classe
        sv.setOnQueryTextListener(this);
        //passando valor de um ico
        sv.setIconifiedByDefault(false);


        //Setando valores por meio de uma nova classe criada no momento
        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Setando valorres de ação para o objeto junto com a criacao da classe MenuItemCompat
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed

                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded

                return true;  // Return true to expand action view
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }


    //Metodo implementado pela Interface OnTextListener  do SearchView
    @Override
    public boolean onQueryTextSubmit(String query) {

        return true;
    }


//Metodo que manda o adapter que esta implementado junto a um filtro
    @Override
    public boolean onQueryTextChange(String newText) {

        recyclerViewProfissional.getFilter().filter(newText);
        return true;
    }


    //Ação da ReciclerView
    @Override
    public void onCustomClick(Object object) {
        Profissional profissional = (Profissional) object;
        Intent intent = new Intent(getContext(), MostarProfissional.class);
        intent.putExtra("avaliacaoProfissional", profissional.getAvaliacao());
        intent.putExtra("nomeProfissional", profissional.getUsuario().getNome());
        // intent.putExtra("cidadeProfissional",profissional.getUsuario().getEndereco().getCidade());
        intent.putExtra("descricaoProfissional", profissional.getDescricao());
        //intent.putExtra("emailProfissional",profissional.getUsuario().getEmail());
        //intent.putExtra("telefoneProfissional",profissional.getUsuario().getTelefone());
        startActivity(intent);

    }

}
