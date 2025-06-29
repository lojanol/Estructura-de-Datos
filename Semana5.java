
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Semana5{

    public static void main(String[] args) {
        // Lista para almacenar las asignaturas iniciales
        List<String> asignaturas = new ArrayList<>();
        asignaturas.add("Matemáticas");
        asignaturas.add("Física");
        asignaturas.add("Química");
        asignaturas.add("Historia");
        asignaturas.add("Lengua");

        // Nota mínima para aprobar
        final double NOTA_MINIMA_APROBACION = 8.0; // Puedes cambiar la nota mínima si lo deseas

        // Lista para almacenar las asignaturas que el usuario debe repetir
        List<String> asignaturasARepetir = new ArrayList<>();

        // Objeto Scanner para leer la entrada del usuario
        Scanner Scanner = new Scanner(System.in);

        System.out.println("Bienvenido al gestor de asignaturas.");
        System.out.println("Por favor, introduce la nota obtenida en cada asignatura (0-10).");

        // Iterar sobre las asignaturas para pedir las notas
        for (String asignatura : asignaturas) {
            double nota = -1; // Inicializar nota a un valor inválido
            boolean entradaValida = false;

            while (!entradaValida) {
                try {
                    System.out.print("¿Qué nota sacaste en " + asignatura + "? ");
                    nota = Double.parseDouble(Scanner.nextLine()); // Leer la línea completa y parsearla

                    if (nota >= 0 && nota <= 10) {
                        entradaValida = true; // La entrada es válida, salir del bucle
                    } else {
                        System.out.println("La nota debe estar entre 0 y 10. Inténtalo de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Por favor, introduce un número para la nota.");
                }
            }

            // Comprobar si la asignatura está aprobada o no
            if (nota < NOTA_MINIMA_APROBACION) {
                asignaturasARepetir.add(asignatura);
                System.out.println("Tienes que repetir " + asignatura + ".");
            } else {
                System.out.println("¡Felicidades! Has aprobado " + asignatura + ".");
            }
        }

        // Mostrar las asignaturas a repetir
        System.out.println("\n--- Resumen ---");
        if (asignaturasARepetir.isEmpty()) {
            System.out.println("¡Felicidades! Has aprobado todas las asignaturas.");
        } else {
            System.out.println("Las asignaturas que tienes que repetir son:");
            for (String asignatura : asignaturasARepetir) {
                System.out.println("- " + asignatura);
            }
        }

        // Cerrar el scanner para liberar recursos
        Scanner.close();
    }
}
