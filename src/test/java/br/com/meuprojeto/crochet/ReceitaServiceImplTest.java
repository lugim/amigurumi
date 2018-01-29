package br.com.meuprojeto.crochet;


import br.com.meuprojeto.crochet.models.Categoria;
import br.com.meuprojeto.crochet.models.NivelDificuldadeReceita;
import br.com.meuprojeto.crochet.models.Receita;
import br.com.meuprojeto.crochet.repositories.CategoriaRepository;
import br.com.meuprojeto.crochet.repositories.ReceitaRepository;
import br.com.meuprojeto.crochet.services.CategoriaService;
import br.com.meuprojeto.crochet.services.CategoriaServiceImpl;
import br.com.meuprojeto.crochet.services.ReceitaServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static  org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReceitaServiceImplTest {

    @InjectMocks
    private ReceitaServiceImpl receitaService;

    @Mock
    private ReceitaRepository receitaRepository;

    @Mock
    private CategoriaServiceImpl categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;


    @Test
    public void testaAdicionarReceita(){
        Categoria categoria1 = categoriaService.buscarPorId(1);
        Receita receita1 = new Receita("Receita 1", "Autor 1",categoria1, NivelDificuldadeReceita.FACIL);
        String path1 = "D:/amigurumi/apple.pdf";
        receitaService.adicionar(receita1,path1);
        verify(receitaRepository,times(1)).saveAndFlush(receita1);
    }

    @Test
    public void TestaListarTodos(){

        Categoria categoria1 = categoriaService.buscarPorId(1);
        Receita receita1 = new Receita("Receita 1", "Autor 1",categoria1, NivelDificuldadeReceita.FACIL);
        String path1 = "D:/amigurumi/apple.pdf";
        receitaService.adicionar(receita1,path1);

        Receita receita2 = new Receita("Receita 2", "Autor 2",categoria1, NivelDificuldadeReceita.FACIL);
        String path2 = "D:/amigurumi/SHARKSOCKS.pdf";
        receitaService.adicionar(receita2,path2);

        Categoria categoria2 = categoriaService.buscarPorId(2);
        Receita receita3 = new Receita("Receita 3", "Autor 3",categoria2, NivelDificuldadeReceita.INTERMEDIARIO);
        String path3 = "D:/amigurumi/SunniPattern.pdf";
        receitaService.adicionar(receita3,path3);

        Receita receita4 = new Receita("Receita 4", "Autor 4",categoria1, NivelDificuldadeReceita.FACIL);
        String path4 = "D:/amigurumi/Tiny_T-Rex_Pattern.pdf";
        receitaService.adicionar(receita4,path4);

        verify(receitaRepository,times(1)).saveAndFlush(receita1);
        verify(receitaRepository,times(1)).saveAndFlush(receita2);
        verify(receitaRepository,times(1)).saveAndFlush(receita3);
        verify(receitaRepository,times(1)).saveAndFlush(receita4);

        when(receitaRepository.findAll()).thenReturn(Arrays.asList(receita1,receita2,receita3,receita4));

        List<Receita> receitas = receitaService.listarTodos();

        Assert.assertEquals(4,receitas.size());
    }

    @Test
    public void TestaBuscarPorId(){
        Categoria categoria1 = categoriaService.buscarPorId(1);
        Receita receita1 = new Receita("Receita 1", "Autor 1",categoria1, NivelDificuldadeReceita.FACIL);
        String path1 = "D:/amigurumi/apple.pdf";
        receita1.setReceitaId(1);
        receitaService.adicionar(receita1,path1);

        Receita receita2 = new Receita("Receita 2", "Autor 2",categoria1, NivelDificuldadeReceita.FACIL);
        String path2 = "D:/amigurumi/SHARKSOCKS.pdf";
        receita2.setReceitaId(2);
        receitaService.adicionar(receita2,path2);

        when(receitaRepository.findOne(2)).thenReturn(receita2);

        Receita receita = receitaService.buscarPorId(2);

        Assert.assertEquals(2, (int) receita.getReceitaId());
        Assert.assertEquals("Receita 2",receita.getNome());

    }

    @Test
    public void TestaEditarReceita(){

        Categoria categoria1 = categoriaService.buscarPorId(1);
        Receita receita1 = new Receita("Receita 1", "Autor 1",categoria1, NivelDificuldadeReceita.FACIL);
        String path1 = "D:/amigurumi/apple.pdf";
        receita1.setReceitaId(1);
        receitaService.adicionar(receita1,path1);

        receita1.setAutoria("Novo Autor");
        receita1.setDificuldade(NivelDificuldadeReceita.INTERMEDIARIO);
        receita1.setAlturaProdutoFinal(25.0);

        receitaService.editar(receita1);

        when(receitaRepository.findOne(1)).thenReturn(receita1);

        Receita receita = receitaService.buscarPorId(1);

        Assert.assertEquals("Novo Autor", receita.getAutoria());
        Assert.assertEquals(Double.valueOf(25.0),receita.getAlturaProdutoFinal());
        Assert.assertEquals(NivelDificuldadeReceita.INTERMEDIARIO, receita.getDificuldade());

    }

    @Test
    public void TestaInativarReceita(){

        Categoria categoria1 = categoriaService.buscarPorId(1);
        Receita receita1 = new Receita("Receita 1", "Autor 1",categoria1, NivelDificuldadeReceita.FACIL);
        String path1 = "D:/amigurumi/apple.pdf";
        receita1.setReceitaId(1);
        receita1.setAtivo(true);

        when(receitaRepository.findOne(1)).thenReturn(receita1);

        receitaService.adicionar(receita1,path1);

        receitaService.inativar(1);



        Receita receita = receitaService.buscarPorId(1);

        Assert.assertFalse(receita.getAtivo());
        }

    @Test
    public void TesteListarReceitaPorCategoria(){

        Categoria categoria1 = categoriaService.buscarPorId(1);
        Receita receita1 = new Receita("Receita 1", "Autor 1",categoria1, NivelDificuldadeReceita.FACIL);
        String path1 = "D:/amigurumi/apple.pdf";
        receitaService.adicionar(receita1,path1);

        Receita receita2 = new Receita("Receita 2", "Autor 2",categoria1, NivelDificuldadeReceita.FACIL);
        String path2 = "D:/amigurumi/SHARKSOCKS.pdf";
        receitaService.adicionar(receita2,path2);

        Categoria categoria2 = categoriaService.buscarPorId(2);
        Receita receita3 = new Receita("Receita 3", "Autor 3",categoria2, NivelDificuldadeReceita.INTERMEDIARIO);
        String path3 = "D:/amigurumi/SunniPattern.pdf";
        receitaService.adicionar(receita3,path3);

        Receita receita4 = new Receita("Receita 4", "Autor 4",categoria1, NivelDificuldadeReceita.FACIL);
        String path4 = "D:/amigurumi/Tiny_T-Rex_Pattern.pdf";
        receitaService.adicionar(receita4,path4);

        when(categoriaRepository.findOne(1)).thenReturn(categoria1);
        when(receitaRepository.getReceitaByCategoria(categoria1)).thenReturn(Arrays.asList(receita1, receita2));

        List<Receita> receitas = receitaService.receitaPorCategoria(1);

        Assert.assertEquals(2,receitas.size());


    }






}
