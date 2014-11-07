package br.com.crawler.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
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
		
		processPage("http://www.tripadvisor.com.br/Hotel_Review-g190454-d3831132-Reviews-Palais_Hansen_Kempinski_Vienna-Vienna.html", idSequencial);
		
	}
 
	public static void processPage(String url, int key) throws SQLException, IOException{
	
			Conteudo con = popularConteudo(url, key);	
			
			Document doc = Jsoup.connect(url).get();
 
			Elements questions = doc.select("div.entry");
			
			for(Element link: questions){
				
				popularComentario(con, url, key, link.select("p").text());
				System.out.println(link.select("p").text());
				key = key + 1;
				
			}
			
		
	}


	private static Conteudo popularConteudo(String url, int key) throws SQLException {
		
		String sql = "";
		Conteudo conteudo = new Conteudo();
		sql = "INSERT INTO conteudo " + "(id,url) VALUES " + "(?,?);";
		PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		conteudo.setId(key);
		conteudo.setUrl(url);
		stmt.setInt(1 , conteudo.getId());
		stmt.setString(2, conteudo.getUrl());
		stmt.execute();
		
		return conteudo;
		
	}

	private static void popularComentario(Conteudo c ,String url, int key, String comentario) throws SQLException, IOException {
		
		String sql = "";
		Comentario coment = new Comentario();
		sql = "INSERT INTO comentario " + "(id,comentario,id_conteudo) VALUES " + "(?,?,?);";
		PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		coment.setId(key);
		coment.setConteudo(c);
		stmt.setInt(1 , coment.getId());
		stmt.setString(2, comentario);
		stmt.setInt(3,  coment.getConteudo().getId());
		stmt.execute();
		
			
	}	
}