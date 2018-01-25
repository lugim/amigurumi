package br.com.meuprojeto.crochet;

import br.com.meuprojeto.crochet.models.Categoria;
import br.com.meuprojeto.crochet.repositories.CategoriaRepository;
import br.com.meuprojeto.crochet.repositories.ReceitaRepository;
import br.com.meuprojeto.crochet.services.CategoriaServiceImpl;
import br.com.meuprojeto.crochet.services.ReceitaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.isNull;
import static  org.mockito.Mockito.verify;
import static  org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceImplTest {

    @InjectMocks
    public CategoriaServiceImpl categoriaService;

    @Mock
    public CategoriaRepository categoriaRepository;

    @InjectMocks
    public ReceitaService receitaService;

    @Mock
    public ReceitaRepository receitaRepository;

    @Test
    public void TesteAdicionarListaCategorias(){

        Categoria categoria1 = new Categoria("Categoria 1", null);
        Categoria categoria2 = new Categoria("Categoria 1-1", categoria1);
        Categoria categoria3 = new Categoria("Nova categoria",null);

        categoriaService.adicionar(Arrays.asList(categoria1, categoria2, categoria3));
        verify(categoriaRepository,times(1)).save(Arrays.asList(categoria1, categoria2, categoria3));

        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2, categoria3));

        List<Categoria> categorias = categoriaService.listaTodos();
        Assert.assertEquals(3,categorias.size());
    }

    @Test
    public void TesteAdicionaUmaCategoria(){

        Categoria categoria1 = new Categoria("Categoria 1", null);


        categoriaService.adicionar(Arrays.asList(categoria1));
        verify(categoriaRepository,times(1)).save(Arrays.asList(categoria1));

        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1));

        List<Categoria> categorias = categoriaService.listaTodos();
        Assert.assertEquals(1,categorias.size());
    }


    @Test
    public void TesteListaTodos(){
        Categoria categoria1 = new Categoria("Categoria 1", null);
        Categoria categoria2 = new Categoria("Categoria 1-1", categoria1);
        Categoria categoria3 = new Categoria("Nova categoria",null);

        categoriaService.adicionar(Arrays.asList(categoria1, categoria2, categoria3));
        verify(categoriaRepository,times(1)).save(Arrays.asList(categoria1, categoria2, categoria3));

        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2, categoria3));

        List<Categoria> categorias = categoriaService.listaTodos();
        Assert.assertEquals(3,categorias.size());
    }

    @Test
    public void TesteListarPorId(){
        Categoria categoria1 = new Categoria("Categoria 1", null);
        categoria1.setCategoriaId(1);
        Categoria categoria2 = new Categoria("Categoria 1-1", categoria1);
        categoria2.setCategoriaId(2);
        Categoria categoria3 = new Categoria("Nova categoria",null);
        categoria3.setCategoriaId(3);

        categoriaService.adicionar(Arrays.asList(categoria1, categoria2, categoria3));


        when(categoriaRepository.findOne(2)).thenReturn(categoria2);

        Categoria result = categoriaService.buscarPorId(2);
        Assert.assertEquals(2,(int)result.getCategoriaId());
        Assert.assertEquals("Categoria 1-1",result.getNome());
        Assert.assertEquals(categoria1,result.getCategoriaPai());
    }

    @Test
    public void  TesteListaFilhas(){

        Categoria categoria1 = new Categoria("Categoria 1", null);
        categoria1.setCategoriaId(1);
        Categoria categoria2 = new Categoria("Categoria 1-1", categoria1);
        categoria2.setCategoriaId(2);
        Categoria categoria3 = new Categoria("Nova categoria 3",null);
        categoria3.setCategoriaId(3);
        Categoria categoria4 = new Categoria("Nova categoria 3-1",categoria3);
        categoria3.setCategoriaId(4);
        Categoria categoria5 = new Categoria("Categoria 1-2",categoria1);
        categoria3.setCategoriaId(5);
        Categoria categoria6 = new Categoria("Categoria 1-2-1",categoria5);
        categoria3.setCategoriaId(6);

        categoriaService.adicionar(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6));
        verify(categoriaRepository,times(1)).save(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6));

        when(categoriaRepository.findByCategoriaPai(1)).thenReturn(Arrays.asList(categoria2,categoria5));
        List<Categoria> categorias = categoriaService.listaFilhas(1);
        Assert.assertEquals(2,categorias.size());


        when(categoriaRepository.findByCategoriaPai(5)).thenReturn(Arrays.asList(categoria6));
        List<Categoria> categorias2 = categoriaService.listaFilhas(5);
        Assert.assertEquals(1,categorias2.size());

    }

    @Test
    public void TesteListaPaiEFilhas(){

        Categoria categoria1 = new Categoria("Categoria 1", null);
        categoria1.setCategoriaId(1);
        Categoria categoria2 = new Categoria("Categoria 1-1", categoria1);
        categoria2.setCategoriaId(2);
        Categoria categoria3 = new Categoria("Nova categoria 3",null);
        categoria3.setCategoriaId(3);
        Categoria categoria4 = new Categoria("Nova categoria 3-1",categoria3);
        categoria3.setCategoriaId(4);
        Categoria categoria5 = new Categoria("Categoria 1-2",categoria1);
        categoria3.setCategoriaId(5);
        Categoria categoria6 = new Categoria("Categoria 1-2-1",categoria5);
        categoria3.setCategoriaId(6);

        categoriaService.adicionar(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6));
        verify(categoriaRepository,times(1)).save(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6));

        when(categoriaRepository.findByCategoriaIdOrCategoriaPai(1,1)).thenReturn(Arrays.asList(categoria1, categoria2,categoria5));
        List<Categoria> categorias = categoriaService.listarPaiAndFilhas(1);
        Assert.assertEquals(3,categorias.size());


        when(categoriaRepository.findByCategoriaIdOrCategoriaPai(5,5)).thenReturn(Arrays.asList(categoria5,categoria6));
        List<Categoria> categorias2 = categoriaService.listarPaiAndFilhas(5);
        Assert.assertEquals(2,categorias2.size());

    }

    @Test
    public void TesteEditar(){

        Categoria categoria1 = new Categoria("Categoria 1", null);
        categoria1.setCategoriaId(1);
        Categoria categoria2 = new Categoria("Categoria 2", null);
        categoria2.setCategoriaId(2);

        categoriaService.adicionar(Arrays.asList(categoria1, categoria2));
        verify(categoriaRepository,times(1)).save(Arrays.asList(categoria1, categoria2));

        when(categoriaRepository.findOne(2)).thenReturn(categoria2);

        Categoria categoria = categoriaService.buscarPorId(2);
        Assert.assertEquals(2, (int) categoria.getCategoriaId());
        Assert.assertEquals(isNull(), categoria.getCategoriaPai());
        Assert.assertEquals("Categoria 2", categoria.getNome());


        categoria2.setCategoriaPai(categoria1);
        categoria2.setNome("Categoria 1-1");

        categoriaService.editar(categoria2);


        Categoria categoriaEditada = categoriaService.buscarPorId(2);
        Assert.assertEquals(categoria1,categoriaEditada.getCategoriaPai());
        Assert.assertEquals("Categoria 1-1",categoriaEditada.getNome());
    }


    @Test
    public void TesteRemoverCategoria(){

        Categoria categoria1 = new Categoria("Categoria 1", null);
        categoria1.setCategoriaId(1);
        Categoria categoria2 = new Categoria("Categoria 1-1", categoria1);
        categoria2.setCategoriaId(2);
        Categoria categoria3 = new Categoria("Nova categoria 3",null);
        categoria3.setCategoriaId(3);
        Categoria categoria4 = new Categoria("Nova categoria 3-1",categoria3);
        categoria3.setCategoriaId(4);
        Categoria categoria5 = new Categoria("Categoria 1-2",categoria1);
        categoria3.setCategoriaId(5);
        Categoria categoria6 = new Categoria("Categoria 1-2-1",categoria5);
        categoria3.setCategoriaId(6);

        categoriaService.adicionar(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6));
        verify(categoriaRepository,times(1)).save(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6));

        //criar receitas com a categoria

        //when(receitaRepository.getReceitaByCategoria())

        categoriaService.remover(6);

        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2,categoria3, categoria4,categoria5));
        List<Categoria> categoriaList = categoriaService.listaTodos();
        Assert.assertEquals(5,categoriaList.size());




    }


}
