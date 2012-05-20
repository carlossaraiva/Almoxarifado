package almoxarifado.classes.logico;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Diego Neves Isidoro
 */
public class Conexao{ 
  
    final private String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
    final private String URL_CONNECTION = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  
    final private String DB_USER = "system";  
    final private String DB_PASS = "Di418746";  
    private Connection conn;
    private Statement stmt;
    private ResultSet reSet;
    public boolean conecta()
    {
           boolean result = true;
           try
           {
               Class.forName(this.JDBC_DRIVER);
               this.conn = DriverManager.getConnection(this.URL_CONNECTION, this.DB_USER, this.DB_PASS);
           }
           catch(ClassNotFoundException Driver)
           {
               JOptionPane.showMessageDialog(null, "Driver não encontrado " + Driver);
               result = false;
           }
           catch(SQLException Fonte)
           {
               JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com a fonte de dados " + Fonte);
               result = false;
           }
           return result;
    }
    public boolean desconecta()
    {
        boolean result= true;
        try
        {
            this.conn.close();
        }
        catch(SQLException Fechar)
        {
            result = false;
            JOptionPane.showMessageDialog(null,"Não foi possivel encerrar a conexao.");
        }
        return result;
    }
    public boolean executaSQL(String sql)
    {
        boolean result = false;
        try
        {
            this.stmt = this.conn.createStatement();
            JOptionPane.showMessageDialog(null,sql);
            this.reSet = this.stmt.executeQuery(sql);
            if(result == false)
            {
                JOptionPane.showMessageDialog(null,"Erro"+result);
            }
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel executar o comando sql '"+ sql +"'.");
        }
        return result;
    }
    public ResultSet executaBusca(String sql)
    {
        try
        {
            this.stmt = this.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            this.reSet = this.stmt.executeQuery(sql);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel executar o comando sql '"+ sql +"'.");
        }
        return this.reSet;
    }
} 
