package ec.edu.uea.experimentall4;

import java.util.*;

// Clase que representa un Aeropuerto
class Aeropuerto {
    String nombre;
    String codigo;
    List<Vuelo> vuelosSalientes;

    public Aeropuerto(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.vuelosSalientes = new ArrayList<>();
    }

    public void agregarVuelo(Aeropuerto destino, int escalas) {
        vuelosSalientes.add(new Vuelo(destino, escalas));
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}

// Clase que representa un Vuelo (arista del grafo)
class Vuelo {
    Aeropuerto destino;
    int escalas;

    public Vuelo(Aeropuerto destino, int escalas) {
        this.destino = destino;
        this.escalas = escalas;
    }
}

// Clase que representa el Grafo de Vuelos
class GrafoVuelos {
    Map<String, Aeropuerto> aeropuertos;

    public GrafoVuelos() {
        aeropuertos = new HashMap<>();
    }

    public Aeropuerto agregarAeropuerto(String nombre, String codigo) {
        Aeropuerto a = new Aeropuerto(nombre, codigo);
        aeropuertos.put(codigo, a);
        return a;
    }

    public void agregarVuelo(String origen, String destino, int escalas) {
        Aeropuerto aOrigen = aeropuertos.get(origen);
        Aeropuerto aDestino = aeropuertos.get(destino);
        if (aOrigen != null && aDestino != null) {
            aOrigen.agregarVuelo(aDestino, escalas);
        }
    }

    // BFS para encontrar la ruta con menor número de escalas
    public void encontrarRuta(String codigoOrigen, String codigoDestino) {
        Aeropuerto origen = aeropuertos.get(codigoOrigen);
        Aeropuerto destino = aeropuertos.get(codigoDestino);

        if (origen == null || destino == null) {
            System.out.println("Aeropuerto no encontrado.");
            return;
        }

        Queue<List<Aeropuerto>> cola = new LinkedList<>();
        Set<Aeropuerto> visitados = new HashSet<>();

        // inicializamos la cola con el aeropuerto de origen
        List<Aeropuerto> rutaInicial = new ArrayList<>();
        rutaInicial.add(origen);
        cola.add(rutaInicial);

        while (!cola.isEmpty()) {
            List<Aeropuerto> ruta = cola.poll();
            Aeropuerto ultimo = ruta.get(ruta.size() - 1);

            if (ultimo.equals(destino)) {
                // Encontramos la ruta óptima
                System.out.print("Ruta encontrada: ");
                for (Aeropuerto a : ruta) {
                    System.out.print(a + " -> ");
                }
                System.out.println("FIN");
                System.out.println("Número de escalas: " + (ruta.size() - 1));
                return;
            }

            if (!visitados.contains(ultimo)) {
                visitados.add(ultimo);

                for (Vuelo vuelo : ultimo.vuelosSalientes) {
                    List<Aeropuerto> nuevaRuta = new ArrayList<>(ruta);
                    nuevaRuta.add(vuelo.destino);
                    cola.add(nuevaRuta);
                }
            }
        }
        System.out.println("No existe ruta entre " + origen + " y " + destino);
    }
}

// Clase de prueba
public class Main {
    public static void main(String[] args) {
        GrafoVuelos grafo = new GrafoVuelos();

        // Crear aeropuertos
        grafo.agregarAeropuerto("Quito", "UIO");
        grafo.agregarAeropuerto("Guayaquil", "GYE");
        grafo.agregarAeropuerto("Cuenca", "CUE");
        grafo.agregarAeropuerto("Manta", "MEC");

        // Agregar vuelos
        grafo.agregarVuelo("UIO", "GYE", 1);
        grafo.agregarVuelo("UIO", "CUE", 1);
        grafo.agregarVuelo("CUE", "MEC", 1);
        grafo.agregarVuelo("GYE", "MEC", 1);

        // Buscar ruta
        grafo.encontrarRuta("UIO", "MEC");
    }
}
