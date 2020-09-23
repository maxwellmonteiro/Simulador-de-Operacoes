package simops.simulador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import simops.carregador.layout.Cotacao;

class Operacao {

	private List<Posicao> posicoes;	
	private Date dataInicio;
	private Date dataTermino;
	private Cotacao cotacaoInicio;
	private Cotacao cotacaoFim;
	
	public Operacao() {
		posicoes = new ArrayList<Posicao>();
	}
	
	public List<Posicao> getPosicoes() {
		return posicoes;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public void addPosicao(Posicao posicao) {
		posicoes.add(posicao);
	}		
	
	public Double calcularResultado() {		
		double resultado = 0;
		for (Posicao posicao : posicoes) {
			resultado += posicao.getPrecoVenda().doubleValue() - posicao.getPrecoCompra().doubleValue();
		}
		return resultado;
	}
	
	public boolean encerrada() {
		for (Posicao posicao : posicoes) {
			if (!posicao.encerrada()) {
				return false;
			}
		}
		return true;
	}

	public Cotacao getCotacaoInicio() {
		return cotacaoInicio;
	}

	public void setCotacaoInicio(Cotacao cotacaoInicio) {
		this.cotacaoInicio = cotacaoInicio;
	}

	public Cotacao getCotacaoFim() {
		return cotacaoFim;
	}

	public void setCotacaoFim(Cotacao cotacaoFim) {
		this.cotacaoFim = cotacaoFim;
	}	
}
