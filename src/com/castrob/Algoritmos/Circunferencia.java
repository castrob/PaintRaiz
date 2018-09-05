package com.castrob.Algoritmos;

import java.awt.*;

/**
 * @author: Joao Castro
 * @date: 30/08/18
 */
public class Circunferencia extends Figura {

    public Circunferencia(double xInicial, double yInicial, double xFinal, double yFinal, Color cor) {
        super(xInicial, yInicial, xFinal, yFinal, cor);
    }

    public Circunferencia() {
        this.isCircunferencia = true;
    }

    @Override
    public void desenharFiguraDDA(Graphics g) {}

    @Override
    public void desenharFiguraBresenham(Graphics g) {
        this.isCircunferencia = true;
        double r = Math.sqrt(Math.pow((pontoFinal.x - pontoInicial.x),2) + Math.pow((pontoFinal.y - pontoInicial.y), 2));
        double x = 0.0;
        double y = r;
        double p = 3 - 2 * r;

        setPixels(x, y, pontoInicial.x, pontoInicial.y, g);

        while( x < y){
            if(p < 0) p+= 4 * x + 6;
            else {
                p += 4 * (x - y) + 10;
                y--;
            }
            x++;
            setPixels(x, y, pontoInicial.x, pontoInicial.y, g);
        }
    }

    public void setPixels(double x, double y, double xc, double yc, Graphics g){
        int sx1 = (int) Math.round(xc + x);
        int sx2 = (int)Math.round(xc - x);
        int sx3 = (int)Math.round(xc + y);
        int sx4 = (int)Math.round(xc - y);

        int sy1 = (int)Math.round(yc + y);
        int sy2 = (int)Math.round(yc - y);
        int sy3 = (int)Math.round(yc + x);
        int sy4 = (int)Math.round(yc - x);

        colorirPonto(sx1, sy1, g);
        colorirPonto(sx1, sy2, g);
        colorirPonto(sx2, sy1, g);
        colorirPonto(sx2, sy2, g);
        colorirPonto(sx3, sy3, g);
        colorirPonto(sx3, sy4, g);
        colorirPonto(sx4, sy3, g);
        colorirPonto(sx4, sy4, g);
    }

    @Override
    public void rotacionarFigura(double grau) { }

    @Override
    public void mudarEscalaFigura(double escalaX, double escalaY) {

    }

    @Override
    public void moverFigura(Ponto novoPonto) {

    }

    @Override
    public void espelharFigura(int opcode) {

    }

    @Override
    public void shearFigura(double fatorX, int opcode) {

    }

    @Override
    public void cohenClip() {

    }

    @Override
    public void liangClip() {

    }
}
