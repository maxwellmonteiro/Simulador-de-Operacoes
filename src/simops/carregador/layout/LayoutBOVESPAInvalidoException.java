package simops.carregador.layout;

public class LayoutBOVESPAInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LayoutBOVESPAInvalidoException() {
		
		super("Layout de arquivo BOVESPA inválido");
	}

	public LayoutBOVESPAInvalidoException(String message) {
		
		super(message);
	}
}
