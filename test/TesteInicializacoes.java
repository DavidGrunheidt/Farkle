import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modelo.Mesa;

public class TesteInicializacoes {
	
	Mesa mesaJogo;
	
	@Before
	public void inicializaMesa() {
		ArrayList<String> listaJogadores = new ArrayList<String>();
		listaJogadores.add("David");
		listaJogadores.add("Joao");
		listaJogadores.add("Luiz");
		mesaJogo = new Mesa((List<String>)listaJogadores);
	}

	@Test
	public void testeIniciarNovoJogo() {
		for (int i = 0; i < mesaJogo.numJogadores(); i++) {
			assertEquals(0, mesaJogo.getRoundTotal(i));
			assertEquals(0, mesaJogo.getGrandTotal(i));
		}
		assertEquals("David", mesaJogo.getJogador(0).getNome());
		assertEquals("Joao", mesaJogo.getJogador(1).getNome());
		assertEquals("Luiz", mesaJogo.getJogador(2).getNome());
	}
	
	@Test
	public void testeComecarPartidaNivelFacil() {
		mesaJogo.contabilizarVotoNivel(2);
		mesaJogo.contabilizarVotoNivel(0);
		mesaJogo.contabilizarVotoNivel(0);
		mesaJogo.comecarPartida();
		assertEquals(-300, mesaJogo.getDescontoNoGrandTotal());
	}
	
	@Test
	public void testeComecarPartidaNivelMedio() {
		mesaJogo.contabilizarVotoNivel(2);
		mesaJogo.contabilizarVotoNivel(1);
		mesaJogo.contabilizarVotoNivel(0);
		mesaJogo.comecarPartida();
		assertEquals(-400, mesaJogo.getDescontoNoGrandTotal());
	}
	
	@Test
	public void testeComecarPartidaNivelDificil() {
		mesaJogo.contabilizarVotoNivel(2);
		mesaJogo.contabilizarVotoNivel(2);
		mesaJogo.contabilizarVotoNivel(2);
		mesaJogo.comecarPartida();
		assertEquals(-500, mesaJogo.getDescontoNoGrandTotal());
	}
}
