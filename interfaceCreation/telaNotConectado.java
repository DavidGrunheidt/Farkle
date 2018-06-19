import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		JPanel painelNotConectado = new JPanel();
		painelNotConectado.setBounds(193, 112, 248, 210);
		janela.getContentPane().add(painelNotConectado);
		painelNotConectado.setOpaque(false);
		painelNotConectado.setLayout(null);
		
		JButton botaoConectar = new JButton("");
		botaoConectar.setBounds(28, 10, 205, 48);
		botaoConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
			}
		});
		painelNotConectado.add(botaoConectar);
		botaoConectar.setOpaque(false);
		botaoConectar.setContentAreaFilled(false);
		botaoConectar.setBorderPainted(false);
		botaoConectar.setIcon(new ImageIcon(getClass().getResource("/botaoConectar.png")));
		botaoConectar.addMouseListener(new MouseListener( ) {
			public void mouseClicked(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
				botaoConectar.setIcon(new ImageIcon(getClass().getResource("/botaoConectarMouseEntered.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoConectar.setIcon(new ImageIcon(getClass().getResource("/botaoConectar.png")));
			}
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		
		JButton botaoSair = new JButton("");
		botaoSair.setBounds(28, 78, 205, 48);
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
			}
		});
		painelNotConectado.add(botaoSair);
		botaoSair.setOpaque(false);
		botaoSair.setContentAreaFilled(false);
		botaoSair.setBorderPainted(false);
		botaoSair.setIcon(new ImageIcon(getClass().getResource("/botaoSair.png")));
		botaoSair.addMouseListener(new MouseListener( ) {
			public void mouseClicked(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
				botaoSair.setIcon(new ImageIcon(getClass().getResource("/botaoSairMouseEntered.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoSair.setIcon(new ImageIcon(getClass().getResource("/botaoSair.png")));
			}
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		
		
		JButton botaoAjuda = new JButton("");
		botaoAjuda.setBounds(28, 146, 205, 48);
		botaoAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
			}
		});
		painelNotConectado.add(botaoAjuda);
		botaoAjuda.setOpaque(false);
		botaoAjuda.setContentAreaFilled(false);
		botaoAjuda.setBorderPainted(false);
		botaoAjuda.setIcon(new ImageIcon(getClass().getResource("/botaoAjuda.png")));
		botaoAjuda.addMouseListener(new MouseListener( ) {
			public void mouseClicked(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
				botaoAjuda.setIcon(new ImageIcon(getClass().getResource("/botaoAjudaMouseEntered.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoAjuda.setIcon(new ImageIcon(getClass().getResource("/botaoAjuda.png")));
			}
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}
}
