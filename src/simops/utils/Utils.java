package simops.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static long diffData(Date dt1, Date dt2) {
		
		long diferenca;
		long tempoDia = 1000 * 60 * 60 * 24;
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		
		dataInicio.setTime(dt1);
		dataFinal.setTime(dt2);		
		diferenca = dataFinal.getTimeInMillis() - dataInicio.getTimeInMillis();
				
		return diferenca / tempoDia;
	}
}
