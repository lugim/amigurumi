package br.com.meuprojeto.crochet;

import Mock
import br.com.meuprojeto.crochet.models.Usuario;
import br.com.meuprojeto.crochet.repositories.UsuarioRepository;
import br.com.meuprojeto.crochet.services.UsuarioService;
import br.com.meuprojeto.crochet.services.UsuarioServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    public UsuarioServiceImpl usuarioService;

    @Mock
    public UsuarioRepository usuarioRepository;


    @Test
    public void testeAdicionarUsuario(){
        Usuario usuario = new Usuario("Usuario 1", '05276658940','1988-05-19','email@email.com','123456';
        usuarioService.adicionarUsuarios(Arrays.asList(usuario1,usuario2));
    }

    public void testeHashSenha(){
    //TODO encriptar senha quando for fazer a autenticação;
    }




}
