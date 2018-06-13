package modelo;
import br.ufsc.inf.leobr.cliente.Jogada;

@SuppressWarnings("serial")
public class Dado implements Jogada {

	protected int valor;
	protected boolean setAside;
	protected int idDado;
	protected boolean selecionado;
	
	public Dado(int idDado) {
		valor = 0;
		setAside = false;
		selecionado = false;
		this.idDado = idDado;
	}

	public boolean isSetAside() {
		return this.setAside;
	}

	public void trocaSetAside() {
		setAside = !setAside;
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isSelecionado() {
		return this.selecionado;
	}

	public void trocaSelecionado() {
		selecionado = !selecionado;
	}

	public void liberarDado() {
		if (setAside) 
			setAside = false;
		valor = 0;
	}

	public int getIdDado() {
		return idDado;
	}

	public void setIdDado(int id) {
		this.idDado = id;
	}

}