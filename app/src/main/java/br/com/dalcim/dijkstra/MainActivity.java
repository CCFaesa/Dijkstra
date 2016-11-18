package br.com.dalcim.dijkstra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.dalcim.dijkstra.model.Aresta;
import br.com.dalcim.dijkstra.model.Grafo;
import br.com.dalcim.dijkstra.model.Vertice;
import br.com.dalcim.dijkstra.util.Comparadora;
import br.com.dalcim.dijkstra.util.Geradora;

import static br.com.dalcim.dijkstra.global.Constante.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscaCaminho(Geradora.geraGrafo(), verticeC, verticeI, A_PE);
    }

    private void buscaCaminho(Grafo grafo, Vertice origem, Vertice destino, int tipoMovimentacao){
        ArrayList<Vertice> lista = new ArrayList<>();
        Vertice verticeVisitado;
        Vertice vizinho;
        int custoDaAresta;

        Comparator<Vertice> comparator = Comparadora.getComparator();

        for (Vertice vertice : grafo.getVertices()) {
            vertice.setCustoAte(Integer.MAX_VALUE);
            vertice.setMarca(0);
            vertice.setVerticePai(null);
        }

        verticeVisitado = origem;
        origem.setMarca(1);
        origem.setCustoAte(0);

        lista.add(origem);

        while(!lista.isEmpty()){
            lista.remove(verticeVisitado);
            verticeVisitado.setMarca(2);

            for (Aresta aresta : verticeVisitado.getArestas()){
                vizinho = aresta.getVertice();
                custoDaAresta = Comparadora.pegaCusto(aresta, tipoMovimentacao);
                if(custoDaAresta == Integer.MAX_VALUE || vizinho.getMarca() == 2){
                    Log.i("LOG", "desconsiderar aresta de :" + verticeVisitado.getNomeVertice() + ":" + vizinho.getNomeVertice() );
                }else if(vizinho.getMarca() == 1){
                    if(vizinho.getCustoAte() > verticeVisitado.getCustoAte() + custoDaAresta){
                        vizinho.setVerticePai(verticeVisitado);
                        vizinho.setCustoAte(verticeVisitado.getCustoAte() + custoDaAresta);
                    }
                }else{
                    lista.add(vizinho);
                    vizinho.setVerticePai(verticeVisitado);
                    vizinho.setCustoAte(verticeVisitado.getCustoAte() + custoDaAresta);
                    vizinho.setMarca(1);
                }
            }

            Collections.sort(lista, comparator);

            if(lista.isEmpty()){
                break;
            }else{
                verticeVisitado = lista.get(0);
            }

            if(verticeVisitado == destino){
                break;
            }
        }
    }
}
