package factorys;
import aplicacao.Level;
import strategies.LevelEasy;
import strategies.LevelHard;
import strategies.LevelNormal;

public class LevelFactory {
	
	Level level;
	
	public LevelFactory(int nivel) {
		level = null;
		switch(nivel) {
		case 0:
			level = new LevelEasy();
			break;
		case 1:
			level = new LevelNormal();
			break;
		case 2:
			level = new LevelHard();
			break;
		}
	}
	
	public Level getLevel() {
		return level;
	}
}