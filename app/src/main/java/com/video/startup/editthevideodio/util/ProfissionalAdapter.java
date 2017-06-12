package com.video.startup.editthevideodio.util;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Profissional;

import java.util.List;


/**
 * Created by Lab. Desenvolvimento on 08/05/2017.
 */

public class ProfissionalAdapter extends ArrayAdapter<Profissional>
{
    private int layout;

    public ProfissionalAdapter(Context context , int layout , List<Profissional> profissionais)
    {
        super(context,layout,profissionais);
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
        TextView nomeProfissional = (TextView)view.findViewById(R.id.textViewNomeProfissional);
        TextView descricaoProfissional = (TextView)view.findViewById(R.id.textViewDescricaoProfissional);
        ImageView imageProfissional = (ImageView)view.findViewById(R.id.imageProfissional);
        RatingBar avaliacaoProssional = (RatingBar)view.findViewById(R.id.ratingBarProfissional);

        Profissional profissional = getItem(position);

        nomeProfissional.setText(profissional.getUsuario().getNome());
        descricaoProfissional.setText(profissional.getDescricao());
        avaliacaoProssional.setRating(profissional.getAvaliacao());
        imageProfissional.setImageResource(R.drawable.ic_menu_camera);
        return view;
    }

}
