package br.com.brunovitor.projetoFilme.model;

import java.io.Serializable;

/**
 * Created by Bruno Vitor Pires on 07/10/2015.
 */
public class Filme implements Serializable {

    public String titulo;
    public String direcao;
    public String lancamento;
    public float classificacao;
    public String sinopse;
    public String capa;

    public Filme(){

    }

    public Filme(String titulo, String direcao, String lancamento, float classificacao, String sinopse, String capa){
        this.titulo = titulo;
        this.direcao = direcao;
        this.lancamento = lancamento;
        this.classificacao = classificacao;
        this.sinopse = sinopse;
        this.capa = capa;
    }

    public String toString(){
        return titulo;

    }

}
