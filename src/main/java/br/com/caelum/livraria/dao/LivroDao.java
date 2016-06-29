package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Parametro;

public class LivroDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -614145449017641172L;

	@Inject
	EntityManager em;

	private DAO<Livro> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Livro>(this.em, Livro.class);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	public List<Livro> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public List<Livro> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}

	public int quantidadeDeElementos() {
		return dao.quantidadeDeElementos();
	}

	public List<Livro> listaTodosPaginada(int inicio, int quantidade, List<Parametro> parametros, String sortField,
			SortOrder sortOrder) {
		return dao.listaTodosPaginada(inicio, quantidade, parametros, sortField, sortOrder);
	}

}
