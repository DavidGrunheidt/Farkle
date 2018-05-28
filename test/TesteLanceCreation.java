import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import factorys.LanceFactory;
import jogadas.Lance;

public class TesteLanceCreation {
	
	private static LanceFactory lanceFactory;
	
	@BeforeClass
	public static void iniciarTestes() {
		lanceFactory = new LanceFactory(3);
	}
	
	@Test
	public void testeCriacaoLanceVotarNivel() {
		Lance lance = lanceFactory.criarLance(0, 2);
		assertEquals(0, lance.getIdPlayerDaVez());
	}
	
	@Test
	public void testeCriacaoLanceRoll() {
		Lance lance = lanceFactory.criarLance(1, 2);
		assertEquals(2, lance.getIdPlayerDaVez());
	}
	
	@Test
	public void testeCriacaoLanceDadoSelecionado() {
		Lance lance = lanceFactory.criarLance(2, 2);
		assertEquals(2, lance.getIdPlayerDaVez());
	}
	
	@Test
	public void testeCriacaoLanceRoundFinalizado() {
		Lance lance = lanceFactory.criarLance(3, 0);
		assertEquals(1, lance.getIdPlayerDaVez());
	}
}
