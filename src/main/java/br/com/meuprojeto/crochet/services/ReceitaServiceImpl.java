package br.com.meuprojeto.crochet.services;

import br.com.meuprojeto.crochet.models.Receita;
import br.com.meuprojeto.crochet.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ReceitaServiceImpl implements ReceitaService{

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;

	@Override
	public List<Receita> listarTodos() {

		return receitaRepository.findAll();

	}

	@Override
	public Receita buscarPorId(Integer receitaId) {
		
		return receitaRepository.findOne(receitaId);
	}

	@Override
	public void adicionar(Receita receita, String pathArquivo) {
		// TODO somente pdf no arquivo!
		receita.setPathReceita(pathArquivo);
		receitaRepository.save(receita);
	}

	@Override
	public void editar(Receita receita) {

		receitaRepository.save(receita);

	}

	@Override
	public void inativar(Integer receitaId) {

		Receita receita = receitaRepository.findOne(receitaId);

		if (receita.getAtivo()) {
			receita.setAtivo(false);
			receitaRepository.save(receita);
		}

	}

	@Override
	public List<Receita> receitaPorCategoria(Integer categoriaId) {
		
		return receitaRepository.getReceitaByCategoria(categoriaServiceImpl.buscarPorId(categoriaId));

	}

	@Override
	public File downloadReceita(Integer receitaId) {

		Receita receita = receitaRepository.findOne(receitaId);

		return new File(receita.getPathReceita());

	}

}
