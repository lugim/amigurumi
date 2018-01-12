package br.com.meuprojeto.crochet.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReceitaConsumoFio{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="receita_consumo_fio_id")
	private Integer receitaConsumoFioId;
	@ManyToOne
 	@JoinColumn(name="receita_id")
	private Receita receita; //FK
	@ManyToOne	
	@JoinColumn(name="fio_id")
	private Fio fio; //FK
	private Double pesoConsumidoKg;
	private Double metragemConsumidaMetros;
	private String observacao;
		
	
	public ReceitaConsumoFio(Receita receita, Fio fio) {
		this.receita = receita;
		this.fio = fio;
	}
	
	
	public Integer getReceitaConsumoFioId() {
		return receitaConsumoFioId;
	}
	
	public void setReceitaConsumoFioId(Integer consumoFioReceitaId) {
		this.receitaConsumoFioId = consumoFioReceitaId;
	}
	
	public Receita getReceita() {
		return receita;
	}
	
	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	
	public Fio getFio() {
		return fio;
	}
	
	public void setFio(Fio fio) {
		this.fio = fio;
	}
//	
	public Double getPesoConsumidoKg() {
		return pesoConsumidoKg;
	}
	
	public void setPesoConsumidoKg(Double pesoConsumido) {
		this.pesoConsumidoKg = pesoConsumido;
	}
	
	public Double getMetragemConsumidaMetros() {
		return metragemConsumidaMetros;
	}
	
	public void setMetragemConsumidaMetros(Double metragemConsumida) {
		this.metragemConsumidaMetros = metragemConsumida;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	
}