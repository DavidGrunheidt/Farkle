package modelo;
import br.ufsc.inf.leobr.cliente.Jogada;

@SuppressWarnings("serial")
public class Dado implements Jogada {

	protected int valor;
	protected boolean setAside;
	protected int idDado;
	protected boolean selecionado;

	public boolean isSetAside() {
		return this.setAside;
	}

	public void trocaSetAside() {
		// TODO - implement Dado.trocaSetAside
		throw new UnsupportedOperationException();
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public void jogarDado(boolean somenteSeDadoLivre) {
		// TODO - implement Dado.jogarDado
		throw new UnsupportedOperationException();
	}

	public boolean isSelecionado() {
		return this.selecionado;
	}

	public void trocaSelecionado() {
		// TODO - implement Dado.trocaSelecionado
		throw new UnsupportedOperationException();
	}

	public void liberarDado() {
		// TODO - implement Dado.liberarDado
		throw new UnsupportedOperationException();
	}

	public int getId() {
		// TODO - implement Dado.getId
		throw new UnsupportedOperationException();
	}

	public void setId(int id) {
		// TODO - implement Dado.setId
		throw new UnsupportedOperationException();
	}

}