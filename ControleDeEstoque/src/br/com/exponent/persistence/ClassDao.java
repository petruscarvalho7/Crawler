package br.com.exponent.persistence;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;






public class ClassDao<T> implements IDao<T>{
	

	private Class<T> entity;
	private Session session;
	
	
	
	
	public ClassDao(Class<T> entity) {
	this.entity = entity;
	}
	
	//Implementação adptada pra padrão hibernate-openssesion-in-view
	
	
	
	@Override
	public void create(T t) {
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.save(t);
		} catch (HibernateException e) {
			e.printStackTrace();
	}
	
	}
	
	
	public T save(T t) {
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.save(t);			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return t;
	
	}
	
	@Override
	public void update(T t) {
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.update(t);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(T t) {
		
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.delete(t);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public List<T> findAll() {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<T> lista = (List<T>)session.createCriteria(entity).list();
		return lista;
	}
	
	
	public List<T> findOrderAsc(String campo) {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<T> lista = (List<T>)session.createCriteria(entity).addOrder(org.hibernate.criterion.Order.asc(campo)).list();
		return lista;
	}
	
	public List<T> findOrderDesc(String campo) {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<T> lista = (List<T>)session.createCriteria(entity).addOrder(org.hibernate.criterion.Order.desc(campo)).list();
		return lista;
	}

	@Override
	public T findByCod(Integer cod) {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			@SuppressWarnings("unchecked")
			T result = (T) session.createCriteria(entity).add(Restrictions.idEq(cod)).uniqueResult();
		return result;
	}
	
	
	@Override
	public List<T> findByName(String nome) {
	@SuppressWarnings("unchecked")
		List<T> lista = (List<T>)session.createCriteria(entity).add(Restrictions.ilike("nome", nome, MatchMode.START)).list();
		return lista;
	
		
	}
	
	
	
	 @SuppressWarnings("unchecked")
	public List<T> consultaByTipo(int startIndex, Integer sizeBlock, int tipoConsulta, String campo, Object valor) {

	    	session = HibernateUtil.getSessionFactory().getCurrentSession();

         Criteria crit = session.createCriteria(entity);
         if(tipoConsulta==0){
              crit.add(Restrictions.ilike(campo, "%"+valor+"%"));
         }if(tipoConsulta==1){
              crit.add(Restrictions.eq(campo, valor));
         }if(tipoConsulta==2){
              crit.add(Restrictions.ilike(campo, "%"+valor));
         }if(tipoConsulta==3){
              crit.add(Restrictions.ilike(campo, valor+"%"));
         }if(tipoConsulta==4){
              crit.add(Restrictions.lt(campo, valor));
         }if(tipoConsulta==5){
              crit.add(Restrictions.le(campo, valor));
         }if(tipoConsulta==6){
              crit.add(Restrictions.ne(campo, valor));
         }
         if(sizeBlock!=null){
             crit.setMaxResults(sizeBlock);
         }if(startIndex!=0){
             crit.setFirstResult(startIndex);

         }         return (List<T>)crit.list();

	 
	 }

	 

    public Criteria consultaByTipoCriteria(int startIndex, Integer sizeBlock, int tipoConsulta, String campo, Object valor) {

    	
    	session = HibernateUtil.getSessionFactory().getCurrentSession();

    	Criteria crit = session.createCriteria(entity);
        if(tipoConsulta==0){
             crit.add(Restrictions.ilike(campo, "%"+valor+"%"));
        }if(tipoConsulta==1){
             crit.add(Restrictions.eq(campo, valor));
        }if(tipoConsulta==2){
             crit.add(Restrictions.ilike(campo, "%"+valor));
        }if(tipoConsulta==3){
             crit.add(Restrictions.ilike(campo, valor+"%"));
        }if(tipoConsulta==4){
             crit.add(Restrictions.lt(campo, valor));
        }if(tipoConsulta==5){
             crit.add(Restrictions.le(campo, valor));
        }if(tipoConsulta==6){
             crit.add(Restrictions.ne(campo, valor));
        }
        if(sizeBlock!=null){
            crit.setMaxResults(sizeBlock);
        }if(startIndex!=0){
            crit.setFirstResult(startIndex);

        }         return crit;
   }



    
    @SuppressWarnings("unchecked")
	public List<T> consultaHQLList(String consulta) {
        return (List<T>) session.createQuery(consulta);
    }
    
    
    public Query consultaHQL(String consulta) {
  session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Query query = session.createQuery(consulta);
       return query;
    	
    }

    public Criteria consultaByCriteria() {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createCriteria(entity);
    }
	
	
	public void clear(){
		session.flush();
		session.clear();		
	}
	
	
	
	
}
