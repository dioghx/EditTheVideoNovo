package com.video.startup.editthevideodio.model;

/**
 * Created by Diogo on 03/06/2017.
 */

public class Profissional {

    private int       id;
    private String    cpf;
    private String    nivel;
    private String 	  descricao;
    private int       avaliacao;
    private Usuario   usuario;


    public int getId()
    {
        return id;
    }
    public void setId(int pId)
    {
        id = pId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }


}
