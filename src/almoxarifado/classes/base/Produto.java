package almoxarifado.classes.base;

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
    
            
}
