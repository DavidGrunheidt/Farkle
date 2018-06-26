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
		for (int i = 0; i < numDados; i++)
			dados[i] = new Dado(i);
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
		dados[idDado].trocaSelecionado();
	}

	public int[] rollDadosLivres() {
		int[] valores = new int[numDadosLivres];
		Random gerador = new Random();
		int cont = 0;
		for (int i = 0; i < dados.length; i++) {
			if (!dados[i].isSetAside()) {
				int valor = 1 + gerador.nextInt(6);
				dados[i].setValor(valor);
				valores[cont] = valor;
				cont++;
			}
		}
		return valores;
	}

	public void incrementarNumDeSetAsides(int numDadosSelecionados) {
		numDadosSetAside += numDadosSelecionados;
		numDadosLivres -= numDadosSelecionados;
	}
	
	public int getNumDadosSetAside() {
		return this.numDadosSetAside;
	}

	public int getNumDadosLivres() {
		return this.numDadosLivres;
	}

	public void liberarDados() {
		numDadosLivres = dados.length;
		numDadosSetAside = 0;
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

	public boolean verificaSeHotDice() {
		return this.numDadosLivres == 0;
	}
	
	public void setDados(Dado[] dados) {
		int numDadosLivres = 0;
		int numDadosSetAside = 0;
		
		for (int i = 0; i < dados.length; i++) {
			if (dados[i].isSetAside()) {
				numDadosSetAside++;
			} else {
				numDadosLivres++;
			}
		}
		
		this.numDadosLivres = numDadosLivres;
		this.numDadosSetAside = numDadosSetAside;
		
		this.dados = dados;
	}
	
	public void alinharDados() {
		Dado[] dadosClone = dados.clone();
		
		int cont = 0;
		for (int i = 0; i < dados.length; i++) {
			if (!dadosClone[i].isSetAside())
				dados[cont++] = dadosClone[i];
		}
		for (int i = 0; i < dados.length; i++) {
			if (dadosClone[i].isSetAside())
				dados[cont++] = dadosClone[i];
		}
	}

}