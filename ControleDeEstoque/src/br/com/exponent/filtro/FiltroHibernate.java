package br.com.exponent.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import br.com.exponent.persistence.HibernateUtil;


/**
 * Servlet Filter implementation class FiltroHibernate
 */
@WebFilter("/*")
public class FiltroHibernate implements Filter {

	
	private SessionFactory sf;
    /**
     * Default constructor. 
     */
    public FiltroHibernate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
			try {
				
				if(sf.getCurrentSession().isOpen()){
					sf.getCurrentSession().close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();

			}

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			System.out.println("Iniciando Transação");
			sf.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			System.out.println("Comitando Transação");
			sf.getCurrentSession().getTransaction().commit();
			
		} catch (Throwable e) {
			
			e.printStackTrace();
			try {
				
				if(sf.getCurrentSession().getTransaction().isActive()){
					sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable e2) {


			}
		}
		
		
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Iniciando configuração de SessionFactory");
		sf = HibernateUtil.getSessionFactory();
		
		if(sf.getCurrentSession().isOpen()){
			System.out.println("Session aberta");
		}
	
	}

}
