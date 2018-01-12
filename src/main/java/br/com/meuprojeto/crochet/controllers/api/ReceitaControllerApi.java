package br.com.meuprojeto.crochet.controllers.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.meuprojeto.crochet.controllers.CollectionResponse;
import br.com.meuprojeto.crochet.models.Receita;
import br.com.meuprojeto.crochet.resources.request.ReceitaUploadRequest;
import br.com.meuprojeto.crochet.resources.response.ReceitaResponse;
import br.com.meuprojeto.crochet.services.FileUploadService;
import br.com.meuprojeto.crochet.services.ReceitaService;

@Controller
public class ReceitaControllerApi {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private FileUploadService fileUpload;

	private static final String CONTROLLER_REQUEST_PATH = "/receitas";

	@RequestMapping(value = CONTROLLER_REQUEST_PATH, method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<ReceitaResponse>> listarTodos(UriComponentsBuilder builder) throws FileNotFoundException, IOException {
		
		CollectionResponse<ReceitaResponse> receitaResponse = new CollectionResponse<>();

		List<Receita> receitas = receitaService.listarTodos();
		
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
			response.setLink(uriComponentsBuilder.path(CONTROLLER_REQUEST_PATH.concat("download/{receitaId}")).buildAndExpand(receita.getReceitaId()).toString());
		
			listaReceitaResponse.add(response);
		}
		
		receitaResponse.setResult(listaReceitaResponse);
		
		return ResponseEntity.ok().body(receitaResponse);
	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/{receitaId}", method = RequestMethod.GET)
	public ResponseEntity<ReceitaResponse> buscarPorId(@PathVariable Integer receitaId, UriComponentsBuilder builder) {

		Receita receita = receitaService.buscarPorId(receitaId);
			
			ReceitaResponse response = new ReceitaResponse();
			
			response.setAlturaProdutoFinal(receita.getAlturaProdutoFinal());
			response.setAtivo(receita.getAtivo());
			response.setAutoria(receita.getAutoria());
			response.setCategoriaNome(receita.getCategoria().getNome());
			response.setLarguraProdutoFinal(receita.getLarguraProdutoFinal());
			response.setNivelDificuldade(receita.getDificuldade().name());
			response.setReceitaNome(receita.getNome());
			response.setObservacao(receita.getObservacao());;

			UriComponentsBuilder uriComponentsBuilder = builder.cloneBuilder();
			response.setLink(uriComponentsBuilder.path(CONTROLLER_REQUEST_PATH.concat("download/{receitaId}")).buildAndExpand(receita.getReceitaId()).toString());
		
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public void adicionar(@ModelAttribute ReceitaUploadRequest receitaUploadBean, HttpServletResponse response)
			throws IOException {

		MultipartFile arquivo = receitaUploadBean.getArquivo();
		if (!arquivo.isEmpty()) {

			try {

				String pathArquivo = fileUpload.upload(arquivo);
				Receita receita = receitaUploadBean.getReceita();
				receitaService.adicionar(receita, pathArquivo);
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
			receitaService.editar(receita);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/inativar/{receitaId}", method = RequestMethod.PATCH)
	public void inativar(@PathVariable Integer receitaId, HttpServletResponse response) {

		try {
			receitaService.inativar(receitaId);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/buscaPorCategoria/{categoriaId}", method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<ReceitaResponse>> receitaPorCategoria(@PathVariable Integer categoriaId, UriComponentsBuilder builder) {

		CollectionResponse<ReceitaResponse> receitaResponse = new CollectionResponse<>();
		
		List<Receita> receitas =  receitaService.receitaPorCategoria(categoriaId);
		
		List<ReceitaResponse> receitaResponseList = new ArrayList<ReceitaResponse>();

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
			response.setLink(uriComponentsBuilder.path(CONTROLLER_REQUEST_PATH.concat("download/{receitaId}")).buildAndExpand(receita.getReceitaId()).toString());
		
			receitaResponseList.add(response);
		}		
		
		receitaResponse.setResult(receitaResponseList);
		
		return ResponseEntity.ok().body(receitaResponse);
	}

	@RequestMapping(value = CONTROLLER_REQUEST_PATH + "/download/{receitaId}", method = RequestMethod.GET)
	public void downloadReceita(@PathVariable Integer receitaId, HttpServletResponse response) throws IOException {

		response.setContentType("application/pdf");

		File pdf = receitaService.downloadReceita(receitaId);
		response.addHeader("Content-Disposition", "attachment; filename=" + pdf.getName());
		response.setContentLength((int) pdf.length());

		FileInputStream inputStream = new FileInputStream(pdf);
		OutputStream outputStream = response.getOutputStream();

		Integer bytes;
		while ((bytes = inputStream.read()) != -1) {
			outputStream.write(bytes);
		}
		inputStream.close();
		outputStream.close();
	}
}
