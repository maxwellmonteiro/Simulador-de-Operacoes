package simops.carregador.layout;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import simops.carregador.filtros.IFiltroCotacao;

public class LayoutBOVESPA {			

	private String nomeArquivoOrigem;
	private Header header;
	private List<Cotacao> cotacoes;
	private Trailer trailer;
	
	private List<IFiltroCotacao> filtrosCotacao;
	
	public static final Long TIPO_REGISTRO_HEADER = 00L;
	public static final Long TIPO_REGISTRO_COTACAO = 01L;
	public static final Long TIPO_REGISTRO_TRAILER = 99L;
	
	public LayoutBOVESPA() {
		filtrosCotacao = new ArrayList<IFiltroCotacao>();
	}
	
	private void filtrar(List<Cotacao> cotacoes, Cotacao cotacao) {
		
		for (IFiltroCotacao filtro : filtrosCotacao) {
			filtro.filtrar(cotacoes, cotacao);
		}
	}

	private String lerCotacoes(BufferedReader br) throws IOException {
			
		String linha;
		Cotacao cotacao;
		
		cotacoes = new ArrayList<Cotacao>();		
		linha = br.readLine();		
		try {
			while (linha != null) {
				cotacao = new Cotacao();
				cotacao.lerCotacao(linha);
				cotacao.validar();
				filtrar(cotacoes, cotacao);				
				linha = br.readLine();
			}
		} catch (RegistroCotacaoInvalidoException e) {
			// Terminou de percorrer os registro de cotação
		}
		return linha;
	}
	
	public String getNomeArquivoOrigem() {
		return nomeArquivoOrigem;
	}

	public void setNomeArquivoOrigem(String nomeArquivoOrigem) {
		this.nomeArquivoOrigem = nomeArquivoOrigem;
	}
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<Cotacao> getCotacoes() {
		return cotacoes;
	}
	public void setCotacoes(List<Cotacao> cotacoes) {
		this.cotacoes = cotacoes;
	}
	public Trailer getTrailer() {
		return trailer;
	}
	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}		

	public void addFiltroCotacao(IFiltroCotacao filtro) {
		filtrosCotacao.add(filtro);
	}

	public void lerLayout(BufferedReader br) throws IOException, LayoutBOVESPAInvalidoException {
	
		String linha;
								
		try {
			linha = br.readLine();	
			header = new Header();
			header.lerHeader(linha);
			linha = lerCotacoes(br);			
			trailer = new Trailer();
			trailer.lerTrailer(linha);	
		} catch (RegistroHeaderInvalidoException e) {
			throw new LayoutBOVESPAInvalidoException(e.getMessage());
		} catch (RegistroTrailerInvalidoException e) {
			throw new LayoutBOVESPAInvalidoException(e.getMessage());
		}							
	}
	
	public void validar() throws LayoutBOVESPAInvalidoException {
		
		try {
			header.validar();
			trailer.validar();
		} catch (RegistroHeaderInvalidoException e) {
			throw new LayoutBOVESPAInvalidoException(e.getMessage());
		} catch (RegistroTrailerInvalidoException e) {
			throw new LayoutBOVESPAInvalidoException(e.getMessage());
		}
		
		if (!header.getCodOrigem().equals("BOVESPA")) {
			throw new LayoutBOVESPAInvalidoException();
		}		
	}
}
