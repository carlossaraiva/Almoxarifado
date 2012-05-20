package almoxarifado.classes.logico;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

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
	
    public void insereFuncionario()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Funcionario values (forCodigo.nextval,'"+this.nome+"','"+this.registro+",'"+this.sexo+"')";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void alterarProduto()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update Funcionario set forNome = '"+this.nome+"', forRegistro = '"+this.registro+"', forSexo = '"+this.sexo+"')";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void excluirFuncionario(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Funcionario where forNome = '"+nome+"'";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaFuncionario(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Funcionario where forNome like '%"+nome+"%'";
        JOptionPane.showMessageDialog(null, sql);
        ResultSet result = conn.executaBusca(sql);
        return result;
    }

	
	
	
}