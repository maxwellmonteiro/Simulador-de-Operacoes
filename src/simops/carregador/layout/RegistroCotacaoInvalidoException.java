package simops.carregador.layout;

public class RegistroCotacaoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistroCotacaoInvalidoException() {
		
		super("Registro cota��o inv�lido");
	}
	
	public RegistroCotacaoInvalidoException(String msg) {
		
		super(msg);
	}
}
