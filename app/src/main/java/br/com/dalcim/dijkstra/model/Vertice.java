package br.com.dalcim.dijkstra.model;

import java.util.ArrayList;

/**
 * Created by Wiliam on 18/11/2016.
 */

public class Vertice {
    private String nomeVertice;
    private ArrayList<Aresta> arestas;
    private int marca;
    private int custoAte;
    private Vertice verticePai;

    public Vertice(String nomeVertice) {
        this.nomeVertice = nomeVertice;
        arestas = new ArrayList<>();
    }

    public String getNomeVertice() {
        return nomeVertice;
    }

    public void setNomeVertice(String nomeVertice) {
        this.nomeVertice = nomeVertice;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getCustoAte() {
        return custoAte;
    }

    public void setCustoAte(int custoAte) {
        this.custoAte = custoAte;
    }

    public Vertice getVerticePai() {
        return verticePai;
    }

    public void setVerticePai(Vertice verticePai) {
        this.verticePai = verticePai;
    }

    public void addAresta(Vertice vertice, int custoPe, int custoCarro, int custoOnibus){
        arestas.add(new Aresta(vertice, custoPe, custoCarro, custoOnibus));
    }

    public void addAresta(Vertice vertice, int custoIndiferente){
        arestas.add(new Aresta(vertice, custoIndiferente, custoIndiferente, custoIndiferente));
    }
}
