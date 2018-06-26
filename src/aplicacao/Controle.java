package aplicacao;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import factorys.LanceFactory;
import modelo.Dado;
import modelo.Jogador;
import modelo.Mesa;
import rede.AtorNetGames;
import visao.AtorJogador;
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
	protected LanceFactory lanceFactory;
	
	public Controle(AtorJogador atorJogador) {
		rede = new AtorNetGames(atorJogador);
	}

	public void nivelSelecionado(int nivel) {
		mesaJogo.contabilizarVotoNivel(nivel);
		Lance lance = lanceFactory.criarLance(0, meuID);
		((LanceVotarNivel)lance).setLevelVoted(nivel);
		idPlayerDaVez = (meuID + 1) % mesaJogo.numJogadores();
		rede.enviarJogada(lance);
	}

	public void clickSairJogo() {
		if (conectado) {
			rede.desconectar();
			conectado = false;
		}
	}

	public int[] clickRoll() {
		int[] valores = mesaJogo.daVezDeuRoll().clone();
		int farkledType = mesaJogo.VerificaFarkled(valores.clone(), meuID);
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
				
				if(mesaJogo.getGrandTotal(meuID) - roundTotal < 0)
					roundTotal = 0;
				
				
			((LanceRoundFinalizado)lance).setRoundTotalDoUltimoDaVez(roundTotal);
			((LanceRoundFinalizado)lance).setFarkledType(farkledType);
			idPlayerDaVez = lance.getIdPlayerDaVez();
			}
		}
		rede.enviarJogada(lance);
		
		return valores;
	}

	public boolean clickConectar(String nome, String servidor) throws JahConectadoException, NaoPossivelConectarException, ArquivoMultiplayerException {
		meuNome = nome;
		conectado = rede.conectar(servidor, nome);
		return conectado;
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
				idPlayerDaVez = lance.getIdPlayerDaVez();
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
		mesaJogo.liberarDados(false);
	}

	public String comecarPartida() {
		return mesaJogo.comecarPartida(idPlayerDaVez);
	}

	public boolean verificaSeConectado() {
		return this.conectado;
	}

	public void iniciarPartida(int numJogadores) throws NaoConectadoException {
		rede.iniciarPartida(numJogadores);
	}

	public void prepararParaVotos(int meuID) {
		mesaJogo = new Mesa(rede.getListaOrdenadaJogadores(), rede.getMeuNome(), meuID);
		lanceFactory = new LanceFactory(mesaJogo.numJogadores());
		this.meuID = meuID;
		idPlayerDaVez = 0;
	}

	public boolean verificarSeMinhaVez() {
		return idPlayerDaVez == meuID;
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
		
		switch(tipoLance) {
		case 0:
			int nivel = ((LanceVotarNivel)jogada).getLevelVoted();
			mesaJogo.contabilizarVotoNivel(nivel);
			break;
		case 1:
			Dado[] dados = ((LanceRoll)jogada).getDados().clone();
			mesaJogo.atualizarDados(dados);
			break;
		case 2:
			int idDado = ((LanceDadoSelecionado)jogada).getIdDado();
			mesaJogo.selecionarDado(idDado);
			break;
		case 3:
			int roundTotal = ((LanceRoundFinalizado)jogada).getRoundTotalDoUltimoDaVez();
			mesaJogo.atualizarGrandTotalDeUmPlayer(roundTotal, ultimoPlayerDaVez);
			mesaJogo.liberarDados(this.verificarSeMinhaVez());
			break;
		}
		return tipoLance;
	}

	public boolean verificaTodosVotaram() {
		return mesaJogo.verificaTodosVotaram();
	}

	public void mudouVez() {
		mesaJogo.liberarDados((idPlayerDaVez == meuID));
	}

	public boolean verificaSeFoiUltimo() {
		int ultimoPlayerDaVez = idPlayerDaVez-1;
		if (ultimoPlayerDaVez == -1)
			ultimoPlayerDaVez = mesaJogo.numJogadores()-1;
		return meuID == ultimoPlayerDaVez;
	}

	public int setAside(boolean veioDoRoll) {
		return mesaJogo.setAside(meuID, veioDoRoll);
	}

	public int getGrandTotal() {
		return mesaJogo.getGrandTotal(meuID);
	}

	public void finalizarPartida() {
		rede.finalizarPartida();
	}
	
	public int getMeuID() {
		return meuID;
	}
	
	public String[] getListaJogadores() {
		return mesaJogo.pegarNomeJogadores();
	}
	
	public int getNumDados() {
		return mesaJogo.getNumDados();
	}
	
	public int getValorDado(int idDado) {
		return mesaJogo.getValorDado(idDado);
	}
			
	public boolean verificaSelecionado(int idDado) {
		return mesaJogo.verificaSelecionado(idDado);
	}
	
	public int getNumJogadores() {
		return mesaJogo.numJogadores();
	}
	
	public int getRoundTotal() {
		return mesaJogo.getRoundTotal(meuID);
	}
	
	public String getNomeDaVez() {
		return mesaJogo.getJogador(idPlayerDaVez).getNome();
	}
	
	public int[] getValoresLivres() {
		return mesaJogo.getValoresLivres();
	}

}