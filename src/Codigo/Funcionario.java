package Codigo;

public class Funcionario {
	private String nome;
	private String registro;
	private String sexo;
	
	
	public Funcionario(){
		
	}
	
	public Funcionario(String nome, String registro, String sexo){	
		this.nome = nome;
		this.registro = registro;
		setSexo(sexo);
	}
	
	public String getNome() {
		return nome;
	}
	
    public void setSexo(String sexo){
    	if (sexo == "'M" || sexo == "F" ){
    		this.sexo = sexo;
    	}
    	else{
    		System.out.println("Valor inv‡lido para sexo.");
    	}
    }
    
    public String getSexo(){
    	return this.sexo;
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