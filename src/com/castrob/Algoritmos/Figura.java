package com.castrob.Algoritmos;

import java.awt.*;

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

    public Figura(){
        this.pontoFinal = null;
        this.pontoInicial = null;
        this.cor = 0x000000;
        this.matriz = null;
    }

    public void colorirPonto (int x, int y, Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x, y);
    }

    public abstract void desenharFiguraDDA(Graphics g);
    public abstract void desenharFiguraBresenham(Graphics g);
    public abstract void rotacionarFigura( double grau);
    public abstract void mudarEscalaFigura(double escala);
    public abstract void moverFigura(Ponto novoPonto);


}
