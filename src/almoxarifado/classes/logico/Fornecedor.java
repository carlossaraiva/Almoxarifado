package almoxarifado.classes.logico;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Fornecedor {
    private String nome;
    private String CNPJ;
    private String telefone;

    public Fornecedor(String RzSocial, String CNPJ, String telefone) {
        this.nome = RzSocial;
        //this.telefone = telefone;
        this.CNPJ = CNPJ;
        this.telefone = telefone;
    }
    

    public Fornecedor(){}

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
    
    public void insereFornecedor()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Fornecedor values (forCodigo.nextval, '"+this.nome+"', '"+this.CNPJ+"','"+this.telefone+"')";
        System.out.println(sql);
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void alterarFornecedor(int forCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update Fornecedor set forNome = '"+this.nome+"', forEndereco = '"+this.CNPJ+"', forTelefone = '"+this.telefone+"' where forCodigo = "+forCodigo+"";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void excluirFornecedor(int forCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Fornecedor where forCodigo = "+forCodigo+"";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaFornecedor(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Fornecedor where forNome like '%"+nome+"%'";
        ResultSet result = conn.executaBusca(sql);
        return result;
    }
}