package jogadas;
import abstracts.Lance;
import modelo.Dado;

@SuppressWarnings("serial")
public class LanceRoll extends Lance {

	protected Dado[] dados;
	protected int roundTotal;

	public void setDados(Dado[] dados) {
		this.dados = dados;
	}

	public Dado[] getDados() {
		return this.dados;
	}

	public int getRoundTotal() {
		return this.roundTotal;
	}

	public void setRoundTotal(int numDadosSetAside) {
		this.roundTotal = numDadosSetAside;
	}

}