package almoxarifado.classes.logico;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Neves Isidoro
 */
public class TipoUsuario {
    private String nome;
    
    public TipoUsuario(){}

    public TipoUsuario(String nome) {
        this.nome = nome;
    }
    /*Procedimentos e funções para retornar os valores encapsulados*/
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void insereUsuario()
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sqle = "insert into TipoUsuario values (tipCodigo.nextval, '"+this.nome+"')";
        conn.executaSQL(sqle);
        String sql = "commit";
        conn.executaBusca(sql);
        conn.desconecta();
    }
    public void alterarUsuario(int tipCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "update TipoUsuario set tipNome = '"+this.nome+"'where tipCodigo = "+tipCodigo+"";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public void deletaUsuario(int tipCodigo)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "delete from TipoUsuario where tipCodigo = "+tipCodigo+"";
        conn.executaSQL(sql);
        conn.desconecta();
    }
    public ResultSet buscaUsuario(String nome)
    {
        Conexao conn = new Conexao();
        conn.conecta();
        String sqle = "select * from TipoUsuario where tipNome like '%"+nome+"%'";
        ResultSet result = conn.executaBusca(sqle);
        return result;
    }
    public String buscaCategoria(int tipCodigo)
    {
        String tipNome = "";
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select tipNome from TipoUsuario where tipCodigo = "+tipCodigo+"";
        ResultSet result = conn.executaBusca(sql);
        try
        {
            while(result.next())
            {
                tipNome = result.getString("tipNome");
            }
        }catch(SQLException sqlex)
        {
            JOptionPane.showMessageDialog(null, "Erro ao retornar o nome do usuario. Descrição: "+sqlex.getLocalizedMessage()+".");
        }
        return tipNome;
    }
}
