package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Venda;

public class VendasDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3866019007878259747L;

	private DAO<Venda> dao;

	@Inject
	private EntityManager em;

	@PostConstruct
	void init() {
		this.dao = new DAO<Venda>(this.em, Venda.class);
	}

	public List<Venda> listaTodos() {
		return dao.listaTodos();
	}

}
