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
    
    
    
    public Produto(){
    }
    public Produto(String nome, double preco, String marca){
        this.nome = nome;        
        this.marca = marca;
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


    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome  + " ,marca=" + marca +'}';
    }
    
    public void insereProduto()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Fornecedor values (forCodigo.nextval,'"+this.nome+"','"+this.marca+")";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void alterarProduto()
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
     
    
            
}
