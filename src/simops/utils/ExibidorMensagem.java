package simops.utils;

import java.util.List;


class ExibidorMensagem implements Runnable {
	
	private boolean stop;
	
	void stop() {			
		stop = true;
	}
	
	ExibidorMensagem() {		
		stop = false;
	}
	
	void mostrarMensagens(List<String> msgs) {
		
		for (String msg : msgs) {
			System.out.println(msg);				
		}
	}
	
	@Override
	public void run() {		
		
		while(!stop) {
			mostrarMensagens(GerenciadorMensagens.getInstance().getMensagens());	
		//	GerenciadorMensagens.getInstance().limparMensagens();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {					
				e.printStackTrace();
			}
		}
		mostrarMensagens(GerenciadorMensagens.getInstance().getMensagens());	
	}				
}
