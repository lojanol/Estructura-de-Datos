// Archivo: Tarea6.java
import java.util.Scanner; // Necesario para leer la entrada del usuario

/**
 * Clase Nodo
 * Representa un único vehículo en el estacionamiento (un elemento en la lista enlazada).
 */
class Nodo {
    String placa;   // Almacena la placa del vehículo
    Nodo siguiente; // Referencia al siguiente nodo en la lista

    /**
     * Constructor para crear un nuevo nodo.
     * @param placa La placa del vehículo.
     */
    public Nodo(String placa) {
        this.placa = placa;
        this.siguiente = null; // Inicialmente, un nodo no apunta a nada
    }
}

/**
 * Clase ListaEnlazada
 * Gestiona la lógica de la lista enlazada para los vehículos del estacionamiento.
 * Incluye métodos para agregar, retirar, buscar y mostrar vehículos.
 */
class ListaEnlazada {
    Nodo cabeza; // El primer nodo de la lista (o null si la lista está vacía)

    /**
     * Constructor de la lista enlazada.
     */
    public ListaEnlazada() {
        this.cabeza = null;
    }

    /**
     * Verifica si la lista está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un nuevo vehículo al final de la lista.
     * @param placa La placa del vehículo a agregar.
     */
    public void agregarVehiculo(String placa) {
        Nodo nuevoNodo = new Nodo(placa);
        if (estaVacia()) {
            cabeza = nuevoNodo; // Si la lista está vacía, el nuevo nodo es la cabeza
        } else {
            Nodo actual = cabeza;
            // Recorrer la lista hasta el último nodo
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo; // Enlazar el nuevo nodo al final
        }
        System.out.println("Vehículo con placa '" + placa + "' ha sido registrado en el estacionamiento.");
    }

    /**
     * Retira un vehículo de la lista por su placa.
     * @param placa La placa del vehículo a retirar.
     * @return true si el vehículo fue retirado, false si no se encontró.
     */
    public boolean retirarVehiculo(String placa) {
        if (estaVacia()) {
            System.out.println("El estacionamiento está vacío, no hay vehículos para retirar.");
            return false;
        }

        Nodo actual = cabeza;
        Nodo anterior = null;

        // Caso especial: el vehículo a retirar es el primero (la cabeza)
        if (actual.placa.equalsIgnoreCase(placa)) {
            cabeza = actual.siguiente; // La nueva cabeza es el siguiente nodo
            System.out.println("Vehículo con placa '" + placa + "' ha sido retirado del estacionamiento.");
            return true;
        }

        // Buscar el vehículo en el resto de la lista
        while (actual != null && !actual.placa.equalsIgnoreCase(placa)) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual != null) { // Si el vehículo fue encontrado
            anterior.siguiente = actual.siguiente; // Saltamos el nodo actual para eliminarlo
            System.out.println("Vehículo con placa '" + placa + "' ha sido retirado del estacionamiento.");
            return true;
        } else { // Si no se encontró el vehículo
            System.out.println("Vehículo con placa '" + placa + "' no encontrado en el estacionamiento.");
            return false;
        }
    }

    /**
     * Busca un vehículo por su placa y muestra su posición.
     * @param placa La placa del vehículo a buscar.
     * @return true si el vehículo fue encontrado, false si no.
     */
    public boolean buscarVehiculo(String placa) {
        if (estaVacia()) {
            System.out.println("El estacionamiento está vacío.");
            return false;
        }

        Nodo actual = cabeza;
        int posicion = 1;
        while (actual != null) {
            if (actual.placa.equalsIgnoreCase(placa)) {
                System.out.println("Vehículo con placa '" + placa + "' encontrado en la posición " + posicion + ".");
                return true;
            }
            actual = actual.siguiente;
            posicion++;
        }
        System.out.println("Vehículo con placa '" + placa + "' no encontrado en el estacionamiento.");
        return false;
    }

    /**
     * Muestra todas las placas de los vehículos estacionados.
     */
    public void mostrarVehiculos() {
        if (estaVacia()) {
            System.out.println("El estacionamiento está vacío.");
            return;
        }

        System.out.println("\n--- Vehículos en el estacionamiento ---");
        Nodo actual = cabeza;
        int contador = 1;
        while (actual != null) {
            System.out.println(contador + ". Placa: " + actual.placa);
            actual = actual.siguiente;
            contador++;
        }
        System.out.println("--------------------------------------");
    }
}

/**
 * Clase principal SistemaEstacionamiento
 * Contiene el método main y gestiona la interfaz de usuario a través de un menú interactivo.
 */
public class Tarea6 {

    /**
     * Método para mostrar las opciones del menú.
     */
    public static void mostrarMenu() {
        System.out.println("\n----- Sistema de Gestión de Estacionamiento -----");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Retirar vehículo");
        System.out.println("3. Buscar vehículo por placa");
        System.out.println("4. Mostrar todos los vehículos estacionados");
        System.out.println("5. Salir");
        System.out.println("-------------------------------------------------");
    }

    /**
     * Método principal que ejecuta el programa.
     * @param args Argumentos de la línea de comandos (no se usan en este caso).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Objeto para leer la entrada del teclado
        ListaEnlazada estacionamiento = new ListaEnlazada(); // Instancia de nuestra lista enlazada

        while (true) { // Bucle infinito para mantener el menú activo
            mostrarMenu();
            System.out.print("Ingrese su opción: ");
            String opcion = scanner.nextLine(); // Lee la opción del usuario

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese la placa del vehículo a registrar: ");
                    // Leer la placa, eliminar espacios extra y convertir a mayúsculas
                    String placaRegistrar = scanner.nextLine().trim().toUpperCase();
                    estacionamiento.agregarVehiculo(placaRegistrar);
                    break;
                case "2":
                    System.out.print("Ingrese la placa del vehículo a retirar: ");
                    String placaRetirar = scanner.nextLine().trim().toUpperCase();
                    estacionamiento.retirarVehiculo(placaRetirar);
                    break;
                case "3":
                    System.out.print("Ingrese la placa del vehículo a buscar: ");
                    String placaBuscar = scanner.nextLine().trim().toUpperCase();
                    estacionamiento.buscarVehiculo(placaBuscar);
                    break;
                case "4":
                    estacionamiento.mostrarVehiculos();
                    break;
                case "5":
                    System.out.println("Saliendo del sistema de estacionamiento. ¡Hasta pronto!");
                    scanner.close(); // Cerrar el scanner antes de salir
                    return; // Termina la ejecución del programa
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}