package visao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import aplicacao.Controle;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import jogadas.Lance;
import jogadas.LanceDadoSelecionado;
import jogadas.LanceFinal;
import jogadas.LanceRoll;
import jogadas.LanceRoundFinalizado;
import jogadas.LanceVotarNivel;
import modelo.Dado;

public class AtorJogador {

	protected Controle controle;
	
	protected JFrame janela, janelaAjuda;
	protected JPanel painelAjuda, painelBotoes;
	protected JPanel painelVotarNivel, painelPontuacoes, painelJogadorDaVez;
	protected JPanel painelOpcoesDurantePartida, painelDosDados;
	protected JTextField nome, quantJog;
	protected JButton botaoAjuda, botaoConectar, botaoSair, botaoNovoJogo;
	protected JButton botaoVotar, botaoRoll, botaoBank, botaoAbandonarJogo;
	protected LinkedList<JButton>dadosLivresPlayerDavez;
	protected JLabel labelAjuda, labelLogo, labelInformaVotar;
	protected LinkedList<JLabel> dadosLivresPlayerEsperando, dadosSetAside, labelJogadores, labelPontuacoes;
	
	public AtorJogador() {
		controle = new Controle(this);
		setarJanela();
		setarLabelLogo();
		setarPainelBotoes();
		setarBotaoConectar();
		setarBotaoSair();
		setarBotaoAjuda();
		setarBotaoNovoJogo();
		setarPainelVotarNivel();
		setarLabelInformaVotar();
	}

	public void atualizarInterfaceGrafica(int farkledType) {
		// TODO - implement AtorJogador.atualizarInterfaceGrafica
		throw new UnsupportedOperationException();
	}

	public void clickConectar() {
		String nome = JOptionPane.showInputDialog("Informe seu nome");
		String servidor = JOptionPane.showInputDialog("Informe o ip do servidor que deseja conectar");
		////////////////////////
		boolean conectar = false;
		try {
			conectar = controle.clickConectar(nome, servidor);
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(janela, e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(janela, e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(janela, e.getMessage());
			e.printStackTrace();
		}
		
		if (conectar) {
			this.desabilitarInterfaceGraficaNotConectado();
			this.habilitarInterfaceGraficaConectado();
		}
	}

	public void clickIniciarNovoJogo() {
		boolean conectado = controle.verificaSeConectado();
		if (conectado) {
			botaoNovoJogo.setVisible(false);
			JLabel titulo = new JLabel();
			painelBotoes.add(titulo);
			titulo.setBounds(22, 5, 205, 28);
			titulo.setIcon(new ImageIcon(getClass().getResource("/tituloInformeNumDeJog.png")));
			JComboBox<String> caixaSelecao = new JComboBox<String>();
			painelBotoes.add(caixaSelecao);
			caixaSelecao.setBounds(22, 35, 205, 28);
			caixaSelecao.addItem("2 Jogadores");
			caixaSelecao.addItem("3 Jogadores");
			caixaSelecao.addItem("4 Jogadores");
			((JLabel)caixaSelecao.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			caixaSelecao.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evnt) {
					String event = evnt.getSource().toString();
					int numJog = 0;
					if (event.contains("2 Jogadores")) {
						numJog = 2;
					} else if (event.contains("3 Jogadores")) {
						numJog = 3;
					} else if (event.contains("4 Jogadores")) {
						numJog = 4;
					}
					if (numJog >= 2) {
						try {
							controle.iniciarPartida(numJog);
						} catch (NaoConectadoException e) {
							JOptionPane.showMessageDialog(null, "Ocorreu algum erro de conexão e você\nnão está mais conectado para realizar esta ação", "Alerta!!", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		} else {
			JOptionPane.showMessageDialog(null, "É preciso estar conectado para realizar esta ação", "Alerta!!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void clickMostrarAjuda() {
		this.mostrarPopUpJanelaAjuda();
	}

	public void selecionarNivel(int nivel) {
		controle.nivelSelecionado(nivel);
		this.desabilitarInterfaceRealizarVotacao();
	}

	public void clickRoll() {
		if (this.setAside(true)) {
			int[] valores = controle.clickRoll().clone();
			if (valores[0] < 0)
				this.atualizarInterfaceGrafica(valores[0]);
		}
	}


	public void selecionarDado(int idDado) {
		controle.selecionarDado(idDado);
	}

	public void clickBank() {
		if (this.setAside(false)) {
			int deuBank = controle.clickBank();
			if ((deuBank == 1) || (deuBank == 2)) {
				this.atualizarPontuacaoTabelaJogadores(controle.getMeuID(), controle.getGrandTotal());
				if (deuBank == 1) {
					this.atualizarInterfaceGrafica(0);
				} else {
					/////////////////////////
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					this.partidaFinalizada();
					this.desabilitarInterfaceGraficaPartidaEmAndamento();
					this.desabilitarInterfaceGraficaConectado();
				}
			} else {
				///////////////////
			}	
		}
	}

	public void clickAbandonarJogo() {
		// TODO - implement AtorJogador.clickAbandonarJogo
		throw new UnsupportedOperationException();
	}

	public void clickSairJogo() {
		boolean confirmou = false;
		/////////////////
		if (confirmou)
			controle.clickSairJogo();
	}

	public void desabilitarInterfaceRealizarVotacao() {
		// TODO - implement AtorJogador.desabilitarInterfaceRealizarVotacao
		throw new UnsupportedOperationException();
	}

	public void habilitarInterfaceGraficaConectado() {
		botaoNovoJogo.setVisible(true);
		if (!painelBotoes.isVisible())
			painelBotoes.setVisible(true);
	}

	public void desabilitarInterfaceGraficaNotConectado() {
		botaoConectar.setVisible(false);
	}

	public void mostrarPopUpJanelaAjuda() {
		// TODO - implement AtorJogador.mostrarPopUpJanelaAjuda
		throw new UnsupportedOperationException();
	}

	public void exibirDadosPosRoll(int valores) {
		// TODO - implement AtorJogador.exibirDadosPosRoll
		throw new UnsupportedOperationException();
	}

	public void desabilitarInterfaceGraficaPartidaEmAndamento() {
		// TODO - implement AtorJogador.desabilitarInterfaceGraficaPartidaEmAndamento
		throw new UnsupportedOperationException();
	}

	public void desabilitarBotaoBank() {
		// TODO - implement AtorJogador.desabilitarBotaoBank
		throw new UnsupportedOperationException();
	}

	public void habilitarSelecaoDeDados() {
		// TODO - implement AtorJogador.habilitarSelecaoDeDados
		throw new UnsupportedOperationException();
	}

	public void desabilitarInterfaceGraficaConectado() {
		painelBotoes.setVisible(false);
	}

	public void habilitarBotaoRoll() {
		// TODO - implement AtorJogador.habilitarBotaoRoll
		throw new UnsupportedOperationException();
	}

	public void desabilitarSelecaoDeDados() {
		// TODO - implement AtorJogador.desabilitarSelecaoDeDados
		throw new UnsupportedOperationException();
	}

	public void habilitarBotaoBank() {
		// TODO - implement AtorJogador.habilitarBotaoBank
		throw new UnsupportedOperationException();
	}

	public void desabilitarBotaoRoll() {
		// TODO - implement AtorJogador.desabilitarBotaoRoll
		throw new UnsupportedOperationException();
	}

	public void habilitarInterfaceGraficaEsperandoAllVotos() {
		painelVotarNivel.setVisible(true);
	}

	public void atualizarDevidoRecebimento(Lance jogada) {
		int tipoLance = controle.atualizarDevidoRecebimento(jogada);
		switch(tipoLance) {
		case 0:
			boolean minhaVez = controle.verificarSeMinhaVez();
			if (minhaVez)
				this.votarNivel();
			boolean todosVotaram = controle.verificaTodosVotaram();
			if (todosVotaram)
				this.comecarPartida();
			break;
		case 1:
			Dado[] dados = ((LanceRoll)jogada).getDados().clone();
			int roundTotal = ((LanceRoll)jogada).getRoundTotal();
			int idDado, valor;
			boolean setAside;
			for (int i = 0; i < dados.length; i++) {
				idDado = dados[i].getIdDado();
				valor = dados[i].getValor();
				setAside = dados[i].isSetAside();
				if (setAside) {
					this.colocarDadoNaAreaLivres(idDado, valor);
				} else {
					this.colocarDadoNaAreaLivres(idDado, valor);
				}
			}
			int idDaVez = jogada.getIdPlayerDaVez();
			this.atualizarRoundTotal(idDaVez, roundTotal);
			break;
		case 2:
			int idDadoSelect = ((LanceDadoSelecionado)jogada).getIdDado();
			this.inverterSelecaoDeDado(idDadoSelect);
			break;
		case 3:
			int idUltimoDaVez = ((LanceRoundFinalizado)jogada).getIdPlayerDaVez();
			int roundTotalLast = ((LanceRoundFinalizado)jogada).getRoundTotalDoUltimoDaVez();
			int farkledType = ((LanceRoundFinalizado)jogada).getFarkledType();
			this.atualizarGrandTotal(idUltimoDaVez, roundTotalLast);
			this.atualizarInterfaceGrafica(farkledType);
			break;
		case 4:
			String name = ((LanceFinal)jogada).getWinnerName();
			int points = ((LanceFinal)jogada).getPoints();
			this.mostrarVencedor(name, points);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.desabilitarInterfaceGraficaPartidaEmAndamento();
			this.habilitarInterfaceGraficaConectado();
			break;
		}
	}

	public void desabilitarInterfaceGraficaEsperandoAllVotos() {
		// TODO - implement AtorJogador.desabilitarInterfaceGraficaEsperandoAllVotos
		throw new UnsupportedOperationException();
	}

	public void habilitarInterfaceGraficaPartidaEmAndamento() {
		// TODO - implement AtorJogador.habilitarInterfaceGraficaPartidaEmAndamento
		throw new UnsupportedOperationException();
	}

	public void mostrarVoto(int playerQueVotou, int levelVotado) {
		// TODO - implement AtorJogador.mostrarVoto
		throw new UnsupportedOperationException();
	}

	public void mostrarVencedor(String nome, int pontos) {
		// TODO - implement AtorJogador.mostrarVencedor
		throw new UnsupportedOperationException();
	}

	public void mostraMesagemFalhou(String msg) {
		// TODO - implement AtorJogador.mostraMesagemFalhou
		throw new UnsupportedOperationException();
	}

	public void atualizarPontuacaoTabelaJogadores(int jogador, int novaPont) {
		// TODO - implement AtorJogador.atualizarPontuacaoTabelaJogadores
		throw new UnsupportedOperationException();
	}

	public void colocarSelecionadosNaAreaSetAside() {
		// TODO - implement AtorJogador.colocarSelecionadosNaAreaSetAside
		throw new UnsupportedOperationException();
	}

	public void colocarSetAsidesNaAreaLivre() {
		// TODO - implement AtorJogador.colocarSetAsidesNaAreaLivre
		throw new UnsupportedOperationException();
	}

	public void informarDadosNaoValidos() {
		// TODO - implement AtorJogador.informarDadosNaoValidos
		throw new UnsupportedOperationException();
	}

	public boolean setAside(boolean veioDoRoll) {
		boolean resp = true;
		int continuar = controle.setAside(veioDoRoll);
		if(continuar == 0) {
			this.informarDadosNaoValidos();
			resp = false;
		} else {
			if ((continuar == 1) && (veioDoRoll)) {
				this.colocarSelecionadosNaAreaSetAside();
			} else {
				if ((continuar == 2) && (veioDoRoll))
					this.colocarSetAsidesNaAreaLivre();
			}
		}
		return resp;
	}

	public void habilitarInterfaceGraficaNotConectado() {
		botaoConectar.setVisible(true);
		botaoSair.setVisible(true);
		botaoAjuda.setVisible(true);
	}


	public void partidaFinalizada() {
		controle.finalizarPartida();
	}

	public void votarNivel() {
		labelInformaVotar.setIcon(new ImageIcon(getClass().getResource("/SuaVezDeVotar.png")));
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
					String event = e.getSource().toString();
					int nivelSelect;
					if (event.contains("Nivel facil")) {
						nivelSelect = 0;
					} else if (event.contains("Nivel médio")) {
						nivelSelect = 1;
					} else {
						nivelSelect = 2;
					}
					labelInformaVotar.setIcon(new ImageIcon(getClass().getResource("/AguardeTodos.png")));
					selecaoNivel.setVisible(false);
					controle.nivelSelecionado(nivelSelect);
			}
		});
	}

	public void comecarPartida() {
		controle.comecarPartida();
		this.desabilitarInterfaceGraficaEsperandoAllVotos();
		this.habilitarInterfaceGraficaPartidaEmAndamento();
		this.atualizarInterfaceGrafica(0);
	}

	public void colocarDadoNaAreaSetAside(int idDado, int valor) {
		// TODO - implement AtorJogador.colocarDadoNaAreaSetAside
		throw new UnsupportedOperationException();
	}

	public void colocarDadoNaAreaLivres(int idDado, int valro) {
		// TODO - implement AtorJogador.colocarDadoNaAreaLivres
		throw new UnsupportedOperationException();
	}

	public void inverterSelecaoDeDado(int idDado) {
		// TODO - implement AtorJogador.inverterSelecaoDeDado
		throw new UnsupportedOperationException();
	}

	public void atualizarRoundTotal(int idDaVez, int roundTotal) {
		// TODO - implement AtorJogador.atualizarRoundTotal
		throw new UnsupportedOperationException();
	}

	public JFrame getJanela() {
		return janela;
	}

	public void atualizarGrandTotal(int idUltimoDaVez, int roundTotal) {
		// TODO - implement AtorJogador.atualizarGrandTotal
		throw new UnsupportedOperationException();
	}
	
	public void receberSolicitacaoInicio(int posicao) {
		controle.prepararParaVotos(posicao-1);
		this.desabilitarInterfaceGraficaConectado();
		this.habilitarInterfaceGraficaEsperandoAllVotos();
		boolean minhaVez = controle.verificarSeMinhaVez();
		if (minhaVez)
			votarNivel();
	}
	
	public void setarJanela() {
		janela = new JFrame();
		janela.setResizable(false);
		janela.setBounds(100, 100, 641, 401);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.getContentPane().setLayout(null);
		janela.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/backgroundFarkle.png"))));
		janela.setVisible(true);
		janela.setResizable(false);
	}
	
	public void setarLabelLogo() {
		labelLogo = new JLabel();
		labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogo.setBounds(193, 10, 248, 79);
		janela.getContentPane().add(labelLogo);
		labelLogo.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
	}
	
	public void setarPainelBotoes() {
		painelBotoes = new JPanel();
		painelBotoes.setBounds(193, 112, 248, 210);
		janela.getContentPane().add(painelBotoes);
		painelBotoes.setOpaque(false);
		painelBotoes.setLayout(null);
	}
	
	public void setarBotaoConectar() {
		botaoConectar = new JButton("");
		botaoConectar.setBounds(22, 10, 205, 48);
		botaoConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickConectar();
			}
		});
		painelBotoes.add(botaoConectar);
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
		botaoConectar.setVisible(false);
	}
	
	public void setarBotaoSair() {
		botaoSair = new JButton("");
		botaoSair.setBounds(22, 78, 205, 48);
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
			}
		});
		painelBotoes.add(botaoSair);
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
		botaoSair.setVisible(false);
	}
	
	public void setarBotaoAjuda() {
		botaoAjuda = new JButton("");
		botaoAjuda.setBounds(22, 146, 205, 48);
		botaoAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// DISPLAY JANELA AJUDA
			}
		});
		painelBotoes.add(botaoAjuda);
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
		botaoAjuda.setVisible(false);
	}
	
	public void setarBotaoNovoJogo() {
		botaoNovoJogo = new JButton("");
		botaoNovoJogo.setBounds(22, 10, 205, 48);
		botaoNovoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickIniciarNovoJogo();
			}
		});
		painelBotoes.add(botaoNovoJogo);
		botaoNovoJogo.setOpaque(false);
		botaoNovoJogo.setContentAreaFilled(false);
		botaoNovoJogo.setBorderPainted(false);
		botaoNovoJogo.setIcon(new ImageIcon(getClass().getResource("/botaoNovoJogo.png")));
		botaoNovoJogo.addMouseListener(new MouseListener( ) {
			public void mouseClicked(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
				botaoNovoJogo.setIcon(new ImageIcon(getClass().getResource("/botaoNovoJogoMouseEntered.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoNovoJogo.setIcon(new ImageIcon(getClass().getResource("/botaoNovoJogo.png")));
			}
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		botaoNovoJogo.setVisible(false);
	}
	
	public void setarPainelVotarNivel() {
		painelVotarNivel = new JPanel();
		painelVotarNivel.setBounds(116, 92, 434, 91);
		janela.getContentPane().add(painelVotarNivel);
		painelVotarNivel.setOpaque(false);
		painelVotarNivel.setLayout(null);
		painelVotarNivel.setVisible(false);
	}
	
	public void setarLabelInformaVotar() {
		labelInformaVotar = new JLabel();
		labelInformaVotar.setHorizontalAlignment(SwingConstants.CENTER);
		labelInformaVotar.setBounds(0, 0, 434, 43);
		painelVotarNivel.add(labelInformaVotar);
		labelInformaVotar.setIcon(new ImageIcon(getClass().getResource("/AguardeSuaVez.png")));
	}

}