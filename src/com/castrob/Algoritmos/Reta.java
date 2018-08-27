package com.castrob.Algoritmos;

import java.awt.*;
import java.util.ArrayList;

public class Reta extends Figura{

    public Reta(int[][] matriz, double xInicial, double yInicial, double xFinal, double yFinal, int cor) {
        super(matriz, xInicial, yInicial, xFinal, yFinal, cor);
    }

    public Reta(){ }

    @Override
    public void desenharFiguraDDA(ArrayList<Figura> figuras, Graphics g) {
        for(Figura f : figuras){
            this.pontoFinal = f.pontoFinal.clone();
            this.pontoInicial = f.pontoInicial.clone();
            desenharFiguraDDA(g);
        }
    }

    /**
     * Metodo para desenhar a reta na tela utilizando o algoritmo DDA
     * Fonte: Slides de Computacao Grafica presentes no SGA
     */
    @Override
    public void desenharFiguraDDA(Graphics g) {
        double dX = this.pontoFinal.x - this.pontoInicial.x;
        double dY = this.pontoFinal.y - this.pontoInicial.y;
        double x = this.pontoInicial.x;
        double y = this.pontoInicial.y;
        int passos;
        // colore o ponto inicial
        colorirPonto((int)Math.round(x), (int)Math.round(y), g);
        if(Math.abs(dX) > Math.abs(dY))
            passos = (int)Math.abs(dX);
        else
            passos = (int)Math.abs(dY);

        double xIncr = dX/passos;
        double yIncr = dY/passos;

        for(;passos >= 0; passos--) {
            x += xIncr;
            y += yIncr;
            colorirPonto((int)Math.round(x), (int)Math.round(y), g);
        }
    }


    /**
     * Metodo para plotar a figura utilizando o algoritmo de Bresenham
     * Fonte: Slides de Computacao Grafica Presentes no SGA
     */
    @Override
    public void desenharFiguraBresenham(Graphics g) {
        int dX, dY, x, y, i, const1, const2, p, xIncr, yIncr;

        dX = (int)Math.round(this.pontoFinal.x - this.pontoInicial.x);
        dY = (int)Math.round(this.pontoFinal.y - this.pontoInicial.y);

        if( dX >= 0)
            xIncr = 1;
        else{
            xIncr = -1;
            dX = dX * -1;
        }
        if(dY >= 0)
            yIncr = 1;
        else {
            yIncr = -1;
            dY = dY * -1;
        }
        x = (int)Math.round(pontoInicial.x);
        y = (int)Math.round(pontoInicial.y);
        colorirPonto(x, y, g);

        if(dY < dX){
            p = (2 * dY) - dX;
            const1 = 2 * dY;
            const2 = 2*(dY - dX);

            for(i = 0; i < dX; i++){
                x += xIncr;
                if (p < 0)
                    p += const1;
                else{
                    y += yIncr;
                    p += const2;
                }
                colorirPonto(x, y, g);
            }
        }else{
            p = (2 * dX) - dY;
            const1 = 2 * dX;
            const2 = 2 * (dX - dY);

            for (i = 0; i < dY; i++){
                y += yIncr;
                if (p < 0)
                    p += const1;
                else{
                    x += xIncr;
                    p += const2;
                }
                colorirPonto(x, y, g);
            }
        }
    }



    @Override
    public void rotacionarFigura(double grau) {
        Ponto pontoOriginal = this.pontoInicial.clone();

        moverFigura(new Ponto(0, 0));

        grau = Math.toRadians(grau);

        this.pontoFinal = new Ponto(
                ((Math.round(this.pontoFinal.x)*Math.cos(grau)) - (Math.round(this.pontoFinal.y) * Math.sin(grau))),
                ((Math.round(this.pontoFinal.x)*Math.sin(grau)) + (Math.round(this.pontoFinal.y) * Math.cos(grau))));

        moverFigura(pontoOriginal);

    }
    //TODO Verificar pontoOriginal (inicial, final)
    @Override
    public void mudarEscalaFigura(double escala) {
        //Armazena a posicao inicial da linha
        Ponto pontoOriginal = this.pontoInicial.clone();

        // aplica a translacao pra origem
        moverFigura(new Ponto(0, 0));

        //Alterando a escala corretamente
        this.pontoFinal = new Ponto(Math.round(this.pontoFinal.x * escala), Math.round(this.pontoFinal.y * escala));

        //Aplicando a translacao para o ponto original
        moverFigura(pontoOriginal);
    }

    /**
     * Metodo de Translacao T(a,b)
     * @param novoPonto Ponto do Mouse (x,y)
     */
    //TODO Verficiar se esta' correto!!!
    @Override
    public void moverFigura(Ponto novoPonto) {
        double dX = novoPonto.x - this.pontoInicial.x;
        double dY = novoPonto.y - this.pontoFinal.y;

        this.pontoInicial = new Ponto(this.pontoInicial.x + dX, this.pontoInicial.y + dY);
        this.pontoFinal = new Ponto(this.pontoFinal.x + dY, this.pontoFinal.y + dY);
    }

}
