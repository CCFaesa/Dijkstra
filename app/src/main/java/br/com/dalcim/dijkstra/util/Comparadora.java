package br.com.dalcim.dijkstra.util;

import java.util.Comparator;

import br.com.dalcim.dijkstra.model.Aresta;
import br.com.dalcim.dijkstra.model.Vertice;

import static br.com.dalcim.dijkstra.global.Constante.A_PE;
import static br.com.dalcim.dijkstra.global.Constante.DE_CARRO;

/**
 * Created by Wiliam on 18/11/2016.
 */

public class Comparadora {
    public static int pegaCusto(Aresta aresta, int tipoMovimentacao) {
        if(tipoMovimentacao == A_PE){
            return aresta.getCustoPe();
        }else if(tipoMovimentacao == DE_CARRO){
            return aresta.getCustoCarro();
        }else{
            return aresta.getCustoOnibus();
        }
    }

    public static Comparator<Vertice> getComparator() {
        return new Comparator<Vertice>() {
            @Override
            public int compare(Vertice vertice, Vertice t1) {
                return vertice.getCustoAte() - t1.getCustoAte();
            }
        };
    }
}
