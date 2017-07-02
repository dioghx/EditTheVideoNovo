package com.video.startup.editthevideodio.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Profissional;

import java.util.ArrayList;


/**
 * Created by Lab. Desenvolvimento on 08/05/2017.
 */

public class ProfissionalAdapter extends ArrayAdapter<Profissional> implements Filterable
{
    private int layout;
    private ArrayList<Profissional>profissionais;
    private ArrayList<Profissional>profissionaisFiltrado;
    private ProfissionalFilter profissionalFilter;

    public ProfissionalAdapter(Context context , int layout , ArrayList<Profissional> profissionais)
    {
        super(context,layout,profissionais);
        this.layout= layout;
        this.profissionais= profissionais;
        profissionaisFiltrado = profissionais;
        //Chamando instancia da classe interna Filter
        getFilter();
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

    @Override
    public Filter getFilter() {
        if (profissionalFilter == null) {
            profissionalFilter = new ProfissionalFilter();
        }

        return profissionalFilter;
    }

    @Override
    public int getCount() {
        return profissionaisFiltrado.size();
    }

    @Override
    public Profissional getItem(int i) {
        return profissionaisFiltrado.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ProfissionalFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //Instancia classe filter Results
            FilterResults filterResults = new FilterResults();
            //Comparacao se constraint(Sequência de caracterer retorna nulo ou maior que 0 )
            if (constraint!=null && constraint.length()>0) {

                ArrayList<Profissional> tempList = new ArrayList<Profissional>();

                // search content in friend list
                for (Profissional profissional : profissionais) {
                    if (profissional.getUsuario().getNome().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(profissional);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = profissionais.size();
                filterResults.values = profissionais;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            profissionaisFiltrado = (ArrayList<Profissional>) results.values;
            notifyDataSetChanged();
        }
    }
    }



