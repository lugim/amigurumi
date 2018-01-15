package br.com.meuprojeto.crochet;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
public class FioServiceImplTest {

	@InjectMocks
	public FioServiceImpl fioService;
	
	@Mock
	public FioRepository fioRepositorioMock;
	
	@Test
	 public void TesteCadastroFios() {

		Fio fio1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		Fio fio2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
		Fio fio3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

		List<Fio> fios = new ArrayList<>();

		fios.add(fio1);
		fios.add(fio2);
		fios.add(fio3);

		when(fioRepositorioMock.findAll()).thenReturn(fios);

		fioService.adicionar(fios);

		verify(fioRepositorioMock, times(1)).save(fios);

		List<Fio> listaResult = fioService.listaFios(null, null);
		Assert.assertEquals(3, listaResult.size());
	}

	@Test
	public void TesteCadastroUmFio() {

		Fio fio1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		fio1.setFioId(1);
		fio1.setAtivo(true);


		fioService.adicionar(Arrays.asList(fio1));

		verify(fioRepositorioMock, times(1)).save(Arrays.asList(fio1));


		when(fioRepositorioMock.findAll()).thenReturn(Arrays.asList(fio1));
		List<Fio> listaResult = fioService.listaFios(null, null);
		Assert.assertEquals(1, listaResult.size());


	}


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
	public void TesteEdicaoFio() {

		Fio fioFalso = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

		when(fioRepositorioMock.findAll()).thenReturn(Arrays.asList(fioFalso));

		fioFalso.setTipoFio(TipoFio.LA);
		List<Fio> listaResult = fioService.listaFios(null, null); // espera 2 parametros, mas são @RequiredFalse = null;

		for(Fio fio:listaResult) {
			Assert.assertEquals(TipoFio.LA, fio.getTipoFio());
		}
	}


	@Test
	public void TesteInativarFio(){
		Fio fio = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);
		fio.setFioId(1);
		fio.setAtivo(true);


		when(fioRepositorioMock.findOne(1)).thenReturn(fio);

		fioService.inativar(1);
		verify(fioRepositorioMock).save(fio);



		Fio retornoFio = fioService.buscarFioPorId(1);

		Assert.assertEquals("Cisne", retornoFio.getMarca());
		Assert.assertEquals(TipoFio.LINHA, retornoFio.getTipoFio());
		Assert.assertEquals(TipoCor.SOLIDA, retornoFio.getTipoCor());
		Assert.assertEquals("Branca", retornoFio.getNomeCor());
		Assert.assertEquals("C013", retornoFio.getCodigoCor());
		Assert.assertEquals(Double.valueOf(80), retornoFio.getMetragemInicial());
		Assert.assertEquals(Double.valueOf(40), retornoFio.getPesoInicial());
		Assert.assertEquals(false, retornoFio.getAtivo());

	}

	@Test
	public void TesteListaFiosSemParam() {

		Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
		Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

		fioService.adicionar(Arrays.asList(fioFalso1,fioFalso2,fioFalso3));

		when(fioRepositorioMock.findAll()).thenReturn(Arrays.asList(fioFalso1, fioFalso2, fioFalso3));

		List<Fio> listaResult = fioService.listaFios(null,null);

		verify(fioRepositorioMock,times(1)).findAll();
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LINHA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.BARBANTE);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.SOLIDA);
		Assert.assertEquals(3, listaResult.size());

	}


	@Test
	public void TesteListaFiosComTipoFioCadastrado() {

		Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
		Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

		fioService.adicionar(Arrays.asList(fioFalso1, fioFalso2, fioFalso3));

		when(fioRepositorioMock.findByTipoFio(TipoFio.LA)).thenReturn(Arrays.asList(fioFalso1, fioFalso2));

		List<Fio> listaResult = fioService.listaFios(TipoFio.LA, null);

		verify(fioRepositorioMock,times(0)).findAll();
		verify(fioRepositorioMock,times(1)).findByTipoFio(TipoFio.LA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LINHA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.BARBANTE);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.SOLIDA);

		Assert.assertEquals(2, listaResult.size());
	}



	@Test
	public void TesteListaFiosComTipoFioNaoCadastrado() {

		Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
		Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

		fioService.adicionar(Arrays.asList(fioFalso1, fioFalso2, fioFalso3));

		when(fioRepositorioMock.findByTipoFio(TipoFio.BARBANTE)).thenReturn(Arrays.asList());

		List<Fio> listaResult = fioService.listaFios(TipoFio.BARBANTE, null);

		verify(fioRepositorioMock,times(0)).findAll();
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LINHA);
		verify(fioRepositorioMock,times(1)).findByTipoFio(TipoFio.BARBANTE);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.SOLIDA);

		Assert.assertEquals(0, listaResult.size());

	}

	@Test
	public void TesteListaFiosComTipoCor() {

		Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
		Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

		fioService.adicionar(Arrays.asList(fioFalso1,fioFalso2,fioFalso3));

		when(fioRepositorioMock.findByTipoCor(TipoCor.MESCLADA)).thenReturn(Arrays.asList(fioFalso2));

		List<Fio> listaResult = fioService.listaFios(null, TipoCor.MESCLADA);
		Assert.assertEquals(1, listaResult.size());

		verify(fioRepositorioMock,times(0)).findAll();
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LINHA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.BARBANTE);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(1)).findByTipoCor(TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.SOLIDA);

		for(Fio fio:listaResult) {
			Assert.assertEquals(TipoCor.MESCLADA, fio.getTipoCor());
		}

	}


	@Test
	public void TesteListaFiosComTipoFioETipoCor() {

	Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
	Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
	Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

	fioService.adicionar(Arrays.asList(fioFalso1,fioFalso2,fioFalso3));

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



	@Test
	public void TesteListarFioByID() {

		Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		fioFalso1.setFioId(1);
		Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
		fioFalso2.setFioId(2);
		Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);
		fioFalso3.setFioId(3);

		fioService.adicionar(Arrays.asList(fioFalso1,fioFalso2,fioFalso3));

		when(fioRepositorioMock.findOne(2)).thenReturn(fioFalso2);

		Fio  fio = fioService.buscarFioPorId(2);

			Assert.assertEquals("Cisne", fio.getMarca());
			Assert.assertEquals(TipoFio.LA, fio.getTipoFio());
			Assert.assertEquals(TipoCor.MESCLADA, fio.getTipoCor());
			Assert.assertEquals("Roxa e Branca", fio.getNomeCor());
			Assert.assertEquals("B024M", fio.getCodigoCor());
			Assert.assertEquals(Double.valueOf(80), fio.getMetragemInicial());
			Assert.assertEquals(Double.valueOf(40), fio.getPesoInicial());

	}

	@Test
	public void TesteListaFiosComTipoFioInvalido() {
		//TODO
		/**
		Fio fioFalso1 = new Fio("Círculo", TipoFio.LA, TipoCor.SOLIDA, "Azul", "05486C", 80.0, 40.0);
		Fio fioFalso2 = new Fio("Cisne", TipoFio.LA, TipoCor.MESCLADA, "Roxa e Branca", "B024M", 80.0, 40.0);
		Fio fioFalso3 = new Fio("Cisne", TipoFio.LINHA, TipoCor.SOLIDA, "Branca", "C013", 80.0, 40.0);

		fioService.adicionar(Arrays.asList(fioFalso1, fioFalso2, fioFalso3));

		when(fioRepositorioMock.findByTipoFio(TipoFio.BARBANTE)).thenReturn(Arrays.asList());

		List<Fio> listaResult = fioService.listaFios(TipoFio.BARBANTE, null);

		verify(fioRepositorioMock,times(0)).findAll();
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LA);
		verify(fioRepositorioMock,times(0)).findByTipoFio(TipoFio.LINHA);
		verify(fioRepositorioMock,times(1)).findByTipoFio(TipoFio.BARBANTE);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoCor(TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.LINHA,TipoCor.SOLIDA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.MESCLADA);
		verify(fioRepositorioMock,times(0)).findByTipoFioAndTipoCor(TipoFio.BARBANTE,TipoCor.SOLIDA);

		Assert.assertEquals(0, listaResult.size());
		*/
	}
}
