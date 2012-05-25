package almoxarifado.classes.logico;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Funcionario extends Usuario{
    
    /*Sobrecarga de construtores*/
    public Funcionario(){		
    }
    public Funcionario(String nome, String login, String senha)
    {	
        super(nome, login, senha);
    }
    /*Chamo o metodo da classe pai e mando o parametro recebido*/
    public void setSexo(String sexo){
    	super.setSexo(sexo);    	
    }
	
    public void insereFuncionario(int tipCodigo)
    {
        super.insereUsuario(tipCodigo);
    }
    public void alterarFuncionario(int usuCodigo, int tipCodigo)
    {
        super.alterarFornecedor(usuCodigo, tipCodigo);
    }
    public void excluirFuncionario(int usuCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Usuario where usuCodigo = '"+usuCodigo+"'";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaFuncionario(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Usuario where usuNome like '%"+nome+"%'";
        ResultSet result = conn.executaBusca(sql);
        return result;
    }
    
    public String logarFuncionario(String funLogin, String funSenha)
    {
        String usuNome = "";
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select usuLogin, usuSenha, usuNome from Usuario where usuLogin = '"+funLogin+"' and usuSenha = '"+funSenha+"'";
        
        ResultSet resultado = conn.executaBusca(sql);
        try
        {
            while(resultado.next())
            {
                usuNome = resultado.getString("usuNome");
            }
        }catch(SQLException sqlex)
        {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar no banco descrição: "+sqlex.getLocalizedMessage()+".");
        }
        if(usuNome == "")
        {
            JOptionPane.showMessageDialog(null, "Usuario ou senha inválidos.");
        }
        return usuNome;
    }
}