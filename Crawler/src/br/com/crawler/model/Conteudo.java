package br.com.crawler.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



@Entity(name="conteudo")
public class Conteudo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName="seq_id_conteudo",initialValue=1, allocationSize=1, name="id_conteudo")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_conteudo")
	private Integer id;
	@Column
	private String url;
	
	public Conteudo() {


	}

	public Conteudo(Integer id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	


	
	
}
