package simops.simulador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import simops.utils.GerenciadorMensagens;

public class FabricaSimulacao {
	
	public static final String SIM_LC_ROLAGEM_LAGARTA = "Rolagem lagarta";
	
	private Map<String, Class<? extends ISimulacao>> simulacoes;
	
	public FabricaSimulacao() {
		simulacoes = new HashMap<String, Class<? extends ISimulacao>>();	
		
		simulacoes.put(SIM_LC_ROLAGEM_LAGARTA, SimulacaoLCRolagemLagarta.class);
	}
	
	ISimulacao getSimulacao(String nomeSimulacao) {
		
		ISimulacao simulacao = null;
		Class<? extends ISimulacao> classe = simulacoes.get(nomeSimulacao);
		
		try {
			simulacao = (ISimulacao) classe.newInstance();			
		} catch (InstantiationException e) {
			GerenciadorMensagens.getInstance().addErro("Falha ao criar simulação " + nomeSimulacao);
		} catch (IllegalAccessException e) {
			GerenciadorMensagens.getInstance().addErro("Falha ao criar simulação " + nomeSimulacao);
		}
		return simulacao;
	}
	
	public List<String> getNomeSimulacoes() {
		List<String> nomeSimulacoes = new ArrayList<String>();
		
		for (Entry<String, Class<? extends ISimulacao>> entry : simulacoes.entrySet()) {
			nomeSimulacoes.add(entry.getKey());
		}
		return nomeSimulacoes;
	}
}
