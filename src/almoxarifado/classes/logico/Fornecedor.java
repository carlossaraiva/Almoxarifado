package almoxarifado.classes.logico;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Fornecedor {
    private String nome;
    private String telefone;
    private String endereco;
    private String CNPJ;


    public Fornecedor(String RzSocial, String CNPJ) {
        this.nome = RzSocial;
        //this.telefone = telefone;
        this.CNPJ = CNPJ;
    }
    

    public Fornecedor() {
		// TODO Auto-generated constructor stub
	}


	public String getRzSocial() {
        return nome;
    }

    public void setRzSocial(String RzSocial) {
        this.nome = RzSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setCNPJ(String CNPJ){
    	this.CNPJ = CNPJ;
    }
    
    public String getCNPJ(){
    	return this.CNPJ;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + '}';
    }  
    
    public void insereFornecedor()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Fornecedor values (forCodigo.nextval,'"+this.nome+"','"+this.endereco+"','"+this.telefone+"')";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void alterarFornecedor()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update Fornecedor set forNome = '"+this.nome+"', forEndereco = '"+this.endereco+"', forTelefone = '"+this.telefone+"'where forCodigo = 2";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void excluirFornecedor(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Fornecedor where forNome = '"+nome+"'";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaFornecedor(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Fornecedor where forNome like '%"+nome+"%'";
        JOptionPane.showMessageDialog(null, sql);
        ResultSet result = conn.executaBusca(sql);
        return result;
    }
}