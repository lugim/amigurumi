package br.com.meuprojeto.crochet;

import br.com.meuprojeto.crochet.models.Usuario;
import br.com.meuprojeto.crochet.repositories.UsuarioRepository;
import br.com.meuprojeto.crochet.services.UsuarioService;
import br.com.meuprojeto.crochet.services.UsuarioServiceImpl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.constraints.AssertTrue;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    public UsuarioServiceImpl usuarioService;

    @Mock
    public UsuarioRepository usuarioRepository;


    @Test
    public void testeAdicionarUsuario(){
        LocalDate date = LocalDate.of(1988,8,19);
        Usuario usuario = new Usuario("Usuario 1", "01234578952",date ,"email@email.com","123456");
        usuarioService.adicionarUsuario(usuario);

        verify(usuarioRepository, times(1)).save(usuario);

    }

    public void testeHashSenha(){
    //TODO encriptar senha quando for fazer a autenticação;
    }


    @Test
    public void TestaListagemUsuarios(){
        LocalDate date = LocalDate.of(1988,8,19);;
        Usuario usuario1 = new Usuario("Usuario 1", "01234578952",date ,"email@email.com","123456");
        usuarioService.adicionarUsuario(usuario1);


        LocalDate date2 = LocalDate.of(1988,8,19);
        Usuario usuario2 = new Usuario("Usuario 2", "02145638520", date2 ,"email@email.com","123456");
        usuarioService.adicionarUsuario(usuario2);

        verify(usuarioRepository,times(1)).save(usuario1);
        verify(usuarioRepository,times(1)).save(usuario2);

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1,usuario2));

        List<Usuario> usuarios  = usuarioService.listaUsuarios();

        Assert.assertEquals(2,usuarios.size());
    }

    @Test
    public void TestaListarPorId(){

        LocalDate date = LocalDate.of(1988,8,19);
        Usuario usuario1 = new Usuario("Usuario 1", "01234578952",date ,"email@email.com","123456");
        usuario1.setUsuarioId(1);
        usuarioService.adicionarUsuario(usuario1);


        LocalDate date2 = LocalDate.of(1988,8,19);
        Usuario usuario2 = new Usuario("Usuario 2", "02145638520", date2 ,"email@email.com","123456");
        usuario1.setUsuarioId(2);
        usuarioService.adicionarUsuario(usuario2);

        verify(usuarioRepository,times(1)).save(usuario1);
        verify(usuarioRepository,times(1)).save(usuario2);

        when(usuarioRepository.findOne(2)).thenReturn(usuario2);

        Usuario usr = usuarioService.buscarPorId(2);
        Assert.assertEquals("Usuario 2",usr.getNome());

    }

    @Test
    public  void TesteEditarUsuario(){
        LocalDate date = LocalDate.of(1988,8,19);
        Usuario usuario1 = new Usuario("Usuario 1", "01234578952",date ,"email@email.com","123456");
        usuario1.setUsuarioId(1);
        usuario1.setAtivo(true);
        usuarioService.adicionarUsuario(usuario1);

        verify(usuarioRepository,times(1)).saveAndFlush(usuario1);

        usuario1.setEmail("novoemail@email.com");
        usuario1.setCpf("11111111111");
        usuario1.setNome("Usuario");

        usuarioService.editarUsuario(usuario1);

        when(usuarioRepository.findOne(1)).thenReturn(usuario1);
        Usuario usr = usuarioService.buscarPorId(1);

        Assert.assertEquals("Usuario",usr.getNome());
        Assert.assertEquals("11111111111",usr.getCpf());
        Assert.assertEquals("novoemail@email.com",usr.getEmail());

    }


    @Test
    public void TesteInativar(){
        LocalDate date = LocalDate.of(1988,8,19);
        Usuario usuario = new Usuario("Usuario 1", "01234578952",date ,"email@email.com","123456");
        usuario.setUsuarioId(1);
        usuario.setAtivo(true);
        usuarioService.adicionarUsuario(usuario);

        when(usuarioRepository.findOne(1)).thenReturn(usuario);

        usuarioService.inativarUsuario(1);

        verify(usuarioRepository,times(2)).saveAndFlush(usuario);

        Usuario usr = usuarioService.buscarPorId(1);

        Assert.assertFalse(usr.getAtivo());

    }

    @Test
    public  void reativarUsuario(){

        LocalDate date = LocalDate.of(1988,8,19);
        Usuario usuario = new Usuario("Usuario 1", "01234578952",date ,"email@email.com","123456");
        usuario.setUsuarioId(1);
        usuario.setAtivo(false);
        usuarioService.adicionarUsuario(usuario);

        when(usuarioRepository.findOne(1)).thenReturn(usuario);

        usuarioService.reativarUsuario(1);

        verify(usuarioRepository,times(2)).saveAndFlush(usuario);

        Usuario usr = usuarioService.buscarPorId(1);

        Assert.assertTrue(usr.getAtivo());


    }



}
