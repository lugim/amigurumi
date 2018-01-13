package br.com.meuprojeto.crochet.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="categoria_id")
	private Integer categoriaId ;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="categoria_pai")
	private Categoria categoriaPai;

	
	public Categoria(String nome, Categoria categoriaPai) {
		this.nome = nome;
		this.categoriaPai = categoriaPai;
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public Categoria () {}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	@Override
	public String toString() {
		
		String retorno = "Categoria: "+this.getNome();
		
		if(this.getCategoriaPai() != null) {
			retorno += "Categoria Pai: "+ this.getCategoriaPai().getNome();
		}
		
		return retorno;
	}
	
}
