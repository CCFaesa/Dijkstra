package br.com.dalcim.dijkstra.global;

import br.com.dalcim.dijkstra.model.Vertice;

/**
 * Created by Wiliam on 18/11/2016.
 */

public class Constante {
    public static final Vertice verticeA = new Vertice("A");
    public static final Vertice verticeB = new Vertice("B");
    public static final Vertice verticeC = new Vertice("C");
    public static final Vertice verticeD = new Vertice("D");
    public static final Vertice verticeE = new Vertice("E");
    public static final Vertice verticeF = new Vertice("F");
    public static final Vertice verticeG = new Vertice("G");
    public static final Vertice verticeH = new Vertice("H");
    public static final Vertice verticeI = new Vertice("I");
    public static final Vertice verticeJ = new Vertice("J");
    public static final Vertice verticeK = new Vertice("K");
    public static final Vertice verticeL = new Vertice("L");
    public static final Vertice verticeM = new Vertice("M");
    public static final Vertice verticeN = new Vertice("N");
    public static final Vertice verticeO = new Vertice("O");
    public static final Vertice verticeP = new Vertice("P");
    public static final Vertice verticeQ = new Vertice("Q");
    public static final Vertice verticeR = new Vertice("R");

    public static final int A_PE = 1;
    public static final int DE_CARRO = 2;
    public static final int DE_ONIBUS = 3;

    public static final Vertice[] VERTICES = new Vertice[]{
            verticeA,
            verticeB,
            verticeC,
            verticeD,
            verticeE,
            verticeF,
            verticeG,
            verticeH,
            verticeI,
            verticeJ,
            verticeK,
            verticeL,
            verticeM,
            verticeN,
            verticeO,
            verticeP,
            verticeQ,
            verticeR
    };

    public static final int[] TRANSPORTES = new int[]{
            A_PE,
            DE_CARRO,
            DE_ONIBUS
    };

}
