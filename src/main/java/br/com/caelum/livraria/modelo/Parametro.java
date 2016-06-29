package br.com.caelum.livraria.modelo;

public class Parametro {
	private String coluna;
	private String valor;

	public Parametro(String coluna, String valor) {
		super();
		this.coluna = coluna;
		this.valor = valor;
	}

	public Parametro() {
		super();
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Parametro [coluna=" + coluna + ", valor=" + valor + "]";
	}
	
	

}
