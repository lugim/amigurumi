package br.com.meuprojeto.crochet.services;

import br.com.meuprojeto.crochet.models.Categoria;

import java.util.List;

public interface CategoriaService {

    void adicionar(List<Categoria> categorias);

    List<Categoria> listaTodos();

    Categoria buscarPorId(Integer categoriaId);

    List<Categoria> listaFilhas(Integer parentId);

    List<Categoria> listarPaiAndFilhas(Integer parentId);

    Categoria editar(Categoria ca);

    Boolean possuiFilhas(Integer categoriaId);

    void remover(Integer categoriaId);



}
