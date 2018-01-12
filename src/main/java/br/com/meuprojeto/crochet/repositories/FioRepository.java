package br.com.meuprojeto.crochet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meuprojeto.crochet.models.Fio;
import br.com.meuprojeto.crochet.models.TipoCor;
import br.com.meuprojeto.crochet.models.TipoFio;

@Repository
public interface FioRepository extends JpaRepository<Fio, Integer> {

	List<Fio> findByTipoFioAndTipoCor(TipoFio tipoFio, TipoCor tipoCor);

	List<Fio> findByTipoFio(TipoFio tipoFio);

	List<Fio> findByTipoCor(TipoCor tipoCor);

}
