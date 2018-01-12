package br.com.meuprojeto.crochet.resources.request;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import br.com.meuprojeto.crochet.models.Receita;

public class ReceitaUploadRequest {

	private MultipartFile arquivo;
	private Receita receita;

	public ReceitaUploadRequest(MultipartFile arquivo, Receita receita) {

		this.arquivo = arquivo;
		this.receita = receita;
	}

	public ReceitaUploadRequest() {

	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public ReceitaUploadRequest(File file, String string) {
		// TODO Auto-generated constructor stub
	}

	public MultipartFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}

}
