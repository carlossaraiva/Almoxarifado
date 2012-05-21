package almoxarifado.classes.logico;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Produto {
    private String nome;
    private String marca;
    private int qtdMinima;
    private int qtdAtual;
    private int qtdMaxima;
    private double preco;
    

    public Produto(String nome, String marca){
        this.nome = nome;        
        this.marca = marca;
    }
        
    public Produto() {
		
	}

	public int getQtdMinima() {
		return qtdMinima;
	}
    
	public void setQtdMinima(int qtdMinima) {
		this.qtdMinima = qtdMinima;
	}
	
	public int getQtdAtual() {
		return qtdAtual;
	}
	
	public void setQtdAtual(int qtdAtual) {
		this.qtdAtual = qtdAtual;
	}
	
	public int getQtdMaxima() {
		return qtdMaxima;
	}
	
	public void setQtdMaxima(int qtdMaxima) {
		this.qtdMaxima = qtdMaxima;
	}

    public String getNome(){
        return this.nome;
    }
    
    public String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
    
    public void insereProduto()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Fornecedor values (forCodigo.nextval,'"+this.nome+"','"+this.marca+")";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void alterarProduto(String nome, int i)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update Produto set forNome = '"+this.nome+"', forMarca = '"+this.marca+"'";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    

    
    
    public void excluirProduto(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Produto where forNome = '"+nome+"'";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaProduto(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Produto where forNome like '%"+nome+"%'";
        JOptionPane.showMessageDialog(null, sql);
        ResultSet result = conn.executaBusca(sql);
        return result;
    }

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", marca=" + marca + ", qtdMinima="
				+ qtdMinima + ", qtdAtual=" + qtdAtual + ", qtdMaxima="
				+ qtdMaxima + "]";
	}


    
    
     
    
            
}
