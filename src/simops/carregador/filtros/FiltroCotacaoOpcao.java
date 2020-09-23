package simops.carregador.filtros;

import java.util.List;

import simops.carregador.layout.Cotacao;

class FiltroCotacaoOpcao extends IFiltroCotacao {

	private static String[] series = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L" };

	@Override
	public void filtrar(List<Cotacao> cotacoes, Cotacao cotacao) {

		String prefix = parametroFiltragem.substring(0, parametroFiltragem.length() - 1);

		for (String serie : series) {

			if (cotacao.getCodNegociacao().contains(prefix + serie) && 
					(cotacao.getTipoMercado().equals(Cotacao.TPMERC_OPCOES_COMPRA) || 
					 cotacao.getTipoMercado().equals(Cotacao.TPMERC_EXERCICIO_OPCOES_COMPRA))
					 && cotacao.getEspecPapel().equals(Cotacao.ESPECI_ACOES_PREFERENCIAIS)
					 // Deve ter alguma liquidez
					 && cotacao.getNumeroNegocios().longValue() >= 50) {
				cotacoes.add(cotacao);
			}
		}
	}
}
