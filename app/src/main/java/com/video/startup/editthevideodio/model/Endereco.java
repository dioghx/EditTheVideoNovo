package com.video.startup.editthevideodio.model;

/**
 * Created by Diogo on 03/06/2017.
 */

public class Endereco {

        private int id;
        private String estado;
        private String cidade;
        private String bairro;
        private String rua;
        private int numero;


        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getEstado() {
            return estado;
        }
        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getCidade() {
            return cidade;
        }
        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getBairro() {
            return bairro;
        }
        public void setBairro(String bairro) {
            this.bairro = bairro;
        }


        public String getRua() {
            return rua;
        }
        public void setRua(String rua) {
            this.rua = rua;
        }

        public int getNumero() {
            return numero;
        }
        public void setNumero(int numero) {
            this.numero = numero;
        }

    public Endereco(int id, String estado, String cidade, String bairro, String rua, int numero) {
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public Endereco() {
    }
}
