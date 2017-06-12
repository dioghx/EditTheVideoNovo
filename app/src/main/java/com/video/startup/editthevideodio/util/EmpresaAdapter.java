package com.video.startup.editthevideodio.util;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Empresa;
import com.video.startup.editthevideodio.model.Profissional;

import java.util.List;

/**
 * Created by Diogo on 11/06/2017.
 */

public class EmpresaAdapter extends ArrayAdapter<Empresa> {
    private int layout;

    public EmpresaAdapter(Context context , int layout , List<Empresa> empresas)
    {
        super(context,layout,empresas);
        this.layout= layout;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if(view == null)
        {
            LayoutInflater inflater =(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.layout,parent,false);
        }
        ImageView imageEmpresa = (ImageView)view.findViewById(R.id.imageEmpresa);
        TextView nomeEmpresa = (TextView)view.findViewById(R.id.textViewNomeEmpresa);
        TextView descricaoEmpresa = (TextView)view.findViewById(R.id.textViewDescricaoEmpresa);



        Empresa empresa =getItem(position);
        nomeEmpresa.setText(empresa.getNome_Curto());
        descricaoEmpresa.setText(empresa.getDescricao());
        imageEmpresa.setImageResource(R.drawable.ic_menu_camera);
        return view;
    }



}
