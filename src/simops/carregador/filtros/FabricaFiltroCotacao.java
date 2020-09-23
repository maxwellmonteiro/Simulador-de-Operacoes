package simops.carregador.filtros;

import java.util.HashMap;
import java.util.Map;

import simops.utils.GerenciadorMensagens;

public class FabricaFiltroCotacao {
	
	public static final Long FILTRO_COTACAO_ACAO = 1L;
	public static final Long FILTRO_COTACAO_OPCAO = 2L;
	
	private Map<Long, Class<? extends IFiltroCotacao>> filtros;
	
	public FabricaFiltroCotacao() {
		
		filtros = new HashMap<Long, Class<? extends IFiltroCotacao>>();
		
		filtros.put(FILTRO_COTACAO_ACAO, FiltroCotacaoAcao.class);
		filtros.put(FILTRO_COTACAO_OPCAO, FiltroCotacaoOpcao.class);
	}
	
	public IFiltroCotacao getFiltro(Long codFiltro, String parametro) {
		
		IFiltroCotacao filtro = null;
		Class<? extends IFiltroCotacao> classe = filtros.get(codFiltro);
		
		try {
			filtro = (IFiltroCotacao) classe.newInstance();
			filtro.setParametroFiltragem(parametro);
		} catch (InstantiationException e) {
			GerenciadorMensagens.getInstance().addErro("Falha ao criar um filtro de cotação");
		} catch (IllegalAccessException e) {
			GerenciadorMensagens.getInstance().addErro("Falha ao criar um filtro de cotação");
		}
		return filtro;
	}

}
