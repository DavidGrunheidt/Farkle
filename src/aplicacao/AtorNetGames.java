package aplicacao;
import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import jogadas.Lance;
import visao.AtorJogador;

@SuppressWarnings("serial")
public class AtorNetGames implements OuvidorProxy {

	protected AtorJogador atorJogador;
	protected Proxy proxy;

	public boolean conectar(String servidor, String nome) {
		// TODO - implement AtorNetGames.conectar
		throw new UnsupportedOperationException();
	}

	public boolean desconectar() {
		// TODO - implement AtorNetGames.desconectar
		throw new UnsupportedOperationException();
	}

	public void iniciarPartida(int numJogadores) throws NaoConectadoException {
		proxy.iniciarPartida(numJogadores);
	}

	public void enviarJogada(Lance lance) {
		// TODO - implement AtorNetGames.enviarJogada
		throw new UnsupportedOperationException();
	}

	public void informarNomeAdversario(String idUsuario) {
		// TODO - implement AtorNetGames.informarNomeAdversario
		throw new UnsupportedOperationException();
	}

	public List<String> getListaOrdenadaJogadores() {
		return proxy.obterNomeAdversarios();
	}

	public void finalizarPartida() {
		// TODO - implement AtorNetGames.finalizarPartida
		throw new UnsupportedOperationException();
	}

	public void finalizarPartidaComErro(String message) {
		// TODO - implement AtorNetGames.finalizarPartidaComErro
		throw new UnsupportedOperationException();
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receberJogada(Jogada jogada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}

}