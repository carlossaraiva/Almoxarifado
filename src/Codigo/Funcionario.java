package Codigo;

public class Funcionario {
	private String nome;
	private String registro;
	
	
	public Funcionario(){
		
	}
	
	public Funcionario(String nome, String registro){
		
		this.nome = nome;
		this.registro = registro;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", registro=" + registro + "]";
	}

	
	
	
}