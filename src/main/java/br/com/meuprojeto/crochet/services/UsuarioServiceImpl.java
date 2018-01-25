package br.com.meuprojeto.crochet.services;


import br.com.meuprojeto.crochet.models.Usuario;
import br.com.meuprojeto.crochet.repositories.UsuarioRepository;
import jdk.nashorn.internal.objects.NativeString;
import org.springframework.beans.factory.annotation.Autowired;
import sun.awt.image.IntegerComponentRaster;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    public UsuarioRepository usuarioRepository;


    @Override
    public void adicionarUsuario(Usuario usuario) {

       Usuario usrEmail = usuarioRepository.findByEmail(usuario.getEmail());

        if(usrEmail == null) {
            usuarioRepository.saveAndFlush(usuario);
        }else{
            //exception?
            /*
            throw new EmailExistsException(
            "There is an account with that email adress:" + accountDto.getEmail());
             */
        }
    }

    @Override
    public List<Usuario> listaUsuarios() {
       return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer usuarioId) {
        return usuarioRepository.findOne(usuarioId);
    }

    @Override
    public void editarUsuario(Usuario usuario) {

        usuarioRepository.saveAndFlush(usuario);

    }

    @Override
    public void inativarUsuario(Integer usuarioId) {

        Usuario usuario = this.buscarPorId(usuarioId);
        usuario.setAtivo(false);

        usuarioRepository.saveAndFlush(usuario);

    }

    @Override
    public void reativarUsuario(Integer usuarioId) {
        Usuario usuario = this.buscarPorId(usuarioId);
        usuario.setAtivo(true);

        usuarioRepository.saveAndFlush(usuario);
    }
}
