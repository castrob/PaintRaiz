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
    private static final int kControlN = 78;
    private static final int kControlR = 82;
    private static final int kControlP = 80;
    private static final int kControlT = 84;
    private static final int kControlX = 88;

    //Objeto Reta para ser plotado

    //Painel onde tem o desenho (Visor ?)
    private DrawingPanel panel;
    private Panel painelFerramentas;

    public PaintRaiz (){
        super("PaintRaiz - ComputacaoGrafica - TP1");
        Dimension d = new Dimension(800, 720);
        this.setPreferredSize(d);
        this.setVisible(true);

        addMenu();
        addPanel();
        addTestPanel();
        this.pack();
        this.addWindowListener(new WindowHandler());
    }

    public void addTestPanel(){
        painelFerramentas = new Panel();
        painelFerramentas.setBackground(Color.gray);
        painelFerramentas.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 50);
        painelFerramentas.setLocation(4, 54);
        this.add(painelFerramentas);

        //Buttons
        Button retaDDA = new Button("Reta DDA");
        Button retaBresenham = new Button("Reta Bresenham");
        Button circunferenciaBresenham = new Button("Circunferencia Bresenham");


        //Handlers
        retaDDA.addActionListener(new WindowHandler());
        retaBresenham.addActionListener(new WindowHandler());
        circunferenciaBresenham.addActionListener(new WindowHandler());

        //Adicionando ao Painel de ferramentas
        painelFerramentas.add(retaBresenham, BorderLayout.CENTER);
        painelFerramentas.add(retaDDA, BorderLayout.WEST);
        painelFerramentas.add(circunferenciaBresenham, BorderLayout.WEST);
    }

    private void addPanel() {
        panel = new DrawingPanel();
        Dimension d = this.getSize();
        Insets ins = this.insets();
        d.height = d.height - ins.top - ins.bottom;
        d.width = d.width - ins.left - ins.right;
        panel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setLocation(ins.left, ins.top + 56);
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
        arquivo.add(new MenuItem("Limpar", new MenuShortcut(kControlN))).addActionListener(new WindowHandler());
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
            }else if(acao.equalsIgnoreCase("Limpar")){
                panel.clear();
            }else if(acao.equalsIgnoreCase("Sobre")){
                JOptionPane.showMessageDialog(null, "TP1 - Computacao Paralela\n Joao Castro - 562874", "About", JOptionPane.PLAIN_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, e.getActionCommand() + " Ainda não implementado!" , "PaintRaiz", JOptionPane.PLAIN_MESSAGE);
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

        public void clear(){
            pontoFinal = pontoInicial = null;
            figura = null;
            figuraList.clear();
            repaint();
        }
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
            if(figura != null && pontoInicial == null){
                pontoInicial = new Ponto(e.getPoint().getX(), e.getPoint().getY());
                figura.pontoInicial = pontoInicial;
            }else if(figura != null && pontoFinal == null){
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
