package modelo;
public class Jogador {

	protected String nome;
	protected int grandTotal = 0;
	protected int contFarkled;
	protected int id;
	protected boolean minhaVez;
	protected int pontuacaoRound;

	public int getGrandTotal() {
		return this.grandTotal;
	}

	public void bank() {
		// TODO - implement Jogador.bank
		throw new UnsupportedOperationException();
	}

	public int descontarThreeFarkled(int custoThreeFarkled) {
		// TODO - implement Jogador.descontarThreeFarkled
		throw new UnsupportedOperationException();
	}

	public boolean contabilizarFarkled() {
		// TODO - implement Jogador.contabilizarFarkled
		throw new UnsupportedOperationException();
	}

	public int getRoundTotal() {
		// TODO - implement Jogador.getRoundTotal
		throw new UnsupportedOperationException();
	}

	public void atualizarRoundTotal(int roundTotal) {
		// TODO - implement Jogador.atualizarRoundTotal
		throw new UnsupportedOperationException();
	}

	public String getNome() {
		return this.nome;
	}

}