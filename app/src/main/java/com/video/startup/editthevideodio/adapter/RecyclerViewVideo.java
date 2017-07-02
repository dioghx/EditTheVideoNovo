package com.video.startup.editthevideodio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.video.startup.editthevideodio.R;
import com.video.startup.editthevideodio.model.Genero;
import com.video.startup.editthevideodio.model.Video;
import com.video.startup.editthevideodio.util.*;

import java.util.List;

import static com.video.startup.editthevideodio.adapter.RecyclerViewProfissional.onClick_recyclerView;

/**
 * Created by Diogo on 26/06/2017.
 */

public class RecyclerViewVideo extends RecyclerView.Adapter<RecyclerViewVideo.RecyclerViewVideoViewHolder> {

    private static OnClick_RecyclerView onClick_recyclerView;
    Context ctx;
    private List<Video> videoList;

    public RecyclerViewVideo(Context ctx ,List<Video>videoList)
    {
        this.ctx = ctx;
        this.videoList = videoList;
    }

    @Override
    public RecyclerViewVideoViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_video,parent,false);
        return new RecyclerViewVideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewVideoViewHolder holder, int position) {
      Video video = videoList.get(position);
       holder.textViewVideoTitulo.setText(video.getTitulo());
       holder.textViewVideoGenero.setText(formatarGenerosEmString(video.getGeneros()));

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private  String  formatarGenerosEmString (List<Genero>listaGenero)
    {
        StringBuilder generos = new StringBuilder();
        for(int i = 0; i <= listaGenero.size();i++)
        {
            Genero genero = listaGenero.get(i);
            generos.append(genero.getNome());
            if(i == listaGenero.size())
            {
                break;
            }
            else {
                generos.append(",");
            }

        }
        return generos.toString();
    }


    protected  class RecyclerViewVideoViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView imageViewVideo;
        protected TextView textViewVideoTitulo;
        protected TextView textViewVideoGenero;

        public RecyclerViewVideoViewHolder(View itemView) {
            super(itemView);
            imageViewVideo = (ImageView)itemView.findViewById(R.id.imageViewVideo);
            textViewVideoTitulo=(TextView)itemView.findViewById(R.id.textViewVideoTitulo);
            textViewVideoGenero=(TextView)itemView.findViewById(R.id.textViewVideoGeneros);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onClick_recyclerView.onCustomClick(videoList.get(getLayoutPosition()));

                }

            });
        }
    }
}
