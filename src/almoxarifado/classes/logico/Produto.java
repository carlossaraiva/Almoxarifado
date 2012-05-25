package almoxarifado.classes.logico;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Produto {
    private String nome;
    private String preco;
    private String marca;
    private String qtdMinima;
    private String qtdAtual;
    private String qtdMaxima;

    public Produto(String nome, String preco, String marca, String qtdMinima, String qtdAtual, String qtdMaxima) {
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
        this.qtdMinima = qtdMinima;
        this.qtdAtual = qtdAtual;
        this.qtdMaxima = qtdMaxima;
    }
        
    public Produto(){}

    public String getQtdMinima() {
            return qtdMinima;
    }

    public void setQtdMinima(String qtdMinima) {
            this.qtdMinima = qtdMinima;
    }

    public String getQtdAtual() {
            return qtdAtual;
    }

    public void setQtdAtual(String qtdAtual) {
            this.qtdAtual = qtdAtual;
    }

    public String getQtdMaxima() {
            return qtdMaxima;
    }

    public void setQtdMaxima(String qtdMaxima) {
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
    
    public String getPreco() {
            return preco;
    }

    public void setPreco(String preco) {
            this.preco = preco;
    }
    
    public void insereProduto(int catCodigo, int forCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "insert into Produto values (proCodigo.nextval, "+catCodigo+", "+forCodigo+", '"+this.nome+"', "+this.preco+", '"+this.marca+"', "+this.qtdMinima+", "+this.qtdAtual+", "+this.qtdMaxima+")";
        System.out.println(sql);
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void alterarProduto(int proCodigo, int catCodigo, int forCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update Produto set catCodigo = "+catCodigo+", proNome = '"+this.nome+"', proPreco = "+this.preco+", proMarca = '"+this.marca+"', proQtdMinima = "+this.qtdMinima+", proQtdAtua = "+this.qtdAtual+", proQtdMaxima = "+this.qtdMaxima+" where proCodigo = "+proCodigo+"";
        System.out.print(sql);
        conn.executaSQL(sql);        
        conn.desconecta();
    }
    public void excluirProduto(int proCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from Produto where proCodigo = "+proCodigo+"";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaProduto(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from Produto where proNome like '%"+nome+"%'";
        ResultSet result = conn.executaBusca(sql);
        return result;
    }
}
