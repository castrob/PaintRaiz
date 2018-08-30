package com.castrob.Algoritmos;

/**
 * Classe Ponto como referencia primaria para as figuras
 * Acredito que tenha a propria classe Point do java
 * mas preferi implementar essa caso queira modificar algo c:
 */

public class Ponto {
    public double x;
    public double y;

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Ponto clone(){
        return (new Ponto(this.x, this.y));
    }
}