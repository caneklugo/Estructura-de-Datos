import java.util.*;

class Cancion {
    String titulo;
    String artista;
    String genero;
    int duracion; // en segundos
    int anio;

    public Cancion(String titulo, String artista, String genero, int duracion, int anio) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.duracion = duracion;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + genero + ", " + anio + ", " + duracion + "s)";
    }
}

class NodoArbol {
    Cancion cancion;
    NodoArbol izquierda, derecha;

    public NodoArbol(Cancion c) {
        cancion = c;
        izquierda = derecha = null;
    }
}

class ArbolCanciones {
    NodoArbol raiz;

    public void insertar(Cancion c) {
        raiz = insertarRec(raiz, c);
    }

    private NodoArbol insertarRec(NodoArbol raiz, Cancion c) {
        if (raiz == null) return new NodoArbol(c);
        if (c.genero.compareTo(raiz.cancion.genero) < 0) raiz.izquierda = insertarRec(raiz.izquierda, c);
        else raiz.derecha = insertarRec(raiz.derecha, c);
        return raiz;
    }

    public void inOrden(NodoArbol raiz) {
        if (raiz != null) {
            inOrden(raiz.izquierda);
            System.out.println(raiz.cancion);
            inOrden(raiz.derecha);
        }
    }
}

class GrafoCanciones {
    Map<String, List<String>> grafo = new HashMap<>();

    public void agregarRelacion(String cancion, String recomendada) {
        grafo.putIfAbsent(cancion, new ArrayList<>());
        grafo.get(cancion).add(recomendada);
    }

    public void mostrarRecomendaciones(String cancion) {
        System.out.println("Recomendaciones para " + cancion + ": " + grafo.getOrDefault(cancion, new ArrayList<>()));
    }
}

public class Reproductor {
    static List<Cancion> canciones = new ArrayList<>();
    static PriorityQueue<Cancion> colaPrioridad = new PriorityQueue<>(Comparator.comparing(c -> c.titulo));
    static HashMap<String, Cancion> mapaCreditos = new HashMap<>();
    static ArbolCanciones arbol = new ArbolCanciones();
    static GrafoCanciones grafo = new GrafoCanciones();

    static int duracionTotal(List<Cancion> lista, int indice) {
        if (indice == lista.size()) return 0;
        return lista.get(indice).duracion + duracionTotal(lista, indice + 1);
    }

    static void ordenarPorDuracion() {
        canciones.sort(Comparator.comparingInt(c -> c.duracion));
    }

    static Cancion buscarPorTitulo(String titulo) {
        canciones.sort(Comparator.comparing(c -> c.titulo));
        int inicio = 0, fin = canciones.size() - 1;
        while (inicio <= fin) {
            int mid = (inicio + fin) / 2;
            int cmp = titulo.compareToIgnoreCase(canciones.get(mid).titulo);
            if (cmp == 0) return canciones.get(mid);
            else if (cmp < 0) fin = mid - 1;
            else inicio = mid + 1;
        }
        return null;
    }

    static void cargarCanciones() {
        String[][] data = {
            {"Despacito","Luis Fonsi","Reguetón","230","2017"},
            {"Bailando","Enrique Iglesias","Pop","250","2014"},
            {"Vivir mi vida","Marc Anthony","Salsa","280","2013"},
            {"Felices los 4","Maluma","Reguetón","220","2017"},
            {"Échame la culpa","Luis Fonsi","Pop","210","2017"},
            {"La Bicicleta","Carlos Vives","Vallenato","240","2016"},
            {"Mi Gente","J Balvin","Reguetón","200","2017"},
            {"Héroe","Enrique Iglesias","Balada","260","2001"},
            {"Colgando en tus manos","Carlos Baute","Pop","270","2009"},
            {"Me enamoré","Shakira","Pop","220","2017"},
            {"Dákiti","Bad Bunny","Reguetón","220","2020"},
            {"Gasolina","Daddy Yankee","Reguetón","200","2004"},
            {"Me gustas tú","Manu Chao","Alternativo","210","2001"},
            {"La Camisa Negra","Juanes","Rock","230","2005"},
            {"Rayando el sol","Maná","Rock","260","1990"},
            {"Te Boté","Nio Garcia","Trap","250","2018"},
            {"Parecen viernes","Marc Anthony","Salsa","270","2019"},
            {"Propuesta indecente","Romeo Santos","Bachata","230","2013"},
            {"Obsesión","Aventura","Bachata","220","2002"},
            {"Andas en mi cabeza","Chino & Nacho","Pop","240","2016"},
            {"Robarte un beso","Carlos Vives","Pop","220","2017"},
            {"Duele el corazón","Enrique Iglesias","Pop","210","2016"},
            {"Corazón partío","Alejandro Sanz","Pop","270","1997"},
            {"Cuando me enamoro","Enrique Iglesias","Pop","250","2010"},
            {"La Flaca","Jarabe de Palo","Rock","260","1996"},
            {"Mariposa traicionera","Maná","Rock","270","2002"},
            {"Ciega sordomuda","Shakira","Pop","240","1998"},
            {"Antología","Shakira","Balada","260","1995"},
            {"Oye como va","Santana","Rock","280","1970"},
            {"La Gozadera","Gente de Zona","Salsa","240","2015"},
            {"Nota de amor","Wisin","Reguetón","220","2015"},
            {"Caraluna","Bacilos","Pop","250","2002"},
            {"Lamento boliviano","Enanitos Verdes","Rock","260","1994"},
            {"De música ligera","Soda Stereo","Rock","230","1990"},
            {"Nada valgo sin tu amor","Juanes","Rock","240","2004"},
            {"La mordidita","Ricky Martin","Pop","220","2015"},
            {"Tu recuerdo","Ricky Martin","Balada","250","2006"},
            {"Livin’ la Vida Loca","Ricky Martin","Pop","240","1999"},
            {"Oye mi amor","Maná","Rock","260","1992"},
            {"Clavado en un bar","Maná","Rock","250","1997"},
            {"A puro dolor","Son by Four","Balada","240","2000"},
            {"Se preparó","Ozuna","Reguetón","220","2017"},
            {"Taki Taki","DJ Snake","Reguetón","220","2018"},
            {"Ella y yo","Don Omar","Reguetón","260","2005"},
            {"Noche de sexo","Wisin y Yandel","Reguetón","250","2005"},
            {"Atrevete te te","Calle 13","Alternativo","250","2005"},
            {"Latinoamérica","Calle 13","Alternativo","300","2011"},
            {"La copa de la vida","Ricky Martin","Pop","270","1998"},
            {"Dime","Ivy Queen","Reguetón","240","2002"}
        };
        for (String[] c : data) {
            Cancion cancion = new Cancion(c[0], c[1], c[2], Integer.parseInt(c[3]), Integer.parseInt(c[4]));
            canciones.add(cancion);
            colaPrioridad.add(cancion);
            mapaCreditos.put(c[0], cancion);
            arbol.insertar(cancion);
        }
        
        // Algunas relaciones de recomendación
        grafo.agregarRelacion("Despacito", "Échame la culpa");
        grafo.agregarRelacion("Gasolina", "Dákiti");
        grafo.agregarRelacion("La Camisa Negra", "Nada valgo sin tu amor");
    }

    // Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cargarCanciones();
        int opcion;
        do {
            System.out.println("\n🎵 Reproductor de Música 🎵");
            System.out.println("1. Reproducir siguiente canción (cola prioridad)");
            System.out.println("2. Mostrar canciones por género (árbol binario)");
            System.out.println("3. Calcular duración total de playlist");
            System.out.println("4. Ver créditos de una canción");
            System.out.println("5. Ordenar canciones por duración");
            System.out.println("6. Buscar canción por título");
            System.out.println("7. Ver recomendaciones de una canción");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch(opcion) {
                case 1:
                    if (!colaPrioridad.isEmpty())
                        System.out.println("Reproduciendo: " + colaPrioridad.poll());
                    else
                        System.out.println("No hay canciones en cola.");
                    break;
                case 2:
                    System.out.println("Canciones organizadas por género:");
                    arbol.inOrden(arbol.raiz);
                    break;
                case 3:
                    System.out.println("Duración total: " + duracionTotal(canciones, 0) + "s");
                    break;
                case 4:
                    System.out.print("Título: ");
                    String t = sc.nextLine();
                    System.out.println(mapaCreditos.getOrDefault(t, null));
                    break;
                case 5:
                    ordenarPorDuracion();
                    canciones.forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Título: ");
                    String b = sc.nextLine();
                    Cancion encontrada = buscarPorTitulo(b);
                    System.out.println(encontrada != null ? encontrada : "No encontrada");
                    break;
                case 7:
                    System.out.print("Título: ");
                    String r = sc.nextLine();
                    grafo.mostrarRecomendaciones(r);
                    break;
            }
        } while(opcion != 0);
        sc.close();
    }
}

