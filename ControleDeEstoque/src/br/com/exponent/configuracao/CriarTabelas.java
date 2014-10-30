package br.com.exponent.configuracao;

import javax.faces.bean.ManagedBean;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@ManagedBean(name="beanBanco")
public class CriarTabelas {

	

public CriarTabelas() {


}
	
public static void main(String[] args) {
	criar();
}



	
	public static String criar(){
	try {
	System.out.println("Criando banco de dados do zero...");
		Configuration cfg = new Configuration();
		cfg.configure("br/com/exponent/config/pgsql_hibernate.cfg.xml");
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	
	} catch (Exception e) {
		e.printStackTrace();

	
	}
	
	
	return null;
	}
	
	
	
	
	
}
