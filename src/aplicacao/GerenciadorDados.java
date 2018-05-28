package aplicacao;

import java.util.LinkedList;
import java.util.Random;

import modelo.Dado;

public class GerenciadorDados {

	protected Dado[] dados;
	protected int numDadosSetAside;
	protected int numDadosLivres;
	
	public GerenciadorDados(int numDados) {
		dados = new Dado[numDados];
		numDadosSetAside = 0;
		numDadosLivres = numDados;
	}

	public void setAsideSelecionados() {
		for (int i = 0; i < dados.length; i++) {
			if (dados[i].isSelecionado()) {
				dados[i].trocaSetAside();
				dados[i].trocaSelecionado();
			}
		}
	}

	public void selecionarDado(int idDado) {
		for (int i = 0; i < dados.length; i++) {
			if (dados[i].getIdDado() == idDado)
				dados[i].trocaSelecionado();
		}
	}

	public int[] rollDadosLivres() {
		int[] valores = new int[numDadosLivres];
		for (int i = 0; i < dados.length; i++) {
			if (!dados[i].isSetAside()) {
				Random gerador = new Random();
				int valor = 1 + gerador.nextInt(6);
				dados[i].setValor(valor);
			}
		}
		return valores;
	}

	public int getNumDadosSetAside() {
		return this.numDadosSetAside;
	}

	public void incrementarNumDeSetAsides(int numDadosSelecionados) {
		// TODO - implement GerenciadorDados.incrementarNumDeSetAsides
		throw new UnsupportedOperationException();
	}

	public int getNumDadosLivres() {
		return this.numDadosLivres;
	}

	public void incrementarNumDeLivres() {
		// TODO - implement GerenciadorDados.incrementarNumDeLivres
		throw new UnsupportedOperationException();
	}

	public void liberarDados() {
		for (int i = 0; i < dados.length; i++)
			dados[i].liberarDado();
	}

	public Dado[] getDados() {
		return this.dados;
	}

	public int[] getValoresDadosSelecionados() {
		LinkedList<Integer> valoresAux = new LinkedList<Integer>();
		for (int i = 0; i < dados.length; i++) {
			if (dados[i].isSelecionado())
				valoresAux.add(dados[i].getValor());
		}
		
		int[] valores = new int[valoresAux.size()];
		for (int i = 0; i < valores.length; i++)
			valores[i] = valoresAux.get(i);
		
		return valores;
	}

	public int getValoresDosDadosLivres() {
		// TODO - implement GerenciadorDados.getValoresDosDadosLivres
		throw new UnsupportedOperationException();
	}

	public boolean verificaSeHotDice() {
		return this.numDadosLivres == 0;
	}

}