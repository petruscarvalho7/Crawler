package br.com.exponent.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;




@Entity
public class ItemPedido implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName="seq_objref_itenpedido",name="objref_itenpedido", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="objref_itenpedido")
	private Integer objref;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="objref_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="objref_produto")
	private Produto produto;
	
	@Column
	private Integer qtd;
	
	
	
	public ItemPedido() {


	}

	
	


	public Integer getQtd() {
		return qtd;
	}





	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}





	public Integer getObjref() {
		return objref;
	}



	public void setObjref(Integer objref) {
		this.objref = objref;
	}



	public Pedido getPedido() {
		return pedido;
	}



	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}



	public Produto getProduto() {
		return produto;
	}



	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	
	
	
	
	
	
}
