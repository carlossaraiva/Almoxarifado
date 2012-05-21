package almoxarifado.classes.logico;
/**
 *
 * @author Diego Neves Isidoro
 */
public class Estoque {
    private Produto produto;


    private int qtdComprar;
    private double valor;
    
    public Estoque(){}
    public Estoque(Produto produto, int qtdMinima, int qtdAtual, int qtdComprar) {
        this.produto = produto;
        this.qtdMinima = qtdMinima;
        this.qtdAtual = qtdAtual;
        this.qtdComprar = qtdComprar;
    }
    public Produto getProduto() {
        return this.produto;
    }
    public int getQtdMinima (){
        return this.qtdMinima;
    }
    public int getQtdAtual (){
        return this.qtdAtual;
    }
    public int getQtdComprar(){
        return this.qtdComprar;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public void setQtdAtual(int qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public void setQtdComprar(int qtdComprar) {
        this.qtdComprar = qtdComprar;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Estoque{" + "produto=" + produto + ", qtdMinima=" + qtdMinima + ", qtdAtual=" + qtdAtual + ", qtdComprar=" + qtdComprar + ", valor=" + valor + '}';
    }
    public int estoqueMinimo(String nome){
        if(nome == null){
            return 0;
        }else{
            if (this.qtdAtual <= this.qtdMinima){
                int teste = 10;
                return teste;
            }else{
                return 1;
            }
        }
    }
    public int EstoqueMaximo(){
        if (this.qtdAtual >= this.qtdMaxima){
            int teste = 12;
            return teste;
        }else{
            return 2;
        }
    }
}
