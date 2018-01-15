package br.com.meuprojeto.crochet;

import br.com.meuprojeto.crochet.models.Categoria;
import br.com.meuprojeto.crochet.repositories.CategoriaRepository;
import br.com.meuprojeto.crochet.services.CategoriaServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
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
<
        Categoria result = categoriaService.buscarPorId(2);
        Assert.assertEquals(2,(int)result.getCategoriaId());
        Assert.assertEquals("Categoria 1-1",result.getNome());
        Assert.assertEquals(categoria1,result.getCategoriaPai());
    }

    @Test
    public void  TesteListaFilhas(){

    }

}
