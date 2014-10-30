package br.com.exponent.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.exponent.entity.Cliente;
import br.com.exponent.entity.ItemPedido;
import br.com.exponent.entity.Pedido;
import br.com.exponent.entity.Produto;
import br.com.exponent.persistence.ClassDao;
import br.com.exponent.persistence.HibernateUtil;


@ManagedBean(name="beanProduto")
@ViewScoped
public class ManagedProduto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private List<Produto> produtoList;
	private ClassDao<Produto> dao;
	private Integer tipoConsulta;
	private String campo;
	private String pesProd;
	private ClassDao<ItemPedido> daoItemPedido;	
	
	
	public ManagedProduto() {
	produto = new Produto();
	dao = new ClassDao<Produto>(Produto.class);
	
	}

	
	
	
	
	
	

	public Integer getTipoConsulta() {
		return tipoConsulta;
	}



	public void setTipoConsulta(Integer tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}








	public String getCampo() {
		return campo;
	}








	public void setCampo(String campo) {
		this.campo = campo;
	}








	public String getPesProd() {
		return pesProd;
	}



	public void setPesProd(String pesProd) {
		this.pesProd = pesProd;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}
	
	public List<Produto> getProdutoList() {
		return this.produtoList;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	@SuppressWarnings("unchecked")
	public String findProd(){
		try {
					
			produtoList  = 	dao.consultaByTipoCriteria(0, null, tipoConsulta, campo, pesProd)
								.addOrder(Order.asc("descricao")).list();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return null;
		
	
	}
	
	
	
	
	public String salvar(){
		

		FacesContext fc = FacesContext.getCurrentInstance();
		
		if(produto.getObjref()!=0){
			try{
				dao.update(produto);
				fc.addMessage("cadProd", new FacesMessage("Produto Salvo com sucesso!!!"));
				produto = new Produto();
				produtoList = dao.findAll();
			}catch(Exception e){
				e.printStackTrace();
				FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
				fc.addMessage(null, ms);
			}
			
			return null;
			
		}
		
		try{
			dao.create(produto);
			fc.addMessage("cadProd", new FacesMessage("Produto Salvo com sucesso!!!"));
			produto = new Produto();
			produtoList = dao.findAll();
		}catch(Exception e){
			e.printStackTrace();
			FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			fc.addMessage(null, ms);
		}


		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String delete(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try{
			
			
			
			daoItemPedido = new ClassDao<ItemPedido>(ItemPedido.class);
			
			List<ItemPedido> itens = (List<ItemPedido>)daoItemPedido.consultaByCriteria()
				.createAlias("produto", "p")
				.add(Restrictions.eq("p.objref", produto.getObjref())).list();
			
				for(ItemPedido p : itens){
					
					daoItemPedido.delete(p);
					
				}
				
				HibernateUtil.getSessionFactory().getCurrentSession().evict(produto);
				
			produto = dao.findByCod(produto.getObjref());	
			dao.delete(produto);
			fc.addMessage("cadProd", new FacesMessage("Produto Deletado com sucesso!!!"));
		}catch(Exception e){
			e.printStackTrace();
			FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			fc.addMessage(null, ms);
		}
	produto = new Produto();
	findProd();
	return null;
	}	
	
	public String selecionado(){
		System.out.println("<<<<<<<<<<<<<<<<<<<"+produto.getDescricao()+">>>>>>>>>>>>>.");

		return null;
	}
	
}
