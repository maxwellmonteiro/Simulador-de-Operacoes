package simops.carregador.layout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cotacao {
	
	public static final Long TPMERC_VISTA = 10L;
	public static final Long TPMERC_EXERCICIO_OPCOES_COMPRA = 12L;
	public static final Long TPMERC_OPCOES_COMPRA = 70L;	
	
	public static final String ESPECI_ACOES_PREFERENCIAIS = "PN";

	private Long tipoRegistro;
	private Date dataPregao;
	private String codBDI;
	private String codNegociacao;
	private Long tipoMercado;
	private String nomeResumido;
	private String especPapel;
	private String prazoMercadoTermo;
	private String moedaReferencia;
	private Double precoAbertura;
	private Double precoMaximo;
	private Double precoMinimo;
	private Double precoMedio;
	private Double precoUltimoNegocio;
	private Double precoMelhorOfertaCompra;
	private Double precoMelhorOfertaVenda;
	private Long numeroNegocios;
	private Long qtdTitulosNegociados;
	private Double volTitulosNegociados;
	private Double precoExercicio;
	private Long indicadorCorrecao;
	private Date dataVencimento;
	private Long fatorCotacao;
	private Double precoExercicioEmPontos;
	private String codPapelISIN;
	private Long numeroDistribuicao;
	
	public Cotacao() {
		
	}
	
	public Cotacao(String codNegociacao) {
		this.codNegociacao = codNegociacao;
	}
	
	private Long lerTipoRegistro(String linha) {
		
		return new Long(linha.substring(0, 2));
	}
	private Date lerDataPregao(String linha) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return new Date(sdf.parse(linha.substring(2, 10)).getTime());	
	}
	private String lerCodBDI(String linha) {
		
		return linha.substring(10, 12).trim();
	}
	private String lerCodNegociacao(String linha) {
		
		return linha.substring(12, 24).trim();
	}
	private Long lerTipoMercado(String linha) {
		
		return new Long(linha.substring(24, 27));
	}
	private String lerNomeResumido(String linha) {
		
		return linha.substring(27, 39).trim();
	}
	private String lerEspecPapel(String linha) {
		
		return linha.substring(39, 49).trim();
	}
	private String lerPrazoMercadoTermo(String linha) {
		
		return linha.substring(49, 52).trim();
	}
	private String lerMoedaReferencia(String linha) {
		
		return linha.substring(52, 56);
	}
	private Double lerPrecoAbertura(String linha) {
		
		return (new Double(linha.substring(56, 69)).doubleValue() / 100);
	}
	private Double lerPrecoMaximo(String linha) {
		
		return (new Double(linha.substring(69, 82)).doubleValue() / 100);
	}
	private Double lerPrecoMinimo(String linha) {
		
		return (new Double(linha.substring(82, 95)).doubleValue() / 100);
	}
	private Double lerPrecoMedio(String linha) {
		
		return (new Double(linha.substring(95, 108)).doubleValue() / 100);
	}
	private Double lerPrecoUltimoNegocio(String linha) {
		
		return (new Double(linha.substring(108, 121)).doubleValue() / 100);
	}
	private Double lerPrecoMelhorOfertaCompra(String linha) {
		
		return (new Double(linha.substring(121, 134)).doubleValue() / 100);
	}
	private Double lerPrecoMelhorOfertaVenda(String linha) {
		
		return (new Double(linha.substring(134, 147)).doubleValue() / 100);
	}
	private Long lerNumeroNegocios(String linha) {
		
		return new Long(linha.substring(147, 152));
	}
	private Long lerQtdTitulosNegociados(String linha) {
		
		return new Long(linha.substring(152, 170));
	}
	private Double lerVolTitulosNegociados(String linha) {
		
		return (new Double(linha.substring(170, 188)).doubleValue() / 100);
	}
	private Double lerPrecoExercicio(String linha) {
		
		return (new Double(linha.substring(188, 201)).doubleValue() / 100);
	}
	private Long lerIndicadorCorrecao(String linha) {
		
		return new Long(linha.substring(201, 202));
	}
	private Date lerDataVencimento(String linha) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return new Date(sdf.parse(linha.substring(202, 210)).getTime());	
	}
	private Long lerFatorCotacao(String linha) {
		
		return new Long(linha.substring(210, 217));
	}
	private Double lerPrecoExercicioEmPontos(String linha) {
		
		return (new Double(linha.substring(217, 230)).doubleValue() / 1000000);
	}
	private String lerCodPapelISIN(String linha) {
		
		return linha.substring(230, 242).trim();
	}
	private Long lerNumeroDistribuicao(String linha) {
		
		return new Long(linha.substring(242, 245));
	}		
	
	public Long getTipoRegistro() {
		return tipoRegistro;
	}
	public Date getDataPregao() {
		return dataPregao;
	}
	public String getCodBDI() {
		return codBDI;
	}
	public String getCodNegociacao() {
		return codNegociacao;
	}
	public Long getTipoMercado() {
		return tipoMercado;
	}
	public String getNomeResumido() {
		return nomeResumido;
	}
	public String getEspecPapel() {
		return especPapel;
	}
	public String getPrazoMercadoTermo() {
		return prazoMercadoTermo;
	}
	public String getMoedaReferencia() {
		return moedaReferencia;
	}
	public Double getPrecoAbertura() {
		return precoAbertura;
	}
	public Double getPrecoMaximo() {
		return precoMaximo;
	}
	public Double getPrecoMinimo() {
		return precoMinimo;
	}
	public Double getPrecoMedio() {
		return precoMedio;
	}
	public Double getPrecoUltimoNegocio() {
		return precoUltimoNegocio;
	}
	public Double getPrecoMelhorOfertaCompra() {
		return precoMelhorOfertaCompra;
	}
	public Double getPrecoMelhorOfertaVenda() {
		return precoMelhorOfertaVenda;
	}
	public Long getNumeroNegocios() {
		return numeroNegocios;
	}
	public Long getQtdTitulosNegociados() {
		return qtdTitulosNegociados;
	}
	public Double getVolTitulosNegociados() {
		return volTitulosNegociados;
	}
	public Double getPrecoExercicio() {
		return precoExercicio;
	}
	public Long getIndicadorCorrecao() {
		return indicadorCorrecao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public Long getFatorCotacao() {
		return fatorCotacao;
	}
	public Double getPrecoExercicioEmPontos() {
		return precoExercicioEmPontos;
	}
	public String getCodPapelISIN() {
		return codPapelISIN;
	}
	public Long getNumeroDistribuicao() {
		return numeroDistribuicao;
	}		
	
	public void lerCotacao(String linha) throws RegistroCotacaoInvalidoException {
		
		tipoRegistro = lerTipoRegistro(linha);
		try {
			dataPregao = lerDataPregao(linha);
		} catch (ParseException e) {
			throw new RegistroCotacaoInvalidoException("Data pregão inválida");
		}
		codBDI = lerCodBDI(linha);
		codNegociacao = lerCodNegociacao(linha);
		tipoMercado = lerTipoMercado(linha);
		nomeResumido = lerNomeResumido(linha);
		especPapel = lerEspecPapel(linha);
		prazoMercadoTermo = lerPrazoMercadoTermo(linha);
		moedaReferencia = lerMoedaReferencia(linha);
		precoAbertura = lerPrecoAbertura(linha);
		precoMaximo = lerPrecoMaximo(linha);
		precoMinimo = lerPrecoMinimo(linha);
		precoMedio = lerPrecoMedio(linha);
		precoUltimoNegocio = lerPrecoUltimoNegocio(linha);
		precoMelhorOfertaCompra = lerPrecoMelhorOfertaCompra(linha);
		precoMelhorOfertaVenda = lerPrecoMelhorOfertaVenda(linha);
		numeroNegocios = lerNumeroNegocios(linha);
		qtdTitulosNegociados = lerQtdTitulosNegociados(linha);
		volTitulosNegociados = lerVolTitulosNegociados(linha);
		precoExercicio = lerPrecoExercicio(linha);
		indicadorCorrecao = lerIndicadorCorrecao(linha);
		try {
			dataVencimento = lerDataVencimento(linha);
		} catch (ParseException e) {
			throw new RegistroCotacaoInvalidoException("Data vencimento opção/termo inválida");
		}
		fatorCotacao = lerFatorCotacao(linha);
		precoExercicioEmPontos = lerPrecoExercicioEmPontos(linha);
		codPapelISIN = lerCodPapelISIN(linha);
		numeroDistribuicao = lerNumeroDistribuicao(linha);
		
	}
	
	public void validar() throws RegistroCotacaoInvalidoException {
		
		if (!tipoRegistro.equals(LayoutBOVESPA.TIPO_REGISTRO_COTACAO)) {
			throw new RegistroCotacaoInvalidoException();
		}		
	}
}
