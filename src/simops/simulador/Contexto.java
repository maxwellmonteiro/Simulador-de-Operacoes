package simops.simulador;

import java.util.ArrayList;
import java.util.List;

import simops.carregador.layout.Cotacao;

class Contexto {

	private String papel;
	private IEstado estado;
	private ISimulacao simulacao;
	private List<Operacao> operacoes;
	private List<Cotacao> cotacoes;
	private int indCotacao = 0;

	public Contexto() {
		operacoes = new ArrayList<Operacao>();
	}
	
	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	public IEstado getEstado() {
		return estado;
	}

	public void setEstado(IEstado estado) {
		this.estado = estado;
	}

	public ISimulacao getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(ISimulacao simulacao) {
		this.simulacao = simulacao;
	}	
	
	public List<Operacao> getOperacoes() {
		return operacoes;
	}

	public Cotacao getCotacaoAtual() {
				
		if (indCotacao < cotacoes.size()) {			
			return cotacoes.get(indCotacao);
		}
		return null;
	}
	
	public List<Cotacao> getCotacoes() {
		return cotacoes;
	}
	
	public int getIndCotacao() {
		return indCotacao;
	}
	
	public void setIndCotacao(int indCotacao) {
		this.indCotacao = indCotacao;
	}
	
	public void setCotacoes(List<Cotacao> cotacoes) {
		this.cotacoes = cotacoes;
	}
	
	// Testa se já chegou ao fim da Lista de cotações
	public boolean EOL() {
		if (indCotacao >= cotacoes.size()) {
			return true;
		}
		return false;
	}
}
