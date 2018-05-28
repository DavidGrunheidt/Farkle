import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aplicacao.Level;
import factorys.LevelFactory;
public class TestLevelCreation {
	
	private LevelFactory levelFactory;
	
	@Test
	public void testaCriacaoLevelFacil () {
		levelFactory = new LevelFactory(0);
		Level level = levelFactory.getLevel();
		assertEquals(-500, level.getDescontoThreeFarkled());
		assertEquals(200, level.getMinimoBank());
		assertEquals(6, level.getDados().length);
		assertEquals(16, level.getPontuacoes().size());
		assertTrue(level.getPontuacoes().get(""+1) == 100);
		assertTrue(level.getPontuacoes().get(""+5) == 50);
		assertTrue(level.getPontuacoes().get(""+11) == 200);
		assertTrue(level.getPontuacoes().get(""+5) == 50);
		assertTrue(level.getPontuacoes().get(""+55) == 100);
		assertTrue(level.getPontuacoes().get(""+222) == 200);
		assertTrue(level.getPontuacoes().get(""+333) == 300);
		assertTrue(level.getPontuacoes().get(""+222) == 200);
		assertTrue(level.getPontuacoes().get(""+444) == 400);
		assertTrue(level.getPontuacoes().get(""+555) == 500);
		assertTrue(level.getPontuacoes().get(""+666) == 600);
		assertTrue(level.getPontuacoes().get(""+111) == 1000);
		assertTrue(level.getPontuacoes().get(""+1111) == 1100);
		assertTrue(level.getPontuacoes().get(""+5555) == 550);
		assertTrue(level.getPontuacoes().get(""+11111) == 1200);
		assertTrue(level.getPontuacoes().get(""+55555) == 600);
		assertTrue(level.getPontuacoes().get(""+111111) == 2000);
		assertTrue(level.getPontuacoes().get(""+555555) == 1000);
	}
	
	@Test
	public void testaCriacaoLevelMedio() {
		levelFactory = new LevelFactory(1);
		Level level = levelFactory.getLevel();
		assertEquals(-750, level.getDescontoThreeFarkled());
		assertEquals(250, level.getMinimoBank());
		assertEquals(5, level.getDados().length);
		assertEquals(14, level.getPontuacoes().size());
		assertTrue(level.getPontuacoes().get(""+1) == 100);
		assertTrue(level.getPontuacoes().get(""+5) == 50);
		assertTrue(level.getPontuacoes().get(""+11) == 200);
		assertTrue(level.getPontuacoes().get(""+5) == 50);
		assertTrue(level.getPontuacoes().get(""+55) == 100);
		assertTrue(level.getPontuacoes().get(""+222) == 200);
		assertTrue(level.getPontuacoes().get(""+333) == 300);
		assertTrue(level.getPontuacoes().get(""+222) == 200);
		assertTrue(level.getPontuacoes().get(""+444) == 400);
		assertTrue(level.getPontuacoes().get(""+555) == 500);
		assertTrue(level.getPontuacoes().get(""+666) == 600);
		assertTrue(level.getPontuacoes().get(""+111) == 1000);
		assertTrue(level.getPontuacoes().get(""+1111) == 1100);
		assertTrue(level.getPontuacoes().get(""+5555) == 550);
		assertTrue(level.getPontuacoes().get(""+11111) == 1200);
		assertTrue(level.getPontuacoes().get(""+55555) == 600);
	}
	
	@Test
	public void testaCriacaoLevelDificil() {
		levelFactory = new LevelFactory(2);
		Level level = levelFactory.getLevel();
		assertEquals(-1000, level.getDescontoThreeFarkled());
		assertEquals(300, level.getMinimoBank());
		assertEquals(5, level.getDados().length);
		assertEquals(14, level.getPontuacoes().size());
		assertTrue(level.getPontuacoes().get(""+1) == 100);
		assertTrue(level.getPontuacoes().get(""+5) == 50);
		assertTrue(level.getPontuacoes().get(""+11) == 200);
		assertTrue(level.getPontuacoes().get(""+5) == 50);
		assertTrue(level.getPontuacoes().get(""+55) == 100);
		assertTrue(level.getPontuacoes().get(""+222) == 200);
		assertTrue(level.getPontuacoes().get(""+333) == 300);
		assertTrue(level.getPontuacoes().get(""+222) == 200);
		assertTrue(level.getPontuacoes().get(""+444) == 400);
		assertTrue(level.getPontuacoes().get(""+555) == 500);
		assertTrue(level.getPontuacoes().get(""+666) == 600);
		assertTrue(level.getPontuacoes().get(""+111) == 1000);
		assertTrue(level.getPontuacoes().get(""+1111) == 1100);
		assertTrue(level.getPontuacoes().get(""+5555) == 550);
		assertTrue(level.getPontuacoes().get(""+11111) == 1200);
		assertTrue(level.getPontuacoes().get(""+55555) == 600);
	}
	
}
