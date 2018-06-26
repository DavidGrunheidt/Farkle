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

	protected static AtorJogador atorJogador;
	protected Proxy proxy;
	
	public AtorNetGames(AtorJogador atorJogador) {
		AtorNetGames.atorJogador = atorJogador;
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}

	public boolean conectar(String servidor, String nome) throws JahConectadoException, NaoPossivelConectarException, ArquivoMultiplayerException  {
		boolean conectar = true;
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			conectar = false;
			throw e;
		} catch (NaoPossivelConectarException e) {
			conectar = false;
			throw e;
		} catch (ArquivoMultiplayerException e) {
			conectar = false;
			throw e;
		}
		return conectar;
	}

	public void desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getJanela(), e.getMessage());
			e.printStackTrace();
		}
	}

	public void iniciarPartida(int numJogadores) throws NaoConectadoException {
			proxy.iniciarPartida(numJogadores);
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
	
	public String getMeuNome() {
		return proxy.getNomeJogador();
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

	@Override
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(null, "Partida cancelada pois um jogador abandonou ou houve ganhador!", "Alerta!!", JOptionPane.ERROR_MESSAGE);
		atorJogador.voltarInterfaceConectado();
	}

}