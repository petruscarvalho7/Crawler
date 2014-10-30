package br.com.exponent.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.criterion.Restrictions;

import br.com.exponent.entity.Cliente;
import br.com.exponent.entity.ItemPedido;
import br.com.exponent.entity.Pedido;
import br.com.exponent.persistence.ClassDao;
import br.com.exponent.persistence.HibernateUtil;


@ManagedBean(name="beancliente")
@ViewScoped
public class ManagedCliente implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6196083165206401225L;
	private Cliente cliente;
	private List<Cliente> clienteList;
	private ClassDao<Cliente> dao;		
	private ClassDao<Pedido> daoPedido;		
	private ClassDao<ItemPedido> daoItemPedido;			
	private int tipoConsulta;
	private String campo;
	private String pesCli;
	
	
	
	public ManagedCliente() {
		cliente = new Cliente();
		dao = new ClassDao<Cliente>(Cliente.class);			

	}

	
	
	


	
	
	
	
	public int getTipoConsulta() {
		return tipoConsulta;
	}



	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}


	public String getCampo() {
		return campo;
	}



	public void setCampo(String campo) {
		this.campo = campo;
	}


	public String getPesCli() {
		return pesCli;
	}


	public void setPesCli(String pesCli) {
		this.pesCli = pesCli;
	}



	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Cliente> getClienteList() {
			return clienteList;
		
	}


	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}


	public String limpar(){
		cliente = new Cliente();
		return null;
	}
	
public String selecionado(){
	System.out.println("<<<<<<<<<<<<<<<<<<<"+cliente.getNome()+">>>>>>>>>>>>>.");

	return null;
}
	



public String salvar(){
	

	FacesContext fc = FacesContext.getCurrentInstance();
	
	if(cliente.getObjref()!=0){
		try{
			dao.update(cliente);
			fc.addMessage("cadCli", new FacesMessage("Cliente Salvo com sucesso!!!"));
			cliente = new Cliente();
			clienteList = dao.findAll();
		}catch(Exception e){
			e.printStackTrace();
			FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
			fc.addMessage(null, ms);
		}
		
		return null;
		
	}
	
	try{
		dao.create(cliente);
		fc.addMessage("cadCli", new FacesMessage("Cliente Salvo com sucesso!!!"));
		cliente = new Cliente();
		clienteList = dao.findAll();
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
		
		
		
		daoPedido = new ClassDao<Pedido>(Pedido.class);
		daoItemPedido = new ClassDao<ItemPedido>(ItemPedido.class);
		
		List<Pedido> pedidos = (List<Pedido>)daoPedido.consultaByCriteria()
			.createAlias("cliente", "c")
			.add(Restrictions.eq("c.objref", cliente.getObjref())).list();
		
		
			for(Pedido p : pedidos){
				List<ItemPedido> itens =  (List<ItemPedido>) daoItemPedido.consultaByCriteria()
								.createAlias("pedido", "p")
								.add(Restrictions.eq("p.objref", p.getObjref())).list();
				for(ItemPedido i: itens){
				
					daoItemPedido.delete(i);
					
				}
				
				daoPedido.delete(p);
			}
			
			HibernateUtil.getSessionFactory().getCurrentSession().evict(cliente);
			
			cliente = dao.findByCod(cliente.getObjref());	
			dao.delete(cliente);
			fc.addMessage("cadCli", new FacesMessage("Cliente Deletado com sucesso!!!"));
	}catch(Exception e){
		e.printStackTrace();
		FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
		fc.addMessage(null, ms);
	}
cliente = new Cliente();
findCli();
return null;
}	



@SuppressWarnings("unchecked")
public String findCli(){
	FacesContext fc = FacesContext.getCurrentInstance();
	try {
		if(campo =="objref")
		{ 
			Integer pesCli1 =   Integer.parseInt(pesCli); 
			clienteList  = (ArrayList<Cliente>)	dao.consultaByTipoCriteria(0, null, tipoConsulta, campo, pesCli1).list();
			return null;
		}		
		clienteList  = 	(ArrayList<Cliente>) dao.consultaByTipoCriteria(0, null, tipoConsulta, campo, pesCli).list();
	} catch (Exception e) {
		e.printStackTrace();
		FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage());
		fc.addMessage(null, ms);
		}
	return null;
	

}




	
	
}
