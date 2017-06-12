package com.video.startup.editthevideodio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Profissional;
import com.video.startup.editthevideodio.model.Usuario;
import com.video.startup.editthevideodio.util.ProfissionalAdapter;
import com.video.startup.editthevideodio.util.RecyclerViewProfissional;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by Diogo on 09/06/2017.
 */

public class TabProfissionalFragment extends Fragment {

    public static final String TAG = "TabProfissionalFragment";
    private List<Profissional> profissionalsList = new ArrayList<>();
    private ProfissionalAdapter profissionalAdapter;
    private ListView listView;



   // @Nullable
    //@Override
    //public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //    View view =inflater.inflate(R.layout.tab_profissionais_fragment,container,false);
    //  profissionalsList = getData();
   //  profissionalAdapter = new ProfissionalAdapter(getActivity(),R.layout.layout_profissionial,profissionalsList);
    //   ListView listView =  (ListView)(getActivity()).findViewById (R.id.listview_profissionais );
    //  listView.setAdapter (profissionalAdapter);
    //  return view;

    //}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_profissionais_fragment,container,false);
        profissionalsList = getData();
        profissionalAdapter = new ProfissionalAdapter(getContext(),R.layout.layout_profissionial,profissionalsList);
        listView =  (ListView)view.findViewById (R.id.listview_profissionais );
        listView.setAdapter (profissionalAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Profissional profissional = (Profissional) adapter.getItemAtPosition(position);
                Intent intent = new Intent(getContext(),MostarProfissional.class);
                intent.putExtra("avaliacaoProfissional",profissional.getAvaliacao());
                intent.putExtra("nomeProfissional",profissional.getUsuario().getNome());
               // intent.putExtra("cidadeProfissional",profissional.getUsuario().getEndereco().getCidade());
                intent.putExtra("descricaoProfissional",profissional.getDescricao());
                //intent.putExtra("emailProfissional",profissional.getUsuario().getEmail());
                //intent.putExtra("telefoneProfissional",profissional.getUsuario().getTelefone());
                startActivity(intent);

            }
        });
        return view;

    }

    public static List<Profissional> getData()
    {
        List<Profissional>profissionals = new ArrayList<>();

        int[] avaliacoes ={1,2,3};
        String[]nomes ={"Pedro","Joana","Robson"};
        String[]descricao ={"Um cara muito gente boa","Uma mulher finisima","Corintiano"};
        for(int i =0;  i< nomes.length;i++)
        {
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




}
