package br.com.dalcim.dijkstra;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private View mapa;
    private Grafo grafo;
    private int tipoMapa = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CRIA GRAFO COM TODOS VERTICES E ARESTAS
        grafo = Geradora.geraGrafo();

        // RECUPERA OS COMPONENTES DA TELA
        spnOrigem = (Spinner) findViewById(R.id.spn_origem);
        spnDestino = (Spinner) findViewById(R.id.spn_destino);
        spnTransporte = (Spinner) findViewById(R.id.spn_transporte);
        mapa = findViewById(R.id.mapa);
        txtResultado = (TextView) findViewById(R.id.resultado);

        // CARREGA DADOS NOS COMBOBOX
        spnOrigem.setAdapter(new ArrayAdapter(this, R.layout.text_spinner, getResources().getStringArray(R.array.vertices)));
        spnDestino.setAdapter(new ArrayAdapter(this, R.layout.text_spinner, getResources().getStringArray(R.array.vertices)));
        spnTransporte.setAdapter(new ArrayAdapter(this, R.layout.text_spinner, getResources().getStringArray(R.array.transporte)));
    }

    // QUANDO CLICAR NO BOTAO BUSCAR, VAI CHAMAR ESSE METODO
    public void buscar(View view) {
        /*
         * TAREFA EXECUTADA EM UMA THREAD SEPARADA
         * RECEBE O GRAFO, O VERTICE DE ORIGEM, O VERTICE DE DESTINO E O TIPO DE TRANSPORTE
         */
        new TarefaBuscaCaminho(grafo, VERTICES[spnOrigem.getSelectedItemPosition()], VERTICES[spnDestino.getSelectedItemPosition()], TRANSPORTES[spnTransporte.getSelectedItemPosition()]).execute();
    }

    // TAREFA QUE SERA EXECUTADA EM UMA THREAD SEPARADA
    class TarefaBuscaCaminho extends AsyncTask<Void, Void, Void>{

        private Grafo grafo;
        private Vertice origem;
        private Vertice destino;
        private int tipoMovimentacao;

        // RECEBENDO OS ATRIBUTOS PARA TRABALHAR
        public TarefaBuscaCaminho(Grafo grafo, Vertice origem, Vertice destino, int tipoMovimentacao) {
            this.grafo = grafo;
            this.origem = origem;
            this.destino = destino;
            this.tipoMovimentacao = tipoMovimentacao;
        }

        // ESTE EH O METODO MAIS IMPORTANTE, EXECUTADO EM UMA THREAD SEPARADA, ELE FAZ O ALGORITMO DE DIJKSTRA
        @Override
        protected Void doInBackground(Void... voids) {
            // ESTE COMPARADOR EH RESPONSAVEL POR ORDENAR A LISTA DE VERTICES PELO CUSTO
            Comparator<Vertice> comparator = Comparadora.getComparator();
            int custoDaAresta;

            ArrayList<Vertice> lista = new ArrayList<>();
            Vertice verticeVisitado;
            Vertice vizinho;

            // INICIANDO VALORES PARA EXECUTAR O ALGORITMO DE DIJKSTRA
            for (Vertice vertice : grafo.getVertices()) {
                vertice.setCustoAte(Integer.MAX_VALUE);
                vertice.setMarca(0);
                vertice.setVerticePai(null);
            }

            // VISTA A ORIGEM, ADICIONA MARCA 1 NELA, ADICIONA NA LISTA E O CUSTO EH ZERO
            verticeVisitado = origem;
            origem.setMarca(1);
            origem.setCustoAte(0);

            lista.add(origem);

            // ENQUANTO TIVER ITEM NA LISTA CONTINUA BUSCANDO O MELHOR CAMINHO
            while(!lista.isEmpty()){
                lista.remove(verticeVisitado);
                verticeVisitado.setMarca(2);

                // PERCORRE TODOS OS VIZINHOS DO VERTICE VISITADO
                for (Aresta aresta : verticeVisitado.getArestas()){
                    vizinho = aresta.getVertice();
                    // PEGA O CUSTO DA ARESTA DE ACORDO COM O MEIO DE TRANSPORTE
                    custoDaAresta = Comparadora.pegaCusto(aresta, tipoMovimentacao);

                    /*
                     * SE O CUSTO FOR MAXIMO(SEM CAMINHO) OU
                     * SE ESTIVER COM MARCA 2(JA FOI ENCONTRADO MELHOR CAMINHO)
                     * DESCONSIDERAR ESTE VERTICE
                     */
                    if(custoDaAresta == Integer.MAX_VALUE || vizinho.getMarca() == 2){
                        Log.i("LOG", "desconsiderar aresta de :" + verticeVisitado.getNomeVertice() + ":" + vizinho.getNomeVertice() );
                    // CASO ESTEJA COM MARCA 1, NECESSITA UMA AVALIACAO DO CAMINHO, SE EH O MELHOR OU NAO
                    }else if(vizinho.getMarca() == 1){
                        /*
                         * SE O NOVO CUSTO FOR MELHOR:
                         * TROCA O PAI E
                         * TROCA O CUSTO TOTAL
                         */
                        if(vizinho.getCustoAte() > verticeVisitado.getCustoAte() + custoDaAresta){
                            vizinho.setVerticePai(verticeVisitado);
                            vizinho.setCustoAte(verticeVisitado.getCustoAte() + custoDaAresta);
                        }
                    // ENTRA NESSE CONDICIONAL VERTICES QUE AINDA NAO FORAM VISITADOS
                    }else{
                        /*
                         * ADICIONA VERTICE NA LISTA
                         * SETTA O PAI E O CUSTO ATE O VERTICE
                         * COLOCA A MARCA DE VISITADO
                         */
                        lista.add(vizinho);
                        vizinho.setVerticePai(verticeVisitado);
                        vizinho.setCustoAte(verticeVisitado.getCustoAte() + custoDaAresta);
                        vizinho.setMarca(1);
                    }
                }

                // ORDENA A LISTA PELO CUSTO, O OBJETIVO EH:
                // QUANDO FOR PEGAR O PROXIMO VERTICE(O DE MENOR CUSTO ATE), PODE PEGAR O PRIMEIRO
                Collections.sort(lista, comparator);

                // SE A LISTA ESTIVER VAZIA, ACABOU O ALMORITMO
                // NAO FOI ENCONTRADO CAMINHO DA ORIGEM ATE O DESTINO
                if(lista.isEmpty()){
                    break;
                }else{
                    // CASO CONTRARIO VISITA O VERTICE DE MENOR CUSTO DA LISTA
                    // NESTE CASO O PRIMEIRO, A LISTA ESTA ORDENADA
                    verticeVisitado = lista.get(0);
                }

                // SE ESTIVER VISITANDO O DESTINO, O MENOR CAMINHO FOI ENCONTRADO
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("mudar mapa");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(tipoMapa == 1){
            mapa.setBackgroundResource(R.drawable.mapacompeso);
            tipoMapa = 0;
        }else{
            mapa.setBackgroundResource(R.drawable.mapa);
            tipoMapa = 1;
        }
        return super.onOptionsItemSelected(item);
    }
}
