package simops.carregador.layout;

public class RegistroHeaderInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegistroHeaderInvalidoException() {
		
		super("Registro header inv�lido");
	}
	
	public RegistroHeaderInvalidoException(String msg) {
		
		super(msg);
	}

}
