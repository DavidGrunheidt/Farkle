package jogadas;

@SuppressWarnings("serial")
public class LanceRoundFinalizado extends Lance {

	protected int roundTotalDoUltimoPlayerDaVez;
	protected int farkledType;
	
	public LanceRoundFinalizado() {}

	public void setRoundTotalDoUltimoDaVez(int roundTotal) {
		this.roundTotalDoUltimoPlayerDaVez = roundTotal;
	}

	public int getRoundTotalDoUltimoDaVez() {
		return this.roundTotalDoUltimoPlayerDaVez;
	}

	public int getFarkledType() {
		return this.farkledType;
	}

	public void setFarkledType(int farkledType) {
		this.farkledType = farkledType;
	}

}