import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aplicacao.Level;
import factorys.LevelFactory;
public class TestLevelCreation {
	
	private LevelFactory levelFactory;
	
	@Test
	public void testaCriacaoLevelFacil () {
		levelFactory = new LevelFactory(0);
		Level level = levelFactory.getLevel();
		assertEquals(-300, level.getDescontoThreeFarkled());
		assertEquals(200, level.getMinimoBank());
		assertEquals(6, level.getDados().length);
	}
	
	@Test
	public void testaCriacaoLevelMedio() {
		levelFactory = new LevelFactory(1);
		Level level = levelFactory.getLevel();
		assertEquals(-400, level.getDescontoThreeFarkled());
		assertEquals(250, level.getMinimoBank());
		assertEquals(5, level.getDados().length);
	}
	
	@Test
	public void testaCriacaoLevelDificil() {
		levelFactory = new LevelFactory(2);
		Level level = levelFactory.getLevel();
		assertEquals(-500, level.getDescontoThreeFarkled());
		assertEquals(300, level.getMinimoBank());
		assertEquals(5, level.getDados().length);
	}
	
}
