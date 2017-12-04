package com.example.edson.appprojetocampanha;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DoacaoActivity extends Activity {

    private Button doar;
    private TextView doacao;
    private TextView quantidade;
    public ImageView imagem;
    public TextView titulo;
    public TextView conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doacao);

        Bind();
    }

    private void Bind() {
        doacao = findViewById(R.id.textoDpacao);
        quantidade = findViewById(R.id.quantidadeDoacao);
        imagem = findViewById(R.id.imgDestaque2);
        titulo = findViewById(R.id.textoTitulo2);
        conteudo = findViewById(R.id.textoConteudo2);


        doar = findViewById(R.id.btnEfetivarDoacao);
        doar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Doação realizada com sucesso", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        CartaoCampanha card = VariavelsGlobal.Campanha;
        if (card != null) {
            titulo.setText(card.getTitulo());
            conteudo.setText(card.getDescricao());

            try {
                Glide.with(this).load(card.getImagem()).into(imagem);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
