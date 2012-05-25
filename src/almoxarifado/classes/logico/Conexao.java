package almoxarifado.classes.logico;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Diego Neves Isidoro
 */
public class Conexao{ 
  
    final private String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
    final private String URL_CONNECTION = "jdbc:oracle:thin:@192.168.0.110:1521:XE";  
    final private String DB_USER = "system";  
    final private String DB_PASS = "4652";  //Di418746
    private Connection conn;
    private Statement stmt;
    private ResultSet reSet;
    /*Procedimento que executa uma conexão com o Banco*/
    public void conecta()
    {
        try
        {
            /*Estabeleço o driver que será usado*/
            Class.forName(this.JDBC_DRIVER);
            /*Defino o local do banco o login e senha e armazeno a conexão em uma variável*/
            this.conn = DriverManager.getConnection(this.URL_CONNECTION, this.DB_USER, this.DB_PASS);
        }
        catch(ClassNotFoundException Driver)
        {
            JOptionPane.showMessageDialog(null, "Driver não encontrado " + Driver);
        }
        catch(SQLException Fonte)
        {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com a fonte de dados " + Fonte);
        }
    }
    /*Procedimento que se desconecta do Banco*/
    public void desconecta()
    {
        try
        {
            /*Fecho a conexao e pronto*/
            this.conn.close();
        }
        catch(SQLException Fechar)
        {
            JOptionPane.showMessageDialog(null,"NÃ£o foi possivel encerrar a conexao.");
        }
    }
    /*Procedimento que é usado para update, delete e insert.*/
    public void executaSQL(String sql)
    {
        try
        {
            /*Crio o caminho que será usado pela query.*/
            this.stmt = this.conn.createStatement();
            /*Executo a query atraves do caminho que estava estabelecido*/
            this.stmt.executeQuery(sql);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel executar o comando sql '"+ sql +"'.");
        }
    }
    /*Função que é usada para busca no banco*/
    public ResultSet executaBusca(String sql)
    {
        try
        {
            System.out.println(sql);
            /*Defino o caminho que vai ser usado e como vai retornar o que buscar do banco*/
            this.stmt = this.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            /*Executo a query e armazeno o valor retornado do banco em uma variavel*/
            this.reSet = this.stmt.executeQuery(sql);
            
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel executar o comando sql '"+ sql +"'.");
        }
        /*Retorno o resultado*/
        return this.reSet;
    }
} 
