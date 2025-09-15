package Actividad_4;

// 1. Clase Nodo 
class Nodo {
    int id;
    String nombre;
    Nodo izquierda, derecha;

    public Nodo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        izquierda = derecha = null;
    }
}

// 2. Clase ArbolBinario
class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    // Insertar un nodo en el árbol
    public void insertar(int id, String nombre) {
        raiz = insertarRec(raiz, id, nombre);
    }

    private Nodo insertarRec(Nodo raiz, int id, String nombre) {
        if (raiz == null) {
            raiz = new Nodo(id, nombre);
            return raiz;
        }
        if (id < raiz.id) {
            raiz.izquierda = insertarRec(raiz.izquierda, id, nombre);
        } else if (id > raiz.id) {
            raiz.derecha = insertarRec(raiz.derecha, id, nombre);
        }
        return raiz;
    }

    // Buscar un nodo por ID
    public Nodo buscar(int id) {
        return buscarRec(raiz, id);
    }

    private Nodo buscarRec(Nodo raiz, int id) {
        if (raiz == null || raiz.id == id)
            return raiz;
        if (id < raiz.id)
            return buscarRec(raiz.izquierda, id);
        return buscarRec(raiz.derecha, id);
    }

    // Eliminar un nodo
    public void eliminar(int id) {
        raiz = eliminarRec(raiz, id);
    }

    private Nodo eliminarRec(Nodo raiz, int id) {
        if (raiz == null) return raiz;
        if (id < raiz.id) {
            raiz.izquierda = eliminarRec(raiz.izquierda, id);
        } else if (id > raiz.id) {
            raiz.derecha = eliminarRec(raiz.derecha, id);
        } else {
            // Caso 1: sin hijos
            if (raiz.izquierda == null && raiz.derecha == null) {
                return null;
            }
            // Caso 2: un hijo
            else if (raiz.izquierda == null) {
                return raiz.derecha;
            } else if (raiz.derecha == null) {
                return raiz.izquierda;
            }
            // Caso 3: dos hijos
            raiz.id = valorMinimo(raiz.derecha);
            raiz.derecha = eliminarRec(raiz.derecha, raiz.id);
        }
        return raiz;
    }

    private int valorMinimo(Nodo raiz) {
        int minv = raiz.id;
        while (raiz.izquierda != null) {
            minv = raiz.izquierda.id;
            raiz = raiz.izquierda;
        }
        return minv;
    }

    // Recorridos
    public void inorden(Nodo raiz) {
        if (raiz != null) {
            inorden(raiz.izquierda);
            System.out.print(raiz.id + " ");
            inorden(raiz.derecha);
        }
    }

    public void preorden(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.id + " ");
            preorden(raiz.izquierda);
            preorden(raiz.derecha);
        }
    }

    public void postorden(Nodo raiz) {
        if (raiz != null) {
            postorden(raiz.izquierda);
            postorden(raiz.derecha);
            System.out.print(raiz.id + " ");
        }
    }
}

// 3. Clase principal
public class Main {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();

        // Lista de empleados
        String[] empleados = {"Ana", "Canek", "Carlos", "Maria", "Jose",
                "Juan", "Pedro", "Laura", "Miguel", "Sofia"
            };
        int[] ids = {45, 20, 65, 10, 30, 50, 70, 25, 35, 60};

        // Insertar empleados en el árbol
        for (int i = 0; i < empleados.length; i++) {
            arbol.insertar(ids[i], empleados[i]);
        }

        // Mostrar recorridos
        System.out.println("Recorrido Inorden:");
        arbol.inorden(arbol.raiz);
        System.out.println("\nRecorrido Preorden:");
        arbol.preorden(arbol.raiz);
        System.out.println("\nRecorrido Postorden:");
        arbol.postorden(arbol.raiz);

        // Buscar empleados
        int buscarId = 30;
        Nodo resultado = arbol.buscar(buscarId);
        if (resultado != null) {
            System.out.println("\nEmpleado encontrado: " + resultado.nombre + " con ID " + resultado.id);
        } else {
            System.out.println("\nEmpleado con ID " + buscarId + " no encontrado.");
        }

        // Eliminar un empleado
        int eliminarId = 20;
        System.out.println("\nEliminando empleado con ID " + eliminarId);
        arbol.eliminar(eliminarId);

        // Mostrar recorrido después de eliminar
        System.out.println("Recorrido Inorden después de eliminar:");
        arbol.inorden(arbol.raiz);
    }
}
