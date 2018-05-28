package jogadas;

@SuppressWarnings("serial")
public class LanceFinal extends Lance {

	protected String winnerName;
	protected int points;
	
	public LanceFinal() {}
	
	public void setWinnerName(String name) {
		this.winnerName = name;
	}

	public String getWinnerName() {
		return this.winnerName;
	}

	public void setPoints(int grandTotal) {
		this.points = grandTotal;
	}

	public int getPoints() {
		return this.points;
	}

}