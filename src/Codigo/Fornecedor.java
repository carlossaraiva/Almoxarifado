package Codigo;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Fornecedor {
    private String nome;
    private String telefone;
    private String endereco;
   
    public Fornecedor(){}

    public Fornecedor(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
}
