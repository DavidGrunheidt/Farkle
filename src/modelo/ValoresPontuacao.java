package modelo;
public enum ValoresPontuacao {
	_1(100), _5(50),
	_1_1(200), _5_5(100),
	_2_2_2(200), _3_3_3(300),
	_4_4_4(400), _6_6_6(600),
	_1_1_1(1000), _5_5_5(500),
	_1_1_1_1(1100), _5_5_5_5(550),
	_1_1_1_1_1(1200), _5_5_5_5_5(600),
	_1_1_1_1_1_1(2000), _5_5_5_5_5_5(1000);

	private int valorPontuacao;

	ValoresPontuacao(int valor) {
		this.valorPontuacao = valor;
	}

	public int getValorPontuacao() {
		return this.valorPontuacao;
	}

}