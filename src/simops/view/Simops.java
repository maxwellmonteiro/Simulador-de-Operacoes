package simops.view;

import java.util.ArrayList;
import java.util.List;

import simops.carregador.Carregador;
import simops.simulador.FabricaSimulacao;
import simops.simulador.Simulador;
import simops.utils.GerenciadorMensagens;

public class Simops {

	private List<String> extrairNomeArquivos(String[] args) {
		
		List<String> arquivos = new ArrayList<String>();
		
		for (String arg : args) {
			
			if (arg.toUpperCase().contains(".TXT")) {
				arquivos.add(arg);
			}
		}		
		return arquivos;
	}	
	
	private String extrairFiltroAcao(String[] args) {
		
		for (String arg : args) {
			
			if (!arg.toUpperCase().contains(".TXT")) {
				return arg;
			}
		}		
		return null;
	}
	
	public static void main(String[] args) {
		
		Simops simops = new Simops();
		
		if (args.length > 0 && simops.extrairFiltroAcao(args) != null) {
			
			Carregador carregador = new Carregador();
			Simulador simulador;
						
			GerenciadorMensagens.getInstance().iniciarExibidorMensagem();
			
			carregador.setFiltroCotacaoAcao(simops.extrairFiltroAcao(args));
			
			GerenciadorMensagens.getInstance().addInfo("Carregando arquivos...");					
			for (String arq : simops.extrairNomeArquivos(args)) {												
				carregador.carregar(arq);			
			}
			
			carregador.salvarTabulado();
			
			GerenciadorMensagens.getInstance().pararExibidorMensagem();			
			
		/*	GerenciadorMensagens.getInstance().addInfo("Início simulação");		
			simulador = new Simulador(FabricaSimulacao.SIM_LC_ROLAGEM_LAGARTA, simops.extrairFiltroAcao(args));			
			simulador.simular(carregador.getCotacoes());
			GerenciadorMensagens.getInstance().addInfo("Fim da simulação");		
			
			for (String msg : GerenciadorMensagens.getInstance().getMensagens()) {
				System.out.println(msg);
			}
			for (String msg : GerenciadorMensagens.getInstance().getInfos()) {
				System.out.println(msg);
			}
			for (String msg : GerenciadorMensagens.getInstance().getErros()) {
				System.out.println(msg);
			}		*/						
		} else {
			System.out.println("simops <Nome arquivo []> <ação>");
		}
	}		
}
