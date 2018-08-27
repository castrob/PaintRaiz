package com.castrob.GUI;

import com.castrob.Algoritmos.Figura;
import com.castrob.Algoritmos.Ponto;
import com.castrob.Algoritmos.Reta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PaintRaiz extends Frame {

    //Constantes para atalhos no menu
    private static final int kControlA = 65;
    private static final int kControlB = 66;
    private static final int kControlD = 68;
    private static final int kControlC = 67;
    private static final int kControlR = 82;
    private static final int kControlP = 80;
    private static final int kControlT = 84;
    private static final int kControlX = 88;

    //Objeto Reta para ser plotado

    //Painel onde tem o desenho (Visor ?)
    private DrawingPanel panel;

    public PaintRaiz (){
        super("PaintRaiz - ComputacaoGrafica - TP1");
        addMenu();
        addPanel();
        this.addWindowListener(new WindowHandler());
        this.setSize(400, 400);
        this.setVisible(true);
    }

    private void addPanel() {
        panel = new DrawingPanel();
        Dimension d = this.getSize();
        Insets ins = this.insets();
        d.height = d.height - ins.top - ins.bottom;
        d.width = d.width - ins.left - ins.right;

        panel.setSize(d);
        panel.setLocation(ins.left, ins.top);
        panel.setBackground(Color.white);

        panel.addMouseListener(panel);
        this.add(panel);
    }


    private void addMenu() {
        MenuBar menuBar = new MenuBar();
        Menu arquivo = new Menu("Arquivo");
        Menu algoritmosReta = new Menu("Algortimos Reta");
        Menu algoritmosCircunferencia = new Menu("Algortimos Circunferencia");
        Menu sobre = new Menu("Sobre");

        arquivo.add(new MenuItem("Sair", new MenuShortcut(kControlX))).addActionListener(new WindowHandler());
        algoritmosReta.add(new MenuItem("Reta DDA", new MenuShortcut(kControlD))).addActionListener(new WindowHandler());
        algoritmosReta.add(new MenuItem("Reta Bresenham", new MenuShortcut(kControlB))).addActionListener(new WindowHandler());
        algoritmosReta.add(new MenuItem("Transladar Reta")).addActionListener(new WindowHandler());
        algoritmosReta.add(new MenuItem("Rotacionar Reta")).addActionListener(new WindowHandler());
        algoritmosReta.add(new MenuItem("Escalar Reta")).addActionListener(new WindowHandler());

        algoritmosCircunferencia.add(new MenuItem("Circunferencia Bresenham")).addActionListener(new WindowHandler());
        algoritmosCircunferencia.add(new MenuItem("Transladar Circunferencia")).addActionListener(new WindowHandler());
        algoritmosCircunferencia.add(new MenuItem("Rotacionar Circunferencia")).addActionListener(new WindowHandler());
        algoritmosCircunferencia.add(new MenuItem("Escalar Circunferencia")).addActionListener(new WindowHandler());

        sobre.add(new MenuItem("Sobre")).addActionListener(new WindowHandler());
        menuBar.add(arquivo);
        menuBar.add(algoritmosReta);
        menuBar.add(algoritmosCircunferencia);
        menuBar.add(sobre);

        if(this.getMenuBar() == null){
            this.setMenuBar(menuBar);
        }
    }

    private class WindowHandler extends WindowAdapter implements ActionListener {
        /**
         * Listener para quando clicar em X da janela
         * @param e
         */
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }

        /**
         * Metodo que irá lidar com o click dos menus
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Reta reta = new Reta();
            String acao = e.getActionCommand();
            if(acao.equalsIgnoreCase("sair")){
                System.exit(0);
            }else if(acao.equalsIgnoreCase("Reta DDA")){
                Menu menu = getMenuBar().getMenu(1);
                for(int i = 0; i < menu.getItemCount(); menu.getItem(i).setEnabled(true), i++);
                getMenuBar().getShortcutMenuItem(new MenuShortcut(kControlD)).setEnabled(true);
                panel.desenharReta(reta, 0);
            }else if(acao.equalsIgnoreCase("Reta Bresenham")){
                Menu menu = getMenuBar().getMenu(1);
                for(int i = 0; i < menu.getItemCount(); menu.getItem(i).setEnabled(true), i++);
                getMenuBar().getShortcutMenuItem(new MenuShortcut(kControlB)).setEnabled(true);
                panel.desenharReta(reta, 1);
            }else if(acao.equalsIgnoreCase("Sobre")){
                JOptionPane.showMessageDialog(null, "TP1 - Computacao Paralela\n Joao Castro - 562874", "About", JOptionPane.PLAIN_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Voce clickou em " + e.getActionCommand(), "PaintRaiz", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private class DrawingPanel extends Panel implements MouseListener {
        private Ponto pontoInicial = null;
        private Ponto pontoFinal = null;
        private Figura figura = null;
        private int algoritmo = 0;
        ArrayList<Figura> figuraList = new ArrayList<Figura>();
        int pos = 0;

        public void paint(Graphics g){
            g.setColor(Color.BLACK);
            if(algoritmo == 0 && !(figuraList.size() == 0)){
                for(Figura f : figuraList)
                    f.desenharFiguraDDA(g);
            }
            else if(algoritmo == 1 && figura != null){
                for(Figura f : figuraList)
                    f.desenharFiguraBresenham(g);
            }
        }

        public void desenharReta(Figura figura, int opcode) {
            this.figura = figura;
            this.algoritmo = opcode;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(pontoInicial == null){
                pontoInicial = new Ponto(e.getPoint().getX(), e.getPoint().getY());
                figura.pontoInicial = pontoInicial;
            }else if(pontoFinal == null){
                pontoFinal = new Ponto(e.getPoint().getX(), e.getPoint().getY());
                figura.pontoFinal = pontoFinal;
                figuraList.add(figura);
                repaint();
                pontoFinal = pontoInicial = null;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }
}