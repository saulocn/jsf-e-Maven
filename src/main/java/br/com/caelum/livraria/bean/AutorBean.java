package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Controller
public class AutorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8864576577481472949L;

	private Autor autor = new Autor();

	private Integer autorId;

	@Inject
	private AutorDao autorDao;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
		this.autor = this.autorDao.buscaPorId(autorId);
	}

	@Transactional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.autorDao.adiciona(this.autor);
		} else {
			this.autorDao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "autor?faces-redirect=true";
	}

	@Transactional
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		this.autorDao.remove(autor);
	}

	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
