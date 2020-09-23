package simops.simulador;


class Liquido implements IEstado {

	@Override
	public void operar(Contexto contexto) {
		contexto.getSimulacao().montarOperacao(contexto);
		contexto.setEstado(new Posicionado());
	}

}
