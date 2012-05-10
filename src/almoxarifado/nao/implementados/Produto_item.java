package almoxarifado.nao.implementados;

import almoxarifado.classes.base.Produto;

public class Produto_item extends  Produto {
	private double valor;

	public Produto_item() {
		
	}

	public Produto_item(String nome, double preco, String marca, double valor) {
		super(nome, preco, marca);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Produto_item [valor=" + valor + ", getNome()=" + getNome()
				+ ", getMarca()=" + getMarca() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
	
}


