package com.example.edson.appprojetocampanha;

/**
 * Created by Edson on 04/12/2017.
 */

public class CartaoCampanha {
    private String imagem;
    private String titulo;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAfinidade() {
        return afinidade;
    }

    public void setAfinidade(Integer afinidade) {
        this.afinidade = afinidade;
    }

    private String descricao;
    private Integer afinidade;


}