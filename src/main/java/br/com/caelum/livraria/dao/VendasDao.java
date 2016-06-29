package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.livraria.modelo.Venda;

@Repository
public class VendasDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3866019007878259747L;

	private DAO<Venda> dao;

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	void init() {
		this.dao = new DAO<Venda>(this.em, Venda.class);
	}

	public List<Venda> listaTodos() {
		return dao.listaTodos();
	}

}
