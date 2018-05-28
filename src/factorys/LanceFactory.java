package factorys;
import jogadas.Lance;
import jogadas.LanceDadoSelecionado;
import jogadas.LanceFinal;
import jogadas.LanceRoll;
import jogadas.LanceRoundFinalizado;
import jogadas.LanceVotarNivel;

public class LanceFactory {

	protected int numJogDaPartida;

	public LanceFactory(int numJogDaPartida) {
		this.numJogDaPartida = numJogDaPartida;
	}
	
	public Lance criarLance(int tipo, int meuID) {
		Lance lance = null;
		switch(tipo) {
		case 0:
			lance = new LanceVotarNivel();
			lance.setIdPlayerDaVez((meuID + 1) % numJogDaPartida);
			break;
		case 1:
			lance = new LanceRoll();
			lance.setIdPlayerDaVez(meuID);
			break;
		case 2:
			lance = new LanceDadoSelecionado();
			lance.setIdPlayerDaVez(meuID);
			break;
		case 3:
			lance = new LanceRoundFinalizado();
			lance.setIdPlayerDaVez(meuID+1 % numJogDaPartida);
			break;
		case 4:
			lance = new LanceFinal();
			break;
		}
		lance.setTipoLance(tipo);
		return lance;
	}

}