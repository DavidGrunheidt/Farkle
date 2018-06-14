package rede;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import jogadas.Lance;
import visao.AtorJogador;

@SuppressWarnings("serial")
public class AtorNetGames implements OuvidorProxy {

	protected AtorJogador atorJogador;
	protected Proxy proxy;
	
	public AtorNetGames() {
		super();
		atorJogador = AtorJogador.getInstance();
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}

	public boolean conectar(String servidor, String nome) {
		boolean conectar = true;
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			conectar = false;
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			conectar = false;
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			conectar = false;
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		}
		return conectar;
	}

	public void desconectar(String message) {
		finalizarPartidaComErro(message);
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		}
	}

	public void iniciarPartida(int numJogadores) throws NaoConectadoException {
		try {
			proxy.iniciarPartida(numJogadores);
		} catch(NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		}
	}

	public void enviarJogada(Lance lance) {
		try {
			proxy.enviaJogada(lance);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		}
	}

	public List<String> getListaOrdenadaJogadores() {
		return proxy.obterNomeAdversarios();
	}

	public void finalizarPartida() {
		try {
			proxy.finalizarPartida();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		}
	}

	public void finalizarPartidaComErro(String message) {
		proxy.finalizarPartidaComErro(message);
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		atorJogador.receberSolicitacaoInicio(posicao);	
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void receberJogada(Jogada jogada) {
		atorJogador.atualizarDevidoRecebimento((Lance)jogada);
	}

	@Override
	public void tratarConexaoPerdida() {
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
	}

}