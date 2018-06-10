import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import aplicacao.Level;
import factorys.LevelFactory;
import modelo.Dado;

public class TesteLevel {
	
	Level levelFacil;
	Level levelMedio;
	Level levelDificil;
	
	@Before
	public void inicializaLevels() {
		LevelFactory levelFactory = new LevelFactory(0);
		levelFacil = levelFactory.getLevel();
		levelFactory = new LevelFactory(1);
		levelMedio = levelFactory.getLevel();
		levelFactory = new LevelFactory(2);
		levelDificil = levelFactory.getLevel();	
	}
	
	@Test
	public void testaRolls() {
		int[] dados = levelFacil.rollDadosLivres().clone();
		assertEquals(6, dados.length);
		for (int i = 0; i < dados.length; i++)
			assertTrue( (dados[i] > 0) && (dados[i] <= 6));
		
		dados = levelMedio.rollDadosLivres().clone();
		assertEquals(5, dados.length);
		for (int i = 0; i < dados.length; i++)
			assertTrue( (dados[i] > 0) && (dados[i] <= 6));
		
		dados = levelDificil.rollDadosLivres().clone();
		assertEquals(5, dados.length);
		for (int i = 0; i < dados.length; i++)
			assertTrue( (dados[i] > 0) && (dados[i] <= 6));
	}
	
	@Test 
	public void testaSelecao() {
		Dado[] dadosFacil, dadosMedio, dadosDificil;
		
 		for (int i = 0; i < levelFacil.getDados().length; i++) {
 			levelFacil.selecionarDado(i);
 			if (i < levelMedio.getDados().length) {
 				levelMedio.selecionarDado(i);
 				levelDificil.selecionarDado(i);
 			}
 		}
 		
 		dadosFacil = levelFacil.getDados().clone();
 		dadosMedio = levelMedio.getDados().clone();
 		dadosDificil = levelDificil.getDados().clone();
 		
 		for (int i = 0; i < dadosFacil.length; i++) {
 			assertEquals(true, dadosFacil[i].isSelecionado());
 			if (i < dadosMedio.length) {
 				assertTrue(dadosMedio[i].isSelecionado());
 				assertTrue(dadosDificil[i].isSelecionado());
 			}
 		}
	}
	
	@Test
	public void testaTesteFarkled() {
		int valores[] = {2,4,2,6,3,2};
		assertFalse(levelFacil.verificaFarkled(valores));
		assertFalse(levelMedio.verificaFarkled(valores));
		assertFalse(levelDificil.verificaFarkled(valores));
		
		valores = new int[]{2,3,4};
		
		assertTrue(levelFacil.verificaFarkled(valores));
		assertTrue(levelMedio.verificaFarkled(valores));
		assertTrue(levelDificil.verificaFarkled(valores));
	}

}
