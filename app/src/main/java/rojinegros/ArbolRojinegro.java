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
    //necesario para funcionamiento correcto
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

    public ArbolRojinegro getTio() {
        ArbolRojinegro abuelo = this.getFather().getFather();
        if (abuelo != null) {
            if (this.getFather().getValor() < abuelo.getValor()) {
                return abuelo.getDer();
            } else {
                return abuelo.getIzq();
            }
        } else {
            return null;
        }
    }

    public ArbolRojinegro getAbuelo() {
        return this.getFather().getFather();
    }

    public void arreglarInsercion(ArbolRojinegro nodo) throws Exception {
        ArbolRojinegro padre = nodo.getFather();
        if (padre == null) {
            nodo.black = true;
            return;
        }
        if (padre.isBlack()) {
            return;
        }

        while (!nodo.getFather().isBlack()) {
            if (!nodo.isBlack() && !nodo.getFather().isBlack() && nodo.getTio() == null) {
                nodo = casosInsercion(nodo);
            } else {
                if (!nodo.getFather().isBlack() && !nodo.getTio().isBlack()) {
                    nodo.getFather().setBlack(true);
                    nodo.getTio().setBlack(true);
                    nodo.getAbuelo().setBlack(false);
                    nodo = nodo.getAbuelo();
                    if (nodo.getFather() == null) {
                        break;
                    }
                } else {
                    nodo = casosInsercion(nodo);
                }
            }
        }

        if (nodo != null) {
            while (nodo.getFather() != null) {
                nodo = nodo.getFather();
            }
            nodo.setBlack(true);
        }
        this.setIzq(nodo.getIzq());
        this.setDer(nodo.getDer());
        this.setValor(nodo.getValor());
        this.setBlack(nodo.isBlack());
    }

    private ArbolRojinegro casosInsercion(ArbolRojinegro nodo) throws Exception {
        if (nodo == nodo.getFather().getIzq() && nodo.getFather() == nodo.getAbuelo().getIzq()) {
            nodo.getAbuelo().setBlack(false);
            nodo.getFather().setBlack(true);
            nodo.getAbuelo().rotacionDerecha(nodo.getAbuelo().getValor());

        } else if (nodo == nodo.getFather().getDer() && nodo.getFather() == nodo.getAbuelo().getDer()) {
            nodo.getAbuelo().setBlack(false);
            nodo.getFather().setBlack(true);
            nodo.getAbuelo().rotacionIzquierda(nodo.getAbuelo().getValor());

        } else if (nodo == nodo.getFather().getIzq() && nodo.getFather() == nodo.getAbuelo().getDer()) {
            nodo = nodo.getFather();
            nodo.rotacionDerecha(nodo.getValor());

        } else if (nodo == nodo.getFather().getDer() && nodo.getFather() == nodo.getAbuelo().getIzq()) {
            nodo = nodo.getFather();
            nodo.rotacionIzquierda(nodo.getValor());
        }
        return nodo;
    }

//Puntos del taller

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
//Inserción binaria no rojinegra
    public void insertarnormal(int x) throws Exception {
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

    public void insertar(int x) throws Exception {
        if (this.getFather() == null && this.getValor() == 0) {
            this.setValor(x);
            this.setBlack(false);
            arreglarInsercion(this);
        } else {
            ArbolRojinegro nodo = this;
            ArbolRojinegro padre = null;

            while (nodo != null) {
                padre = nodo;
                if (x < nodo.getValor()) {
                    nodo = nodo.getIzq();
                } else if (x > nodo.getValor()) {
                    nodo = nodo.getDer();
                } else {
                    throw new Exception("Valor ya encontrado en el arbol: " + x);
                }
            }

            ArbolRojinegro nuevoNodo = new ArbolRojinegro();
            nuevoNodo.setValor(x);
            nuevoNodo.setBlack(false);
            if (x < padre.getValor()) {
                padre.setIzq(nuevoNodo);
            } else {
                padre.setDer(nuevoNodo);
            }
            nuevoNodo.setFather(padre);
            arreglarInsercion(nuevoNodo);
        }

    }

//Rotacion Izquierda
    public void rotacionIzquierda(int x) throws Exception {
        ArbolRojinegro arbARotar = this.search(x);
        ArbolRojinegro padre = arbARotar.getFather();
        ArbolRojinegro nuevaRaiz = arbARotar.getDer();
        nuevaRaiz.setFather(padre);
        if (padre != null) {
            arbARotar.setDer(nuevaRaiz.getIzq());
            if (arbARotar.getDer() != null) {
                arbARotar.getDer().setFather(arbARotar);
            }
            arbARotar.setFather(nuevaRaiz);
            nuevaRaiz.setIzq(arbARotar);
            if (nuevaRaiz.getValor() < padre.getValor()) {
                padre.setIzq(nuevaRaiz);
            } else {
                padre.setDer(nuevaRaiz);
            }
        } else {

            ArbolRojinegro nodoIzquierdo = new ArbolRojinegro(arbARotar.getIzq(), arbARotar.getDer(), arbARotar.getValor(), false);
            nodoIzquierdo.setDer(nuevaRaiz.getIzq());
            nuevaRaiz.setIzq(nodoIzquierdo);
            nodoIzquierdo.setFather(nuevaRaiz);
            if (nuevaRaiz.getIzq().getDer() != null) {
                nuevaRaiz.getIzq().getDer().setFather(nodoIzquierdo);
            }
            if (nuevaRaiz.getIzq().getIzq() != null) {
                nuevaRaiz.getIzq().getIzq().setFather(nodoIzquierdo);
            }
            arbARotar.setValor(nuevaRaiz.getValor());
            arbARotar.setBlack(nuevaRaiz.isBlack());
            arbARotar.setDer(nuevaRaiz.getDer());
            arbARotar.setIzq(nuevaRaiz.getIzq());

        }


    }
//Rotacion derecha
    public void rotacionDerecha(int x) throws  Exception {
        ArbolRojinegro arbARotar = search(x);
        ArbolRojinegro padre = arbARotar.getFather();
        ArbolRojinegro nuevaRaiz = arbARotar.getIzq();
        nuevaRaiz.setFather(padre);
        if (padre != null) {
            arbARotar.setIzq(nuevaRaiz.getDer());
            arbARotar.setFather(nuevaRaiz);
            if (arbARotar.getIzq() != null) {
                arbARotar.getIzq().setFather(arbARotar);
            }
            nuevaRaiz.setDer(arbARotar);
            if (nuevaRaiz.getValor() < padre.getValor()) {
                padre.setIzq(nuevaRaiz);
            } else {
                padre.setDer(nuevaRaiz);
            }

        } else {
            ArbolRojinegro nodoDerecho = new ArbolRojinegro(arbARotar.getIzq(), arbARotar.getDer(), arbARotar.getValor(), arbARotar.isBlack());
            nodoDerecho.setIzq(nuevaRaiz.getDer());
            nuevaRaiz.setDer(nodoDerecho);
            nodoDerecho.setFather(nuevaRaiz);
            if (nuevaRaiz.getDer().getIzq() != null) {
                nuevaRaiz.getDer().getIzq().setFather(nodoDerecho);
            }
            if (nuevaRaiz.getDer().getDer() != null) {
                nuevaRaiz.getDer().getDer().setFather(nodoDerecho);
            }
            arbARotar.setValor(nuevaRaiz.getValor());
            arbARotar.setBlack(nuevaRaiz.isBlack());
            arbARotar.setIzq(nuevaRaiz.getIzq());
            arbARotar.setDer(nodoDerecho);

        }
    }

    // Referencia: https://www.happycoders.eu/algorithms/red-black-tree-java/

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
