package br.com.meuprojeto.crochet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuprojeto.crochet.models.Fio;
import br.com.meuprojeto.crochet.models.TipoCor;
import br.com.meuprojeto.crochet.models.TipoFio;
import br.com.meuprojeto.crochet.repositories.FioRepository;

@Service
public class FioServiceImpl implements FioService{

	@Autowired
	private FioRepository fioRepository;

	@Override
	public List<Fio> listaFios(TipoFio tipoFio, TipoCor tipoCor) {
		
		List<Fio> fios;
		//TODO padrão de projeto?
		if(tipoFio != null) {
			
			if(tipoCor != null) {
				 fios = fioRepository.findByTipoFioAndTipoCor(tipoFio, tipoCor);
			}else {
				fios = fioRepository.findByTipoFio(tipoFio);
			}
		}else {
			if(tipoCor!= null) {
				fios = fioRepository.findByTipoCor(tipoCor);
			}else {
				fios = fioRepository.findAll();
			}
		}
		
		return fios;
	}

	@Override
	public void adicionar(List<Fio> fios) {
		
		if (!fios.isEmpty()){
			fioRepository.save(fios);
		}

	}

	@Override
	public Fio buscarFioPorId(Integer fioId) {
		return fioRepository.findOne(fioId);
	}

	@Override
	public void inativar(Integer fioId) {
		Fio fio = this.buscarFioPorId(fioId);
		fio.setAtivo(false);
		fioRepository.save(fio);
		
	}
	
	@Override
	public void editar(Fio fio) {
		if (fio != null) {
			fioRepository.save(fio);
		}
	}

}
