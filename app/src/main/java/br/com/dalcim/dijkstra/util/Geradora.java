package br.com.dalcim.dijkstra.util;

import br.com.dalcim.dijkstra.model.Grafo;

import static br.com.dalcim.dijkstra.global.Constante.verticeA;
import static br.com.dalcim.dijkstra.global.Constante.verticeB;
import static br.com.dalcim.dijkstra.global.Constante.verticeC;
import static br.com.dalcim.dijkstra.global.Constante.verticeD;
import static br.com.dalcim.dijkstra.global.Constante.verticeE;
import static br.com.dalcim.dijkstra.global.Constante.verticeF;
import static br.com.dalcim.dijkstra.global.Constante.verticeG;
import static br.com.dalcim.dijkstra.global.Constante.verticeH;
import static br.com.dalcim.dijkstra.global.Constante.verticeI;
import static br.com.dalcim.dijkstra.global.Constante.verticeJ;
import static br.com.dalcim.dijkstra.global.Constante.verticeK;
import static br.com.dalcim.dijkstra.global.Constante.verticeL;
import static br.com.dalcim.dijkstra.global.Constante.verticeM;
import static br.com.dalcim.dijkstra.global.Constante.verticeN;
import static br.com.dalcim.dijkstra.global.Constante.verticeO;
import static br.com.dalcim.dijkstra.global.Constante.verticeP;
import static br.com.dalcim.dijkstra.global.Constante.verticeQ;
import static br.com.dalcim.dijkstra.global.Constante.verticeR;

/**
 * Created by Wiliam on 18/11/2016.
 */
public class Geradora {
    public static Grafo geraGrafo(){
        Grafo  grafo = new Grafo();

        grafo.add(verticeA);
        grafo.add(verticeB);
        grafo.add(verticeC);
        grafo.add(verticeD);
        grafo.add(verticeE);
        grafo.add(verticeF);
        grafo.add(verticeG);
        grafo.add(verticeH);
        grafo.add(verticeI);
        grafo.add(verticeJ);
        grafo.add(verticeK);
        grafo.add(verticeL);
        grafo.add(verticeM);
        grafo.add(verticeN);
        grafo.add(verticeO);
        grafo.add(verticeP);
        grafo.add(verticeQ);
        grafo.add(verticeR);

        verticeA.addAresta(verticeB, 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
        verticeA.addAresta(verticeC, 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
        verticeA.addAresta(verticeD, 3, Integer.MAX_VALUE, Integer.MAX_VALUE);

        verticeB.addAresta(verticeA, 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
        verticeB.addAresta(verticeH, 8, 8, Integer.MAX_VALUE);
        verticeB.addAresta(verticeC, 5);

        verticeC.addAresta(verticeA, 2);
        verticeC.addAresta(verticeB, 5);
        verticeC.addAresta(verticeD, 4);
        verticeC.addAresta(verticeI, Integer.MAX_VALUE, 9, 9);

        verticeD.addAresta(verticeA, 3);
        verticeD.addAresta(verticeC, 4);
        verticeD.addAresta(verticeE, 3);

        verticeE.addAresta(verticeD, 3);
        verticeE.addAresta(verticeF, 2);
        verticeE.addAresta(verticeG, 2);

        verticeF.addAresta(verticeE, 2);
        verticeF.addAresta(verticeJ, 3, 8, 3);

        verticeG.addAresta(verticeE,2);
        verticeG.addAresta(verticeK,4, 4, Integer.MAX_VALUE);

        verticeH.addAresta(verticeB, 8);
        verticeH.addAresta(verticeI, 3);

        verticeI.addAresta(verticeC, Integer.MAX_VALUE, 9, 9);
        verticeI.addAresta(verticeH, 3, 3, Integer.MAX_VALUE);
        verticeI.addAresta(verticeJ, 4, 13, Integer.MAX_VALUE);
        verticeI.addAresta(verticeL, 2);

        verticeJ.addAresta(verticeF, 3, 8, 3);
        verticeJ.addAresta(verticeI, 4, 13, Integer.MAX_VALUE);
        verticeJ.addAresta(verticeK, 2, 2, Integer.MAX_VALUE);

        verticeK.addAresta(verticeG, 4);
        verticeK.addAresta(verticeJ, 2, 2, Integer.MAX_VALUE);
        verticeK.addAresta(verticeO, Integer.MAX_VALUE, 6, 6);

        verticeL.addAresta(verticeI, 2);
        verticeL.addAresta(verticeM, 3);
        verticeL.addAresta(verticeN, 3);

        verticeM.addAresta(verticeL, 3);
        verticeM.addAresta(verticeP, 3);

        verticeN.addAresta(verticeL, 3);
        verticeN.addAresta(verticeO, 5, 25, 5);
        verticeN.addAresta(verticeP, 2, Integer.MAX_VALUE, Integer.MAX_VALUE);

        verticeO.addAresta(verticeK, Integer.MAX_VALUE, 6, 6);
        verticeO.addAresta(verticeN, 5, 25, 5);
        verticeO.addAresta(verticeR, 4);

        verticeP.addAresta(verticeM, 3);
        verticeP.addAresta(verticeN, 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
        verticeP.addAresta(verticeQ, 1);

        verticeQ.addAresta(verticeP, 1);
        verticeQ.addAresta(verticeR, 4);

        verticeR.addAresta(verticeQ, 4);
        verticeR.addAresta(verticeO, 4);

        return grafo;
    }
}
