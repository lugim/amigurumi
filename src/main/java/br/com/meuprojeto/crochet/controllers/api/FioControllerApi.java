package br.com.meuprojeto.crochet.controllers.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuprojeto.crochet.controllers.CollectionResponse;
import br.com.meuprojeto.crochet.models.Fio;
import br.com.meuprojeto.crochet.models.TipoCor;
import br.com.meuprojeto.crochet.models.TipoFio;
import br.com.meuprojeto.crochet.services.FioServiceImpl;

@RestController
public class FioControllerApi {
	
	@Autowired
	private FioServiceImpl fioService;
	
	@RequestMapping(value = "/fios", method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<Fio>> listarFios(@RequestParam(required=false) TipoFio tipoFio, @RequestParam(required=false) TipoCor tipoCor) {
		List<Fio> fios = fioService.listaFios(tipoFio, tipoCor);
		CollectionResponse<Fio> listaFios = new CollectionResponse<Fio>();
		listaFios.setResult(fios);
		
		return ResponseEntity.ok().body(listaFios);
	}


	@RequestMapping(value = "/fios", method = RequestMethod.POST)
	public void addFio(@RequestBody List<Fio> fio, HttpServletResponse response) {
		// TODO - verificar metodo para retornar o Iterable<Fio> ou msgErro + httpStatus

		try {
			fioService.adicionar(fio);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) { //TODO mover tratamento de esxceção 
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/fios/{fioId}", method = RequestMethod.GET)  //TODO - retornar collection msm so retornando 1??
	public Fio buscarFioPorId(@PathVariable Integer fioId, HttpServletResponse response) {
		System.out.println("id: " + fioId);

		if (fioId == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);// "Id não informado ou mal formatado"
			return null;
		} else {
			Fio fio = fioService.buscarFioPorId(fioId);
			if(fio != null) {
			response.setStatus(HttpServletResponse.SC_OK);		
			}else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
			return fio;
			
		}
	}

	@RequestMapping(value = "/fios/{fioId}", method = RequestMethod.PATCH) // Patch altera uma parte PUT - altera
																				// todo o obj.
	public void inativaFio(@PathVariable Integer fioId, HttpServletResponse response) {
		// TODO - verificar metodo para retornar o Iterable<Fio> ou msgErro + httpStatus

		if (fioId == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //TODO msg=id não informado
		} else {
			Fio fio = fioService.buscarFioPorId(fioId);

			if (fio == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT); //TODO "Nenhum registro encontrado"
			} else {

				try {
					fioService.inativar(fioId);
					response.setStatus(HttpServletResponse.SC_OK);
				} catch (Exception e) {
					//TODO mover exceptionHandler "Não foi possível atualizar o registro: " + e.getMessage()
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
		}
	}

}
