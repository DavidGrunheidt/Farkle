package modelo;
public class Jogador {

	protected String nome;
	protected int grandTotal;
	protected int contFarkled;
	protected int id;
	protected int roundTotal;
	
	public Jogador(int id, String nome) {
		this.nome = nome;
		grandTotal = 0;
		contFarkled = 0;
		this.id = id;
		roundTotal = 0;
	}

	public int getGrandTotal() {
		return this.grandTotal;
	}

	public int bank() {
		grandTotal +=  roundTotal;
		roundTotal = 0;
		return grandTotal;
	}

	public int descontarThreeFarkled(int custoThreeFarkled) {
		// TODO - implement Jogador.descontarThreeFarkled
		throw new UnsupportedOperationException();
	}

	public boolean contabilizarFarkled() {
		boolean threeFarkled = false;
		if (++contFarkled == 3) {
			threeFarkled = true;
			contFarkled = 0;
		}
		return threeFarkled;
	}

	public int getRoundTotal() {
		return this.roundTotal;
	}

	public void atualizarRoundTotal(int roundTotal) {
		// TODO - implement Jogador.atualizarRoundTotal
		throw new UnsupportedOperationException();
	}

	public String getNome() {
		return this.nome;
	}
	
	public int getId() {
		return this.id;
	}

}