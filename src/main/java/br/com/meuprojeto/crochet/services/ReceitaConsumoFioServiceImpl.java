package br.com.meuprojeto.crochet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuprojeto.crochet.models.Fio;

@Service
public class ReceitaConsumoFioServiceImpl {

	@Autowired
	private FioServiceImpl fioService;
	

	protected void adicionar(Integer receitaId, Integer fioId, Double pesoConsumido, String observacao){
	
		Fio fio = fioService.buscarFioPorId(fioId);
		
	//	Receita receita = 
		
		
	}
	
	protected Double calculaMetragemConsumida(Integer fioId, Double pesoConsumido){ 
		//TODO pode ser double, uma vez que será uma chamada interna,e não precisa retornar nada além do valor?
			
		Fio fio = fioService.buscarFioPorId(fioId);
		
		Double metragemConsumido = (fio.getMetragemInicial() * fio.getPesoInicial())/pesoConsumido;
		
		return metragemConsumido;
	}
	
	
	
	
	
}
