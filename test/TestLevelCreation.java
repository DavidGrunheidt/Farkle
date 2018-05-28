import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aplicacao.Level;
import factorys.LevelFactory;
public class TestLevelCreation {
	
	private LevelFactory levelFactory;
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testaCriacaoLevelFacil () {
		levelFactory = new LevelFactory(0);
		Level level = levelFactory.getLevel();
		assertEquals(500, level.getDescontoThreeFarkled());
		assertEquals(200, level.getMinimoBank());
		assertEquals(6, level.getDados().length);
		assertEquals(16, level.getPontuacoes().size());
		assertTrue(level.getPontuacoes().get(""+1) == 100);
	}
	
	@Test
	public void testaCriacaoLevelMedio() {
		levelFactory = new LevelFactory(1);
		Level level = levelFactory.getLevel();
		assertEquals(750, level.getDescontoThreeFarkled());
		assertEquals(250, level.getMinimoBank());
		assertEquals(5, level.getDados().length);
		assertEquals(14, level.getPontuacoes().size());
		assertTrue(level.getPontuacoes().get(""+1) == 100);
	}
	
	@Test
	public void testaCriacaoLevelDificil() {
		levelFactory = new LevelFactory(2);
		Level level = levelFactory.getLevel();
		assertEquals(1000, level.getDescontoThreeFarkled());
		assertEquals(300, level.getMinimoBank());
		assertEquals(5, level.getDados().length);
		assertEquals(14, level.getPontuacoes().size());
		assertTrue(level.getPontuacoes().get(""+1) == 100);
	}
	
}
