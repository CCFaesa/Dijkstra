package br.com.dalcim.dijkstra.model;

/**
 * Created by Wiliam on 18/11/2016.
 */

public class Aresta {
    private Vertice vertice;
    private int custoPe;
    private int custoCarro;
    private int custoOnibus;

    public Aresta(Vertice vertice, int custoPe, int custoCarro, int custoOnibus) {
        this.vertice = vertice;
        this.custoPe = custoPe;
        this.custoCarro = custoCarro;
        this.custoOnibus = custoOnibus;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public int getCustoPe() {
        return custoPe;
    }

    public int getCustoCarro() {
        return custoCarro;
    }

    public int getCustoOnibus() {
        return custoOnibus;
    }
}
