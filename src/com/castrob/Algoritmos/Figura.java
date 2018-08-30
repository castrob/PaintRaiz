package com.castrob.Algoritmos;

import java.awt.*;

public abstract class Figura {

    public Ponto pontoInicial, pontoFinal;
    public Color cor;

    public Figura(double xInicial, double yInicial, double xFinal, double yFinal, Color cor) {
        this.pontoInicial = new Ponto( xInicial, yInicial);
        this.pontoFinal = new Ponto( xFinal, yFinal);
        this.cor = cor;
    }

    public Figura(){
        this.pontoFinal = null;
        this.pontoInicial = null;
        this.cor = Color.black;
    }

    public void colorirPonto (int x, int y, Graphics g){
        g.setColor(this.cor);
        g.drawLine(x, y, x, y);
    }

    public abstract void desenharFiguraDDA(Graphics g);
    public abstract void desenharFiguraBresenham(Graphics g);
    public abstract void rotacionarFigura( double grau);
    public abstract void mudarEscalaFigura(double escalaX, double escalaY);
    public abstract void moverFigura(Ponto novoPonto);

    @Override
    public String toString() {
        return "Figura{" +
                "pontoInicial=" + pontoInicial +
                ", pontoFinal=" + pontoFinal +
                '}';
    }
}
