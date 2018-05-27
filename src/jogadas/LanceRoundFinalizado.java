package jogadas;
import abstracts.Lance;

@SuppressWarnings("serial")
public class LanceRoundFinalizado extends Lance {

	protected int roundTotalDoUltimoPlayerDaVez;
	protected int farkledType;

	public void setRoundTotalDoUltimoDaVez(int roundTotal) {
		// TODO - implement LanceRoundFinalizado.setRoundTotalDoUltimoDaVez
		throw new UnsupportedOperationException();
	}

	public int getRoundTotalDoUltimoDaVez() {
		// TODO - implement LanceRoundFinalizado.getRoundTotalDoUltimoDaVez
		throw new UnsupportedOperationException();
	}

	public int getFarkledType() {
		return this.farkledType;
	}

	public void setFarkledType(int farkledType) {
		this.farkledType = farkledType;
	}

}