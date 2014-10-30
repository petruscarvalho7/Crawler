package br.com.exponent.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity(name="pedido")
public class Pedido implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName="seq_pedido_objref", initialValue=1, allocationSize=1,name="objref_pedido")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="objref_pedido")
	private Integer objref;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dtPedido;
		
	@ManyToOne
	@JoinColumn(name="objref_cliente")
	private Cliente cliente;
	
	@Column
	private String formaPg;
	
	@Column
	private String percelamento;
	
	@Column
	private Double total;
	
	@Column
	private String status;
	
	
	

	
	
	public Pedido() {


	}


	
	
	
	




	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFormaPg() {
		return formaPg;
	}


	public void setFormaPg(String formaPg) {
		this.formaPg = formaPg;
	}






	public String getPercelamento() {
		return percelamento;
	}






	public void setPercelamento(String percelamento) {
		this.percelamento = percelamento;
	}






	public Integer getObjref() {
		return objref;
	}



	public void setObjref(Integer objref) {
		this.objref = objref;
	}



	


	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Date getDtPedido() {
		return dtPedido;
	}



	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}
	
	
	
	
	
	
	
}
