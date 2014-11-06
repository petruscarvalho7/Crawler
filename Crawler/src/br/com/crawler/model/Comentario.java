package br.com.crawler.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;



@Entity(name="comentario")
public class Comentario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName="seq_id_comentario",initialValue=1, allocationSize=1, name="id_comentario")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_comentario")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_conteudo")
	private Conteudo conteudo;
	
	@Column
	private String comentario;
	
	public Comentario() {


	}

	
	public Comentario(Integer id, Conteudo conteudo, String comentario) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.comentario = comentario;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Conteudo getConteudo() {
		return conteudo;
	}


	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}