package br.com.exponent.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "cliente")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName = "seq_objref_cliente", initialValue = 1, allocationSize = 1, name = "objref_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objref_cliente")
	private Integer objref;
	@Column
	private String nome;
	@Column
	private String nomeLoja;

	@Column
	private String rua;

	@Column
	private String bairro;

	@Column
	private String cidade;

	@Column
	private String estado;

	@Column
	private String numero;

	@Column
	private String complemento;

	@Column(nullable = false)
	private String cpfCnpj;

	public Cliente() {

	}

	public Cliente(Integer objref, String nome, String nomeLoja, String rua,
			String bairro, String cidade, String estado, String numero,
			String complemento, String cpfCnpj) {
		super();
		this.objref = objref;
		this.nome = nome;
		this.nomeLoja = nomeLoja;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.numero = numero;
		this.complemento = complemento;
		this.cpfCnpj = cpfCnpj;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getObjref() {
		return objref;
	}

	public void setObjref(Integer objref) {
		this.objref = objref;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

}
