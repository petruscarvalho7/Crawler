package br.com.crawler.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.crawler.dao.DatabaseDAO;
import br.com.crawler.model.Comentario;
import br.com.crawler.model.Conteudo;
 
 
public class Crawler {
	
	public static DatabaseDAO db = new DatabaseDAO();
	public static int idSequencial = 1;
 
	public static void main(String[] args) throws SQLException, IOException {
		
		//db.runSql2("TRUNCATE conteudo;");
		processPage("http://www.tripadvisor.com.br/", idSequencial);
	}
 
	public static void processPage(String url, int key) throws SQLException, IOException{
		//check if the given URL is already in database
		String sql = "select * from conteudo where url = '"+url+"'";
		Conteudo con = new Conteudo();
		ResultSet rs = db.runSql(sql);
		sql = "INSERT INTO conteudo " + "(id,url) VALUES " + "(?,?);";
		PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if(rs.next()){
		}else{
			con.setId(key);
			con.setUrl(url);
			stmt.setInt(1 , con.getId());
			stmt.setString(2, con.getUrl());
			stmt.execute();
			
			//popularComentario(con ,con.getUrl(), key);
			
			Document doc = Jsoup.connect(url).get();
 
			if(doc.text().contains("hotel")){
				System.out.println(url);
			}
 
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("abs:href").contains("Hotel_Review"))
					System.out.println(doc.select("div.entry.p.partial_entry").text());
					processPage(link.attr("abs:href"), key+1);
			}
			
		}
	}


	private static void popularComentario(Conteudo c ,String url, int key) throws SQLException, IOException {
		String sql = "select * from comentario where id_conteudo = '"+c.getId()+"'";
		Comentario con = new Comentario();
		ResultSet rs = db.runSql(sql);
		sql = "INSERT INTO comentario " + "(id,conteudo,comentario) VALUES " + "(?,?,?);";
		PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if(rs.next()){
			
		}else{
			con.setId(key);
			con.setConteudo(c);
			stmt.setInt(1 , con.getId());
			stmt.setInt(2,  con.getConteudo().getId());
			
			Document doc = Jsoup.connect(url).get();
			
			if(doc.text().contains("hotel")){
				System.out.println(doc.select("p.partial_entry").text());
			}
 
			Elements questions = doc.select("p.partial_entry");
			for(Element link: questions){
				if(link.attr("partial_entry").contains("hotel")){
					con.setComentario(link.attr("partial_entry"));
					stmt.setString(3, con.getComentario());
				}
			}
			stmt.execute();
		}	
	}
}