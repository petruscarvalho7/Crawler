package br.com.crawler.dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

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
		System.out.println("Sucesso!!");
	} catch (Exception e) {
		e.printStackTrace();

	
	}
	
	
	return null;
	}
	
	
}
