package jogadas;
import br.ufsc.inf.leobr.cliente.Jogada;

@SuppressWarnings("serial")
public abstract class Lance implements Jogada {

	protected int tipoLance;
	protected int idPlayerDaVez;

	public void setIdPlayerDaVez(int idPlayerDaVez) {
		this.idPlayerDaVez = idPlayerDaVez;
	}

	public int getIdPlayerDaVez() {
		return this.idPlayerDaVez;
	}

	public void setTipoLance(int tipo) {
		this.tipoLance = tipo;
	}

	public int getTipoLance() {
		return this.tipoLance;
	}

}