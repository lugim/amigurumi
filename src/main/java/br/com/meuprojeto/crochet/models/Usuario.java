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
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer clienteId;
	private String nome;
	private String cpf;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private String email;
	private String senha;
	private Boolean ativo;
	
	@ManyToMany
	private List<Receita> receitas;
	private Date dataInicio;
	private Date dataFim;
	@Temporal(TemporalType.TIME)
	private Date horasConsumidas;


	public Usuario(String nome, String cpf, Date dataNascimento, String email, String senha) {
	    this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getHorasConsumidas() {
		return horasConsumidas;
	}

	public void setHorasConsumidas(Date horasConsumidas) {
		this.horasConsumidas = horasConsumidas;
	}
}
