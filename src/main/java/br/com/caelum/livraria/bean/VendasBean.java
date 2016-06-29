package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.stereotype.Controller;

import br.com.caelum.livraria.dao.VendasDao;
import br.com.caelum.livraria.modelo.Venda;

@Controller
public class VendasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7017935107172287289L;

	@Inject
	private VendasDao vendasDao;

	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas();
		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		model.addSeries(vendaSerie);

		return model;
	}

	public List<Venda> getVendas() {
		return vendasDao.listaTodos();
	}

}
