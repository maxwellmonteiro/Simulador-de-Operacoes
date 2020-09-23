package simops.simulador;

import java.util.Comparator;
import simops.carregador.layout.Cotacao;

public class HplOrdenarPorDataPregao implements Comparator<Cotacao> {

	@Override
	public int compare(Cotacao arg0, Cotacao arg1) {		
		return arg0.getDataPregao().compareTo(arg1.getDataPregao());
	}
}
