package br.com.meuprojeto.crochet.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuprojeto.crochet.models.Receita;
import br.com.meuprojeto.crochet.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private CategoriaService categoriaService;

	public List<Receita> listarTodos() throws FileNotFoundException, IOException {

		List<Receita> receitas = receitaRepository.findAll();

		return receitas;

	}

	public Receita buscarPorId(Integer receitaId) {

		Receita receita = receitaRepository.findOne(receitaId);
		
		return receita;
	}

	public void adicionar(Receita receita, String pathArquivo) {
		// TODO somente pdf no arquivo!
		receita.setPathReceita(pathArquivo);
		receitaRepository.save(receita);
	}

	public void editar(Receita receita) {

		receitaRepository.save(receita);

	}

	public void inativar(Integer receitaId) {

		Receita receita = receitaRepository.findOne(receitaId);

		if (receita.getAtivo() == true) {
			receita.setAtivo(false);
			receitaRepository.save(receita);
		}

	}

	public List<Receita> receitaPorCategoria(Integer categoriaId) {

		List<Receita> receitas = receitaRepository.getReceitaByCategoria(categoriaService.buscarPorId(categoriaId));
		
		return receitas;

	}

	public File downloadReceita(Integer receitaId) {

		Receita receita = receitaRepository.findOne(receitaId);

		File arquivo = new File(receita.getPathReceita());

		return arquivo;

	}

}
