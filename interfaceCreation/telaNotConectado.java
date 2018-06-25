import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaNotConectado {

	private JFrame janela;

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
		labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		janela.getContentPane().add(labelLogo);
		labelLogo.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(157, 97, 311, 183);
		janela.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		janela.validate();
		janela.repaint();

	}
}

