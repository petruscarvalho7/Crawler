package br.com.exponent.entity;

import java.util.List;

public class Usuario {
	
	
	
	private Integer objref;
	private String nome;
	private String login;
	private String senha;
	private boolean ativo;
	private List<Permissao> pemissoes;
	

	
	public Usuario(Integer objref, String nome, String login, String senha,
			boolean ativo, List<Permissao> pemissoes) {
		super();
		this.objref = objref;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.pemissoes = pemissoes;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public List<Permissao> getPemissoes() {
		return pemissoes;
	}
	public void setPemissoes(List<Permissao> pemissoes) {
		this.pemissoes = pemissoes;
	} 
	
	
	
	
	
	
	
}
