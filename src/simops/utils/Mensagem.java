package simops.utils;

class Mensagem {

	static final Long MSG_ERRO = 1L;
	static final Long MSG_INFO = 2L;
	static final Long MSG_ANY = 3L;
	
	private Long tipo;
	private String mensagem;
	private boolean displayed;
	
	Mensagem(Long tipo, String msg) {
		
		this.tipo = tipo;
		this.mensagem = msg;
		this.displayed = false;
	}
	
	public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public boolean isDisplayed() {
		return displayed;
	}

	public void setDisplayed(boolean displayed) {
		this.displayed = displayed;
	}		
}
