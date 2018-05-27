package jogadas;
import abstracts.Lance;

@SuppressWarnings("serial")
public class LanceDadoSelecionado extends Lance {

	protected int idDado;

	public void setIdDado(int idDado) {
		this.idDado = idDado;
	}

	public int getIdDado() {
		return this.idDado;
	}

}