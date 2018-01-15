package br.com.meuprojeto.crochet.services;

import br.com.meuprojeto.crochet.models.Receita;

import java.io.File;
import java.util.List;

public interface ReceitaService {

    List<Receita> listarTodos();

    Receita buscarPorId(Integer receitaId);

    void adicionar(Receita receita, String pathArquivo);

    void editar(Receita receita);

    void inativar(Integer receitaId);

    List<Receita> receitaPorCategoria(Integer categoriaId);

    File downloadReceita(Integer receitaId);
}
