package br.com.meuprojeto.crochet.services;


import br.com.meuprojeto.crochet.models.Usuario;
import br.com.meuprojeto.crochet.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;


    @Override
    public void adicionarUsuario(Usuario usuario) {

       Usuario usrEmail = usuarioRepository.findByEmail(usuario.getEmail());

        if(usrEmail == null) {
            usuarioRepository.save(usuarioList);
        }else{
            //exception?
            /*
            throw new EmailExistsException(
            "There is an account with that email adress:" + accountDto.getEmail());
             */
        }
    }
}
