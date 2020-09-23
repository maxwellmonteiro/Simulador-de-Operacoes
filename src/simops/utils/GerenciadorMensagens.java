package simops.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorMensagens {
	
	private static GerenciadorMensagens instance;
	
	private List<Mensagem> mensagens;
	private static final String LOG_FILE = "log.txt";
	private ExibidorMensagem exibidor;

	private GerenciadorMensagens() {
		
		mensagens = new ArrayList<Mensagem>();		
	}
	
	private void gravarLog(Exception exception) {
		
		OutputStream os;
		try {
			os = new FileOutputStream(LOG_FILE);
			PrintWriter pw = new PrintWriter(os);
			exception.printStackTrace(pw);
			pw.close();
			os.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public void iniciarExibidorMensagem() {
		
		exibidor = new ExibidorMensagem();
		new Thread(exibidor).start();
	}
	
	public void pararExibidorMensagem() {
		
		exibidor.stop();
	}
	
	public static GerenciadorMensagens getInstance() {
		
		if (instance == null) {
			instance = new GerenciadorMensagens();
			return instance;
		}
		return instance;
	}
	
	synchronized public void limparMensagens() {
		
		mensagens.clear();
	}
	
	synchronized public List<String> getInfos() {
		
		List<String> infos = new ArrayList<String>();
		
		for (Mensagem msg : mensagens) {
			
			if (msg.getTipo().equals(Mensagem.MSG_INFO) && !msg.isDisplayed()) {
				infos.add(msg.getMensagem());	
				msg.setDisplayed(true);
			}
		}
		
		return infos;
	}
	
	synchronized public List<String> getErros() {
		
		List<String> erros = new ArrayList<String>();
		
		for (Mensagem msg : mensagens) {
			
			if (msg.getTipo().equals(Mensagem.MSG_ERRO) && !msg.isDisplayed()) {
				erros.add(msg.getMensagem());
				msg.setDisplayed(true);
			}
		}
		
		return erros;
	}
	
	synchronized public List<String> getMensagens() {
		
		List<String> msgs = new ArrayList<String>();
		
		for (Mensagem msg : mensagens) {
			if (!msg.isDisplayed()) {
				if (msg.getTipo().equals(Mensagem.MSG_INFO)) {
					msgs.add("INFO: " + msg.getMensagem());
				} else if (msg.getTipo().equals(Mensagem.MSG_ERRO)) {
					msgs.add("ERRO: " + msg.getMensagem());
				} else {
					msgs.add(msg.getMensagem());
				}
				msg.setDisplayed(true);
			}
		}		
		return msgs;
	}
	
	synchronized public void addInfo(String msg) {
		
		mensagens.add(new Mensagem(Mensagem.MSG_INFO, msg));
	}
	
	synchronized public void addErro(String msg) {
		
		mensagens.add(new Mensagem(Mensagem.MSG_ERRO, msg));
	}
	
	synchronized public void addMsg(String msg) {
		
		mensagens.add(new Mensagem(Mensagem.MSG_ANY, msg));
	}
	
	synchronized public void addInfo(Exception e) {
		
		mensagens.add(new Mensagem(Mensagem.MSG_INFO, e.getMessage()));
		gravarLog(e);
	}
	
	synchronized public void addErro(Exception e) {
		
		mensagens.add(new Mensagem(Mensagem.MSG_ERRO, e.getMessage()));
		gravarLog(e);
	}
}
