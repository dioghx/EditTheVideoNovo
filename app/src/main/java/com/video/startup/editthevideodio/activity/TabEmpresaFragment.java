package com.video.startup.editthevideodio.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Empresa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diogo on 10/06/2017.
 */

public class TabEmpresaFragment extends Fragment {
    List<Empresa> empresas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_empresas_fragment,container,false);
        empresas = getData();
        return view;

    }
    public static List<Empresa> getData()
    {
        List<Empresa>empresas = new ArrayList<>();


        String[]nomes ={"João ltda","Pedro cia","Robson Couve Flor"};
        String[]descricao ={"Uma empresa","Uma empresa 2","Uma empresa 3"};
        for(int i =0;  i< nomes.length;i++)
        {
            Empresa current = new Empresa();
            current.setNome_Curto(nomes[i]);
            current.setDescricao(descricao[i]);
            empresas.add(current);
        }
        return empresas;
    }
}
