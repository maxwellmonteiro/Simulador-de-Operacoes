package simops.simulador;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import simops.carregador.layout.Cotacao;
import simops.utils.GerenciadorMensagens;

public class Simulador {

	private ISimulacao simulacao;
	private String papel;
	private Cotacao cotacaoInicial;
	private Cotacao cotacaoFinal;

	private void exibirResultado(Contexto contexto) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = new DecimalFormat("#0.00");
		double lucroTotal = 0;
		double porcent = 0;
		long countL = 0;
		long countP = 0;
		String msg;
		for (Operacao operacao : contexto.getOperacoes()) {	
			if (operacao.getDataTermino() != null) {
				msg = "\nInício: " + operacao.getCotacaoInicio().getCodNegociacao() 
						+ " @ R$ " + nf.format(operacao.getCotacaoInicio().getPrecoMedio()) + " "
						+ sdf.format(operacao.getDataInicio()) + "\n";
				msg += "Término: " + operacao.getCotacaoFim().getCodNegociacao() 
						+ " @ R$ " + nf.format(operacao.getCotacaoFim().getPrecoMedio()) + " "
						+ sdf.format(operacao.getDataTermino()) + "\n";
				for (Posicao posicao : operacao.getPosicoes()) {
					msg += "+" + posicao.getQtd() + " " + posicao.getPapel().getCodNegociacao()
					+ " " + nf.format(posicao.getPapel().getPrecoExercicio())
					+ " @ R$" + nf.format(posicao.getPrecoCompra()) + " VE " 
					+ nf.format(posicao.getVeCompra()) +  "\n";
					msg += "-" + posicao.getQtd() + " " + posicao.getPapel().getCodNegociacao()
					+ " " + nf.format(posicao.getPapel().getPrecoExercicio())
					+ " @ R$" + nf.format(posicao.getPrecoVenda()) + " VE " 
					+ nf.format(posicao.getVeVenda()) + "\n";					
				}
				if (operacao.calcularResultado() > 0) {
					countL++;
				} else {
					countP++;
				}
				msg += "Lucro: R$ " + nf.format(operacao.calcularResultado());
				lucroTotal += operacao.calcularResultado();
				GerenciadorMensagens.getInstance().addMsg(msg);
			}
		}
		msg =  "\nCotação inicial......: " + cotacaoInicial.getCodNegociacao() 
				+ " @ R$ " + nf.format(cotacaoInicial.getPrecoMedio()) + " " 
				+ sdf.format(cotacaoInicial.getDataPregao()) + "\n";
		msg += "Cotação final........: " + cotacaoFinal.getCodNegociacao() 
				+ " @ R$ " + nf.format(cotacaoFinal.getPrecoMedio()) + " " 
				+ sdf.format(cotacaoFinal.getDataPregao()) + "\n";
		msg += "N. operaçoes lucro...: " + countL + "\n";
		msg += "N. operações prejuízo: " + countP + "\n";
		porcent = (lucroTotal / cotacaoInicial.getPrecoMedio()) * 100;
		msg += "Lucro total..........: R$ " + nf.format(lucroTotal) + " " + nf.format(porcent) + "%\n";		
		GerenciadorMensagens.getInstance().addMsg(msg);
	}

	public Simulador(String nomeSimulacao, String papel) {
		this.simulacao = new FabricaSimulacao().getSimulacao(nomeSimulacao);
		this.papel = papel;		
	}

	public void simular(List<Cotacao> cotacoes) {
		
		Collections.sort(cotacoes, new HplOrdenarPorDataPregao());
		
		Contexto contexto = new Contexto();

		contexto.setPapel(papel);
		contexto.setEstado(new Liquido());
		contexto.setSimulacao(simulacao);
		contexto.setCotacoes(cotacoes);
		cotacaoInicial = ServSim.getCotacaoDoDia(contexto, contexto.getPapel());

		while (contexto.getCotacaoAtual() != null) {
			contexto.getEstado().operar(contexto);
		}
		contexto.setIndCotacao(contexto.getIndCotacao() - 1);
		cotacaoFinal = ServSim.getCotacaoDoDia(contexto, contexto.getPapel());
		
		exibirResultado(contexto);
	}
}
