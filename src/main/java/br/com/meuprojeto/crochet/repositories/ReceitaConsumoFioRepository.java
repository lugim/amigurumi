package br.com.meuprojeto.crochet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meuprojeto.crochet.models.ReceitaConsumoFio;

@Repository
public interface ReceitaConsumoFioRepository extends JpaRepository<ReceitaConsumoFio,Integer>{

}
