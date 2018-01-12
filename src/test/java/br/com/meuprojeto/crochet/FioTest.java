package br.com.meuprojeto.crochet;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.meuprojeto.crochet.models.Fio;
import br.com.meuprojeto.crochet.models.TipoCor;
import br.com.meuprojeto.crochet.models.TipoFio;
import br.com.meuprojeto.crochet.repositories.FioRepository;
import br.com.meuprojeto.crochet.services.FioServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FioTest {

	@InjectMocks
	public FioServiceImpl fioService;
	
	@Mock
	public FioRepository fioRepositorioMock;
	
//	@Test
//	 public void TesteCadastroFios() {
//	
//	Fio fio1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
//	Fio fio2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
//	Fio fio3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);
//
//	List<Fio> fios = new ArrayList<>();
//	
//	fios.add(fio1);
//	fios.add(fio2);
//	fios.add(fio3);
//	
//	HttpServletResponse response = mock(HttpServletResponse.class);
//	
//	FioRepository fioRepositorioMock = mock(FioRepository.class);
//	when(fioRepositorioMock.findAll()).thenReturn(fios);	
//	
//	fioControllerApi.addFio(fios, response);
//	
//	verify(fioRepositorioMock, times(1)).save(fios);
//	
//	}
//	
			
	@Test
	public void TesteListaFiosSemParametro() {

	Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
	Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
	Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

	when(fioRepositorioMock.findAll()).thenReturn(Arrays.asList(fioFalso1, fioFalso2, fioFalso3));
	
	List<Fio> listaResult = fioService.listaFios(null, null); // espera 2 parametros, mas são @RequiredFalse
	Assert.assertFalse(listaResult.isEmpty());	

	}
	
	@Test
	public void TesteListaFiosComTipoFioETipoCor() {
		

	
	Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
	Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
	Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);


	when(fioRepositorioMock.findByTipoFioAndTipoCor(TipoFio.LINHA, TipoCor.SOLIDA)).thenReturn(Arrays.asList(fioFalso3));
	
	List<Fio> listaResult = fioService.listaFios(TipoFio.LINHA, TipoCor.SOLIDA);
	Assert.assertEquals(1, listaResult.size());
		for(Fio fio:listaResult) {
			Assert.assertEquals("Cisne", fio.getMarca());
			Assert.assertEquals(TipoFio.LINHA, fioFalso3.getTipoFio());
			Assert.assertEquals(TipoCor.SOLIDA, fioFalso3.getTipoCor());
			Assert.assertEquals("Branca", fio.getNomeCor());
			Assert.assertEquals("C013", fioFalso3.getCodigoCor());
			Assert.assertEquals(Double.valueOf(80), fio.getMetragemInicial());
			Assert.assertEquals(Double.valueOf(40), fio.getPesoInicial());
		}

	}

	
//	@Test
//	public void TesteBuscaPorFioId() {
//		
//		FioRepository fioRepositorioMock = mock(FioRepository.class);
//		when(fioRepositorioMock.findOne()).thenReturn();	
//		
//	
//	}
	
	
	
	

}
