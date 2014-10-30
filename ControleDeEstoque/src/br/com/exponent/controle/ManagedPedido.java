package br.com.exponent.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.exponent.entity.Cliente;
import br.com.exponent.entity.ItemPedido;
import br.com.exponent.entity.Pedido;
import br.com.exponent.entity.Produto;
import br.com.exponent.persistence.ClassDao;



@ManagedBean(name="beanPedido")
@SessionScoped
public class ManagedPedido implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private List<Pedido> pedidosList;
	private ClassDao<Pedido> daoPedido;
	private ClassDao<ItemPedido> daoItensPedido ;
	private ItemPedido itenPedido;
	private List<ItemPedido> itenPedidos;
	private Cliente cliente;
	private Integer qtdProd;
	private Produto produto;
	private Double total;
	

	public ManagedPedido() {
		daoPedido = new ClassDao<Pedido>(Pedido.class);
		daoItensPedido = new ClassDao<ItemPedido>(ItemPedido.class);
		produto = new Produto();
		cliente = new Cliente();
		qtdProd = 1;
		itenPedidos = new ArrayList<ItemPedido>();
		itenPedido = new ItemPedido();
		pedido = new Pedido();
		total = 0.;
	}

	
	
	

	
	
	

	
	
//Regras de neg�cios	
	

	public String selecionado(){
		
	pedido = new Pedido();
	pedido.setCliente(cliente);
	pedido.setDtPedido(new Date());
	itenPedido.setPedido(pedido);
	itenPedido.setProduto(produto);
	itenPedido.setQtd(qtdProd);
	
	itenPedidos.add(itenPedido);
	
	total = total+( produto.getPreco()*qtdProd);	
	
	pedido.setTotal(total);
	System.out.println("Valor do Total do Pedidos:  "+total.toString());
		itenPedido = new ItemPedido();
		System.out.println(produto.getDescricao()+"  "+" qtd "+total+" "+"Cont "+itenPedidos.size());
		qtdProd = 1;		
		return null;
		
}
	
	
	public String remover(){
		itenPedidos.remove(itenPedido);
		total = total - (itenPedido.getProduto().getPreco()*itenPedido.getQtd());
		pedido.setTotal(total);
		return null;
	}
	
	
	
	//Formata para separar por virgula
	/*
	private String format(String valor){
		String con[] = new String[1];
		con = valor.split(",");
		valor = con[0]+"."+con[1];
		return valor;
	}

	//Formata para separar por ponto
	private String format(Double valor){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(valor);
	}

	*/
	
	
	public String limparPedido(){
		itenPedidos.clear();
		total = 0.;
		cliente = new Cliente();
		return null;
	}
	
	
	
	public String finalizarPedido(){
		
		System.out.println(pedido.toString());
		FacesContext fc = FacesContext.getCurrentInstance();

		if(cliente.getNome()==null || itenPedidos.size() < 1) {
			FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Insira os dados corretamente:", " Cliente ou Produto vazio");
			fc.addMessage(null, ms);
			return null;
		}
		
	System.out.println("Numeros de produtos no momento: "+ itenPedidos.size());
	
			//por default o pedido � setado como aberto
			pedido.setStatus("Em aberto");
			
			try {			
				
				if(pedido.getFormaPg()==""){
					pedido.setFormaPg("Dinheiro ");
				}
				
			pedido = daoPedido.save(pedido);

				
				for(int i = 0; i< itenPedidos.size(); i++){
					itenPedido.setPedido(pedido);
					itenPedido.setProduto(itenPedidos.get(i).getProduto());
					itenPedido.setQtd(itenPedidos.get(i).getQtd());
					daoItensPedido.create(itenPedido);
					itenPedido = new ItemPedido();
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
				fc.addMessage(null, ms);
		
			
			}
			
			System.out.println(">>>>>>>>>>>PEDIDO CRIADO COM SUCESSO!!!!!!!<<<<<<<<<");

		cliente = new Cliente();
		pedido = new Pedido();
		itenPedidos = new ArrayList<ItemPedido>();
		total = 0.;
		return "verPedidos.jsf";
	}
	
	
	//Fims Neg�cios
	
	
	
	
	
//Metodos Gets Sets Para sincroniza��o dos Beans


	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getQtdProd() {
		return qtdProd;
	}

	public void setQtdProd(Integer qtdProd) {
		this.qtdProd = qtdProd;
	}

	public ItemPedido getItenPedido() {
		return itenPedido;
	}

	public void setItenPedido(ItemPedido itenPedido) {
		this.itenPedido = itenPedido;
	}

	public List<ItemPedido> getItenPedidos() {
		return itenPedidos;
	}

	public void setItenPedidos(List<ItemPedido> itenPedidos) {
		this.itenPedidos = itenPedidos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidosList() {
		return pedidosList;
	}

	public void setPedidosList(List<Pedido> pedidosList) {
		this.pedidosList = pedidosList;
	}


	//Fim Gets Sets
	
	
	
}
