package com.castrob.GUI;

import com.castrob.Algoritmos.Figura;
import com.castrob.Algoritmos.Ponto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PaintRaiz extends JFrame {

    //Painel onde tem o desenho (Visor ?)
    private DrawingPanel drawingPanel = new DrawingPanel();
    private JPanel painelFerramentas = new JPanel();
    private ArrayList<JRadioButton> buttons = new ArrayList<>();

    public PaintRaiz(){
        super("PaintRaiz - ComputacaoGrafica - TP1");
        //Configurando o Layout Principal
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);
        painelFerramentas.setLayout(new GridBagLayout());

        //Constraints
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints cGroup = new GridBagConstraints();
        GridBagConstraints cItem = new GridBagConstraints();

        //Labels
        JLabel rLabel = new JLabel("Retas");
        JLabel cLabel = new JLabel("Circunferencia");
        JLabel tLabel = new JLabel("Transformações");
        JLabel clipLabel = new JLabel("Recorte");

        //Panels
        JPanel rPanel = new JPanel(new GridBagLayout());
        JPanel cPanel = new JPanel(new GridBagLayout());
        JPanel tPanel = new JPanel(new GridBagLayout());
        JPanel clipPanel = new JPanel(new GridBagLayout());

        //Buttons
        JRadioButton rDDA = new JRadioButton("DDA");
        JRadioButton rBresenham = new JRadioButton("Bresenham");
        JRadioButton cBresenham = new JRadioButton("Bresenham(Circ.)");
        JRadioButton clipLiangBarsky = new JRadioButton("Liang-Barsky");
        JRadioButton clipCohenSutherLand = new JRadioButton("Cohen-Sutherland");
        JRadioButton rotacionar = new JRadioButton("Rotacionar");
        JRadioButton mover = new JRadioButton("Mover");
        JRadioButton redimencionar = new JRadioButton("Redimencionar");


        //Listeners
        rDDA.addActionListener(new ButtonListener());
        rBresenham.addActionListener(new ButtonListener());
        cBresenham.addActionListener(new ButtonListener());
        mover.addActionListener(new ButtonListener());
        rotacionar.addActionListener(new ButtonListener());
        redimencionar.addActionListener(new ButtonListener());
        clipCohenSutherLand.addActionListener(new ButtonListener());
        clipLiangBarsky.addActionListener(new ButtonListener());

        //Filling the button list
        buttons.add(rDDA);
        buttons.add(rBresenham);
        buttons.add(cBresenham);
        buttons.add(mover);
        buttons.add(rotacionar);
        buttons.add(redimencionar);
        buttons.add(clipCohenSutherLand);
        buttons.add(clipLiangBarsky);


        // Configurando Painel de Ferramentas
        // Panel de Retas
        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        rPanel.add(rLabel, cItem);

        //Botao para Reta DDA
        cItem.gridy++;
        cItem.insets = new Insets(0,0,5,0);
        rPanel.add(rDDA, cItem);

        //Botao para Reta Bresenham
        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        rPanel.add(rBresenham, cItem);

        cGroup.gridx = 0;
        cGroup.gridy = 0;
        cGroup.anchor = GridBagConstraints.NORTHWEST;
        cGroup.insets = new Insets(10,10,10,10);
        painelFerramentas.add(rPanel, cGroup);

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        cPanel.add(cLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0 ,0, 0);
        cPanel.add(cBresenham, cItem);

        cGroup.gridy++;
        painelFerramentas.add(cPanel, cGroup);

        //Adicionando Ferramentas de Transformacao
        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        tPanel.add(tLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(mover, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(rotacionar, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 0, 0);
        tPanel.add(redimencionar, cItem);

        cGroup.gridy++;
        painelFerramentas.add(tPanel, cGroup);

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        clipPanel.add(clipLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,5,0);
        clipPanel.add(clipCohenSutherLand, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        clipPanel.add(clipLiangBarsky, cItem);

        cGroup.gridy++;
        painelFerramentas.add(clipPanel, cGroup);

        cGroup.gridy++;
        cGroup.weightx = cGroup.weighty = 1.0;
        cGroup.insets = new Insets(0,0,0,0);
        cGroup.fill = GridBagConstraints.BOTH;
        painelFerramentas.add(new JPanel(new GridBagLayout()), cGroup);

        painelFerramentas.validate();

        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.VERTICAL;

        add(painelFerramentas, c);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;

        add(drawingPanel, c);

        //Definindo preferencias
        drawingPanel.setBackground(Color.white);
        drawingPanel.addMouseListener(drawingPanel);
        Dimension d = new Dimension(600, 500);

        setPreferredSize(d);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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


        public void rotacionarSelecao(double grau) {
            if(!(figuraList.size() == 0)){
               for(Figura f : figuraList){
                   f.rotacionarFigura(grau);
               }
            }
            repaint();
        }


        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "DDA":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Bresenham":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Bresenham(Circ.)":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Mover":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Rotacionar":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Redimencionar":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Cohen-Sutherland":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Liang-Barsky":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;

            }
        }

        public void limparSelecionados(int index){
            for(int i = 0; i < buttons.size(); i++)
                if(i != index)
                    buttons.get(i).setSelected(false);
        }
    }
}
