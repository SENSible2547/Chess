package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chess.Ficha;
import chess.MiFrame;
import chess.Tablero;

public class MiFrame extends JFrame {
	
    private JPanel chessBoardPanel;
    public Tablero[][] boardMatrix;

    public MiFrame(Tablero[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
        
    }
    public MiFrame() {
    	setTitle("Ajedrez");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        chessBoardPanel = new JPanel(new GridLayout(8, 8)); // GridLayout para el tablero
        add(chessBoardPanel, BorderLayout.CENTER);

        iniciarTablero();

        setVisible(true);

    }
    
    public void iniciarTablero() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                if ((row + col) % 2 == 0) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.BLACK);
                    
                }
//                Ficha piece = board[row][col]; // Obtiene la ficha en esta posición
//                if (piece != null) {
//                    
//                    
//                    JLabel label = new JLabel(icon);
//                    square.add(label);
//                } 	
                chessBoardPanel.add(square);
            }
        }
        revalidate(); 
        repaint(); 
    }
   
    public static void main(String[] args) {
    	Ficha[][] board = new Ficha[8][8];
		MiFrame f = new MiFrame();
	}
   

}

