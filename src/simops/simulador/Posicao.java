package simops.simulador;

import simops.carregador.layout.Cotacao;

class Posicao {

	private Cotacao papel;
	private Long qtd;
	private Double precoCompra;
	private Double precoVenda;
	private Double veCompra;
	private Double veVenda;
		
	public Cotacao getPapel() {
		return papel;
	}
	public void setPapel(Cotacao papel) {
		this.papel = papel;
	}
	public Long getQtd() {
		return qtd;
	}
	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}
	public Double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}	
	
	public boolean encerrada() {		
		if (precoCompra != null && precoVenda != null) {
			return true;
		}
		return false;
	}
	public Double getVeCompra() {
		return veCompra;
	}
	public void setVeCompra(Double veCompra) {
		this.veCompra = veCompra;
	}
	public Double getVeVenda() {
		return veVenda;
	}
	public void setVeVenda(Double veVenda) {
		this.veVenda = veVenda;
	}
	
}
