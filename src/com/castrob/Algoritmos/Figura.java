package com.castrob.Algoritmos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Figura {

    public Ponto pontoInicial, pontoFinal;
    public Color cor;
    public boolean isCircunferencia;

    public Figura(double xInicial, double yInicial, double xFinal, double yFinal, Color cor) {
        this.pontoInicial = new Ponto( xInicial, yInicial);
        this.pontoFinal = new Ponto( xFinal, yFinal);
        this.cor = cor;
        this.isCircunferencia = false;
    }

    public Figura(){
        this.pontoFinal = null;
        this.pontoInicial = null;
        this.cor = Color.black;
        this.isCircunferencia = false;}
    public Figura(boolean isCircunferencia){
        this.pontoFinal = null;
        this.pontoInicial = null;
        this.cor = Color.black;
        this.isCircunferencia = isCircunferencia;
    }

    public void colorirPonto (int x, int y, BufferedImage g){
        g.setRGB(x,y,Color.BLACK.getRGB());
    }

    public abstract void desenharFiguraDDA(BufferedImage g);
    public abstract void desenharFiguraBresenham(BufferedImage g);
    public abstract void rotacionarFigura(double grau);
    public abstract void mudarEscalaFigura(double escalaX, double escalaY);
    public abstract void moverFigura(Ponto novoPonto);
    public abstract void espelharFigura(int opcode);
    public abstract void shearFigura(double fatorX, int opcode);
    public abstract void cohenClip();
    public abstract void liangClip();

    @Override
    public String toString() {
        return "Figura{" +
                "pontoInicial=" + pontoInicial +
                ", pontoFinal=" + pontoFinal +
                '}';
    }
}
