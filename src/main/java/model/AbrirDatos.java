/* 
    Nombre del programa: Program_3
    Nombre: Andrés David Mojica Ospina
    Fecha: 28-02-2016
    Descripción: Permite abrir los archivos.txt para convertilos en list
*/
package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class AbrirDatos 
{    
    // Metodo que lee el archivo .txt y llena una lista
    public LinkedList llenarLista(String ruta)
    {
        LinkedList lista = new LinkedList();
        try 
        {
            // Abrimos el archivo
            FileInputStream fstream = new FileInputStream(ruta);
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            // Leer el archivo linea por linea
            while ((strLinea = buffer.readLine()) != null)   
            {
                // Imprimimos la línea por pantalla
                lista.add(Double.parseDouble(strLinea));
            }
            // Se cierra el archivo
            entrada.close();
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("Archivo no encontrado: " + ex);
        } 
        catch (IOException ex) 
        {
            System.out.println("Archivo no puede ser leido: " + ex);
        }
        return lista;
    }
}
