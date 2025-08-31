import java.util.*;

class Cancion {
    private String nombre;
    private String genero;

    public Cancion(String nombre, String genero) {
        this.nombre = nombre;
        this.genero = genero;
    }

    public String getNombre() { return nombre; }
    public String getGenero() { return genero; }

    @Override
    public String toString() {
        return nombre + " (" + genero + ")";
    }
}

class Pila {
    private Stack<Cancion> pila = new Stack<>();

    public void push(Cancion c) { pila.push(c); }
    public Cancion pop() { return pila.isEmpty() ? null : pila.pop(); }
    public Cancion peek() { return pila.isEmpty() ? null : pila.peek(); }
    public List<Cancion> mostrar() {
        List<Cancion> lista = new ArrayList<>(pila);
        Collections.reverse(lista); // Para mostrar en orden
        return lista;
    }
}

class Cola {
    private Queue<Cancion> cola = new LinkedList<>();

    public void enqueue(Cancion c) { cola.add(c); }
    public Cancion dequeue() { return cola.poll(); }
    public Cancion front() { return cola.peek(); }
    public List<Cancion> mostrar() { return new ArrayList<>(cola); }
}


class Lista {
    private List<Cancion> lista = new ArrayList<>();

    public void insertar(Cancion c) { lista.add(c); }
    public void eliminar(String nombre) {
        lista.removeIf(c -> c.getNombre().equalsIgnoreCase(nombre));
    }
    public List<Cancion> buscar(String nombre) {
        List<Cancion> encontradas = new ArrayList<>();
        for (Cancion c : lista) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                encontradas.add(c);
            }
        }
        return encontradas;
    }
    public List<Cancion> mostrar() { return lista; }
}

public class ReproductorMusica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pila pila = new Pila();
        Cola cola = new Cola();
        Lista lista = new Lista();

        while (true) {
            System.out.println("\n--- REPRODUCTOR ---");
            System.out.println("1. Agregar a Playlist (Pila)");
            System.out.println("2. Reproducir de Playlist (Pila)");
            System.out.println("3. Agregar a Cola de espera (Cola)");
            System.out.println("4. Reproducir de Cola de espera (Cola)");
            System.out.println("5. Agregar a Lista");
            System.out.println("6. Eliminar de Lista");
            System.out.println("7. Buscar en Lista");
            System.out.println("8. Ver todas las canciones ordenadas");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre: ");
                    String n1 = sc.nextLine();
                    System.out.print("Género: ");
                    String g1 = sc.nextLine();
                    pila.push(new Cancion(n1, g1));
                    break;

                case "2":
                    Cancion c1 = pila.pop();
                    System.out.println("Reproduciendo: " + (c1 != null ? c1 : "Nada"));
                    break;

                case "3":
                    System.out.print("Nombre: ");
                    String n2 = sc.nextLine();
                    System.out.print("Género: ");
                    String g2 = sc.nextLine();
                    cola.enqueue(new Cancion(n2, g2));
                    break;

                case "4":
                    Cancion c2 = cola.dequeue();
                    System.out.println("Reproduciendo: " + (c2 != null ? c2 : "Nada"));
                    break;

                case "5":
                    System.out.print("Nombre: ");
                    String n3 = sc.nextLine();
                    System.out.print("Género: ");
                    String g3 = sc.nextLine();
                    lista.insertar(new Cancion(n3, g3));
                    break;

                case "6":
                    System.out.print("Nombre a eliminar: ");
                    String elim = sc.nextLine();
                    lista.eliminar(elim);
                    break;

                case "7":
                    System.out.print("Nombre a buscar: ");
                    String busc = sc.nextLine();
                    System.out.println("Encontradas: " + lista.buscar(busc));
                    break;

                case "8":
                    List<Cancion> todas = new ArrayList<>();
                    todas.addAll(pila.mostrar());
                    todas.addAll(cola.mostrar());
                    todas.addAll(lista.mostrar());

                    if (!todas.isEmpty()) {
                        System.out.println("Ordenadas alfabéticamente:");
                        todas.stream()
                             .sorted(Comparator.comparing(Cancion::getNombre))
                             .forEach(System.out::println);

                        System.out.println("\nOrdenadas por género:");
                        todas.stream()
                             .sorted(Comparator.comparing(Cancion::getGenero))
                             .forEach(System.out::println);
                    } else {
                        System.out.println("No hay canciones registradas");
                    }
                    break;

                case "9":
                    System.out.println("Saliendo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
