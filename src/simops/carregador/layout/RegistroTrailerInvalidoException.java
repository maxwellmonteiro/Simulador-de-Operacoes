package simops.carregador.layout;

public class RegistroTrailerInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegistroTrailerInvalidoException() {
		
		super("Registro trailer inválido.");
	}	
	
	public RegistroTrailerInvalidoException(String msg) {
		
		super(msg);
	}
}
