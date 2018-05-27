package modelo;
import java.util.HashMap;

import abstracts.Level;

public class Mesa {

	private HashMap<Integer, Jogador> listaJogadores;
	protected Level level;
	protected int contVotos;
	protected int levelVotos;

	public void contabilizarVotoNivel(int nivel) {
		// TODO - implement Mesa.contabilizarVotoNivel
		throw new UnsupportedOperationException();
	}

	public int numJogadores() {
		// TODO - implement Mesa.numJogadores
		throw new UnsupportedOperationException();
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int VerificaFarkled(int valores) {
		// TODO - implement Mesa.VerificaFarkled
		throw new UnsupportedOperationException();
	}

	public void zeraPontuacaoRound() {
		// TODO - implement Mesa.zeraPontuacaoRound
		throw new UnsupportedOperationException();
	}

	public int VerificaThreeFarkled(int idJogador) {
		// TODO - implement Mesa.VerificaThreeFarkled
		throw new UnsupportedOperationException();
	}

	public int definirNivel() {
		// TODO - implement Mesa.definirNivel
		throw new UnsupportedOperationException();
	}

	public void comecarPartida() {
		// TODO - implement Mesa.comecarPartida
		throw new UnsupportedOperationException();
	}

	public int atualizarGrandTotalDeUmPlayer(int roundTotal, int idPlayerDaVez) {
		// TODO - implement Mesa.atualizarGrandTotalDeUmPlayer
		throw new UnsupportedOperationException();
	}

	public int daVezDeuRoll() {
		// TODO - implement Mesa.daVezDeuRoll
		throw new UnsupportedOperationException();
	}

	public void liberarDados() {
		// TODO - implement Mesa.liberarDados
		throw new UnsupportedOperationException();
	}
	
	public void selecionarDado(int IdDado) {
		// TODO - implement Mesa.selecionarDado
		throw new UnsupportedOperationException();
	}

	public int setAside() {
		// TODO - implement Mesa.setAside
		throw new UnsupportedOperationException();
	}

	public Dado getDados() {
		// TODO - implement Mesa.getDados
		throw new UnsupportedOperationException();
	}

	public int getRoundTotal(int meuID) {
		// TODO - implement Mesa.getRoundTotal
		throw new UnsupportedOperationException();
	}

	public int tentarBank(int meuID) {
		// TODO - implement Mesa.tentarBank
		throw new UnsupportedOperationException();
	}

	public Jogador getJogador(int meuID) {
		// TODO - implement Mesa.getJogador
		throw new UnsupportedOperationException();
	}

	public int getPontos(int meuID) {
		// TODO - implement Mesa.getPontos
		throw new UnsupportedOperationException();
	}

	public int getDescontoNoGrandTotal() {
		// TODO - implement Mesa.getDescontoNoGrandTotal
		throw new UnsupportedOperationException();
	}

}