package simops.simulador;


class Posicionado implements IEstado {

	@Override
	public void operar(Contexto contexto) {
		contexto.getSimulacao().desmontarOperacao(contexto);
		contexto.setEstado(new Liquido());
	}

}
