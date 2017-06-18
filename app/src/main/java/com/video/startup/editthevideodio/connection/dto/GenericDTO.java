package com.video.startup.editthevideodio.connection.dto;

import java.util.List;

/**
 * Created by Diogo on 18/06/2017.
 */

public class GenericDTO {

    private boolean      ok;
    private String       mensagem;
    private Object       objeto;
    private List<Object> lista;
    private int id;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public List<Object> getLista() {
        return lista;
    }

    public void setLista(List<Object> lista) {
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
