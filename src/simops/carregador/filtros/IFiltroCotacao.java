package simops.carregador.filtros;

import java.util.List;

import simops.carregador.layout.Cotacao;

public abstract class IFiltroCotacao {

	protected String parametroFiltragem;
	
	void setParametroFiltragem(String parametroFiltragem) {
		this.parametroFiltragem = parametroFiltragem;
	}

	public abstract void filtrar(List<Cotacao> cotacoes, Cotacao cotacao);
}
