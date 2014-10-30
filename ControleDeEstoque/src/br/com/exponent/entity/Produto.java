package br.com.exponent.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;



@Entity(name="produto")
public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName="seq_objref_produto",initialValue=1, allocationSize=1, name="objref_produto")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="objref_produto")
	private Integer objref;
	@Column
	private String codigo;
	@Column
	private String descricao;
	@Column
	private String un;
	@Column
	private Double preco;
	
	public Produto() {


	}


	
	
	
	public Produto(Integer objref, String codigo, String descricao, String un,
			Double preco) {
		super();
		this.objref = objref;
		this.codigo = codigo;
		this.descricao = descricao;
		this.un = un;
		this.preco = preco;
	}

	
	




	public String getCodigo() {
		return codigo;
	}





	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public Integer getObjref() {
		return objref;
	}




	public void setObjref(Integer objref) {
		this.objref = objref;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public String getUn() {
		return un;
	}




	public void setUn(String un) {
		this.un = un;
	}




	public Double getPreco() {
		return preco;
	}




	public void setPreco(Double preco) {
		this.preco = preco;
	}



	


	
	
	
	
	
	
}
