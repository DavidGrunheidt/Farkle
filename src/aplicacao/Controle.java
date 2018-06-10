package aplicacao;

import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import factorys.LanceFactory;
import modelo.Dado;
import modelo.Jogador;
import modelo.Mesa;
import jogadas.LanceRoll;
import jogadas.LanceRoundFinalizado;
import jogadas.LanceFinal;
import jogadas.LanceVotarNivel;
import jogadas.Lance;
import jogadas.LanceDadoSelecionado;

public class Controle {

	protected AtorNetGames rede;
	protected Mesa mesaJogo;
	protected String meuNome;
	protected int meuID;
	protected int idPlayerDaVez;
	protected boolean conectado;
	protected static LanceFactory lanceFactory;
	
	public Controle() {
		
	}

	public void nivelSelecionado(int nivel) {
		mesaJogo.contabilizarVotoNivel(nivel);
		Lance lance = lanceFactory.criarLance(0, meuID);
		((LanceVotarNivel)lance).setLevelVoted(nivel);
		rede.enviarJogada(lance);
	}

	public void clickSairJogo() {
		// TODO - implement Controle.clickSairJogo
		throw new UnsupportedOperationException();
	}

	public int[] clickRoll() {
		int[] valores = mesaJogo.daVezDeuRoll().clone();
		int farkledType = mesaJogo.VerificaFarkled(valores, meuID);
		int tipo;
		
		if (farkledType == 0) {
			tipo = 1;
		} else {
			valores[0] = farkledType;
			tipo = 3;
		}
		
		Lance lance = lanceFactory.criarLance(tipo, meuID);
		int roundTotal = mesaJogo.getRoundTotal(meuID);
		
		if (farkledType == 0) {
			Dado[] dados = mesaJogo.getDados().clone();
			((LanceRoll)lance).setDados(dados);
			((LanceRoll)lance).setRoundTotal(roundTotal);
		} else {
			if (farkledType < 0) {
				if (farkledType == -2)
					roundTotal = mesaJogo.getDescontoNoGrandTotal();
				else
					roundTotal = 0;
			((LanceRoundFinalizado)lance).setRoundTotalDoUltimoDaVez(roundTotal);
			((LanceRoundFinalizado)lance).setFarkledType(farkledType);
			}
		}
		rede.enviarJogada(lance);
		return valores;
	}

	public boolean clickConectar(String nome, String servidor) {
		meuNome = nome;
		throw new UnsupportedOperationException();
	}

	public void desconectar() {
		// TODO - implement Controle.desconectar
		throw new UnsupportedOperationException();
	}

	public void clickAbandonarJogo() {
		// TODO - implement Controle.clickAbandonarJogo
		throw new UnsupportedOperationException();
	}

	public int clickBank() {
		int deuBank = 0;
		int roundTotal = mesaJogo.tentarBank(meuID);
		
		if (roundTotal != 0) {
			int tipo;
			if (roundTotal > 0)
				tipo = 3;
			else
				tipo = 4;
			Lance lance = lanceFactory.criarLance(tipo, meuID);
			
			if (roundTotal > 0) {
				deuBank = 1;
				((LanceRoundFinalizado)lance).setRoundTotalDoUltimoDaVez(roundTotal);
			} else {
				deuBank = 2;
				Jogador jogador = mesaJogo.getJogador(meuID);
				int grandTotal = jogador.getGrandTotal();
				String name = jogador.getNome();
				((LanceFinal)lance).setPoints(grandTotal);
				((LanceFinal)lance).setWinnerName(name);
			}
			rede.enviarJogada(lance);
		}	
		return deuBank;
	}

	public void mudaJogadorDaVez() {
		mesaJogo.liberarDados();
	}

	public void comecarPartida() {
		mesaJogo.comecarPartida();
	}

	public boolean verificaSeConectado() {
		return this.conectado;
	}

	public boolean iniciarPartida(int numJogadores) {
		boolean iniciouPartida = true;
		try {
			rede.iniciarPartida(numJogadores);
		} catch (NaoConectadoException e) {
			iniciouPartida = false;
			e.printStackTrace();
		}
		return iniciouPartida;
	}

	public void prepararParaVotos(int meuID) {
		mesaJogo = new Mesa(rede.getListaOrdenadaJogadores());
		this.meuID = meuID;
		idPlayerDaVez = 0;
	}

	public boolean verificarSeMinhaVez() {
		return idPlayerDaVez == meuID;
	}

	public void zeraPontuacaoRound() {
		// TODO - implement Controle.zeraPontuacaoRound
		throw new UnsupportedOperationException();
	}

	public void selecionarDado(int idDado) {
		mesaJogo.selecionarDado(idDado);
		Lance lance = lanceFactory.criarLance(2, meuID);
		((LanceDadoSelecionado)lance).setIdDado(idDado);
		rede.enviarJogada(lance);
	}

	public int atualizarDevidoRecebimento(Lance jogada) {
		int tipoLance = jogada.getTipoLance();
		idPlayerDaVez = jogada.getIdPlayerDaVez();
		
		int ultimoPlayerDaVez = idPlayerDaVez-1;
		if (ultimoPlayerDaVez == -1)
			ultimoPlayerDaVez = mesaJogo.numJogadores()-1;
		
		if (tipoLance == 0) {
			int nivel = ((LanceVotarNivel)jogada).getLevelVoted();
			mesaJogo.contabilizarVotoNivel(nivel);
		} else {
			if (tipoLance == 3) {
				int roundTotal = ((LanceRoundFinalizado)jogada).getRoundTotalDoUltimoDaVez();
				mesaJogo.atualizarGrandTotalDeUmPlayer(roundTotal, ultimoPlayerDaVez);
			}
		}
		return tipoLance;
	}

	public boolean verificaTodosVotaram() {
		return mesaJogo.verificaTodosVotaram();
	}

	public void mudouVez() {
		mesaJogo.liberarDados();
	}

	public boolean verificaSeFoiUltimo() {
		int ultimoPlayerDaVez = idPlayerDaVez-1;
		if (ultimoPlayerDaVez == -1)
			ultimoPlayerDaVez = mesaJogo.numJogadores()-1;
		return meuID == ultimoPlayerDaVez;
	}

	public int setAside(boolean veioDoRoll) {
		return mesaJogo.setAside(meuID);
	}

	public int getGrandTotal() {
		return mesaJogo.getGrandTotal(meuID);
	}

	public void finalizarPartida() {
		rede.finalizarPartida();
	}

}