package almoxarifado.classes.logico;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Usuario {
    private String nome;
    private String senha;
    private String login;
    private String sexo;
    
    public Usuario(){}

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
    }
    /*Procedimentos e funções para retornar os valores encapsulados*/
    public void setSexo(String sexo){
    	if (sexo == "'M" || sexo == "F" ){
    		this.sexo = sexo;
    	}
    	else{
    		System.out.println("Valor inv�lido para sexo.");
    	}
    }    
    public String getSexo(){
    	return this.sexo;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void insereUsuario(int tipCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Usuario values (usuCodigo.nextval, "+tipCodigo+", '"+this.nome+"', '"+this.login+"', '"+this.senha+"', '"+this.sexo+"')";
        System.out.print(sql);
        conn.executaSQL(sql);
        String sqle = "commit";
        conn.executaBusca(sqle);
        conn.desconecta();
    }
    public void alterarFornecedor(int usuCodigo, int tipCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update Usuario set tipCodigo = "+tipCodigo+", , usuNome = '"+this.nome+"', usuLogin = '"+this.login+"', usuSenha = '"+this.senha+"', usuSexo = '"+this.sexo+"' where usuCodigo = "+usuCodigo+"";
        System.out.println(sql);
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void excluirFornecedor(int usuCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Usuario where usuCodigo = '"+usuCodigo+"'";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaUsuario(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Usuario where usuNome like '%"+nome+"%'";
        ResultSet result = conn.executaBusca(sql);
        return result;
    }
    public ResultSet buscaUsuarioCodigo(int usuCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Usuario where usuCodigo = "+usuCodigo+"";
        ResultSet result = conn.executaBusca(sql);
        return result;
    }
    
}
