package simops.simulador;

import java.util.Date;
import java.util.List;

import simops.carregador.layout.Cotacao;
import simops.utils.Utils;

public class SimulacaoLCRolagemLagarta implements ISimulacao {

	private boolean verificaTempo(Cotacao cotacao) {
		long diff = Utils.diffData(cotacao.getDataPregao(), cotacao.getDataVencimento());
		if (diff >= 25 && diff <= 40) { // Só vender se estiver entre 25 e 40 dias do vencimento
			return true;
		}
		return false;
	}

	private boolean verificaPremio(Cotacao cotacaoAcao, Cotacao cotacaoOpcao) {
		double razao = cotacaoOpcao.getPrecoMedio().doubleValue()
				/ cotacaoAcao.getPrecoMedio().doubleValue();

		if (razao >= 0.015) { // Só aceito premio maior que 1,5% do valor da ação
			return true;
		}
		return false;
	}

	private boolean verificaOTM(Cotacao cotacaoAcao, Cotacao cotacaoOpcao) {
		if (cotacaoOpcao.getPrecoExercicio().compareTo(cotacaoAcao.getPrecoMedio()) > 0) {
			return true;
		}
		return false;
	}
	
	private boolean processarPregaoMontagem(Contexto contexto) {
		int i;		
		Cotacao cotacaoAcao = ServSim.getCotacaoDoDia(contexto, contexto.getPapel());
		List<Cotacao> cotacoes = contexto.getCotacoes();
		Date data = contexto.getCotacaoAtual().getDataPregao();
		boolean testaOTM = false;
		boolean ehOTM = false;

		if (contexto.getOperacoes().size() == 0) { // primeira montagem
			testaOTM = true;
		}
		
		for (i = contexto.getIndCotacao(); i < cotacoes.size() 
				&& data.equals(cotacoes.get(i).getDataPregao()); i++) {
			if (testaOTM) {
				ehOTM = verificaOTM(cotacaoAcao, cotacoes.get(i));
			} else {
				ehOTM = true;
			}
			
			if (cotacoes.get(i).getTipoMercado().equals(Cotacao.TPMERC_OPCOES_COMPRA)
					&& verificaTempo(cotacoes.get(i))
					&& verificaPremio(cotacaoAcao, cotacoes.get(i))
					&& ehOTM) {

				Posicao posicao = new Posicao();
				posicao.setPapel(cotacoes.get(i));
				posicao.setPrecoVenda(cotacoes.get(i).getPrecoMedio());
				posicao.setVeVenda(getVE(posicao, cotacaoAcao, contexto));
				posicao.setQtd(new Long(1));
				Operacao operacao = new Operacao();
				operacao.addPosicao(posicao);
				operacao.setDataInicio(cotacoes.get(i).getDataPregao());
				operacao.setCotacaoInicio(cotacaoAcao);
				contexto.getOperacoes().add(operacao);
				
				contexto.setIndCotacao(i + 1);
				return false;
			}
		}
		contexto.setIndCotacao(i);
		return true;
	}
	
	private long diasParaExercicio(Contexto contexto) {		
		Operacao operacao = contexto.getOperacoes().get(contexto.getOperacoes().size() - 1);
		Posicao posicao = operacao.getPosicoes().get(0);
		long dias = Utils.diffData(contexto.getCotacaoAtual().getDataPregao(), posicao.getPapel().getDataVencimento());
		return dias;
	}	
	
	private Cotacao getMelhorRolagem(Posicao posicao, Contexto contexto) {
		int i = contexto.getIndCotacao();
		List<Cotacao> cotacoes = contexto.getCotacoes();
		Date data = cotacoes.get(i).getDataPregao();
		double menorDiff = 9999;
		double diff;
		Cotacao melhorRolagem = new Cotacao("dummy");		
		Cotacao cot = ServSim.getCotacaoDoDia(contexto, posicao.getPapel().getCodNegociacao());						
		
		for (i = contexto.getIndCotacao(); cot != null && i < cotacoes.size() 
				&& data.equals(cotacoes.get(i).getDataPregao()); i++) {
			diff = cotacoes.get(i).getPrecoMedio() - cot.getPrecoMedio();
			if (diff >= 0 && diff < menorDiff 
					&& !cot.getCodNegociacao().equals(cotacoes.get(i).getCodNegociacao())) {
				menorDiff = diff;
				melhorRolagem = cotacoes.get(i);
			}			
		}
		for (i = contexto.getIndCotacao(); cot != null && i >= 0 
				&& data.equals(cotacoes.get(i).getDataPregao()); i--) {
			diff = cotacoes.get(i).getPrecoMedio() - cot.getPrecoMedio();
			if (diff >= 0 && diff < menorDiff 
					&& !cot.getCodNegociacao().equals(cotacoes.get(i).getCodNegociacao())) {
				menorDiff = diff;
				melhorRolagem = cotacoes.get(i);
			}				
		}
		return melhorRolagem;
	}
	
	private boolean rolarSemPagar(Posicao posicao, Cotacao cotacao, Contexto contexto) {			
		Cotacao cot = ServSim.getCotacaoDoDia(contexto, posicao.getPapel().getCodNegociacao());				
		if (cot != null && cot.getPrecoMedio() <= cotacao.getPrecoMedio()) {
			return true;
		}
		return false;
	}
	
	private double getVE(Posicao posicao, Cotacao cotacaoAcao, Contexto contexto) {			
		Cotacao cot = ServSim.getCotacaoDoDia(contexto, posicao.getPapel().getCodNegociacao());	
		if (cot != null) {
			double ve = cot.getPrecoExercicio() + cot.getPrecoMedio() - cotacaoAcao.getPrecoMedio();
			if (ve > cot.getPrecoMedio().doubleValue()) {
				return cot.getPrecoMedio().doubleValue();
			}
			return ve;
		}
		return 9999;
	}
	
	private boolean processarPregaoDesmontagem(Contexto contexto) {				
		int i;					
		Cotacao cotacaoAcao = ServSim.getCotacaoDoDia(contexto, contexto.getPapel());
		List<Cotacao> cotacoes = contexto.getCotacoes();
		Date data = contexto.getCotacaoAtual().getDataPregao();
		Posicao posicao = contexto.getOperacoes().get(contexto.getOperacoes().size() - 1).getPosicoes().get(0);
		long diasParaExercicio = diasParaExercicio(contexto);
		double ve = getVE(posicao, cotacaoAcao, contexto);
		Cotacao melhorRolagem = getMelhorRolagem(posicao, contexto);
		
		for (i = contexto.getIndCotacao(); i < cotacoes.size() 
				&& data.equals(cotacoes.get(i).getDataPregao()); i++) {
			
			if (cotacoes.get(i).getTipoMercado().equals(Cotacao.TPMERC_OPCOES_COMPRA)
					&& ((verificaTempo(cotacoes.get(i))
					&& verificaPremio(cotacaoAcao, cotacoes.get(i))
			//		&& verificaOTM(cotacaoAcao, cotacoes.get(i))
					&& melhorRolagem.getCodNegociacao().equals(cotacoes.get(i).getCodNegociacao())
					&& rolarSemPagar(posicao, cotacoes.get(i), contexto)
					&& diasParaExercicio <= 7
					&& ServSim.getCotacaoDoDia(contexto, posicao.getPapel().getCodNegociacao()) != null)
					|| posicao.getPapel().getDataVencimento().equals(data)
					|| ve <= 0)) {
				
				Cotacao cotacao = ServSim.getCotacaoDoDia(contexto, posicao.getPapel().getCodNegociacao());
				if (!verificaOTM(cotacaoAcao, posicao.getPapel())) { // Não virou pó, se não tiver negócios do papel nesse dia, vai dar nullpointer					
					posicao.setPrecoCompra(cotacao.getPrecoMedio());	
					posicao.setVeCompra(ve);
				} else { // Virou pó
					if (cotacao != null) {
						posicao.setPrecoCompra(cotacao.getPrecoMedio());	
						posicao.setVeCompra(ve);
					} else {
						posicao.setPrecoCompra(new Double(0));
						posicao.setVeCompra(new Double(0));
					}					
				}
				
				Operacao operacao = contexto.getOperacoes().get(contexto.getOperacoes().size() - 1);
				operacao.setDataTermino(data);
				operacao.setCotacaoFim(cotacaoAcao);
				contexto.setIndCotacao(i);
				return false;
			}
		}
		contexto.setIndCotacao(i);
		return true;
	}

	@Override
	public void montarOperacao(Contexto contexto) {
		while (processarPregaoMontagem(contexto) && !contexto.EOL()); 					
	}

	@Override
	public void desmontarOperacao(Contexto contexto) {
		while (processarPregaoDesmontagem(contexto) && !contexto.EOL());
	}

}
