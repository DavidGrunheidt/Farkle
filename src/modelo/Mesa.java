package modelo;
import java.util.HashMap;
import java.util.List;

import aplicacao.Level;
import factorys.LevelFactory;

public class Mesa {

	private HashMap<Integer, Jogador> listaJogadores;
	protected Level level;
	protected int contVotos;
	protected int levelVotos;
	protected boolean firstRoll;
	
	public Mesa(List<String> listaJogadores, String meuNome, int meuID) {
		this.listaJogadores = new HashMap<Integer, Jogador>();
		contVotos = 0;
		levelVotos = 0;
		firstRoll = false;
		Jogador jogadorAux;
		for (int i = 0; i <= meuID; i++) {
			if (i != meuID) 
				jogadorAux = new Jogador(i ,listaJogadores.get(i));
			else
				jogadorAux = new Jogador(meuID , meuNome);
			this.listaJogadores.put(i, jogadorAux);	
		}
		for (int i = meuID+1; i < listaJogadores.size()+1; i++) {
			jogadorAux = new Jogador(i,listaJogadores.get(i-1));
			this.listaJogadores.put(i, jogadorAux);
		}
	}

	public void contabilizarVotoNivel(int nivel) {
		levelVotos += nivel;
		contVotos++;
	}

	public int numJogadores() {
		return listaJogadores.size();
	}
	
	public int VerificaFarkled(int[] valores, int meuID) {
		int farkledType = 0;
		if (level.verificaFarkled(valores)) {
			farkledType = -1;
			Jogador jogador = listaJogadores.get(meuID);
			boolean threeFarkled = jogador.contabilizarFarkled();
			if (threeFarkled) {
				jogador.descontarThreeFarkled(level.getDescontoThreeFarkled());
				farkledType = -2;
			}
			listaJogadores.replace(meuID, jogador);
		}
		return farkledType;
	}

	public String comecarPartida(int idPlayerDaVez) {
		int nivel = (int) levelVotos/contVotos;
		LevelFactory levelFactory = new LevelFactory(nivel);
		level = levelFactory.getLevel();
		return listaJogadores.get(idPlayerDaVez).getNome();
	}

	public void atualizarGrandTotalDeUmPlayer(int roundTotal, int idPlayer) {
		Jogador jogador = listaJogadores.get(idPlayer);
		jogador.atualizarRoundTotal(roundTotal);
		listaJogadores.replace(idPlayer, jogador);
	}

	public int[] daVezDeuRoll() {
		return  level.rollDadosLivres();
	}

	public void liberarDados(boolean minhaVez) {
		if (minhaVez)
			firstRoll = true;
		else
			firstRoll = false;
		level.liberarDados();
	}
	
	public void selecionarDado(int idDado) {
		level.selecionarDado(idDado);
	}

	public int setAside(int meuID,  boolean veioDoRoll) {
		if (firstRoll) {
			firstRoll = false;
			return -1;
		} else {
			int pontuacao = level.pontuarRound(veioDoRoll, listaJogadores.get(meuID).getRoundTotal());
			if (pontuacao > 0) {
				Jogador jogador = listaJogadores.get(meuID);
				jogador.atualizarRoundTotal(pontuacao);
				listaJogadores.replace(meuID, jogador);
				if (level.verificaSeHotDice()) {
					level.liberarDados();
					return 2;
				} else {
					level.setAsideSelecionados();
					if (((!firstRoll) && (!veioDoRoll)))
						return -1;
					else
						return 1;
				}
			} else {
				if (veioDoRoll)
					return 0;
				else
					return -1;
			}
		}
	}

	public Dado[] getDados() {
		return level.getDados();
	}

	public int getRoundTotal(int meuID) {
		return listaJogadores.get(meuID).getRoundTotal();
	}

	public int tentarBank(int meuID) {
		Jogador jogador = listaJogadores.get(meuID);
		int roundTotal = jogador.getRoundTotal();
		boolean temPontMinima = level.verfSeTemPontMinima(roundTotal);
		if (temPontMinima) {
			int grandTotal = jogador.bank();
			listaJogadores.replace(meuID, jogador);
			boolean ganhouPartida = level.verificaPartidaTemVencedor(grandTotal);
			if (ganhouPartida)
				return -1;
			else
				return roundTotal;
		} else {
			return 0;
		}
	}

	public Jogador getJogador(int meuID) {
		return listaJogadores.get(meuID);
	}

	public int getGrandTotal(int meuID) {
		return listaJogadores.get(meuID).getGrandTotal();
	}

	public int getDescontoNoGrandTotal() {
		return level.getDescontoThreeFarkled();
	}
	
	public boolean verificaTodosVotaram() {
		return contVotos == listaJogadores.size();
	}
	
	public String[] pegarNomeJogadores() {
		String[] nomes = new String[listaJogadores.size()];
		for (int i = 0; i < listaJogadores.size(); i++) {
			nomes[i] = listaJogadores.get(i).getNome();
		}
		return nomes;
	}
	
	public int getNumDados() {
		return level.getDados().length;
	}
	
	public int getValorDado(int idDado) {
		return level.getValorDado(idDado);
	}
	
	public void atualizarDados(Dado[] dados) {
		level.atualizarDados(dados);
	}
	
	public boolean verificaSelecionado(int idDado) {
		return level.verificaSelecionado(idDado);
	}
	
	public int[] getValoresLivres() {
		return level.getValoresLivres();
	}
}