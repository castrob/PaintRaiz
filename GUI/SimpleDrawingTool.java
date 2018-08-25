import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JOptionPane;

public class SimpleDrawingTool extends Frame{
    //Constantes para o menu
    private static final int kControlA = 65;
    private static final int kControlD = 68;
    private static final int kControlC = 67;
    private static final int kControlR = 82;
    private static final int kControlP = 80;
    private static final int kControlT = 84;
    private static final int kControlX = 88;

    public SimpleDrawingTool (){
        super("Computacao Grafica - TP1");
        addMenu();
        this.setSize(400,400);
        this.setVisible(true);
    }

    private void addMenu(){
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu shape = new Menu("Shapes");
        Menu about = new Menu("About");

        file.add(new MenuItem("Exit", new MenuShortcut(kControlX))).addActionListener(new WindowHandler());;

        shape.add(new MenuItem("Rectangle", new MenuShortcut(kControlR))).addActionListener(new WindowHandler());
        shape.add(new MenuItem("Circle", new MenuShortcut(kControlC))).addActionListener(new WindowHandler());
        shape.add(new MenuItem("Triangle", new MenuShortcut(kControlT))).addActionListener(new WindowHandler());
        shape.add(new MenuItem("Polygon", new MenuShortcut(kControlP))).addActionListener(new WindowHandler());
        shape.add(new MenuItem("Draw Plygon", new MenuShortcut(kControlD))).addActionListener(new WindowHandler());
        
        about.add(new MenuItem("About", new MenuShortcut(kControlA))).addActionListener(new WindowHandler());

        menuBar.add(file);
        menuBar.add(shape);
        menuBar.add(about);

        if(null == this.getMenuBar())
            this.setMenuBar(menuBar);
    }

    private class WindowHandler extends WindowAdapter implements ActionListener {
        
        public void WindowClosing(WindowEvent e){
            System.exit(0);
        }

        public void actionPerformed(ActionEvent e){
            System.out.println(e.getActionCommand());

            if(e.getActionCommand().equalsIgnoreCase("exit")){
                System.exit(0);
            }else if(e.getActionCommand().equalsIgnoreCase("About")){
                JOptionPane.showMessageDialog(null, "Teste", "About", JOptionPane.PLAIN_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Teste do " + e.getActionCommand(), "PaintRaizera", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    
    public static void main(String[] args) {
        SimpleDrawingTool simpleDrawingTool = new SimpleDrawingTool();
    }
}