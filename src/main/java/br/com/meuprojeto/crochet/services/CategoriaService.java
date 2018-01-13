package br.com.meuprojeto.crochet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuprojeto.crochet.models.Categoria;
import br.com.meuprojeto.crochet.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ReceitaService receitaService;

	public void adicionar(List<Categoria> categorias) {
		categoriaRepository.save(categorias);
	}

	public List<Categoria> listaTodos() {
		return categoriaRepository.findAll();
	}

	public Categoria buscarPorId(Integer categoriaId) {
		return categoriaRepository.findOne(categoriaId);
	}

	public List<Categoria> listaFilhas(Integer parentId) {
		Categoria pai = this.buscarPorId(parentId);
		return categoriaRepository.findByCategoriaPai(pai);
	}

	public List<Categoria> listarPaiAndFilhas(Integer parentId) {
		Categoria pai = this.buscarPorId(parentId);
		return categoriaRepository.findByCategoriaIdOrCategoriaPai(parentId, pai);
	}

	public Categoria editar(Categoria ca) {

		Categoria categoria = categoriaRepository.findOne(ca.getCategoriaId());

		if (categoria.getNome() != ca.getNome()) {
			categoria.setNome(ca.getNome());
		}

		if (categoria.getCategoriaPai() != ca.getCategoriaPai()) {
			categoria.setCategoriaPai(ca.getCategoriaPai());
		}
		categoriaRepository.save(categoria);
		return null;
	}

	public Boolean possuiFilhas(Integer categoriaId) {

		Categoria filhas = categoriaRepository.getOne(categoriaId);

		Boolean retorno;

		if (filhas != null) {
			retorno = true;
		} else {
			retorno = false;
		}

		return retorno;

	}

	public void remover(Integer categoriaId) {

		// TODO ver retorno - boolean pra sinalizar que apagou ou faz um exception de que não pode apagar pq tem associação??
		if ((!this.possuiFilhas(categoriaId)) && (receitaService.receitaPorCategoria(categoriaId) == null)) {
			// se não tem categorias filhas, e nem categorias pai/filhas possuem receitas,
			// delete.
			categoriaRepository.delete(categoriaId);
		}

	}

}
