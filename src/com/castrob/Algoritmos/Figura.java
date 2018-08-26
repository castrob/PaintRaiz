package com.castrob.Algoritmos;

public abstract class Figura {

    public int[][] matriz;
    public Ponto pontoInicial, pontoFinal;
    public int cor;

    public Figura(int[][] matriz, double xInicial, double yInicial, double xFinal, double yFinal, int cor) {
        this.matriz = matriz;
        this.pontoInicial = new Ponto( xInicial, yInicial);
        this.pontoFinal = new Ponto( xFinal, yFinal);
        this.cor = cor;
    }

    public void colorirPonto (int x, int y, int cor){
        matriz[x][y] = cor;
    }

    public abstract void desenharFigura();
    public abstract void rotacionarFigura( double grau);
    public abstract void mudarEscalaFigura(double escala);
    public abstract void moverFigura(Ponto novoPonto);


}
