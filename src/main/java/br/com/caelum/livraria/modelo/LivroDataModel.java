package br.com.caelum.livraria.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;

import br.com.caelum.livraria.dao.LivroDao;

@Component
public class LivroDataModel extends LazyDataModel<Livro> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2036840763021827679L;

	@Inject
	private LivroDao livroDao;

	@PostConstruct
	void init() {
		super.setRowCount(this.livroDao.quantidadeDeElementos());
	}

	@Override
	public List<Livro> load(int inicio, int quantidade, String sortField, SortOrder sortOrder,
			Map<String, Object> filtros) {
		List<Parametro> parametros = new ArrayList<Parametro>();

		String titulo = (String) filtros.get("titulo");
		String genero = (String) filtros.get("genero");

		if (titulo != null) {
			parametros.add(new Parametro("titulo", titulo));
		}

		if (genero != null) {
			parametros.add(new Parametro("genero", genero));
		}
		return this.livroDao.listaTodosPaginada(inicio, quantidade, parametros, sortField, sortOrder);
	}
}
