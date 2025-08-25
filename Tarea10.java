import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class Campaña Vacunacion {

    public static void main(String[] args) {
        // --- Generación de datos ficticios ---
        // 1. Conjunto de 500 ciudadanos
        Set<Ciudadano> todosLosCiudadanos = new HashSet<>();
        for (int i = 1; i <= 500; i++) {
            todosLosCiudadanos.add(new Ciudadano("ID-" + i, "Ciudadano " + i));
        }

        // 2. Conjunto de 75 ciudadanos vacunados con Pfizer
        Set<Ciudadano> vacunadosPfizer = new HashSet<>();
        for (int i = 1; i <= 75; i++) {
            vacunadosPfizer.add(new Ciudadano("ID-" + i, "Ciudadano " + i));
        }

        // 3. Conjunto de 75 ciudadanos vacunados con AstraZeneca
        Set<Ciudadano> vacunadosAstraZeneca = new HashSet<>();
        for (int i = 76; i <= 150; i++) {
            vacunadosAstraZeneca.add(new Ciudadano("ID-" + i, "Ciudadano " + i));
        }

        // Datos ficticios para los que han recibido ambas dosis
        Set<Ciudadano> dobleDosisPfizer = new HashSet<>();
        for (int i = 1; i <= 40; i++) {
            dobleDosisPfizer.add(new Ciudadano("ID-" + i, "Ciudadano " + i));
        }

        Set<Ciudadano> dobleDosisAstraZeneca = new HashSet<>();
        for (int i = 76; i <= 115; i++) {
            dobleDosisAstraZeneca.add(new Ciudadano("ID-" + i, "Ciudadano " + i));
        }

        // --- Procesamiento de datos con teoría de conjuntos ---
        
        // a) Ciudadanos que no se han vacunado
        Set<Ciudadano> todosVacunados = new HashSet<>(vacunadosPfizer);
        todosVacunados.addAll(vacunadosAstraZeneca);
        Set<Ciudadano> noVacunados = new HashSet<>(todosLosCiudadanos);
        noVacunados.removeAll(todosVacunados);
        
        System.out.println("### Ciudadanos que no se han vacunado (" + noVacunados.size() + "):");
        noVacunados.forEach(c -> System.out.println(c.getNombre()));
        
        System.out.println("\n------------------------------------------------\n");
        
        // b) Ciudadanos que han recibido ambas dosis
        Set<Ciudadano> ambasDosis = new HashSet<>(dobleDosisPfizer);
        ambasDosis.addAll(dobleDosisAstraZeneca);

        System.out.println("### Ciudadanos que han recibido ambas dosis (" + ambasDosis.size() + "):");
        ambasDosis.forEach(c -> System.out.println(c.getNombre()));

        System.out.println("\n------------------------------------------------\n");

        // c) Ciudadanos que solo han recibido la vacuna de Pfizer
        Set<Ciudadano> soloPfizer = new HashSet<>(vacunadosPfizer);
        soloPfizer.removeAll(vacunadosAstraZeneca);

        System.out.println("### Ciudadanos que solo han recibido la vacuna de Pfizer (" + soloPfizer.size() + "):");
        soloPfizer.forEach(c -> System.out.println(c.getNombre()));

        System.out.println("\n------------------------------------------------\n");

        // d) Ciudadanos que solo han recibido la vacuna de AstraZeneca
        Set<Ciudadano> soloAstraZeneca = new HashSet<>(vacunadosAstraZeneca);
        soloAstraZeneca.removeAll(vacunadosPfizer);

        System.out.println("### Ciudadanos que solo han recibido la vacuna de AstraZeneca (" + soloAstraZeneca.size() + "):");
        soloAstraZeneca.forEach(c -> System.out.println(c.getNombre()));
    }
}

class Ciudadano {
    private String id;
    private String nombre;

    public Ciudadano(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudadano ciudadano = (Ciudadano) o;
        return Objects.equals(id, ciudadano.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
