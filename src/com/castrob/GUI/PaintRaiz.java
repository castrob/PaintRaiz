package com.castrob.GUI;

import com.castrob.Algoritmos.Figura;
import com.castrob.Algoritmos.Ponto;
import com.castrob.Algoritmos.Reta;

import java.awt.*;
import java.awt.event.*;

public class PaintRaiz extends Frame {

    //Constantes para atalhos no menu
    private static final int kControlA = 65;
    private static final int kControlD = 68;
    private static final int kControlC = 67;
    private static final int kControlR = 82;
    private static final int kControlP = 80;
    private static final int kControlT = 84;
    private static final int kControlX = 88;

    //Objeto Reta para ser plotado
    private Reta reta = new Reta();

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
        algoritmosReta.add(new MenuItem("Reta DDA")).addActionListener(new WindowHandler());
        algoritmosReta.add(new MenuItem("Reta Bresenham")).addActionListener(new WindowHandler());
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
            String acao = e.getActionCommand();
            if(acao.equalsIgnoreCase("sair")){
                System.exit(0);
            }else if(acao.equalsIgnoreCase("Reta DDA")){
                Menu menu = getMenuBar().getMenu(1);
                for(int i = 0; i < menu.getItemCount(); menu.getItem(i).setEnabled(false), i++);
                panel.desenharFigura(reta);
            }
        }
    }

    private class DrawingPanel extends Panel implements MouseListener {
        private Ponto pontoInicial = null;
        private Ponto pontoFinal = null;
        private Figura figura = null;

        public void paint(Graphics g){
            g.setColor(Color.BLACK);
            figura.desenharFiguraBresenham(g);
        }

        public void desenharFigura(Figura figura) {
            this.figura = figura;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(pontoInicial == null){
                pontoInicial = new Ponto(e.getPoint().getX(), e.getPoint().getY());
                figura.pontoInicial = pontoInicial;
            }else{
                pontoFinal = new Ponto(e.getPoint().getX(), e.getPoint().getY());
                figura.pontoFinal = pontoFinal;
                repaint();
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
