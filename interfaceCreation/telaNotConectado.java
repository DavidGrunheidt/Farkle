import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class telaNotConectado {

	private JFrame janela;
	JButton dadosLivres[];
	JPanel painelDosDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaNotConectado window = new telaNotConectado();
					window.janela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telaNotConectado() {
		initialize();
	}

	/**
	 * Initialize the contents of the janela.
	 */
	private void initialize() {
		janela = new JFrame();
		janela.setBounds(100, 100, 641, 401);
		janela.getContentPane().setLayout(null);

		JLabel labelLogo = new JLabel();
		labelLogo.setBounds(193, 10, 248, 79);
		janela.getContentPane().add(labelLogo);
		labelLogo.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
		
		painelDosDados = new JPanel();
		painelDosDados.setBounds(157, 97, 311, 183);
		janela.getContentPane().add(painelDosDados);
		painelDosDados.setOpaque(false);
		painelDosDados.setLayout(null);
		
		JLabel labelNumDadosLivres = new JLabel("6", SwingConstants.CENTER);
		labelNumDadosLivres.setBounds(243, 0, 68, 30);
		labelNumDadosLivres.setForeground(Color.white);
		labelNumDadosLivres.setFont(new Font("Arial Black", Font.PLAIN, 18));
		painelDosDados.add(labelNumDadosLivres);
		
		JLabel labelNumDadosSetAside = new JLabel("0", SwingConstants.CENTER);
		labelNumDadosSetAside.setBounds(243, 94, 68, 30);
		labelNumDadosSetAside.setForeground(Color.white);
		labelNumDadosSetAside.setFont(new Font("Arial Black", Font.PLAIN, 18));
		painelDosDados.add(labelNumDadosSetAside);
		
		dadosLivres = new JButton[6];
		int posX = 0;
		for (int i = 0; i < 6; i++) {
			this.setDado(i, posX);
			posX += 52;
		}
		
		JLabel label_7 = new JLabel("New label");
		label_7.setBounds(0, 132, 51, 51);
		painelDosDados.add(label_7);
		
		JLabel label_8 = new JLabel("New label");
		label_8.setBounds(52, 132, 51, 51);
		painelDosDados.add(label_8);
		
		JLabel label_9 = new JLabel("New label");
		label_9.setBounds(104, 132, 51, 51);
		painelDosDados.add(label_9);
		
		JLabel label_10 = new JLabel("New label");
		label_10.setBounds(156, 132, 51, 51);
		painelDosDados.add(label_10);
		
		JLabel label_11 = new JLabel("New label");
		label_11.setBounds(260, 132, 51, 51);
		painelDosDados.add(label_11);
		
		JLabel label_12 = new JLabel("New label");
		label_12.setBounds(208, 132, 51, 51);
		painelDosDados.add(label_12);
		
		JLabel label_13 = new JLabel();
		label_13.setBounds(0, 0, 311, 183);
		label_13.setIcon(new ImageIcon(getClass().getResource("/backgroundPainelDosDados.png")));
		painelDosDados.add(label_13);

		
		janela.validate();
		janela.repaint();

	}
	
	public void setDado(int i, int posX) {
		dadosLivres[i] = new JButton();
		dadosLivres[i].setBounds(posX, 35, 51, 51);
		painelDosDados.add(dadosLivres[i]);
		dadosLivres[i].setOpaque(false);
		dadosLivres[i].setContentAreaFilled(false);
		dadosLivres[i].setBorderPainted(false);
		dadosLivres[i].setIcon(new ImageIcon(getClass().getResource("/dado"+(i+1)+".png")));
		dadosLivres[i].addMouseListener(new MouseListener( ) {
			private boolean pressed = false; 

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			
			public void mouseEntered(MouseEvent arg0) {
				if (!pressed)
					dadosLivres[i].setIcon(new ImageIcon(getClass().getResource("/dado"+(i+1)+"Mouse.png")));
				else
					dadosLivres[i].setIcon(new ImageIcon(getClass().getResource("/dado"+(i+1)+"After.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (!pressed)
					dadosLivres[i].setIcon(new ImageIcon(getClass().getResource("/dado"+(i+1)+".png")));
				else
					dadosLivres[i].setIcon(new ImageIcon(getClass().getResource("/dado"+(i+1)+"Pressed.png")));
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				pressed = !pressed;
				if (pressed)
					dadosLivres[i].setIcon(new ImageIcon(getClass().getResource("/dado"+(i+1)+"Pressed.png")));
				else
					dadosLivres[i].setIcon(new ImageIcon(getClass().getResource("/dado"+(i+1)+".png")));
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}
}

