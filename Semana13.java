package ec.edu.uea.semana13;
using System;
using System.Collections.Generic;

class Revista
{
    public string Titulo { get; set; }
}

class CatalogoRevistas
{
    private List<Revista> revistas;

    public CatalogoRevistas()
    {
        revistas = new List<Revista>();
        // Ingresar al menos 10 títulos de revistas
        revistas.Add(new Revista { Titulo = "National Geographic" });
        revistas.Add(new Revista { Titulo = "Scientific American" });
        revistas.Add(new Revista { Titulo = "Time" });
        revistas.Add(new Revista { Titulo = "The Economist" });
        revistas.Add(new Revista { Titulo = "Vogue" });
        revistas.Add(new Revista { Titulo = "Forbes" });
        revistas.Add(new Revista { Titulo = "Wired" });
        revistas.Add(new Revista { Titulo = "Cosmopolitan" });
        revistas.Add(new Revista { Titulo = "People" });
        revistas.Add(new Revista { Titulo = "Bloomberg Businessweek" });
    }

    // Búsqueda iterativa de un título
    public bool BuscarRevistaIterativo(string tituloBuscado)
    {
        // Convertir la búsqueda a minúsculas para una comparación sin distinción de mayúsculas y minúsculas
        string tituloNormalizado = tituloBuscado.ToLower(); 

        foreach (Revista revista in revistas)
        {
            if (revista.Titulo.ToLower() == tituloNormalizado)
            {
                return true; // Título encontrado
            }
        }
        return false; // Título no encontrado
    }

    public void Iniciar()
    {
        bool continuar = true;
        while (continuar)
        {
            Console.Clear();
            Console.WriteLine("--- Gestor de Catálogo de Revistas ---");
            Console.WriteLine("1. Buscar revista por título");
            Console.WriteLine("2. Salir");
            Console.Write("\nSeleccione una opción: ");

            string opcion = Console.ReadLine();

            switch (opcion)
            {
                case "1":
                    Console.Write("\nIngrese el título de la revista a buscar: ");
                    string titulo = Console.ReadLine();
                    bool encontrado = BuscarRevistaIterativo(titulo);

                    if (encontrado)
                    {
                        Console.WriteLine("\n¡Encontrado!");
                    }
                    else
                    {
                        Console.WriteLine("\nNo encontrado.");
                    }
                    Console.WriteLine("\nPresione cualquier tecla para continuar...");
                    Console.ReadKey();
                    break;

                case "2":
                    continuar = false;
                    Console.WriteLine("\nSaliendo del programa...");
                    break;

                default:
                    Console.WriteLine("\nOpción no válida. Presione cualquier tecla para intentar de nuevo...");
                    Console.ReadKey();
                    break;
            }
        }
    }
}

class Program
{
    static void Main(string[] args)
    {
        CatalogoRevistas catalogo = new CatalogoRevistas();
        catalogo.Iniciar();
    }
}