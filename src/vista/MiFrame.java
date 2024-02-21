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
    
    private void iniciarTablero() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                if ((row + col) % 2 == 0) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.BLACK);
                    
                }
                Ficha piece = board[row][col]; // Obtiene la ficha en esta posición
                if (piece != null) {
                    
                    ImageIcon icon = new createImageIcon(piece); // Suponiendo que ChessPiece tiene un método getImage() que devuelve la imagen de la ficha
                    JLabel label = new JLabel(icon);
                    square.add(label);
                } 	
                chessBoardPanel.add(square);
            }
        }
        revalidate(); 
        repaint(); 
    }
    private ImageIcon createImageIcon(Ficha piece) {
        // Aquí puedes implementar la lógica para crear la imagen de la ficha
        // Puedes usar diferentes imágenes para diferentes tipos de fichas
        // Por ejemplo, podrías cargar imágenes desde archivos o dibujarlas dinámicamente
        
        // Ejemplo básico: Crear un icono con un texto que representa el tipo de ficha
        String text = piece.getTipo(); // Suponiendo que ChessPiece tiene un método getType() que devuelve el tipo de ficha
        ImageIcon icon = new ImageIcon(createImageWithText(text));
        return icon;
    }

    private Image createImageWithText(String text) {
        int size = 50; // Tamaño de la imagen (píxeles)
        Image image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, size, size);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, size / 4, size / 2);
        g2d.dispose();
        return image;
    }
    public static void main(String[] args) {
    	Ficha[][] board = new Ficha[8][8];
		MiFrame f = new MiFrame();
	}
   

}

