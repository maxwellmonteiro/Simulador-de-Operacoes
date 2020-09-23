package simops.carregador.filtros;

import java.util.List;

import simops.carregador.layout.Cotacao;

class FiltroCotacaoAcao extends IFiltroCotacao {

	@Override
	public void filtrar(List<Cotacao> cotacoes, Cotacao cotacao) {

		if (cotacao.getCodNegociacao().equals(parametroFiltragem)
				&& cotacao.getTipoMercado().equals(Cotacao.TPMERC_VISTA)) {
			cotacoes.add(cotacao);
		}
	}
}
