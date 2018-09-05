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
        JLabel fillLabel = new JLabel("Preenchimento");
        JLabel optionLabel = new JLabel("Opções");
        JLabel aboutMeNameLabel = new JLabel("João Castro - 562874");
        JLabel aboutMeEmailLabel = new JLabel("jpcbpereira@sga.pucminas.br");

        //Panels
        JPanel rPanel = new JPanel(new GridBagLayout());
        JPanel cPanel = new JPanel(new GridBagLayout());
        JPanel tPanel = new JPanel(new GridBagLayout());
        JPanel clipPanel = new JPanel(new GridBagLayout());
        JPanel fillPanel = new JPanel(new GridBagLayout());
        JPanel optionPanel = new JPanel(new GridBagLayout());
        JPanel aboutPanel = new JPanel(new GridBagLayout());

        //Buttons
        JRadioButton rDDA = new JRadioButton("DDA");
        JRadioButton rBresenham = new JRadioButton("Bresenham");
        JRadioButton cBresenham = new JRadioButton("Bresenham(Circ.)");
        JRadioButton clipLiangBarsky = new JRadioButton("Liang-Barsky");
        JRadioButton clipCohenSutherLand = new JRadioButton("Cohen-Sutherland");
        JRadioButton rotacionar = new JRadioButton("Rotacionar");
        JRadioButton mover = new JRadioButton("Mover");
        JRadioButton redimencionar = new JRadioButton("Redimencionar");
        JRadioButton cisalhamento = new JRadioButton("Cisalhamento");
        JRadioButton reflexao = new JRadioButton("Reflexao");
        JRadioButton boundaryFill = new JRadioButton("Boundary-Fill");
        JRadioButton floodFill = new JRadioButton("Flood-Fill");
        JButton limparTela = new JButton("Limpar Desenhos");



        //Listeners
        rDDA.addActionListener(new ButtonListener());
        rBresenham.addActionListener(new ButtonListener());
        cBresenham.addActionListener(new ButtonListener());
        mover.addActionListener(new ButtonListener());
        rotacionar.addActionListener(new ButtonListener());
        redimencionar.addActionListener(new ButtonListener());
        cisalhamento.addActionListener(new ButtonListener());
        reflexao.addActionListener(new ButtonListener());
        clipCohenSutherLand.addActionListener(new ButtonListener());
        clipLiangBarsky.addActionListener(new ButtonListener());
        boundaryFill.addActionListener(new ButtonListener());
        floodFill.addActionListener(new ButtonListener());
        limparTela.addActionListener(new ButtonListener());

        //Filling the button list
        buttons.add(rDDA);
        buttons.add(rBresenham);
        buttons.add(cBresenham);
        buttons.add(mover);
        buttons.add(rotacionar);
        buttons.add(redimencionar);
        buttons.add(clipCohenSutherLand);
        buttons.add(clipLiangBarsky);
        buttons.add(cisalhamento);
        buttons.add(reflexao);
        buttons.add(boundaryFill);
        buttons.add(floodFill);


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
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(redimencionar, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(reflexao, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 0, 0);
        tPanel.add(cisalhamento, cItem);


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

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        fillPanel.add(fillLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,5,0);
        fillPanel.add(boundaryFill, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        fillPanel.add(floodFill, cItem);

        cGroup.gridy++;
        painelFerramentas.add(fillPanel, cGroup);

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);

        optionPanel.add(optionLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        optionPanel.add(limparTela, cItem);

        cGroup.gridy++;
        painelFerramentas.add(optionPanel, cGroup);

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,0);
        aboutPanel.add(aboutMeNameLabel, cItem);

        cItem.gridy++;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,0);
        aboutPanel.add(aboutMeEmailLabel, cItem);

        cGroup.gridy++;
        painelFerramentas.add(aboutPanel, cGroup);


//        cGroup.gridy++;
//        cGroup.weightx = cGroup.weighty = 1.0;
//        cGroup.insets = new Insets(0,0,0,0);
//        cGroup.fill = GridBagConstraints.BOTH;
//        painelFerramentas.add(new JPanel(new GridBagLayout()), cGroup);

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
        Dimension d = new Dimension(800, 600);

        setPreferredSize(d);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private class DrawingPanel extends Panel implements MouseListener{
        private static final int RETA = 0;
        private static final int CIRCUNFERENCIA = 1;
        private static final int MOVER = 1;
        private static final int ROTACIONAR = 2;
        private static final int REDIMENCIONAR = 3;
        private boolean isDefaultAlgorithm = true;
        private Ponto pontoInicial = null;
        private Ponto pontoFinal = null;
        private Figura figura = null;
        //Qual operacao a ser realizada ao clicar (mover = 1, rotacionar = 2, redimencionar = 3)
        private int action = 0;
        //Todas as figuras inseridas na tela
        ArrayList<Figura> figuras = new ArrayList<>();
        //Qual figura deve ser realizado a operacao
        private int index;

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
                        if(f.isCircunferencia)
                            f.desenharFiguraBresenham(g);
                        else
                        f.desenharFiguraDDA(g);
                }
            }
        }

        /**
         * Metodo a ser chamado por classes superiores para inserir nova figura
         * @param key - RETA - 0, CIRCUNFERENCIA - 1
         * @param algorithm DDA - 0, BRESENHAM - 1
         */
        public void desenharFigura(int key, int algorithm){
                if(key == CIRCUNFERENCIA){
                    this.figura = new Circunferencia();
                    this.isDefaultAlgorithm = true;
                }else if(algorithm == 0){
                    this.figura = new Reta();
                    this.isDefaultAlgorithm = false;
                }else if(algorithm == 1){
                    this.figura = new Reta();
                    this.isDefaultAlgorithm = true;
                }
            action = 0;
        }

        private void updatePaint() {
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(action == 0){
                if(pontoInicial == null && pontoFinal == null){
                    pontoInicial = new Ponto(e.getX(), e.getY());
                }else if(pontoFinal == null){
                    pontoFinal = new Ponto(e.getX(), e.getY());
                    if(figura != null){
                        figura.pontoInicial = pontoInicial;
                        figura.pontoFinal = pontoFinal;
                    }
                    figuras.add(figura);
                    if(!figura.isCircunferencia)
                        figura = new Reta();
                    else
                        figura = new Circunferencia();
                    pontoFinal = pontoInicial = null;
                    updatePaint();
                }
            }else if(action == MOVER){
                if(figuras.isEmpty()){
                    JOptionPane.showMessageDialog(null,"não há nenhuma figura!");
                }else if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo mover circunferencia!");
                    else{
                        f.moverFigura(new Ponto(e.getX(), e.getY()));
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"posição invalida!");
                }
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

        public void rotacionarFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo rotacionar uma circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite o grau de rotação: ", "0.0");
                        double grau = Double.parseDouble(input);
                        f.rotacionarFigura(grau);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }

        public void redimencionarFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo redimencionar uma circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite a escala de redimencionamento de X: ", "0.0");
                        double escalaX = Double.parseDouble(input);
                        input = JOptionPane.showInputDialog("Digite a escala de redimencionamento de Y: ", "0.0");
                        double escalaY = Double.parseDouble(input);
                        f.mudarEscalaFigura(escalaX, escalaY);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }

        public void clear() {
            this.figuras.clear();
            this.pontoFinal = pontoInicial = null;
            this.figura = null;
            updatePaint();
        }

        public void espelharFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo espelhar uma circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite qual eixo a ser espelhado: (0 - Eixo X, 1 - Eixo Y, 2 - Origem) ", "0");
                        int opcode = Integer.parseInt(input);
                        f.espelharFigura(opcode);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }

        public void cisalharFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo aplicar uma pressao numa circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite qual eixo a ser cisalhado: (0 - Eixo X, 1 - Eixo Y) ", "0");
                        int opcode = Integer.parseInt(input);
                        input = JOptionPane.showInputDialog("Digite um fator de cisalhamento a ser aplicado: ", "0.0");
                        double fator = Double.parseDouble(input);
                        f.shearFigura(fator, opcode);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "DDA":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        drawingPanel.desenharFigura(0, 0);
                    break;
                case "Bresenham":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        drawingPanel.desenharFigura(0, 1);
                    break;
                case "Bresenham(Circ.)":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        drawingPanel.desenharFigura(1, 1);
                    break;
                case "Mover":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        String input = JOptionPane.showInputDialog("Qual reta deve ser rotacionada? (0,1,2..)","0");
                        drawingPanel.action = 1;
                        drawingPanel.index = Integer.parseInt(input);
                    break;
                case "Rotacionar":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        input = JOptionPane.showInputDialog("Qual reta deve ser redimencionada? (0,1,2..)","0");
                        drawingPanel.index = Integer.parseInt(input);
                        drawingPanel.rotacionarFigura();
                    break;
                case "Redimencionar":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        input = JOptionPane.showInputDialog("Qual reta deve ser redimencionada? (0,1,2..)","0");
                        drawingPanel.action = 3;
                        drawingPanel.index = Integer.parseInt(input);
                        drawingPanel.redimencionarFigura();
                    break;
                case "Reflexao":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    input = JOptionPane.showInputDialog("Qual reta deve ser espelhada? (0,1,2..)","0");
                    drawingPanel.action = 4;
                    drawingPanel.index = Integer.parseInt(input);
                    drawingPanel.espelharFigura();
                    break;
                case "Cisalhamento":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    input = JOptionPane.showInputDialog("Qual reta deve ser cisalhada? (0,1,2..)","0");
                    drawingPanel.action = 5;
                    drawingPanel.index = Integer.parseInt(input);
                    drawingPanel.cisalharFigura();
                    break;
                case "Cohen-Sutherland":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Liang-Barsky":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Boundary-Fill":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Flood-Fill":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    break;
                case "Limpar Desenhos":
                    drawingPanel.clear();
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
