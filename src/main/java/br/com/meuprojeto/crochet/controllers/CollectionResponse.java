package br.com.meuprojeto.crochet.controllers;

import java.util.List;

/**
 * 
 * @author Luiza
 *
 * @param <T>
 * 
 * Classe para criar um objeto com uma lista, permitindo assim que o ResponseEntity retorno um objeto com a lista desejada.
 * A lista é setada com setResult, e o objeto é criado passando o tipo da lista desejada.
 * 
 */

public class CollectionResponse<T> {

	List<T> result;

	public CollectionResponse() {
		// default constructor
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
