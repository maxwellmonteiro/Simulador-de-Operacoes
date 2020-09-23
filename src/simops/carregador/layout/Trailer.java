package simops.carregador.layout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trailer {

	private Long tipoRegistro;
	private String nomeArquivo;
	private String codOrigem;
	private Date dataGeracao;
	private Long totalRegistros;
	private String reserva;
	
	
	private Long lerTipoRegistro(String linha) {
		
		return new Long(linha.substring(0, 2));
	}
	
	private String lerNomeArquivo(String linha) {
		
		return linha.substring(2, 15).trim();
	}
	
	private String lerCodOrigem(String linha) {
		
		return linha.substring(15, 23).trim();
	}
	
	private Date lerDataGeracao(String linha) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return new Date(sdf.parse(linha.substring(23, 31)).getTime());	
	}
	
	private Long lerTotalRegistros(String linha) {
		
		return new Long(linha.substring(31, 42));
	}
	
	private String lerReserva(String linha) {
		
		return linha.substring(42, 245).trim();
	}	
	
	public Long getTipoRegistro() {
		return tipoRegistro;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public String getCodOrigem() {
		return codOrigem;
	}
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public String getReserva() {
		return reserva;
	}		
	
	public void lerTrailer(String linha) throws RegistroTrailerInvalidoException {
		
		tipoRegistro = lerTipoRegistro(linha);
		nomeArquivo = lerNomeArquivo(linha);
		codOrigem = lerCodOrigem(linha);
		try {
			dataGeracao = lerDataGeracao(linha);
		} catch (ParseException e) {
			throw new RegistroTrailerInvalidoException("Data geração do arquivo inválida");
		}
		totalRegistros = lerTotalRegistros(linha);
		reserva = lerReserva(linha);
	}
	
	public void validar() throws RegistroTrailerInvalidoException {
		
		if (!tipoRegistro.equals(LayoutBOVESPA.TIPO_REGISTRO_TRAILER)) {
			throw new RegistroTrailerInvalidoException();
		}
	}
}
