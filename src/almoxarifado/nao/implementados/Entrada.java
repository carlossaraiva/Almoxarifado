package almoxarifado.nao.implementados;

import almoxarifado.classes.Produto;

/**
 *
 * @author Diego Neves Isidoro
 */
public class Entrada extends Controle {
    private int numero;
    private Produto pro;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Produto getPro() {
        return pro;
    }

    public void setPro(Produto pro) {
        this.pro = pro;
    }

    @Override
    public String toString() {
        return "Entrada{" + "numero=" + numero + ", pro=" + pro + ", data=" + data + '}';
    }
    
    
}
