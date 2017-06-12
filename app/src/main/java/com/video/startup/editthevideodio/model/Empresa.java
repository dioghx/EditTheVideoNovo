package com.video.startup.editthevideodio.model;

/**
 * Created by Diogo on 03/06/2017.
 */

public class Empresa {

    private int       id;
    private String    nome_curto;
    private String    razao_social;
    private String    cnpj;
    private Usuario   usuario;
    private String    descricao;


    public int getId()
    {
        return id;
    }

    public void setId(int pId)
    {
        id = pId;
    }


    public String getNome_Curto()
    {
        return nome_curto;
    }


    public void setNome_Curto(String pNome_Curto)
    {
        this.nome_curto = pNome_Curto;
    }

    public String getRazao_Social()
    {
        return razao_social;
    }

    public void setRazao_Social(String pRazao_Social)
    {
        this.razao_social = pRazao_Social;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String pCnpj)
    {
        cnpj = pCnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
