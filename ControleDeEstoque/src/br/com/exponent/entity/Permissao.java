package br.com.exponent.entity;

public class Permissao {
	
	
	private Integer objref;
	private String permissao;
	private Usuario usuario;
	
	
	
	




	public Permissao(Integer objref, String permissao, Usuario usuario) {
		super();
		this.objref = objref;
		this.permissao = permissao;
		this.usuario = usuario;
	}








	public Integer getObjref() {
		return objref;
	}








	public void setObjref(Integer objref) {
		this.objref = objref;
	}








	public String getPermissao() {
		return permissao;
	}








	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}








	public Usuario getUsuario() {
		return usuario;
	}








	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
	
}
