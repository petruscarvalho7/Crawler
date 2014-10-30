package br.com.exponent.persistence;

import java.util.List;

public interface IDao<T> {
	
	


	
	
	public void create(T t);

	public void update(T t);

	public void delete(T t);
	
	public List<T>findAll();
	
	public T findByCod(Integer cod);
	
	public List<T>findByName(String nome);
	
	
	
}
