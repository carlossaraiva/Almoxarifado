package almoxarifado.classes.logico;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Usuario {
    private String nome;
    private int numIdentificacao;
    private String senha;
    private String login;
    private String sexo;
    
    public Usuario(){}

    public Usuario(String nome, int numIdentificacao, String senha, String login) {
        this.nome = nome;
        this.numIdentificacao = numIdentificacao;
        this.senha = senha;
        this.login = login;
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

    public int getNumIdentificacao() {
        return numIdentificacao;
    }

    public void setNumIdentificacao(int numIdentificacao) {
        this.numIdentificacao = numIdentificacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", numIdentificacao=" + numIdentificacao + ", senha=" + senha + ", login=" + login + '}';
    }
    
    public void insereUsuario()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Usuario values (forCodigo.nextval,'"+this.nome+"','"+this.login+"','"+this.numIdentificacao+"','"+this.senha+"','"+this.sexo+"')";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void alterarFornecedor()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update Usuario set forNome = '"+this.nome+"', forNumIdentificacao = '"+this.numIdentificacao+"', forLogin = '"+this.login+"', forSenha = '"+this.sexo+"'where forSenha = 2";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void excluirFornecedor(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Usuario where forNome = '"+nome+"'";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaFornecedor(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Usuario where forNome like '%"+nome+"%'";
        JOptionPane.showMessageDialog(null, sql);
        ResultSet result = conn.executaBusca(sql);
        return result;
    }
     
    
    
}
