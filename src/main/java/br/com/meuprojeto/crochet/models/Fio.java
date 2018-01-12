package br.com.meuprojeto.crochet.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fioId;
	
	private String marca;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoFio tipoFio;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoCor tipoCor;
	
	private String nomeCor;
	
	private String codigoCor;
	
	private Double metragemInicial;
	
	private Double pesoInicial;
	
	@Column(nullable=false, columnDefinition="BOOLEAN default true" ,insertable=false)
	private Boolean ativo;
	
		
	public Fio(String marca, TipoFio tipoFio, TipoCor tipoCor, String nomeCor, String codigoCor, Double metragemInicial,
			Double pesoInicial) {
		super();
		this.marca = marca;
		this.tipoFio = tipoFio;
		this.tipoCor = tipoCor;
		this.nomeCor = nomeCor;
		this.codigoCor = codigoCor;
		this.metragemInicial = metragemInicial;
		this.pesoInicial = pesoInicial;
	}
	
	
	public Fio() {} //precisa ter SEMPRE?


	public Integer getFioId() {
		return fioId;
	}
	
	public void setFioId(Integer fioId) {
		this.fioId = fioId;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public TipoFio getTipoFio() {
		return tipoFio;
	}
	
	public void setTipoFio(TipoFio tipoFio) {
		this.tipoFio = tipoFio;
	}
	
	public TipoCor getTipoCor() {
		return tipoCor;
	}
	
	public void setTipoCor(TipoCor tipoCor) {
		this.tipoCor = tipoCor;
	}
	
	public String getNomeCor() {
		return nomeCor;
	}
	
	public void setNomeCor(String nomeCor) {
		this.nomeCor = nomeCor;
	}
	
	public String getCodigoCor() {
		return codigoCor;
	}
	
	public void setCodigoCor(String codigoCor) {
		this.codigoCor = codigoCor;
	}
	
	public Double getMetragemInicial() {
		return metragemInicial;
	}
	
	public void setMetragemInicial(Double metragemInicial) {
		this.metragemInicial = metragemInicial;
	}
	
	public Double getPesoInicial() {
		return pesoInicial;
	}
	
	public void setPesoInicial(Double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		
		return "Fio  : Marca "+this.getMarca()+" tipoFio "+ this.getTipoFio() +" tipoCor "+this.getTipoCor()+" cor "+this.getNomeCor()+" codigo "+this.getCodigoCor()+" metragem "+this.getMetragemInicial()+" peso "+this.getPesoInicial()+" Ativo: "+this.getAtivo();
	}
	
}
