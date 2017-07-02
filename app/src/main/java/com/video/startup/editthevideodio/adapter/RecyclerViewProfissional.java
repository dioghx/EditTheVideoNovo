package com.video.startup.editthevideodio.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Profissional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Diogo on 09/06/2017.
 */

public class RecyclerViewProfissional extends RecyclerView.Adapter<RecyclerViewProfissional.RecyclerViewProfissionalViewHolder>implements Filterable {
    public static OnClick_RecyclerView onClick_recyclerView;
    //Contexto que será passado por quem esta chamando,lista que vai segurar todos os valores,lista que sera filtrada e atualizada,
    //Instanciando classe interna de filtro
    Context mctx;
    private List<Profissional> profissionalList;
    private ArrayList<Profissional> profissionalListFiltrado;
    private ProfissionalFilter profissionalFilter;

    //Chamando metódo getFilter para criar objeto da classe interna
    public RecyclerViewProfissional(Context ctx, ArrayList<Profissional> list, OnClick_RecyclerView onClick_recyclerView) {
        this.mctx = ctx;
        this.profissionalList = list;
        this.onClick_recyclerView = onClick_recyclerView;
        profissionalListFiltrado =  list;
        getFilter();
    }

    //Passando layout para saber que layout o método deve popular
    @Override
    public RecyclerViewProfissionalViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profissionial, parent, false);
        return new RecyclerViewProfissionalViewHolder(itemView);
    }

    //Método para passar objeto da lista e passar para VIEW
    @Override
    public void onBindViewHolder(RecyclerViewProfissionalViewHolder holder, int i) {
        Profissional profissional = profissionalListFiltrado.get(i);
        holder.imageProfissional.setImageResource(R.drawable.ic_menu_camera);
        holder.ratingBarProfissional.setRating(profissional.getAvaliacao());
        holder.textViewNomeProfissional.setText(profissional.getUsuario().getNome());
        holder.textViewDescricaoProfissional.setText(profissional.getDescricao());
    }



    //Classe para mapear campos da view para o objeto
    protected class RecyclerViewProfissionalViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imageProfissional;
        protected RatingBar ratingBarProfissional;
        protected TextView textViewNomeProfissional;
        protected TextView textViewDescricaoProfissional;

//Implementaçao que pega valores da view de pendendo da posiçao do layout
        public RecyclerViewProfissionalViewHolder(final View itemView) {
            super(itemView);
            imageProfissional = (ImageView) itemView.findViewById(R.id.imageProfissional);
            ratingBarProfissional = (RatingBar) itemView.findViewById(R.id.ratingBarProfissional);
            textViewNomeProfissional = (TextView) itemView.findViewById(R.id.textViewNomeProfissional);
            textViewDescricaoProfissional = (TextView) itemView.findViewById(R.id.textViewDescricaoProfissional);

            //Método para pegar a posição da lista
            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onClick_recyclerView.onCustomClick(profissionalListFiltrado.get(getLayoutPosition()));

                }

            });
        }
    }
    @Override
    public Filter getFilter() {
        if (profissionalFilter == null) {
            profissionalFilter = new ProfissionalFilter();
        }

        return profissionalFilter;
    }

    //Método para contar a quantidade de linhas
    @Override
    public int getItemCount() {
        return profissionalListFiltrado.size();
    }
  //Pegar id do objeto
    @Override
    public long getItemId(int i) {
        return i;
    }


  //Implementando a filtragem
    private class ProfissionalFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //Instancia classe filter Results
            FilterResults filterResults = new FilterResults();
            //Comparacao se constraint(Sequência de caracterer retorna nulo ou maior que 0 )
            if (constraint != null && constraint.length() > 0) {

                ArrayList<Profissional> tempList = new ArrayList<Profissional>();

                // search content in friend list
                for (Profissional profissional : profissionalList) {
                    if (profissional.getUsuario().getNome().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(profissional);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;

            } else {
                filterResults.count = profissionalList.size();
                filterResults.values = profissionalList;
            }
            ;
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            profissionalListFiltrado = (ArrayList<Profissional>) results.values;
            notifyDataSetChanged();

        }

    }
}
