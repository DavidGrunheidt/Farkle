package jogadas;

@SuppressWarnings("serial")
public class LanceVotarNivel extends Lance {

	protected int levelVoted;
	
	public LanceVotarNivel() {}

	public int getLevelVoted() {
		return this.levelVoted;
	}

	public void setLevelVoted(int nivel) {
		this.levelVoted = nivel;
	}

}