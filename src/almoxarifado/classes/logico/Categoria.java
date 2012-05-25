package almoxarifado.classes.logico;

import java.sql.ResultSet;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import javax.swing.JOptionPane;

//Classe meio vazia mas tem que ser criada
public class Categoria {
	//Criando as variaveis globais
	private String nome;
	//Criando os construtores
	public Categoria()
	{}
	public Categoria(String Nome)
	{
		this.nome = Nome;
	}
	//Funï¿½ï¿½o que retorna o nome
	public String getNome() {
		return nome;
	}
	//Procedimento que retorna o valor da global
	public void setNome(String nome) {
		this.nome = nome;
	}
	//Inserir uma nova categoria
	public void insereCategoria()
	{
		//Instanciando uma conexao
		Conexao conn = new Conexao();
		//Se conectando com o banco
		conn.conecta();
		//Criando a query de inserï¿½ï¿½o
		String sql = "insert into Categoria values (catCodigo.nextval, '"+this.nome+"')";
		//Executando a query no banco
		conn.executaSQL(sql);
                String sqle = "commit";
                conn.executaBusca(sqle);
		//Se desconectando do banco
		conn.desconecta();
	}
	//Alterar uma categoria existente
	public void alteraCategoria(int catCodigo)
	{
		//Instanciando uma conexao
		Conexao conn = new Conexao();
		//Se conectando com o banco
		conn.conecta();
		//Criando a query de alteraï¿½ï¿½o
		String sql = "update Categoria set catNome = '"+this.nome+"' where catCodigo = "+catCodigo+"";
		//Executando a query no banco
		conn.executaSQL(sql);
		//Se desconectando do banco
		conn.desconecta();
	}
	//Deletar uma categoria
	public void deletaCategoria(int catCodigo)
	{
		System.out.println(catCodigo);
                //Instanciando uma conexao
		Conexao conn = new Conexao();
		//Se conectando com o banco
		conn.conecta();
		//Criando a query de alteraï¿½ï¿½o
		String sql = "delete from Categoria where catCodigo = "+catCodigo+"";
                
		//Executando a query no banco
		conn.executaSQL(sql);
		//Se desconectando do banco
		conn.desconecta();
	}
	//buscar as categorias existentes no banco
        /**
         * Busca na categoria todas as entradas 
         * @param nome
         * @return 
         */
	public ResultSet buscaCategoria(String nome)
	{
		//Instanciando uma conexao
		Conexao conn = new Conexao();
		//Se conectando com o banco
		conn.conecta();
		//Criando a query de alteraï¿½ï¿½o
		String sqle = "select * from Categoria where catNome like '%"+nome+"%'";
		//Executando a query no banco
		ResultSet result = conn.executaBusca(sqle);
		//Se desconectando do banco
		return result;
	}
}
