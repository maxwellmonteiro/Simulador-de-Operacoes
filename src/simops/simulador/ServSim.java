package simops.simulador;

import java.util.Date;
import java.util.List;

import simops.carregador.layout.Cotacao;

public class ServSim {
	public static Cotacao getCotacaoDoDia(Contexto contexto, String papel) {

		int i = contexto.getIndCotacao();
		List<Cotacao> cotacoes = contexto.getCotacoes();
		Date data = cotacoes.get(i).getDataPregao();

		for (i = contexto.getIndCotacao(); i < cotacoes.size() 
			&& data.equals(cotacoes.get(i).getDataPregao()); i++) {
			if (papel.equals(cotacoes.get(i).getCodNegociacao())) {
				return cotacoes.get(i);
			}			
		}
		for (i = contexto.getIndCotacao(); data.equals(cotacoes.get(i).getDataPregao()); i--) {
			if (papel.equals(cotacoes.get(i).getCodNegociacao())) {
				return cotacoes.get(i);
			}			
		}
		return null;
	}
}
