package aplicacao;
import java.util.HashMap;

import modelo.Dado;

public abstract class Level {

	protected GerenciadorDados gerenciadorDeDados;
	protected int maxPont;
	protected int descontoThreeFarkled;
	protected int minimoParaBank;
	protected HashMap<String, Integer> pontuacoes;

	public boolean verificaFarkled(int[] valores) {
		boolean farkled = true;
		int[] valoresAux = quickSortValores(valores).clone();
		String keyAux = "";
		
		for (int i = 0; i < valoresAux.length; i++)
			keyAux = keyAux.concat(""+valoresAux[i]);
		
		if (pontuacoes.get(keyAux) != null)
			farkled = false;
		
		return farkled;
	}

	public int[] rollDadosLivres() {
		return gerenciadorDeDados.rollDadosLivres();
	}


	public void selecionarDado(int idDado) {
		gerenciadorDeDados.selecionarDado(idDado);
	}

	public int getDescontoThreeFarkled() {
		return this.descontoThreeFarkled;
	}
	
	public int getMinimoBank() {
		return this.minimoParaBank;
	}

	public void liberarDados() {
		gerenciadorDeDados.liberarDados();
	}


	public int[] quickSortValores(int[] valores) {
		// TODO - implement Level.quickSortValores
		throw new UnsupportedOperationException();
	}

	
	public int procurarPontuacao(int[] valores) {
		String key = "";
		for (int i = 0; i < valores.length; i++)
			key = key.concat(""+valores[i]);
		int pontuacao;
		try {
			pontuacao = pontuacoes.get(key);
		} catch(NullPointerException e) {
			pontuacao = 0;
		}
		return pontuacao;
	}

	public int pontuarRound() {
		int valores[] = gerenciadorDeDados.getValoresDadosSelecionados();
		valores = quickSortValores(valores);
		int pontuacao = procurarPontuacao(valores);
		if (pontuacao > 0)
			gerenciadorDeDados.incrementarNumDeSetAsides(valores.length);
		return pontuacao;
	}

	public Dado[] getDados() {
		return this.gerenciadorDeDados.getDados();
	}

	public boolean verificaSeHotDice() {
		return gerenciadorDeDados.verificaSeHotDice();
	}

	public void setAsideSelecionados() {
		gerenciadorDeDados.setAsideSelecionados();
	}

	public boolean verfSeTemPontMinima(int roundTotal) {
		return roundTotal >= minimoParaBank;
	}


	public boolean verificaPartidaTemVencedor(int grandTotal) {
		return grandTotal >= maxPont;
	}
	
	public HashMap<String, Integer> getPontuacoes() {
		return this.pontuacoes;
	}

}