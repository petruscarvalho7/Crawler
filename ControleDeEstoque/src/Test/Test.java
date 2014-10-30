package Test;
import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class Test {
	
	
	
	
	
	public static void main(String[] args) {
	
		
		/*
		
		ClassDao<Produto> daoP = new ClassDao<Produto>(Produto.class);
		ClassDao<Cliente> daoC = new ClassDao<Cliente>(Cliente.class);
		ClassDao<ItenPedido> daoI = new ClassDao<ItenPedido>(ItenPedido.class);
		ClassDao<Pedido> daoPi = new ClassDao<Pedido>(Pedido.class);
		
		
		
		Cliente c = new Cliente();
		c.setNome("Atila");
		c.setNomeLoja("bico doce");
		try {
		
			c = daoC.save(c);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		Pedido p = new Pedido();
		p.setCliente(c);
		p.setDtPedido(new Date());
		p.setPercelamento("test");
		p.setFormaPg("Cheque");
		p.setStatus("Em aberto");
		p.setTotal(50.);
		int num = 1;
			
		try {
			
		p = daoPi.save(p);
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		for(Produto ip : daoP.findAll()){
			System.out.println(ip.getDescricao());
			if(num==10){
				break;
			}
			
			ItenPedido i = new ItenPedido();
			i.setPedido(p);
			i.setProduto(ip);
			i.setQtd(2);
			try {
			
				daoI.create(i);
				
			} catch (Exception e) {
				e.printStackTrace();

			}
			
			num++;
		}
		
		*/
		
String diretorio;

File[] f = File.listRoots();
File f1 = new  File(f[0] +"/arquivo");
f1.mkdir();
diretorio = f[0] +"/arquivo";
	}
	
	
	
}
