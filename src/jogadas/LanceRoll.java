package jogadas;
import modelo.Dado;

@SuppressWarnings("serial")
public class LanceRoll extends Lance {

	protected Dado[] dados;
	protected int roundTotal;
	
	public LanceRoll() {}

	public void setDados(Dado[] dados) {
		this.dados = dados;
	}

	public Dado[] getDados() {
		return this.dados;
	}

	public int getRoundTotal() {
		return this.roundTotal;
	}

	public void setRoundTotal(int roundTotal) {
		this.roundTotal = roundTotal;
	}

}