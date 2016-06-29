package br.com.caelum.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.modelo.Parametro;

public class DAO<T> {

	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.classe = classe;
		this.em = em;
	}

	public void adiciona(T t) {
		// persiste o objeto
		em.persist(t);
	}

	public void remove(T t) {
		em.remove(em.merge(t));

	}

	public void atualiza(T t) {
		em.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(Integer id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) em.createQuery("select count(n) from livro n").getSingleResult();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);

		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		return lista;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		Root<T> root = query.from(classe);

		if (valor != null)
			query = query.where(em.getCriteriaBuilder().like(root.<String> get(coluna), valor + "%"));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		return lista;
	}

	public int quantidadeDeElementos() {
		long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n").getSingleResult();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int inicio, int quantidade, List<Parametro> parametros, String sortField,
			SortOrder sortOrder) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(classe);
		Root<T> root = query.from(classe);
		List<Predicate> predicates = new ArrayList<Predicate>();

		for (Parametro parametro : parametros) {
			System.out.println(parametro);
			Expression<String> path = criteriaBuilder.upper(root.get(parametro.getColuna()).as(String.class));
			if (parametro.getValor() != null && !parametro.getValor().equals("")) {
				predicates.add(criteriaBuilder.like(path, "%" + parametro.getValor() + "%"));
			}
		}
		System.out.println(sortField);

		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		if (sortField != null && !sortField.equals("null")) {
			if (sortOrder.equals(SortOrder.ASCENDING)) {
				query.orderBy(criteriaBuilder.asc(root.get(sortField)));
			} else if (sortOrder.equals(SortOrder.DESCENDING)) {
				query.orderBy(criteriaBuilder.desc(root.get(sortField)));
			}
		}

		List<T> lista = em.createQuery(query).setFirstResult(inicio).setMaxResults(quantidade).getResultList();

		return lista;
	}

}
