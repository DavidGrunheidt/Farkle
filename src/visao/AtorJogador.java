package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
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
	protected JButton[] dadosLivres;
	protected JLabel labelAjuda, labelLogo, labelInformaVotar, labelPlayerDaVez;
	protected JLabel labelNumDadosLivres, labelNumDadosSetAside, labelPontRoundDaVez;
	protected JLabel[] labelJogadores, labelPontuacoes, dadosSetAside;

	public AtorJogador() {
		controle = new Controle(this);
		setarJanela();
		setarLabelLogo();
	}

	public void atualizarInterfaceGrafica(int farkledType) {
		controle.mudouVez();
		this.resetarTodosDados(controle.getNumDados());

		labelPlayerDaVez.setText(controle.getNomeDaVez());
		labelPontRoundDaVez.setText("0");

		if (controle.verificarSeMinhaVez()) {
			this.habilitarBotaoBank();
			this.habilitarBotaoRoll();
		} else {
			if (controle.verificaSeFoiUltimo()) {
				this.desabilitarBotaoBank();
				this.desabilitarBotaoRoll();
			}
		}
		if (farkledType == -1) {
			JOptionPane.showMessageDialog(null, "Ultimo jogador deu farkled e perdeu a vez", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
		} else if (farkledType == -2) {
			JOptionPane.showMessageDialog(null, "Ultimo jogador deu ThreeFarkled, perdeu pontuacao e a vez","Atenção!!", JOptionPane.ERROR_MESSAGE);
		}

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
			((JLabel) caixaSelecao.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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
							JOptionPane.showMessageDialog(null,"Ocorreu algum erro de conexão e você\nnão está mais conectado para realizar esta ação","Alerta!!", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		} else {
			JOptionPane.showMessageDialog(null, "É preciso estar conectado para realizar esta ação", "Alerta!!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void clickMostrarAjuda() {
		this.mostrarPopUpJanelaAjuda();
	}

	public void selecionarNivel(int nivel) {
		controle.nivelSelecionado(nivel);
	}

	public void clickRoll() {
		if (this.setAside(true)) {
			int[] valores = controle.clickRoll().clone();
			if (valores[0] < 0) {
				this.atualizarInterfaceGrafica(valores[0]);
				if (valores[0] == -2) {
					this.atualizarPontuacaoTabelaJogadores(controle.getMeuID(), controle.getGrandTotal());
				}
			} else {
				trocarDados(valores);
			}
		}
	}

	public void atualizarRoundTotal(int roundTotal) {
		this.labelPontRoundDaVez.setText("" + roundTotal);
	}

	public void resetDadosLivres(int numDados) {
		for (int i = 0; i < numDados; i++) {
			if (dadosLivres[i].getMouseListeners().length != 0)
				dadosLivres[i].removeMouseListener(dadosLivres[i].getMouseListeners()[0]);
			dadosLivres[i].setSelected(false);
		}
	}

	public void selecionarDado(int idDado) {
		controle.selecionarDado(idDado);
	}

	public void trocarDados(int[] valores) {
		this.resetDadosLivres(controle.getNumDados());
		for (int i = 0; i < valores.length; i++)
			this.colocarDadoNaAreaRoll(i, valores[i]);
		for (int i = 0; i < controle.getNumDados() - valores.length; i++)
			this.retirarDaAreaRoll(valores.length + i);
		this.atualizarRoundTotal(controle.getRoundTotal());
	}

	public void clickBank() {
		if (this.setAside(false)) {
			int deuBank = controle.clickBank();
			if ((deuBank == 1) || (deuBank == 2)) {
				this.atualizarPontuacaoTabelaJogadores(controle.getMeuID(), controle.getGrandTotal());
				if (deuBank == 1) {
					this.atualizarInterfaceGrafica(0);
				} else {
					JOptionPane.showMessageDialog(null, "Parabéns, você ganhou!", "Alerta!!", JOptionPane.ERROR_MESSAGE);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					this.partidaFinalizada();
					this.desabilitarInterfaceGraficaPartidaEmAndamento();
					this.voltarInterfaceConectado();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Pontuacao minima não atingida para bank", "Alerta!!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void clickAbandonarJogo() {
		controle.finalizarPartida();
	}

	public void clickSairJogo() {
		boolean confirmou = false;
		/////////////////
		if (confirmou)
			controle.clickSairJogo();
	}

	public void habilitarInterfaceGraficaConectado() {
		setarBotaoNovoJogo();
		if (!painelBotoes.isVisible())
			painelBotoes.setVisible(true);
	}

	public void desabilitarInterfaceGraficaNotConectado() {
		botaoConectar.setVisible(false);
	}

	public void mostrarPopUpJanelaAjuda() {
		janelaAjuda = new JFrame();
		janelaAjuda.setResizable(false);
		janelaAjuda.setBounds(100, 100, 400, 400);
		janelaAjuda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaAjuda.getContentPane().setLayout(null);
		janelaAjuda.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/ajudaBackground.png"))));
		janelaAjuda.setVisible(true);
		janelaAjuda.setResizable(false);
	}

	public void desabilitarInterfaceGraficaPartidaEmAndamento() {
		painelPontuacoes.removeAll();
		painelPontuacoes.setVisible(false);
		painelJogadorDaVez.removeAll();
		painelJogadorDaVez.setVisible(false);
		painelOpcoesDurantePartida.removeAll();
		painelOpcoesDurantePartida.setVisible(false);
		painelDosDados.removeAll();
		painelDosDados.setVisible(false);
		janela.remove(painelPontuacoes);
		janela.remove(painelJogadorDaVez);
		janela.remove(painelOpcoesDurantePartida);
		janela.remove(painelDosDados);
	}

	public void desabilitarBotaoBank() {
		botaoBank.setVisible(false);
	}

	public void desabilitarInterfaceGraficaConectado() {
		painelBotoes.setVisible(false);
	}

	public void habilitarBotaoRoll() {
		botaoRoll.setVisible(true);
	}

	public void habilitarBotaoBank() {
		botaoBank.setVisible(true);
	}

	public void desabilitarBotaoRoll() {
		botaoRoll.setVisible(false);
	}

	public void habilitarInterfaceGraficaEsperandoAllVotos() {
		setarPainelVotarNivel();
		setarLabelInformaVotar();
		painelVotarNivel.setVisible(true);
	}

	public void atualizarDevidoRecebimento(Lance jogada) {
		int tipoLance = controle.atualizarDevidoRecebimento(jogada);
		switch (tipoLance) {
		case 0:
			boolean todosVotaram = controle.verificaTodosVotaram();
			boolean minhaVez = controle.verificarSeMinhaVez();
			if (todosVotaram) {
				this.comecarPartida();
			} else if (!todosVotaram && minhaVez) {
				this.votarNivel();
			}
			break;
		case 1:
			Dado[] dados = ((LanceRoll) jogada).getDados().clone();
			int roundTotal = ((LanceRoll) jogada).getRoundTotal();
			int valor;
			boolean setAside;

			this.colocarSelecionadosNaAreaSetAside(controle.getNumDados());

			this.resetDadosLivres(dados.length);
			int count = 0;
			for (int i = 0; i < dados.length; i++) {
				if (!dados[i].isSetAside())
					count++;
			}
			int numDados = controle.getNumDados();
			if(count == numDados)
				this.resetarTodosDados(numDados);
			
			for (int i = 0; i < dados.length; i++) {
				valor = dados[i].getValor();
				setAside = dados[i].isSetAside();
				if (setAside) {
					this.retirarDaAreaRoll(i);
				} else {
					this.colocarDadoNaAreaRollDesabilitada(i, valor);
				}
			}
			this.atualizarRoundTotal(roundTotal);
			break;
		case 2:
			int idDadoSelect = ((LanceDadoSelecionado) jogada).getIdDado();
			int valorDado = controle.getValorDado(idDadoSelect);
			this.inverterSelecaoDeDado(idDadoSelect, valorDado, controle.verificaSelecionado(idDadoSelect));
			break;
		case 3:
			int idPlayerDaVez = ((LanceRoundFinalizado) jogada).getIdPlayerDaVez();
			int roundTotalLast = ((LanceRoundFinalizado) jogada).getRoundTotalDoUltimoDaVez();
			int farkledType = ((LanceRoundFinalizado) jogada).getFarkledType();
			int ultimoPlayerDaVez = idPlayerDaVez - 1;
			if (ultimoPlayerDaVez == -1)
				ultimoPlayerDaVez = controle.getNumJogadores() - 1;
			this.atualizarGrandTotal(ultimoPlayerDaVez, roundTotalLast);
			this.atualizarInterfaceGrafica(farkledType);
			break;
		case 4:
			String name = ((LanceFinal) jogada).getWinnerName();
			int points = ((LanceFinal) jogada).getPoints();
			this.mostrarVencedor(name, points);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.desabilitarInterfaceGraficaPartidaEmAndamento();
			this.voltarInterfaceConectado();
			break;
		}
	}

	public void desabilitarInterfaceGraficaEsperandoAllVotos() {
		painelVotarNivel.setVisible(false);
		painelVotarNivel.removeAll();
		janela.remove(painelVotarNivel);
	}

	public void habilitarInterfaceGraficaPartidaEmAndamento(String nomeDaVez, String[] listaJogadores, int numDados) {
		this.setarPainelJogadorDaVez(nomeDaVez);
		this.setarPainelOpcoesDurantePartida();
		this.setarPainelPontuacoes(listaJogadores);
		this.setarPainelDosDados(numDados);
	}

	public void mostrarVencedor(String nome, int pontos) {
		JOptionPane.showMessageDialog(null, "Jogador = "+nome+" venceu com "+pontos+" pontos", "Partida Encerrada!!", JOptionPane.ERROR_MESSAGE);
	}

	public void atualizarPontuacaoTabelaJogadores(int jogador, int novaPont) {
		labelPontuacoes[jogador].setText("" + novaPont);
	}

	public void colocarSelecionadosNaAreaSetAside(int numDados) {
		int count = 0;
		JButton[] dadosLivresClone = dadosLivres.clone();
		for (int i = 0; i < dadosLivresClone.length; i++) {
			if (dadosLivresClone[i].isSelected()) {
				for (int j = 0; j < dadosSetAside.length; j++) {
					if (dadosSetAside[j].getIcon() == null) {
						dadosSetAside[j].setIcon(dadosLivresClone[i].getIcon());
						break;
					}
				}
				count++;
			}
		}

		int numDadosLivres, numDadosSetAside;
		numDadosLivres = Integer.parseInt(labelNumDadosLivres.getText()) - count;
		numDadosSetAside = Integer.parseInt(labelNumDadosSetAside.getText()) + count;

		if (numDadosLivres == 0) {
			numDadosLivres = numDados;
			numDadosSetAside = 0;
		}
		labelNumDadosLivres.setText("" + numDadosLivres);
		labelNumDadosSetAside.setText("" + numDadosSetAside);

	}

	public void resetarTodosDados(int numDados) {
		for (int i = 0; i < numDados; i++) {
			if (dadosLivres[i].getMouseListeners().length != 0)
				dadosLivres[i].removeMouseListener(dadosLivres[i].getMouseListeners()[0]);
			dadosLivres[i].setSelected(false);
			dadosLivres[i].setIcon(null);
			dadosLivres[i].setVisible(true);
			dadosSetAside[i].setIcon(null);
		}
		labelNumDadosLivres.setText("" + numDados);
		labelNumDadosSetAside.setText("0");
	}

	public void informarDadosNaoValidos() {
		JOptionPane.showMessageDialog(null, "Dados selecionados não resultam em pontuacao!", "Alerta!!",
				JOptionPane.ERROR_MESSAGE);
	}

	public boolean setAside(boolean veioDoRoll) {
		boolean resp = true;
		int continuar = controle.setAside(veioDoRoll);
		switch (continuar) {
		case 0:
			this.informarDadosNaoValidos();
			resp = false;
			break;
		case 1:
			this.colocarSelecionadosNaAreaSetAside(controle.getNumDados());
			break;
		case 2:
			this.resetarTodosDados(controle.getNumDados());
			break;
		}
		return resp;
	}

	public void habilitarInterfaceGraficaNotConectado() {
		setarPainelBotoes();
		setarBotaoConectar();
		setarBotaoSair();
		setarBotaoAjuda();

		janela.validate();
		janela.repaint();
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
		((JLabel) selecaoNivel.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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
				if (controle.verificaTodosVotaram())
					comecarPartida();
			}
		});
	}

	public void comecarPartida() {
		String nomeDaVez = controle.comecarPartida();
		this.desabilitarInterfaceGraficaEsperandoAllVotos();
		this.habilitarInterfaceGraficaPartidaEmAndamento(nomeDaVez, controle.getListaJogadores().clone(),
				controle.getNumDados());
		this.atualizarInterfaceGrafica(0);
	}

	public void colocarDadoNaAreaRoll(int index, int valor) {
		dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + ".png")));
		dadosLivres[index].addMouseListener(new MouseListener() {
			private boolean pressed = false;

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!pressed)
					dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + "Mouse.png")));
				else
					dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + "After.png")));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (!pressed)
					dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + ".png")));
				else
					dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + "Pressed.png")));

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				pressed = !pressed;
				if (pressed) {
					dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + "Pressed.png")));
				} else {
					dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + ".png")));
				}
				dadosLivres[index].setSelected(pressed);
				selecionarDado(index);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}

	public void retirarDaAreaRoll(int index) {
		dadosLivres[index].setVisible(false);
	}

	public void colocarDadoNaAreaRollDesabilitada(int index, int valor) {
		dadosLivres[index].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + ".png")));
	}

	public void inverterSelecaoDeDado(int idDado, int valor, boolean selecionado) {
		if (!selecionado) {
			dadosLivres[idDado].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + ".png")));
		} else {
			dadosLivres[idDado].setIcon(new ImageIcon(getClass().getResource("/dado" + valor + "Pressed.png")));
		}
		dadosLivres[idDado].setSelected(selecionado);
	}

	public JFrame getJanela() {
		return janela;
	}

	public void atualizarGrandTotal(int idUltimoDaVez, int roundTotal) {
		int grandTotal = Integer.parseInt(labelPontuacoes[idUltimoDaVez].getText());
		grandTotal += roundTotal;
		labelPontuacoes[idUltimoDaVez].setText(grandTotal + "");
	}

	public void receberSolicitacaoInicio(int posicao) {
		controle.prepararParaVotos(posicao - 1);
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
		botaoConectar.addMouseListener(new MouseListener() {
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
	}

	public void setarBotaoSair() {
		botaoSair = new JButton("");
		botaoSair.setBounds(22, 78, 205, 48);
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controle.clickSairJogo();
				janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
			}
		});
		painelBotoes.add(botaoSair);
		botaoSair.setOpaque(false);
		botaoSair.setContentAreaFilled(false);
		botaoSair.setBorderPainted(false);
		botaoSair.setIcon(new ImageIcon(getClass().getResource("/botaoSair.png")));
		botaoSair.addMouseListener(new MouseListener() {
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
	}

	public void setarBotaoAjuda() {
		botaoAjuda = new JButton("");
		botaoAjuda.setBounds(22, 146, 205, 48);
		botaoAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickMostrarAjuda();
			}
		});
		painelBotoes.add(botaoAjuda);
		botaoAjuda.setOpaque(false);
		botaoAjuda.setContentAreaFilled(false);
		botaoAjuda.setBorderPainted(false);
		botaoAjuda.setIcon(new ImageIcon(getClass().getResource("/botaoAjuda.png")));
		botaoAjuda.addMouseListener(new MouseListener() {
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
		botaoNovoJogo.addMouseListener(new MouseListener() {
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

	public void setarPainelJogadorDaVez(String nomeDaVez) {
		painelJogadorDaVez = new JPanel();
		painelJogadorDaVez.setBounds(157, 302, 311, 50);
		janela.getContentPane().add(painelJogadorDaVez);
		painelJogadorDaVez.setOpaque(false);
		painelJogadorDaVez.setLayout(null);

		labelPlayerDaVez = new JLabel(nomeDaVez, SwingConstants.CENTER);
		labelPlayerDaVez.setBounds(225, 0, 86, 26);
		labelPlayerDaVez.setFont(new Font("Arial Black", Font.PLAIN, 18));
		labelPlayerDaVez.setForeground(Color.white);
		painelJogadorDaVez.add(labelPlayerDaVez);

		labelPontRoundDaVez = new JLabel("0", SwingConstants.CENTER);
		labelPontRoundDaVez.setBounds(225, 24, 86, 26);
		labelPontRoundDaVez.setFont(new Font("Arial Black", Font.PLAIN, 18));
		painelJogadorDaVez.add(labelPontRoundDaVez);

		JLabel labelIconPlayerDaVez = new JLabel();
		labelIconPlayerDaVez.setBounds(0, 0, 223, 50);
		labelIconPlayerDaVez.setIcon(new ImageIcon(getClass().getResource("/jogadorDaVez.png")));
		painelJogadorDaVez.add(labelIconPlayerDaVez);

		JLabel backgroundPainelJogadorDaVez = new JLabel();
		backgroundPainelJogadorDaVez.setBounds(0, 0, 311, 50);
		backgroundPainelJogadorDaVez.setIcon(new ImageIcon(getClass().getResource("/backgroundPainelDaVez.png")));
		painelJogadorDaVez.add(backgroundPainelJogadorDaVez);
		janela.validate();
		janela.repaint();
	}

	public void setarPainelOpcoesDurantePartida() {
		painelOpcoesDurantePartida = new JPanel();
		painelOpcoesDurantePartida.setBounds(10, 135, 137, 217);
		janela.getContentPane().add(painelOpcoesDurantePartida);
		painelOpcoesDurantePartida.setOpaque(false);
		painelOpcoesDurantePartida.setLayout(null);

		botaoAjuda.setBounds(10, 63, 117, 30);
		painelOpcoesDurantePartida.add(botaoAjuda);
		botaoAjuda.setIcon(new ImageIcon(getClass().getResource("/ajuda2.png")));
		botaoAjuda.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				botaoAjuda.setIcon(new ImageIcon(getClass().getResource("/ajuda2Pressed.png")));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoAjuda.setIcon(new ImageIcon(getClass().getResource("/ajuda2.png")));
			}

			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

		setarBotaoAbandonarJogo();
		setarBotaoBank();
		setarBotaoRoll();

		JLabel backgroundPainelOpcoesPartida = new JLabel();
		backgroundPainelOpcoesPartida.setBounds(0, 0, 137, 217);
		painelOpcoesDurantePartida.add(backgroundPainelOpcoesPartida);
		backgroundPainelOpcoesPartida
				.setIcon(new ImageIcon(getClass().getResource("/backgroundPainelOpcoesPartida.png")));

		janela.validate();
		janela.repaint();
	}

	public void setarBotaoAbandonarJogo() {
		botaoAbandonarJogo = new JButton();
		botaoAbandonarJogo.setBounds(10, 11, 117, 30);
		painelOpcoesDurantePartida.add(botaoAbandonarJogo);
		botaoAbandonarJogo.setIcon(new ImageIcon(getClass().getResource("/abandonar.png")));
		botaoAbandonarJogo.setOpaque(false);
		botaoAbandonarJogo.setContentAreaFilled(false);
		botaoAbandonarJogo.setBorderPainted(false);
		botaoAbandonarJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickAbandonarJogo();
			}
		});
		botaoAbandonarJogo.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				botaoAbandonarJogo.setIcon(new ImageIcon(getClass().getResource("/AbandonarPressed.png")));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoAbandonarJogo.setIcon(new ImageIcon(getClass().getResource("/abandonar.png")));
			}

			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}

	public void setarBotaoBank() {
		botaoBank = new JButton();
		botaoBank.setBounds(10, 118, 117, 30);
		painelOpcoesDurantePartida.add(botaoBank);
		botaoBank.setIcon(new ImageIcon(getClass().getResource("/Bank.png")));
		botaoBank.setOpaque(false);
		botaoBank.setContentAreaFilled(false);
		botaoBank.setBorderPainted(false);
		botaoBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickBank();
			}
		});
		botaoBank.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				botaoBank.setIcon(new ImageIcon(getClass().getResource("/bankPressed.png")));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoBank.setIcon(new ImageIcon(getClass().getResource("/bank.png")));
			}

			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		botaoBank.setVisible(false);
	}

	public void setarBotaoRoll() {
		botaoRoll = new JButton();
		botaoRoll.setBounds(10, 171, 117, 30);
		painelOpcoesDurantePartida.add(botaoRoll);
		botaoRoll.setIcon(new ImageIcon(getClass().getResource("/roll.png")));
		botaoRoll.setOpaque(false);
		botaoRoll.setContentAreaFilled(false);
		botaoRoll.setBorderPainted(false);
		botaoRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickRoll();
			}
		});
		botaoRoll.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				botaoRoll.setIcon(new ImageIcon(getClass().getResource("/rollPressed.png")));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				botaoRoll.setIcon(new ImageIcon(getClass().getResource("/roll.png")));
			}

			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		botaoRoll.setVisible(false);
	}

	public void setarPainelPontuacoes(String[] listaJogadores) {
		painelPontuacoes = new JPanel();
		painelPontuacoes.setBounds(478, 10, 137, 342);
		janela.getContentPane().add(painelPontuacoes);
		painelPontuacoes.setOpaque(false);
		painelPontuacoes.setLayout(null);

		setarBackgroundsPontuacao(listaJogadores);
		janela.validate();
		janela.repaint();
	}

	public void setarBackgroundsPontuacao(String[] listaJogadores) {
		labelJogadores = new JLabel[listaJogadores.length];
		labelPontuacoes = new JLabel[listaJogadores.length];

		int altura = 306;
		for (int i = 0; i < listaJogadores.length; i++) {
			labelPontuacoes[i] = new JLabel("0");
			labelPontuacoes[i].setBounds(10, altura, 117, 25);
			labelPontuacoes[i].setIcon(new ImageIcon(getClass().getResource("/labelPontosJogador.png")));
			labelPontuacoes[i].setHorizontalTextPosition(JLabel.CENTER);
			labelPontuacoes[i].setVerticalTextPosition(JLabel.CENTER);
			painelPontuacoes.add(labelPontuacoes[i]);

			altura -= 26;

			labelJogadores[i] = new JLabel(listaJogadores[i]);
			labelJogadores[i].setBounds(10, altura, 117, 25);
			labelJogadores[i].setIcon(new ImageIcon(getClass().getResource("/labelNomeJogador.png")));
			labelJogadores[i].setHorizontalTextPosition(JLabel.CENTER);
			labelJogadores[i].setVerticalTextPosition(JLabel.CENTER);
			painelPontuacoes.add(labelJogadores[i]);

			altura -= 30;
		}
	}

	public void setarPainelDosDados(int numDados) {
		painelDosDados = new JPanel();
		painelDosDados.setBounds(157, 97, 311, 183);
		janela.getContentPane().add(painelDosDados);
		painelDosDados.setOpaque(false);
		painelDosDados.setLayout(null);

		labelNumDadosLivres = new JLabel("6", SwingConstants.CENTER);
		labelNumDadosLivres.setBounds(243, 0, 68, 30);
		labelNumDadosLivres.setForeground(Color.white);
		labelNumDadosLivres.setFont(new Font("Arial Black", Font.PLAIN, 18));
		painelDosDados.add(labelNumDadosLivres);

		labelNumDadosSetAside = new JLabel("0", SwingConstants.CENTER);
		labelNumDadosSetAside.setBounds(243, 94, 68, 30);
		labelNumDadosSetAside.setForeground(Color.white);
		labelNumDadosSetAside.setFont(new Font("Arial Black", Font.PLAIN, 18));
		painelDosDados.add(labelNumDadosSetAside);

		setarDadosLivre(numDados);

		setarDadosSetAside(numDados);

		JLabel backgroundPainelDosDdados = new JLabel();
		backgroundPainelDosDdados.setBounds(0, 0, 311, 183);
		backgroundPainelDosDdados.setIcon(new ImageIcon(getClass().getResource("/backgroundPainelDosDados.png")));
		painelDosDados.add(backgroundPainelDosDdados);

		janela.validate();
		janela.repaint();

	}

	public void setarDadosLivre(int numDados) {
		dadosLivres = new JButton[numDados];
		int posX = 0;
		for (int i = 0; i < numDados; i++) {
			dadosLivres[i] = new JButton();
			dadosLivres[i].setBounds(posX, 35, 51, 51);
			painelDosDados.add(dadosLivres[i]);
			dadosLivres[i].setOpaque(false);
			dadosLivres[i].setContentAreaFilled(false);
			dadosLivres[i].setBorderPainted(false);
			posX += 52;
		}
	}

	public void setarDadosSetAside(int numDados) {
		dadosSetAside = new JLabel[numDados];
		int posX = 0;
		for (int i = 0; i < numDados; i++) {
			dadosSetAside[i] = new JLabel();
			dadosSetAside[i].setBounds(posX, 132, 51, 51);
			painelDosDados.add(dadosSetAside[i]);
			posX += 52;
		}
	}
	
	public void voltarInterfaceConectado() {
		desabilitarInterfaceGraficaPartidaEmAndamento();
		habilitarInterfaceGraficaNotConectado();
		desabilitarInterfaceGraficaNotConectado();
		habilitarInterfaceGraficaConectado();
	}
	
}