package ec.edu.uea.semana14;
using System;
using System.Collections.Generic;

// Definición de la clase Nodo para el árbol binario
public class Node
{
    public int Data;
    public Node Left;
    public Node Right;

    public Node(int data)
    {
        Data = data;
        Left = null;
        Right = null;
    }
}

// Definición de la clase Árbol Binario
public class BinaryTree
{
    private Node root;

    public BinaryTree()
    {
        root = null;
    }

    // Método para insertar un nodo en el árbol
    public void Insert(int data)
    {
        root = InsertRec(root, data);
    }

    private Node InsertRec(Node root, int data)
    {
        if (root == null)
        {
            root = new Node(data);
            return root;
        }

        if (data < root.Data)
        {
            root.Left = InsertRec(root.Left, data);
        }
        else if (data > root.Data)
        {
            root.Right = InsertRec(root.Right, data);
        }

        return root;
    }

    // Método para buscar un nodo en el árbol
    public bool Search(int data)
    {
        return SearchRec(root, data);
    }

    private bool SearchRec(Node root, int data)
    {
        if (root == null)
        {
            return false;
        }

        if (root.Data == data)
        {
            return true;
        }

        if (data < root.Data)
        {
            return SearchRec(root.Left, data);
        }
        else
        {
            return SearchRec(root.Right, data);
        }
    }

    // Método para eliminar un nodo del árbol
    public void Delete(int data)
    {
        root = DeleteRec(root, data);
    }

    private Node DeleteRec(Node root, int data)
    {
        if (root == null)
        {
            return root;
        }

        if (data < root.Data)
        {
            root.Left = DeleteRec(root.Left, data);
        }
        else if (data > root.Data)
        {
            root.Right = DeleteRec(root.Right, data);
        }
        else
        {
            // Caso 1: El nodo no tiene hijos o solo tiene uno
            if (root.Left == null)
            {
                return root.Right;
            }
            else if (root.Right == null)
            {
                return root.Left;
            }

            // Caso 2: El nodo tiene dos hijos
            root.Data = MinValue(root.Right);
            root.Right = DeleteRec(root.Right, root.Data);
        }
        return root;
    }

    private int MinValue(Node root)
    {
        int minv = root.Data;
        while (root.Left != null)
        {
            minv = root.Left.Data;
            root = root.Left;
        }
        return minv;
    }

    // Recorrido Inorden: Izquierda -> Raíz -> Derecha
    public void InorderTraversal()
    {
        Console.Write("Recorrido Inorden: ");
        InorderRec(root);
        Console.WriteLine();
    }

    private void InorderRec(Node root)
    {
        if (root != null)
        {
            InorderRec(root.Left);
            Console.Write(root.Data + " ");
            InorderRec(root.Right);
        }
    }

    // Recorrido Preorden: Raíz -> Izquierda -> Derecha
    public void PreorderTraversal()
    {
        Console.Write("Recorrido Preorden: ");
        PreorderRec(root);
        Console.WriteLine();
    }

    private void PreorderRec(Node root)
    {
        if (root != null)
        {
            Console.Write(root.Data + " ");
            PreorderRec(root.Left);
            PreorderRec(root.Right);
        }
    }

    // Recorrido Postorden: Izquierda -> Derecha -> Raíz
    public void PostorderTraversal()
    {
        Console.Write("Recorrido Postorden: ");
        PostorderRec(root);
        Console.WriteLine();
    }

    private void PostorderRec(Node root)
    {
        if (root != null)
        {
            PostorderRec(root.Left);
            PostorderRec(root.Right);
            Console.Write(root.Data + " ");
        }
    }
}

// Clase principal del programa
public class Program
{
    public static void Main(string[] args)
    {
        BinaryTree tree = new BinaryTree();
        bool exit = false;

        while (!exit)
        {
            Console.WriteLine("\n--- Menú de Árbol Binario ---");
            Console.WriteLine("1. Insertar un nodo");
            Console.WriteLine("2. Recorrer el árbol (Inorden)");
            Console.WriteLine("3. Recorrer el árbol (Preorden)");
            Console.WriteLine("4. Recorrer el árbol (Postorden)");
            Console.WriteLine("5. Buscar un elemento");
            Console.WriteLine("6. Eliminar un nodo");
            Console.WriteLine("7. Salir");
            Console.Write("Seleccione una opción: ");

            string choice = Console.ReadLine();
            int data;

            switch (choice)
            {
                case "1":
                    Console.Write("Ingrese el valor a insertar: ");
                    if (int.TryParse(Console.ReadLine(), out data))
                    {
                        tree.Insert(data);
                        Console.WriteLine($"El nodo {data} ha sido insertado.");
                    }
                    else
                    {
                        Console.WriteLine("Entrada inválida. Por favor, ingrese un número entero.");
                    }
                    break;
                case "2":
                    tree.InorderTraversal();
                    break;
                case "3":
                    tree.PreorderTraversal();
                    break;
                case "4":
                    tree.PostorderTraversal();
                    break;
                case "5":
                    Console.Write("Ingrese el valor a buscar: ");
                    if (int.TryParse(Console.ReadLine(), out data))
                    {
                        if (tree.Search(data))
                        {
                            Console.WriteLine($"El valor {data} se encuentra en el árbol.");
                        }
                        else
                        {
                            Console.WriteLine($"El valor {data} no se encuentra en el árbol.");
                        }
                    }
                    else
                    {
                        Console.WriteLine("Entrada inválida. Por favor, ingrese un número entero.");
                    }
                    break;
                case "6":
                    Console.Write("Ingrese el valor a eliminar: ");
                    if (int.TryParse(Console.ReadLine(), out data))
                    {
                        if (tree.Search(data))
                        {
                            tree.Delete(data);
                            Console.WriteLine($"El nodo {data} ha sido eliminado.");
                        }
                        else
                        {
                            Console.WriteLine($"El valor {data} no se encuentra en el árbol.");
                        }
                    }
                    else
                    {
                        Console.WriteLine("Entrada inválida. Por favor, ingrese un número entero.");
                    }
                    break;
                case "7":
                    exit = true;
                    Console.WriteLine("Saliendo del programa...");
                    break;
                default:
                    Console.WriteLine("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }
}


