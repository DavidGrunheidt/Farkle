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

	public void descontarThreeFarkled(int custoThreeFarkled) {
		grandTotal += custoThreeFarkled;
		if (grandTotal < 0)
			grandTotal = 0;
	}

	public boolean contabilizarFarkled() {
		roundTotal = 0;
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
		this.roundTotal += roundTotal;
	}

	public String getNome() {
		return this.nome;
	}
	
	public int getId() {
		return this.id;
	}

}