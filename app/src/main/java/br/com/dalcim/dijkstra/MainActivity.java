package br.com.dalcim.dijkstra;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.dalcim.dijkstra.model.Aresta;
import br.com.dalcim.dijkstra.model.Grafo;
import br.com.dalcim.dijkstra.model.Vertice;
import br.com.dalcim.dijkstra.util.Comparadora;
import br.com.dalcim.dijkstra.util.Geradora;

import static br.com.dalcim.dijkstra.global.Constante.TRANSPORTES;
import static br.com.dalcim.dijkstra.global.Constante.VERTICES;

public class MainActivity extends AppCompatActivity {
    private Spinner spnOrigem;
    private Spinner spnDestino;
    private Spinner spnTransporte;
    private TextView txtResultado;
    private Grafo grafo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grafo = Geradora.geraGrafo();
        spnOrigem = (Spinner) findViewById(R.id.spn_origem);
        spnDestino = (Spinner) findViewById(R.id.spn_destino);
        spnTransporte = (Spinner) findViewById(R.id.spn_transporte);

        txtResultado = (TextView) findViewById(R.id.resultado);

        spnOrigem.setAdapter(new ArrayAdapter<String>(this, R.layout.text_spinner, getResources().getStringArray(R.array.vertices)));
        spnDestino.setAdapter(new ArrayAdapter<String>(this, R.layout.text_spinner, getResources().getStringArray(R.array.vertices)));
        spnTransporte.setAdapter(new ArrayAdapter<String>(this, R.layout.text_spinner, getResources().getStringArray(R.array.transporte)));
    }

    public void buscar(View view) {
        new TarefaBuscaCaminho(grafo, VERTICES[spnOrigem.getSelectedItemPosition()], VERTICES[spnDestino.getSelectedItemPosition()], TRANSPORTES[spnTransporte.getSelectedItemPosition()]).execute();
    }

    class TarefaBuscaCaminho extends AsyncTask<Void, Void, Void>{

        private Grafo grafo;
        private Vertice origem;
        private Vertice destino;
        private int tipoMovimentacao;

        public TarefaBuscaCaminho(Grafo grafo, Vertice origem, Vertice destino, int tipoMovimentacao) {
            this.grafo = grafo;
            this.origem = origem;
            this.destino = destino;
            this.tipoMovimentacao = tipoMovimentacao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
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

            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);
            exibeCaminho(destino);
        }
    }

    private void exibeCaminho(Vertice destino) {
        if(destino.getVerticePai() == null){
            txtResultado.setText("Nao possui caminho");
        }else{
            Vertice verticeEscrever = destino;
            String stb = destino.getNomeVertice();
            while(true){
                verticeEscrever = verticeEscrever.getVerticePai();
                if(verticeEscrever == null) break;
                stb = verticeEscrever.getNomeVertice() + "> " + stb;
            }

            txtResultado.setText(stb);
        }
    }
}
