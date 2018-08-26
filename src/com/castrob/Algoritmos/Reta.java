package com.castrob.Algoritmos;

public class Reta extends Figura{

    public Reta(int[][] matriz, double xInicial, double yInicial, double xFinal, double yFinal, int cor) {
        super(matriz, xInicial, yInicial, xFinal, yFinal, cor);
    }

    /**
     * Metodo para desenhar a reta na tela utilizando o algoritmo DDA
     */
    @Override
    public void desenharFigura() {
        double dX = this.pontoFinal.x - this.pontoInicial.x;
        double dY = this.pontoFinal.y - this.pontoInicial.y;
        double x = this.pontoInicial.x;
        double y = this.pontoInicial.y;
        int passos;
        // colore o ponto inicial
        colorirPonto((int)Math.round(x), (int)Math.round(y), 0x000000);
        if(Math.abs(dX) > Math.abs(dY))
            passos = (int)Math.abs(dX);
        else
            passos = (int)Math.abs(dY);

        double xIncr = dX/passos;
        double yIncr = dY/passos;

        for(;passos >= 0; passos--) {
            x += xIncr;
            y += yIncr;
            colorirPonto((int)Math.round(x), (int)Math.round(y), 0x000000);
        }
    }

    //TODO Colocar Algoritmo de Desenhar sendo DDA ou Bresenham

    @Override
    public void rotacionarFigura(double grau) {
    //TODO RotacionarReta
    }

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
    @Override
    public void moverFigura(Ponto novoPonto) {
        double dX = novoPonto.x - this.pontoInicial.x;
        double dY = novoPonto.y - this.pontoFinal.y;

        this.pontoInicial = new Ponto(this.pontoInicial.x + dX, this.pontoInicial.y + dY);
        this.pontoFinal = new Ponto(this.pontoFinal.x + dY, this.pontoFinal.y + dY);
    }

}
