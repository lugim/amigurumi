package br.com.meuprojeto.crochet.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer clienteId;
	private String nome;
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@ManyToMany
	private List<Receita> receitas;
	private Date dataInicio;
	private Date dataFim;
	@Temporal(TemporalType.TIME)
	private Date horasConsumidas;
	@OneToOne
	private Usuario usuario;
	//usuario
	
}
