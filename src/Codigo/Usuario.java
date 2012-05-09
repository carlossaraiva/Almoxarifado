package Codigo;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Usuario {
    private String nome;
    private int numIdentificacao;
    private String senha;
    private String login;
    
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
    
    
}
