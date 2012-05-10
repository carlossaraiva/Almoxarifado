package Codigo;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Fornecedor {
    private String RzSocial;
    private String telefone;
    private String endereco;
    private String CNPJ;
   
    public Fornecedor(){}

    public Fornecedor(String RzSocial, String telefone, String CNPJ, String endereco) {
        this.RzSocial = RzSocial;
        this.telefone = telefone;
        this.CNPJ = CNPJ;
    }
    

    public String getRzSocial() {
        return RzSocial;
    }

    public void setRzSocial(String RzSocial) {
        this.RzSocial = RzSocial;
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
        return "Fornecedor{" + "nome=" + RzSocial + ", telefone=" + telefone + ", endereco=" + endereco + '}';
    }   
}
