package ec.uea.ClaseArrys3;

//Atributos o propiedades 

public class Estudiante {
    private string id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String[] telefonos;

    //Creamos el constructor , recibe los datos y los guarda en los atributos
    
    public Estudiante(int id, String nombre, String apellido, String direccion, String[] telefonos) {
        this.id = id;  // id del objeto es igual al id que se recibe como parámetro 
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefonos = telefonos;
    }
    //  método  
    public void mostrarDatos() {
        System.out.println("===== DATOS DEL ESTUDIANTE =====");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Dirección: " + direccion);
        System.out.print("Teléfonos: ");
        for (String tel : telefonos) {
            System.out.print(tel + " ");
        }
        System.out.println("\n===============================");
    }
}
