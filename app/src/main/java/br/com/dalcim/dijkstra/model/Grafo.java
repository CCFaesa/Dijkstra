package br.com.dalcim.dijkstra.model;

import java.util.ArrayList;

/**
 * Created by Wiliam on 18/11/2016.
 */

public class Grafo {
    private ArrayList<Vertice> vertices;

    public Grafo() {
        vertices = new ArrayList<>();
    }

    public void add(Vertice vertice){
        vertices.add(vertice);
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }
}
