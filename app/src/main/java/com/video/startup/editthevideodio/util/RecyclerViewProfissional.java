package com.video.startup.editthevideodio.util;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Profissional;

import java.util.List;



/**
 * Created by Diogo on 09/06/2017.
 */

public class RecyclerViewProfissional extends RecyclerView.Adapter<RecyclerViewProfissional.RecyclerViewProfissionalViewHolder>
{
   public static OnClick_RecyclerView onClick_recyclerView;
   Context mctx;
   private List<Profissional> profissionalList;

    //Construtor para setar os valores
    public RecyclerViewProfissional(Context ctx, List<Profissional> list, OnClick_RecyclerView onClick_recyclerView)
    {
        this.mctx=ctx;
        this.profissionalList = list;
        this.onClick_recyclerView = onClick_recyclerView;
    }
//Passando layout para saber que layout o método deve popular
    @Override
    public RecyclerViewProfissionalViewHolder onCreateViewHolder(ViewGroup parent, int i) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profissionial,parent,false);
       return new RecyclerViewProfissionalViewHolder(itemView);
    }
//Método para passar objeto da lista e passar para VIEW
    @Override
    public void onBindViewHolder(RecyclerViewProfissionalViewHolder holder, int i) {
        Profissional profissional = profissionalList.get(i);
        holder.imageProfissional.setImageResource(R.drawable.ic_menu_camera);
        holder.ratingBarProfissional.setRating(profissional.getAvaliacao());
        holder.textViewNomeProfissional.setText(profissional.getUsuario().getNome());
        holder.textViewDescricaoProfissional.setText(profissional.getDescricao());
    }

    //Método para contar a quantidade de linhas
    @Override
    public int getItemCount() {
        return profissionalList.size();
    }

    //Classe para mapear campos da view para o objeto
    protected  class RecyclerViewProfissionalViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView  imageProfissional;
        protected RatingBar  ratingBarProfissional;
        protected TextView   textViewNomeProfissional;
        protected TextView   textViewDescricaoProfissional;
        public RecyclerViewProfissionalViewHolder(final View itemView)
        {
            super(itemView);
            imageProfissional = (ImageView)itemView.findViewById(R.id.imageProfissional);
            ratingBarProfissional = (RatingBar) itemView.findViewById(R.id.ratingBarProfissional);
            textViewNomeProfissional = (TextView) itemView.findViewById(R.id.textViewNomeProfissional);
            textViewDescricaoProfissional = (TextView) itemView.findViewById(R.id.textViewDescricaoProfissional);

            //Método para pegar a posição da lista
            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onClick_recyclerView.onCustomClick(profissionalList.get(getLayoutPosition()));

                }
            });
        }
    }
}
