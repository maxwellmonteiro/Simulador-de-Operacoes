package simops.carregador.layout;

public class RegistroCotacaoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistroCotacaoInvalidoException() {
		
		super("Registro cotação inválido");
	}
	
	public RegistroCotacaoInvalidoException(String msg) {
		
		super(msg);
	}
}
