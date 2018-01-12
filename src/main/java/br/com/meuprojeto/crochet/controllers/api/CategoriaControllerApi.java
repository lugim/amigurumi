package br.com.meuprojeto.crochet.controllers.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.meuprojeto.crochet.controllers.CollectionResponse;
import br.com.meuprojeto.crochet.models.Categoria;
import br.com.meuprojeto.crochet.services.CategoriaService;

@Controller
public class CategoriaControllerApi {

	@Autowired
	private CategoriaService categoriaServico;

	@RequestMapping(value = "/categorias", method = RequestMethod.POST)
	public void adicionar(@RequestBody List<Categoria> categorias, HttpServletResponse response) {

		if (categorias != null) {

			try {
				categoriaServico.adicionar(categorias);
				response.setStatus(HttpServletResponse.SC_CREATED);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/categorias", method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<Categoria>> listarTodos() {

		List<Categoria> categorias = categoriaServico.listaTodos();

		CollectionResponse<Categoria> listaCategorias = new CollectionResponse<Categoria>();
		listaCategorias.setResult(categorias);

		return ResponseEntity.ok().body(listaCategorias);
	}
	
	
	@RequestMapping(value = "/categorias/listarFilhas/{parentId}", method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<Categoria>> listarFilhas(@PathVariable Integer parentId) {
		
		List<Categoria> categoriasFilhas = categoriaServico.listaFilhas(parentId);
		
		CollectionResponse<Categoria> listaCategoriasFilhas = new CollectionResponse<Categoria>();
		listaCategoriasFilhas.setResult(categoriasFilhas);
		
		return ResponseEntity.ok().body(listaCategoriasFilhas);
	}

	
	
	
	@RequestMapping(value = "/categorias/paiEFilhas/{parentId}", method = RequestMethod.GET)
	public ResponseEntity<CollectionResponse<Categoria>> listarPaiEFilhas(@PathVariable Integer parentId) {
		
		List<Categoria> categorias = categoriaServico.listarPaiAndFilhas(parentId);
		
		CollectionResponse<Categoria> listaCategorias = new CollectionResponse<Categoria>();
		listaCategorias.setResult(categorias);
		
		return ResponseEntity.ok().body(listaCategorias);
	}
	
	
	@RequestMapping(value = "/categorias/{categoriaId}", method = RequestMethod.DELETE)
	public void delete(@RequestBody Integer categoriaId, HttpServletResponse response) {
		try {
			categoriaServico.remover(categoriaId);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/categorias", method = RequestMethod.PUT)
	public void editar(@RequestBody Categoria categoria, HttpServletResponse response) {
		try {
			categoriaServico.editar(categoria);
			response.setStatus(HttpServletResponse.SC_OK);

		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value="/categorias/possuiFilhas/{categoriaId}", method=RequestMethod.GET)
	public ResponseEntity<Boolean> possuiFilhas(@PathVariable Integer categoriaId) {
	
		Boolean filhas = categoriaServico.possuiFilhas(categoriaId);
		return ResponseEntity.ok().body(filhas);
	}

}
