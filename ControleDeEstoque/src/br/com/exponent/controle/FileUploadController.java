package br.com.exponent.controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.tomcat.jni.FileInfo;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.exponent.entity.Produto;
import br.com.exponent.persistence.ClassDao;

@ManagedBean
@RequestScoped
public class FileUploadController {

	private UploadedFile file;
	private String diretorio;
	private Produto produto;
	private ClassDao<Produto> dao;
	private Produto p = null;
	private Produto pTemp = null;
	private int cont = 1;
	private int tamlinha = 5;
	private String Slinha[]  = new String[4];;
	private String linha = "";
	private String cod = "";
	private String Sproduto = "";
	private String un = "";
	private String preco = "";

	public FileUploadController() {

		dao = new ClassDao<Produto>(Produto.class);
	}

	public void upload() throws IOException {
		if (file != null) {
			// Abaixo m�todo para upload que n�o est� funcionando, est� dando
			// erro de acesso negado

			FileReader ler = new FileReader(diretorio + file.getFileName());
			BufferedReader b = new BufferedReader(ler);

			
			//teste git
			// linha recebe a linha lida at� a linha lida ser nula
			while ((linha = b.readLine()) != null) {
				//se a linha vier com a tamanho diferente do que � esperado que � 5
				// � pq os dados do produtos n�o vieram corretamente 
				// o Slinha tem length 4 pq � s� at� ai que ficam os dados que quero
				//do produto
				if (linha.split(";").length == tamlinha) {
					inserirProduto();
				}else{
					System.out.println("Problema ao obter produto importado!!");
					System.out.println("LINHA ----->"+cont);
				}
			}
			ler.close();
			b.close();
		}
		System.out.println("TOTAL DE PRODUTOS ------>>>>>>> " + cont);
	}

	
	public void handleFileUpload(FileUploadEvent event) throws IOException {
		file = event.getFile();
		uploadArquivo();
		upload();
		FacesMessage msg = new FacesMessage("Succesfull", event.getFile()
				.getFileName() + " Arquivo importado com sucesso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	

	public Double convert(String numero) {
		String num[] = new String[1];
		num = numero.split(",");
		numero = num[0] + "." + num[1];
		return Double.parseDouble(numero);
	}

	
	public File abrirArquivo(String diretorio) {
		File arquivo = new File(diretorio);
		return arquivo;
	}

	
	public void uploadArquivo() {

		FacesContext context = FacesContext.getCurrentInstance();
		diretorio = context.getExternalContext().getRealPath("/WebContent/");
		diretorio = diretorio + "\\upload\\";
		File file1 = new File(diretorio);
		file1.mkdirs();

		OutputStream fos;
		try {

			fos = new FileOutputStream(diretorio + file.getFileName());
			OutputStreamWriter os = new OutputStreamWriter(fos);
			BufferedWriter bf = new BufferedWriter(os);

				InputStreamReader rd = new InputStreamReader(file.getInputstream());
				BufferedReader d = new BufferedReader(rd);
				while(d.readLine()!=null){
					bf.write(d.readLine());	
					bf.newLine();
				}
				bf.flush();
				bf.close();
				
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("Diretorio recuperado:  " + diretorio);
		System.out.println(file.getFileName() + " Arquivo>>>>>>>>>>>>>>>>>>>>>");

	}
	
	
	
	public void inserirProduto(){
		
		Slinha = linha.split(";");
		System.out.println("Tamanho Slinha ------>>" + Slinha.length);

		cod = Slinha[0];
		Sproduto = Slinha[1];
		un = Slinha[2];
		preco = Slinha[4];

		Double valor = convert(preco);

		if (cont % 20 == 0) {
			dao.clear();
			System.out.println("limpando Cache Hibernate");
		}
		
		pTemp  = (Produto) dao.consultaByCriteria().add(Restrictions.eq("codigo", cod)).uniqueResult();
		//se n�o encontrar nenhum produto pelo codigo cria um novo
		if(pTemp == null){
			p = new Produto(null, cod, Sproduto, un, valor);
			p = dao.save(p);
			System.out.println("Produto inserido =  " + "Codigo: "
					+ cod + " " + "Produto: " + Sproduto + " "
					+ " UN: " + un + " " + "Preco: " + preco + " "
					+ "Contatdor: " + cont);
	//se nenhum produto foi criado � pq encontrou		
		}else{	
			pTemp.setCodigo(cod);
			pTemp.setDescricao(Sproduto);
			pTemp.setPreco(valor);
			pTemp.setUn(un);
			dao.update(pTemp);
			System.out.println("Produto atualizado ---> "+pTemp.getDescricao());
			pTemp = null;
		}
		
		p = null;
		cont++;
	}
	

	
	
	

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
