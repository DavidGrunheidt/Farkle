package abstracts;
import java.util.HashMap;

import aplicacao.GerenciadorDados;
import modelo.Dado;

public abstract class Level {

	protected GerenciadorDados gerenciadorDeDados;
	protected int descontoThreeFarkled;
	protected int tipoLevel;
	protected int minimoParaBank;
	protected HashMap<String, Integer> todasCombParaPont;

	public boolean verificaFarkled(int valores) {
		// TODO - implement Level.verificaFarkled
		throw new UnsupportedOperationException();
	}

	public int rollDadosLivres() {
		// TODO - implement Level.rollDadosLivres
		throw new UnsupportedOperationException();
	}

	public boolean verificaSeValorValidoSegundoNivel() {
		// TODO - implement Level.verificaSeValorValidoSegundoNivel
		throw new UnsupportedOperationException();
	}


	public void selecionarDado(int idDado) {
		// TODO - implement Level.selecionarDado
		throw new UnsupportedOperationException();
	}

	public int getDescontoThreeFarkled() {
		return this.descontoThreeFarkled;
	}

	public int getTipoLevel() {
		return this.tipoLevel;
	}

	public void liberarDados() {
		// TODO - implement Level.liberarDados
		throw new UnsupportedOperationException();
	}


	public int quickSortValores(int valores) {
		// TODO - implement Level.quickSortValores
		throw new UnsupportedOperationException();
	}

	
	public int procurarPontuacao(int valores) {
		// TODO - implement Level.procurarPontuacao
		throw new UnsupportedOperationException();
	}

	public int pontuarRound() {
		// TODO - implement Level.pontuarRound
		throw new UnsupportedOperationException();
	}

	public Dado getDados() {
		// TODO - implement Level.getDados
		throw new UnsupportedOperationException();
	}

	public boolean verificaSeHotDice() {
		// TODO - implement Level.verificaSeHotDice
		throw new UnsupportedOperationException();
	}

	public void setAsideSelecionados() {
		// TODO - implement Level.setAsideSelecionados
		throw new UnsupportedOperationException();
	}

	public boolean verfSeTemPontMinima() {
		// TODO - implement Level.verfSeTemPontMinima
		throw new UnsupportedOperationException();
	}


	public boolean verificaPartidaTemVencedor(int grandTotal) {
		// TODO - implement Level.verificaPartidaTemVencedor
		throw new UnsupportedOperationException();
	}

}