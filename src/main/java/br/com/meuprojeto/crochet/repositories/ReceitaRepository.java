package br.com.meuprojeto.crochet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meuprojeto.crochet.models.Categoria;
import br.com.meuprojeto.crochet.models.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer>{

	List<Receita> getReceitaByCategoria(Categoria categoria);//como usar uma list como parametro?

}
