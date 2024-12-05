import java.util.Comparator;

public class ListaCircular {
    private NodoLista inicio;
    private NodoLista fin;
    private int tamano;

    public ListaCircular() {
        this.inicio = null;
        this.fin = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public NodoLista obtenerInicio() {
        return inicio;
    }

    public void agregarElemento(int valor) {
        NodoLista nuevo = new NodoLista(valor);

        if (estaVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.anterior = fin;
            fin.siguiente = nuevo;
            fin = nuevo;
        }

        fin.siguiente = inicio;
        inicio.anterior = fin;
        tamano++;
    }

    public void removerElemento(int valor) {
        if (estaVacia()) {
            return;
        }

        NodoLista actual = inicio;

        if (actual.valor == valor) {
            if (tamano == 1) {
                inicio = null;
                fin = null;
            } else {
                inicio = inicio.siguiente;
                fin.siguiente = inicio;
                inicio.anterior = fin;
            }
            tamano--;
            return;
        }

        do {
            if (actual.siguiente.valor == valor) {
                if (actual.siguiente == fin) {
                    fin = actual;
                }
                actual.siguiente = actual.siguiente.siguiente;
                actual.siguiente.anterior = actual;
                tamano--;
                return;
            }
            actual = actual.siguiente;
        } while (actual != inicio);
    }

    public NodoLista buscarElemento(int valor, Comparator<Integer> comparador) {
        if (estaVacia()) {
            return null;
        }

        NodoLista actual = inicio;

        do {
            if (comparador.compare(actual.valor, valor) == 0) {
                return actual;
            }
            actual = actual.siguiente;
        } while (actual != inicio);

        return null;
    }

    public void ordenarLista(Comparator<Integer> comparador) {
        if (estaVacia() || tamano == 1) {
            return;
        }

        NodoLista actual = inicio;

        do {
            NodoLista menor = actual;
            NodoLista siguiente = actual.siguiente;

            while (siguiente != inicio && comparador.compare(siguiente.valor, menor.valor) < 0) {
                menor = siguiente;
                siguiente = siguiente.siguiente;
            }

            if (menor != actual) {
                int temp = actual.valor;
                actual.valor = menor.valor;
                menor.valor = temp;
            }

            actual = actual.siguiente;
        } while (actual != inicio);
    }
}

