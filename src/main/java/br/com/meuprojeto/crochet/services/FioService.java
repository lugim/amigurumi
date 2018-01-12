package br.com.meuprojeto.crochet.services;

import java.util.List;

import br.com.meuprojeto.crochet.models.Fio;
import br.com.meuprojeto.crochet.models.TipoCor;
import br.com.meuprojeto.crochet.models.TipoFio;

public interface FioService {

	List<Fio> listaFios(TipoFio tipoFio, TipoCor tipoCor);
	
	void adicionar(List<Fio> fios);
	
	Fio buscarFioPorId(Integer fioId);
	
	void inativar(Integer fioId);
	
	void editar(Fio fio);
	
	
}
