package br.com.meuprojeto.crochet.controllers.api;

import br.com.meuprojeto.crochet.controllers.CollectionResponse;
import br.com.meuprojeto.crochet.models.Receita;
import br.com.meuprojeto.crochet.resources.request.ReceitaUploadRequest;
import br.com.meuprojeto.crochet.resources.response.ReceitaResponse;
import br.com.meuprojeto.crochet.services.FileUploadServiceImpl;
import br.com.meuprojeto.crochet.services.ReceitaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReceitaControllerApi {

	@Autowired
	private ReceitaServiceImpl receitaServiceImpl;

	@Autowired
	private FileUploadServiceImpl fileUpload;

	private static final String CONTROLLER_REQUEST_PATH = "/receitas";

	private static final String DOWNLOAD_PATH = "download/{receitaId}";

	@RequestMapping(value = CONTROLLER_REQUEST_PATH, method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<ReceitaResponse>> listarTodos(UriComponentsBuilder builder){
		
		CollectionResponse<ReceitaResponse> receitaResponse = new CollectionResponse<>();

		List<Receita> receitas = receitaServiceImpl.listarTodos();
		
		List<ReceitaResponse> listaReceitaResponse = new ArrayList<>();
			
		for (Receita receita : receitas) {
			
			ReceitaResponse response = new ReceitaResponse();
			
			response.setAlturaProdutoFinal(receita.getAlturaProdutoFinal());
			response.setAtivo(receita.getAtivo());
			response.setAutoria(receita.getAutoria());
			response.setCategoriaNome(receita.getCategoria().getNome());
			response.setLarguraProdutoFinal(receita.getLarguraProdutoFinal());
			response.setNivelDificuldade(receita.getDificuldade().name());
			response.setReceitaNome(receita.getNome());
			response.setObservacao(receita.getObservacao());

			UriComponentsBuilder uriComponentsBuilder = builder.cloneBuilder();
			response.setLink(uriComponentsBuilder.path(CONTROLLER_REQUEST_PATH.concat(DOWNLOAD_PATH)).buildAndExpand(receita.getReceitaId()).toString());
		
			listaReceitaResponse.add(response);
		}
		
		receitaResponse.setResult(listaReceitaResponse);
		
		return ResponseEntity.ok().body(receitaResponse);
	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/{receitaId}", method = RequestMethod.GET)
	public ResponseEntity<ReceitaResponse> buscarPorId(@PathVariable Integer receitaId, UriComponentsBuilder builder) {

		Receita receita = receitaServiceImpl.buscarPorId(receitaId);
			
			ReceitaResponse response = new ReceitaResponse();
			
			response.setAlturaProdutoFinal(receita.getAlturaProdutoFinal());
			response.setAtivo(receita.getAtivo());
			response.setAutoria(receita.getAutoria());
			response.setCategoriaNome(receita.getCategoria().getNome());
			response.setLarguraProdutoFinal(receita.getLarguraProdutoFinal());
			response.setNivelDificuldade(receita.getDificuldade().name());
			response.setReceitaNome(receita.getNome());
			response.setObservacao(receita.getObservacao());

			UriComponentsBuilder uriComponentsBuilder = builder.cloneBuilder();
			response.setLink(uriComponentsBuilder.path(CONTROLLER_REQUEST_PATH.concat(DOWNLOAD_PATH)).buildAndExpand(receita.getReceitaId()).toString());
		
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public void adicionar(@ModelAttribute ReceitaUploadRequest receitaUploadBean, HttpServletResponse response){

		MultipartFile arquivo = receitaUploadBean.getArquivo();
		if (!arquivo.isEmpty()) {

			try {

				String pathArquivo = fileUpload.upload(arquivo);
				Receita receita = receitaUploadBean.getReceita();
				receitaServiceImpl.adicionar(receita, pathArquivo);
				response.setStatus(HttpServletResponse.SC_CREATED);

			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			// TODO Arquivo não selecionado (add swagger?)
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/{receitaId}", method = RequestMethod.PATCH)
	public void editar(@PathVariable Integer receitaId, @RequestBody Receita receita, HttpServletResponse response) {

		try {
			receitaServiceImpl.editar(receita);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/inativar/{receitaId}", method = RequestMethod.PATCH)
	public void inativar(@PathVariable Integer receitaId, HttpServletResponse response) {

		try {
			receitaServiceImpl.inativar(receitaId);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/buscaPorCategoria/{categoriaId}", method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<ReceitaResponse>> receitaPorCategoria(@PathVariable Integer categoriaId, UriComponentsBuilder builder) {

		CollectionResponse<ReceitaResponse> receitaResponse = new CollectionResponse<>();
		
		List<Receita> receitas =  receitaServiceImpl.receitaPorCategoria(categoriaId);
		
		List<ReceitaResponse> receitaResponseList = new ArrayList<>();

		for (Receita receita : receitas) {
					
			ReceitaResponse response = new ReceitaResponse();
			
			response.setAlturaProdutoFinal(receita.getAlturaProdutoFinal());
			response.setAtivo(receita.getAtivo());
			response.setAutoria(receita.getAutoria());
			response.setCategoriaNome(receita.getCategoria().getNome());
			response.setLarguraProdutoFinal(receita.getLarguraProdutoFinal());
			response.setNivelDificuldade(receita.getDificuldade().name());
			response.setReceitaNome(receita.getNome());
			response.setObservacao(receita.getObservacao());

			UriComponentsBuilder uriComponentsBuilder = builder.cloneBuilder();
			response.setLink(uriComponentsBuilder.path(CONTROLLER_REQUEST_PATH.concat(DOWNLOAD_PATH)).buildAndExpand(receita.getReceitaId()).toString());
		
			receitaResponseList.add(response);
		}		
		
		receitaResponse.setResult(receitaResponseList);
		
		return ResponseEntity.ok().body(receitaResponse);
	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/download/{receitaId}", method = RequestMethod.GET)
	public void downloadReceita(@PathVariable Integer receitaId, HttpServletResponse response) throws IOException {

		response.setContentType("application/pdf");

		OutputStream outputStream = null;

		File pdf = receitaServiceImpl.downloadReceita(receitaId);

		try (FileInputStream inputStream = new FileInputStream(pdf)){

			response.addHeader("Content-Disposition", "attachment; filename=" + pdf.getName());
			response.setContentLength((int) pdf.length());
			outputStream = response.getOutputStream();

			Integer bytes;
			while ((bytes = inputStream.read()) != -1) {
				outputStream.write(bytes);
			}

		}catch (Exception e){
			//TODO tratar exceção
		}

	}
}
