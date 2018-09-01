package com.castrob.GUI;

import com.castrob.Algoritmos.Circunferencia;
import com.castrob.Algoritmos.Figura;
import com.castrob.Algoritmos.Ponto;
import com.castrob.Algoritmos.Reta;

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


    private class DrawingPanel extends Panel implements MouseListener{
        private static final int RETA = 0;
        private static final int CIRCUNFERENCIA = 1;
        private boolean isDefaultAlgorithm = true;
        private Ponto pontoInicial = null;
        private Ponto pontoFinal = null;
        private Figura figura = null;
        //Todas as figuras inseridas na tela
        ArrayList<Figura> figuras = new ArrayList<>();

        /**
         * Metodo Paint default da classe Panel desenha todas as figuras
         * utilizando por default o algoritmo de Bresenham
         * @param g - Graphics object
         */
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if(!figuras.isEmpty()){
                if(isDefaultAlgorithm){
                    for(Figura f : figuras)
                        f.desenharFiguraBresenham(g);
                }else{
                    for(Figura f : figuras)
                        f.desenharFiguraDDA(g);
                }
            }
        }

        /**
         * Metodo a ser chamado por classes superiores para inserir nova figura
         * @param figura - Objeto referencia a ser adicionado
         * @param key - RETA - 0, CIRCUNFERENCIA - 1
         * @param algorithm DDA - 0, BRESENHAM - 1
         */
        public void desenharFigura(Figura figura, int key, int algorithm){
            if(figura != null){
                if(key == CIRCUNFERENCIA){
                    this.figura = new Circunferencia();
                    this.isDefaultAlgorithm = true;
                }else if(algorithm == 0){
                    this.figura = new Reta();
                    this.isDefaultAlgorithm = false;
                }
            }
        }

        private void updatePaint() {
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

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
