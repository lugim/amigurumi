package br.com.meuprojeto.crochet.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Receita {

	@Id
	@Column(name="receita_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer receitaId;

	private String nome;
	
	private String autoria;
	
	private String pathReceita;
	
	@ManyToOne
	@JoinColumn(name="categoria_id", referencedColumnName="categoria_id")
	private Categoria categoria;

	@Enumerated(EnumType.ORDINAL)
	private NivelDificuldadeReceita nivelDificuldade;
	
	private Double alturaProdutoFinal;
	
	private Double larguraProdutoFinal;
	
	private String observacao;
	
	@Column(nullable = false, columnDefinition = "BOOLEAN default true", insertable = false)
	private Boolean ativo;


	public Receita(String nome, String autoria, Categoria categoria, NivelDificuldadeReceita nivelDificuldade) {
		this.nome = nome;
		this.autoria = autoria;
		this.categoria = categoria;
		this.nivelDificuldade = nivelDificuldade;
	}

	public Integer getReceitaId() {
		return receitaId;
	}

	public void setReceitaId(Integer receitaId) {
		this.receitaId = receitaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String receitaNome) {
		this.nome = receitaNome;
	}

	public String getAutoria() {
		return autoria;
	}

	public void setAutoria(String autoria) {
		this.autoria = autoria;
	}

	public String getPathReceita() {
		return pathReceita;
	}

	public void setPathReceita(String pathReceita) {
		this.pathReceita = pathReceita;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

//	public List<ReceitaConsumoFioServiceImpl> getReceitaConsumoFio() {
//		return receitaconsumoFio;
//	}
//
//	public void setReceitaConsumoFio(List<ReceitaConsumoFioServiceImpl> receitaConsumoFio) {
//		this.receitaconsumoFio = receitaConsumoFio;
//	}

	public NivelDificuldadeReceita getDificuldade() {
		return nivelDificuldade;
	}

	public void setDificuldade(NivelDificuldadeReceita dificuldade) {
		this.nivelDificuldade = dificuldade;
	}

	public Double getAlturaProdutoFinal() {
		return alturaProdutoFinal;
	}

	public void setAlturaProdutoFinal(Double alturaProdutoFinalizado) {
		this.alturaProdutoFinal = alturaProdutoFinalizado;
	}

	public Double getLarguraProdutoFinal() {
		return larguraProdutoFinal;
	}

	public void setLarguraProdutoFinal(Double larguraProdutoFinalizado) {
		this.larguraProdutoFinal = larguraProdutoFinalizado;
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

	public NivelDificuldadeReceita getNivelDificuldade() {
		return nivelDificuldade;
	}

	public void setNivelDificuldade(NivelDificuldadeReceita nivelDificuldade) {
		this.nivelDificuldade = nivelDificuldade;
	}

	@Override
	public String toString() {
		return "Receita: Nome: "+this.getNome()+" autor(a): "+this.getAutoria() + " Categoria: "+ this.getCategoria()+ " Dificuldade: "+ this.getDificuldade()+" Altura: "+ this.getAlturaProdutoFinal()+ " Largura:" + this.getLarguraProdutoFinal() + " Observação: "+ this.getObservacao()+" a receita está salva em: "+this.getPathReceita();
	}
	
}
