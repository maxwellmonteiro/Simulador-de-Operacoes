package simops.carregador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import simops.carregador.filtros.FabricaFiltroCotacao;
import simops.carregador.layout.Cotacao;
import simops.carregador.layout.LayoutBOVESPA;
import simops.carregador.layout.LayoutBOVESPAInvalidoException;
import simops.utils.GerenciadorMensagens;

public class Carregador {

	private List<LayoutBOVESPA> layouts;

	private String filtroCotacaoAcao;

	public Carregador() {

		layouts = new ArrayList<LayoutBOVESPA>();
	}

	public void setFiltroCotacaoAcao(String parm) {
		filtroCotacaoAcao = parm;
	}

	public void carregar(String nomeArquivo) {

		LayoutBOVESPA layout;
		FileReader arquivo = null;

		try {
			arquivo = new FileReader(nomeArquivo);
			BufferedReader br = new BufferedReader(arquivo);

			layout = new LayoutBOVESPA();
			layout.addFiltroCotacao(new FabricaFiltroCotacao()
					.getFiltro(FabricaFiltroCotacao.FILTRO_COTACAO_ACAO, filtroCotacaoAcao));
			layout.addFiltroCotacao(new FabricaFiltroCotacao()
					.getFiltro(FabricaFiltroCotacao.FILTRO_COTACAO_OPCAO, filtroCotacaoAcao));
			layout.setNomeArquivoOrigem(nomeArquivo);
			layout.lerLayout(br);
			layout.validar();
			layouts.add(layout);
			br.close();
			arquivo.close();
			GerenciadorMensagens.getInstance().addInfo(
					"Arquivo " + nomeArquivo + " carregado");
		} catch (FileNotFoundException e) {
			GerenciadorMensagens.getInstance().addErro(e);
		} catch (IOException e) {
			GerenciadorMensagens.getInstance().addErro(e);
		} catch (LayoutBOVESPAInvalidoException e) {
			GerenciadorMensagens.getInstance().addErro(e);
		}
	}

	public void salvarTabulado() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = new DecimalFormat("#0.00");

		for (LayoutBOVESPA layout : layouts) {

			FileOutputStream os = null;
			String arquivo = layout.getNomeArquivoOrigem().toUpperCase()
					.replace(".TXT", "-TABULADO.TXT");
			try {
				os = new FileOutputStream(arquivo);
			} catch (FileNotFoundException e) {
				GerenciadorMensagens.getInstance().addErro(
						"Erro ao criar o arquivo " + arquivo);
			}
			PrintWriter pw = new PrintWriter(os);
			for (Cotacao cotacao : layout.getCotacoes()) {
			//	pw.print(cotacao.getTipoRegistro() + "\t");
				pw.print(sdf.format(cotacao.getDataPregao()) + "\t");
			//	pw.print(cotacao.getCodBDI() + "\t");
				pw.print(cotacao.getCodNegociacao() + "\t");
			//	pw.print(cotacao.getTipoMercado() + "\t");
			//	pw.print(cotacao.getNomeResumido() + "\t");
			//	pw.print(cotacao.getEspecPapel() + "\t");
			//	pw.print(cotacao.getPrazoMercadoTermo() + "\t");
			//	pw.print(cotacao.getMoedaReferencia() + "\t");
			//	pw.print(cotacao.getPrecoAbertura() + "\t");
			//	pw.print(cotacao.getPrecoMaximo() + "\t");
			//	pw.print(cotacao.getPrecoMinimo() + "\t");
				pw.print(nf.format(cotacao.getPrecoMedio()) + "\t");
			//	pw.print(cotacao.getPrecoUltimoNegocio() + "\t");
			//	pw.print(cotacao.getPrecoMelhorOfertaCompra() + "\t");
			//	pw.print(cotacao.getPrecoMelhorOfertaVenda() + "\t");
			//	pw.print(cotacao.getNumeroNegocios() + "\t");
			//	pw.print(cotacao.getQtdTitulosNegociados() + "\t");
			//	pw.print(cotacao.getVolTitulosNegociados() + "\t");
				pw.print(nf.format(cotacao.getPrecoExercicio()) + "\t");
			//	pw.print(cotacao.getIndicadorCorrecao() + "\t");
				pw.print(sdf.format(cotacao.getDataVencimento()) + "\t");
			//	pw.print(cotacao.getFatorCotacao() + "\t");
			//	pw.print(cotacao.getPrecoExercicioEmPontos() + "\t");
			//	pw.print(cotacao.getCodPapelISIN() + "\t");
			//	pw.print(cotacao.getNumeroDistribuicao());
				pw.print("\n");
			}
			pw.close();
			try {
				os.close();
			} catch (IOException e) {
				GerenciadorMensagens.getInstance().addErro(
						"Erro ao fechar arquivo " + arquivo);
			}
			GerenciadorMensagens.getInstance().addInfo(
					"Arquivo " + arquivo + " salvo");
		}
	}
	
	public List<Cotacao> getCotacoes() {
		List<Cotacao> cotacoes = new ArrayList<Cotacao>();
		for (LayoutBOVESPA layout : layouts) {
			cotacoes.addAll(layout.getCotacoes());
		}
		return cotacoes;
	}
}
