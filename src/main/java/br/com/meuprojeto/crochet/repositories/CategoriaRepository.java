package br.com.meuprojeto.crochet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meuprojeto.crochet.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	List<Categoria> findByCategoriaPai(Categoria parentId);

	List<Categoria> findByCategoriaIdOrCategoriaPai(Integer categoriaId, Categoria parentId);
}
	

