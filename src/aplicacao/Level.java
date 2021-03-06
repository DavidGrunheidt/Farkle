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
		int[] valoresAux = sortValores(valores).clone();
		int[] contValues = new int[6];
		
		
		for (int i = 0; i < valoresAux.length; i++)
			contValues[valoresAux[i]-1]++;
		
		if ((contValues[0] >= 1) || (contValues[4] >= 1) || (contValues[1] >= 3) || (contValues[2] >= 3) || (contValues[3] >= 3) || (contValues[5] >= 3))
			farkled = false;
		
		return farkled;
	}

	public int[] rollDadosLivres() {
		int [] valores = gerenciadorDeDados.rollDadosLivres();
		gerenciadorDeDados.alinharDados();
		return valores;
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


	public int[] sortValores(int[] valores) {
        for (int i = 0; i < valores.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < valores.length; j++)
                if (valores[j] < valores[index]) 
                    index = j;
      
            int smallerNumber = valores[index];  
            valores[index] = valores[i];
            valores[i] = smallerNumber;
        }
        return valores;
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

	public int pontuarRound(boolean veioDoRoll, int roundTotal) {
		int valores[] = gerenciadorDeDados.getValoresDadosSelecionados();
		valores = sortValores(valores);
		int pontuacao = procurarPontuacao(valores);
		
		if ((pontuacao > 0) && (!veioDoRoll)) {
			if ((pontuacao + roundTotal) < minimoParaBank)
				pontuacao = 0;
		} else if ((pontuacao > 0) && (veioDoRoll)) {
			gerenciadorDeDados.incrementarNumDeSetAsides(valores.length);
		}
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
	
	public int getValorDado(int idDado) {
		return gerenciadorDeDados.getDados()[idDado].getValor();
	}
	
	public void atualizarDados(Dado[] dados) {
		gerenciadorDeDados.setDados(dados);
	}
	
	public boolean verificaSelecionado(int idDado) {
		return gerenciadorDeDados.getDados()[idDado].isSelecionado();
	}
	
	public int[] getValoresLivres() {
		int[] valoresLivres = new int[gerenciadorDeDados.getNumDadosLivres()];
		Dado[] dados = gerenciadorDeDados.getDados().clone();
		int count = 0;
		for(int i = 0; i < dados.length; i++) {
			if (!dados[i].isSetAside())
				valoresLivres[count++] = dados[i].getValor();
		}
		return valoresLivres;
	}

}