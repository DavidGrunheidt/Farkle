package visao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import aplicacao.Controle;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import jogadas.Lance;
import jogadas.LanceDadoSelecionado;
import jogadas.LanceFinal;
import jogadas.LanceRoll;
import jogadas.LanceRoundFinalizado;
import jogadas.LanceVotarNivel;
import modelo.Dado;

public class AtorJogador {

	protected Controle controle;
	protected static AtorJogador instanciaUnica;
	
	protected JFrame janela, janelaAjuda;
	protected JPanel painelAjuda, painelBotoes;
	protected JPanel painelVotarNivel, painelPontuacoes, painelJogadorDaVez;
	protected JPanel painelOpcoesDurantePartida, painelDosDados;
	protected JTextField nome, quantJog;
	protected JButton botaoAjuda, botaoConectar, botaoSair, botaoCriarNovoJogo;
	protected JButton botaoDesconectar, botaoVotar, botaoRoll, botaoBank, botaoAbandonarJogo;
	protected LinkedList<JButton>dadosLivresPlayerDavez;
	protected JLabel labelAjuda, labelLogo;
	protected LinkedList<JLabel> dadosLivresPlayerEsperando, dadosSetAside, labelJogadores, labelPontuacoes;
	
	public AtorJogador() {
		controle = new Controle(this);
		setarJanela();
		setarLabelLogo();
		setarPainelBotoes();
		setarBotaoConectar();
		setarBotaoSair();
		setarBotaoAjuda();
	}

	public void atualizarInterfaceGrafica(int farkledType) {
		// TODO - implement AtorJogador.atualizarInterfaceGrafica
		throw new UnsupportedOperationException();
	}

	public void clickConectar() {
		String nome = null, servidor = null;
		////////////////////////
		boolean conectar = controle.clickConectar(nome, servidor);
		
		if (conectar) {
			this.desabilitarInterfaceGraficaNotConectado();
			this.habilitarInterfaceGraficaConectado();
		} else {
			////////////////
		}
	}

	public void clickIniciarNovoJogo() {
		boolean conectado = controle.verificaSeConectado();
		if (conectado) {
			int numJogadores = 0;
			//// Label pra selecionar num de jogadores////
			//////////////////////
			try {
				controle.iniciarPartida(numJogadores);
			} catch (NaoConectadoException e) {
				/// Error Nao Conectado /// 
			}
		} else {
			//////////////////
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
		botaoCriarNovoJogo.setVisible(true);
		botaoDesconectar.setVisible(true);
	}

	public void desabilitarInterfaceGraficaNotConectado() {
		botaoConectar.setVisible(false);
		botaoSair.setVisible(false);
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
		// TODO - implement AtorJogador.desabilitarInterfaceGraficaConectado
		throw new UnsupportedOperationException();
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

	public int habilitarInterfaceGraficaRealizarVotacao() {
		// TODO - implement AtorJogador.habilitarInterfaceGraficaRealizarVotacao
		throw new UnsupportedOperationException();
	}

	public void desabilitarBotaoRoll() {
		// TODO - implement AtorJogador.desabilitarBotaoRoll
		throw new UnsupportedOperationException();
	}

	public void habilitarInterfaceGraficaEsperandoAllVotos() {
		// TODO - implement AtorJogador.habilitarInterfaceGraficaEsperandoAllVotos
		throw new UnsupportedOperationException();
	}

	public void atualizarDevidoRecebimento(Lance jogada) {
		int tipoLance = controle.atualizarDevidoRecebimento(jogada);
		switch(tipoLance) {
		case 0:
			int levelVoted = ((LanceVotarNivel)jogada).getLevelVoted();
			this.atualizarVotos(levelVoted);
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

	public void atualizarVotos(int levelVoted) {
		// TODO - implement AtorJogador.atualizarVotos
		throw new UnsupportedOperationException();
	}

	public void votarNivel() {
		int nivel = this.habilitarInterfaceGraficaRealizarVotacao();
		controle.nivelSelecionado(nivel);
		this.desabilitarInterfaceRealizarVotacao();
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
			this.votarNivel();
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
		labelLogo = new JLabel("");
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

}