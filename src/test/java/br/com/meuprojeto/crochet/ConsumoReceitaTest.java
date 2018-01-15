package br.com.meuprojeto.crochet;

import java.util.List;

import br.com.meuprojeto.crochet.services.ReceitaServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.meuprojeto.crochet.models.Fio;
import br.com.meuprojeto.crochet.models.Receita;
import br.com.meuprojeto.crochet.models.ReceitaConsumoFio;
import br.com.meuprojeto.crochet.services.FioServiceImpl;


public class ConsumoReceitaTest {

	@Autowired
	private ReceitaServiceImpl receitaServiceImpl;
	
	@Autowired
	private FioServiceImpl fioService;
	
	@Test
	public List<ReceitaConsumoFio> listarConsumoPorReceita(){
			
		Receita receita = receitaServiceImpl.buscarPorId(1);
		Fio fio = fioService.buscarFioPorId(3);
			
		
		ReceitaConsumoFio consumo = new ReceitaConsumoFio(receita,fio);
		consumo.setMetragemConsumidaMetros(0.01);
		consumo.setObservacao("teste");
		consumo.setPesoConsumidoKg(0.01);		
		
		return null;
	}
	
}
