package br.com.meuprojeto.crochet.resources.response;

public class ReceitaResponse {

	private String link;
	
	private String receitaNome;
	
	private String autoria;

	private String categoriaNome;
	
	private String nivelDificuldade;
	
	private Double alturaProdutoFinal;
	
	private Double larguraProdutoFinal;
	
	private String observacao;
	
	private Boolean ativo;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getReceitaNome() {
		return receitaNome;
	}

	public void setReceitaNome(String nome) {
		this.receitaNome = nome;
	}

	public String getAutoria() {
		return autoria;
	}

	public void setAutoria(String autoria) {
		this.autoria = autoria;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}

	public String getNivelDificuldade() {
		return nivelDificuldade;
	}

	public void setNivelDificuldade(String nivelDificuldade) {
		this.nivelDificuldade = nivelDificuldade;
	}

	public Double getAlturaProdutoFinal() {
		return alturaProdutoFinal;
	}

	public void setAlturaProdutoFinal(Double alturaProdutoFinal) {
		this.alturaProdutoFinal = alturaProdutoFinal;
	}

	public Double getLarguraProdutoFinal() {
		return larguraProdutoFinal;
	}

	public void setLarguraProdutoFinal(Double larguraProdutoFinal) {
		this.larguraProdutoFinal = larguraProdutoFinal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
	
}
