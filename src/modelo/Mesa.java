package modelo;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import aplicacao.Level;
import factorys.LevelFactory;

public class Mesa {

	private HashMap<Integer, Jogador> listaJogadores;
	protected Level level;
	protected int contVotos;
	protected int levelVotos;
	
	public Mesa(List<String> listaJogadores) {
		this.listaJogadores = new HashMap<Integer, Jogador>();
		contVotos = 0;
		levelVotos = 0;
		for (int i = 0; i < listaJogadores.size(); i++) {
			Jogador jogadorAux = new Jogador(i ,listaJogadores.get(i));
			this.listaJogadores.put(i, jogadorAux);
		}
	}

	public void contabilizarVotoNivel(int nivel) {
		levelVotos += nivel;
		contVotos++;
	}

	public int numJogadores() {
		return listaJogadores.size()+1;
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

	public void comecarPartida() {
		int nivel = (int) levelVotos/contVotos;
		LevelFactory levelFactory = new LevelFactory(nivel);
		level = levelFactory.getLevel();
	}

	public void atualizarGrandTotalDeUmPlayer(int roundTotal, int idPlayer) {
		Jogador jogador = listaJogadores.get(idPlayer);
		jogador.atualizarRoundTotal(roundTotal);
		listaJogadores.replace(idPlayer, jogador);
	}

	public int[] daVezDeuRoll() {
		return  level.rollDadosLivres();
	}

	public void liberarDados() {
		level.liberarDados();
	}
	
	public void selecionarDado(int idDado) {
		level.selecionarDado(idDado);
	}

	public int setAside(int meuID) {
		int pontuacao = level.pontuarRound();
		if (pontuacao > 0) {
			Jogador jogador = listaJogadores.get(meuID);
			jogador.atualizarRoundTotal(pontuacao);
			listaJogadores.replace(meuID, jogador);
			if (level.verificaSeHotDice()) {
				level.liberarDados();
				return 2;
			} else {
				level.setAsideSelecionados();
				return 1;
			}
		} else {
			return 0;
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

}