package jogadas;

@SuppressWarnings("serial")
public class LanceDadoSelecionado extends Lance {

	protected int idDado;
	
	public LanceDadoSelecionado() {}

	public void setIdDado(int idDado) {
		this.idDado = idDado;
	}

	public int getIdDado() {
		return this.idDado;
	}

}