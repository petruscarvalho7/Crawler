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
	
	private static final String URL = "http://www.tripadvisor.com.br/Hotel_Review-g190454-d3831132-Reviews-Palais_Hansen_Kempinski_Vienna-Vienna.html";
	public static DatabaseDAO db = new DatabaseDAO();
	public static int idSequencial = 1;
	private static int contadorPagComentario = 10;
	private static int keyComentario = 1;
	static Document doc = null;
	
	
	public static void main(String[] args) throws SQLException, IOException {
		
		
		processPage();
		
	}
 
	public static void processPage() throws SQLException, IOException{
	
			Conteudo con = popularConteudo(URL);	
			
			if(doc == null){
				doc =  Jsoup.connect(URL).get();
			}
			
			processaComentarios(URL, con, doc);
			//System.err.println("PASSOU!");
			
			processaArvoreComentarios(doc, URL);
		
	}

	private static void processaArvoreComentarios(Document doc, String url)
			throws SQLException, IOException {
		
		Elements questions = doc.select("a.guiArw");
		
		if((questions.size() == 1) && (questions.first().attr("abs:href").contains(montarOR()))){
			processPage();
		}
		else{
			for(Element link : questions){
				if(link.attr("abs:href").contains(montarOR())){
					processPage();
				}
				
			}
		}
			
			
	}

	private static String montarOR() {
		
		StringBuilder retorno = new StringBuilder("-or");
		retorno.append(contadorPagComentario);
		//System.out.println(retorno.toString());
		contadorPagComentario = contadorPagComentario + 10;
		
		return retorno.toString();
	}

	private static void processaComentarios(String url, Conteudo con,
			Document doc) throws SQLException, IOException {
	
		Elements questions = doc.select("div.entry");
		
		for(Element link: questions){
			
			popularComentario(con, url, link.select("p").text());
			System.out.println(link.select("p").text());
			keyComentario += 1;
		}
		
	}


	private static Conteudo popularConteudo(String url) throws SQLException {
		
		String sql = "";
		Conteudo conteudo = new Conteudo();
		sql = "INSERT INTO conteudo " + "(id,url) VALUES " + "(?,?);";
		PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		conteudo.setId(idSequencial);
		conteudo.setUrl(url);
		stmt.setInt(1 , conteudo.getId());
		stmt.setString(2, conteudo.getUrl());
		stmt.execute();
		
		idSequencial += 1;
		
		return conteudo;
		
	}

	private static void popularComentario(Conteudo c ,String url, String comentario) throws SQLException, IOException {
		
		String sql = "";
		Comentario coment = new Comentario();
		sql = "INSERT INTO comentario " + "(id,comentario,id_conteudo) VALUES " + "(?,?,?);";
		PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		coment.setId(keyComentario);
		coment.setConteudo(c);
		stmt.setInt(1 , coment.getId());
		stmt.setString(2, comentario);
		stmt.setInt(3,  coment.getConteudo().getId());
		stmt.execute();
			
	}

	public static int getContadorPagComentario() {
		return contadorPagComentario;
	}

	public static void setContadorPagComentario(int contadorPagComentario) {
		Crawler.contadorPagComentario = contadorPagComentario;
	}	
}