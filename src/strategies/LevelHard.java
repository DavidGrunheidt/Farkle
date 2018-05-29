package strategies;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

import aplicacao.GerenciadorDados;
import aplicacao.Level;
import modelo.ValoresPontuacao;

public class LevelHard extends Level {
	
	public LevelHard() {
		maxPont = 7000;
		gerenciadorDeDados = new GerenciadorDados(5);
		descontoThreeFarkled = -500;
		minimoParaBank = 300;
		pontuacoes = new HashMap<String, Integer>();
		
		ArrayList<ValoresPontuacao> listaAux = new ArrayList<ValoresPontuacao>(EnumSet.allOf(ValoresPontuacao.class));
		for (int i = 0; i < listaAux.size(); i ++) {
			ValoresPontuacao valAux = listaAux.get(i);
			if (valAux.toString().length() < 12) {
				int pontuacao = valAux.getValorPontuacao();
				String keyAux = valAux.toString();
				String key = "";
				
				for (int j = 1; j < keyAux.length(); j += 2)
					key = key.concat(""+keyAux.charAt(j));
				
				pontuacoes.put(key, pontuacao);
			}
		}
	}
}