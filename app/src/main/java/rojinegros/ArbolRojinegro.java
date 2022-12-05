package rojinegros;

import lombok.Getter;
import lombok.Setter;

// import javax.naming.OperationNotSupportedException; -- eliminar (?)
import java.util.LinkedList;
import java.util.Queue;

public class ArbolRojinegro {
    @Getter
    @Setter
    private ArbolRojinegro izq;

    @Getter
    @Setter
    private ArbolRojinegro der;

    @Getter
    @Setter
    private int valor;

    @Getter
    @Setter
    private boolean black; //Si es negro True, en otro caso rojo

    public ArbolRojinegro(ArbolRojinegro izq,
                          ArbolRojinegro der,
                          int valor,
                          boolean black) {
        this.izq = izq;
        this.der = der;
        this.valor = valor;
        this.black = black;
    }

    public ArbolRojinegro() {
        this.izq = null;
        this.der = null;
        this.black = true;
    }
    /*
     * Metodos a implementar
     */

    public void setFather(ArbolRojinegro father) {
        if (father == null) {
            return;
        }
        if (father.getValor() > this.getValor()) {
            father.setIzq(this);
        } else {
            father.setDer(this);
        }
    }

    private ArbolRojinegro getFather() {
        return null;
    }

    public int maximo() throws Exception {
        ArbolRojinegro subarbol = this;
        while (subarbol.getDer() != null) {
            subarbol = subarbol.getDer();
        }
        return subarbol.getValor();
    }

    public int minimo() throws Exception {
        ArbolRojinegro subarbol = this;
        while (subarbol.getIzq() != null) {
            subarbol = subarbol.getIzq();
        }
        return subarbol.getValor();
    }

    public ArbolRojinegro search(int valorarbol) throws Exception {
        ArbolRojinegro subarbol = this;
        while (subarbol != null) {
            if (subarbol.getValor() == valorarbol) {
                return subarbol;
            }
            if (subarbol.getValor() > valorarbol) {
                subarbol = subarbol.getIzq();
            } else {
                subarbol = subarbol.getDer();
            }
        }
        return null;
    }

    public void insertar(int x) throws Exception {
        ArbolRojinegro padre = null;
        ArbolRojinegro hijo = this;
        while (hijo != null) {
            padre = hijo;
            if (hijo.getValor() > x) {
                hijo = hijo.getIzq();
            } else {
                hijo = hijo.getDer();
            }
        }
        ArbolRojinegro nuevo = new ArbolRojinegro(null, null, x, false);
        nuevo.setFather(padre);
        if (padre == null) {
            this.setValor(x);
            this.setBlack(true);
            this.setIzq(null);
            this.setDer(null);
        } else if (padre.getValor() > x) {
            padre.setIzq(nuevo);
        } else {
            padre.setDer(nuevo);
        }
    }

    public void rotacionDerecha(int nodo) {
        ArbolRojinegro padre = this.getFather();
        ArbolRojinegro hijo = this.getIzq();
        this.setIzq(hijo.getDer());
        hijo.setDer(this);
        hijo.setFather(padre);
        if (padre != null) {
            if (padre.getIzq() == this) {
                padre.setIzq(hijo);
            } else {
                padre.setDer(hijo);
            }
        }
    }
    public void rotacionIzquierda(int nodo) {
        ArbolRojinegro padre = this.getFather();
        ArbolRojinegro hijo = this.getDer();
        this.setDer(hijo.getIzq());
        hijo.setIzq(this);
        hijo.setFather(padre);
        if (padre != null) {
            if (padre.getIzq() == this) {
                padre.setIzq(hijo);
            } else {
                padre.setDer(hijo);
            }
        }
    }
    /*
     *  Area de pruebas, no modificar.
     */
    //Verificaciones
    /*
     * Busqueda por amplitud para verificar arbol.
     */
    public String bfs() {
        String salida = "";
        String separador = "";
        Queue<ArbolRojinegro> cola = new LinkedList<>();
        cola.add(this);
        while (cola.size() > 0) {
            ArbolRojinegro nodo = cola.poll();
            salida += separador + String.valueOf(nodo.getValor());
            separador = " ";
            if (nodo.getIzq() != null) {
                cola.add(nodo.getIzq());
            }
            if (nodo.getDer() != null) {
                cola.add(nodo.getDer());
            }
        }
        return salida;
    }

    /*
     * Recorrido inorder.
     * Verifica propiedad de orden.
     */
    public String inorden() {
        String recorrido = "";
        String separador = "";
        if (this.getIzq() != null) {
            recorrido += this.getIzq().inorden();
            separador = " ";
        }
        recorrido += separador + String.valueOf(this.getValor());
        if (this.getDer() != null) {
            recorrido += " " + this.getDer().inorden();
        }
        return recorrido;
    }

}