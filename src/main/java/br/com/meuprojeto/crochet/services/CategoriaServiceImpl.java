package br.com.meuprojeto.crochet.services;

import java.util.List;

import br.com.meuprojeto.crochet.models.Receita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuprojeto.crochet.models.Categoria;
import br.com.meuprojeto.crochet.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	protected CategoriaRepository categoriaRepository;

	@Autowired
	protected ReceitaServiceImpl receitaServiceImpl;

	@Override
	public void adicionar(List<Categoria> categorias) {
		categoriaRepository.save(categorias);
	}

	@Override
	public List<Categoria> listaTodos() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer categoriaId) {
		return categoriaRepository.findOne(categoriaId);
	}

	@Override
	public List<Categoria> listaFilhas(Integer parentId) {
		return categoriaRepository.findByCategoriaPai(parentId);
	}

	@Override
	public List<Categoria> listarPaiAndFilhas(Integer parentId) {
		Categoria pai = this.buscarPorId(parentId);
		return categoriaRepository.findByCategoriaIdOrCategoriaPai(parentId, parentId);
	}

	@Override
	public Categoria editar(Categoria ca) {

		Categoria categoria = categoriaRepository.findOne(ca.getCategoriaId());

		if (categoria.getNome() != ca.getNome()) {
			categoria.setNome(ca.getNome());
		}

		if (categoria.getCategoriaPai() != ca.getCategoriaPai()) {
			categoria.setCategoriaPai(ca.getCategoriaPai());
		}
		categoriaRepository.saveAndFlush(categoria);

		return null;
	}

	@Override
	public Boolean possuiFilhas(Integer categoriaId) {

		List<Categoria> filhas = this.listaFilhas(categoriaId);

		Boolean retorno;

		if (filhas != null) {
			retorno = true;
		} else {
			retorno = false;
		}

		return retorno;

	}

	@Override
	public void remover(Integer categoriaId) {

	// TODO ver retorno - faz um exception de que não pode apagar pq tem associação. Avaliar: controller, fazer um find pra garantir q excluiu e retornar no swagger.
	//TODO padrao de projeto para tirar os if's

		List<Receita> receitas = receitaServiceImpl.receitaPorCategoria(categoriaId);

		if(receitas == null){
			if(!this.possuiFilhas(categoriaId)){
					categoriaRepository.delete(categoriaId);
			}else{
				List<Categoria> categoriasFilhas = this.listaFilhas(categoriaId);

				Integer filhas = 0;

				for (int i =0; i<categoriasFilhas.size();i++) {

					if(filhas < receitaServiceImpl.receitaPorCategoria(categoriasFilhas.indexOf(i)).size())
					filhas = receitaServiceImpl.receitaPorCategoria(categoriasFilhas.indexOf(i)).size();

				}
				if (filhas ==0 ){
					categoriaRepository.delete(categoriaId);
				}
			}

		}

	}

}
