package br.com.meuprojeto.crochet.services;

import br.com.meuprojeto.crochet.models.Usuario;

import java.util.List;

public interface UsuarioService {

    void adicionarUsuario(Usuario usuario);

    List<Usuario> listaUsuarios();

    Usuario buscarPorId(Integer i);

    void inativarUsuario(Integer i);

    void editarUsuario(Usuario usuario1);

    void reativarUsuario(Integer i);
}
