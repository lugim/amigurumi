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

	
	public Categoria(String nome, Categoria categoria_pai) {
		this.nome = nome;
		this.categoriaPai = categoria_pai;
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public Categoria () {}
	
	
	public Integer getCategoria_id() {
		return categoriaId;
	}
	public void setCategoria_id(Integer categoria_id) {
		this.categoriaId = categoria_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Categoria getCategoria_pai() {
		return categoriaPai;
	}
	public void setCategoria_pai(Categoria categoria_pai) {
		this.categoriaPai = categoria_pai;
	}
	
	@Override
	public String toString() {
		
		String retorno = "Categoria: "+this.getNome();
		
		if(this.getCategoria_pai() != null) {
			retorno += "Categoria Pai: "+ this.getCategoria_pai().getNome();
		}
		
		return retorno;
	}
	
}
