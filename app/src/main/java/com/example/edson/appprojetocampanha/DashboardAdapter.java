package com.example.edson.appprojetocampanha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Edson on 04/12/2017.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    private ArrayList<CartaoCampanha> mDataset;
    private Context ctx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imagem;
        public TextView titulo;
        public TextView conteudo;
        public Button botao;

        public ViewHolder(View v) {
            super(v);

            imagem = v.findViewById(R.id.imgDestaque);
            titulo = v.findViewById(R.id.textoTitulo);
            conteudo = v.findViewById(R.id.textoConteudo);
            botao = v.findViewById(R.id.btnDoar);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DashboardAdapter(Context ctx, ArrayList<CartaoCampanha> myDataset) {
        mDataset = myDataset;
        this.ctx = ctx;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_layout, parent, false);

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CartaoCampanha card = mDataset.get(position);

        holder.titulo.setText(card.getTitulo());
        holder.conteudo.setText(card.getDescricao());

        holder.botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariavelsGlobal.Campanha = card;

                Intent doar = new Intent(ctx, DoacaoActivity.class);
                doar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(doar);
            }
        });

        try {
            Glide.with(ctx).load(card.getImagem()).into(holder.imagem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}