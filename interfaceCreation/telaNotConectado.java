import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

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
		janela.setVisible(true);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogo.setBounds(193, 10, 248, 79);
		janela.getContentPane().add(labelLogo);
		labelLogo.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
		
		JPanel painelVotarNivel = new JPanel();
		painelVotarNivel.setBounds(116, 92, 434, 91);
		janela.getContentPane().add(painelVotarNivel);
		painelVotarNivel.setLayout(null);
		
		JLabel labelInformaVotar = new JLabel("");
		labelInformaVotar.setHorizontalAlignment(SwingConstants.CENTER);
		labelInformaVotar.setBounds(0, 0, 434, 43);
		painelVotarNivel.add(labelInformaVotar);
		labelInformaVotar.setIcon(new ImageIcon(getClass().getResource("/AguardeSuaVez.png")));
		
		JComboBox<String> selecaoNivel = new JComboBox<String>();
		selecaoNivel.setBounds(168, 54, 93, 20);
		painelVotarNivel.add(selecaoNivel);
		selecaoNivel.addItem("Nivel facil");
		selecaoNivel.addItem("Nivel médio");
		selecaoNivel.addItem("Nivel dificil");
		((JLabel)selecaoNivel.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		selecaoNivel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					int nivel = 0;
					String event = e.getSource().toString();
					if (event.contains("Nivel facil")) {
						nivel = 0;
					} else if (event.contains("Nivel médio")) {
						nivel = 1;
					} else if (event.contains("Nivel dificil")) {
						nivel = 2;
					}
					System.out.println("Nivel = "+nivel);
			}
		});
	}
}
