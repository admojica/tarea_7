/* 
    Nombre del programa: Program_3
    Nombre: Andrés David Mojica Ospina
    Fecha: 28-02-2016
    Descripción: Permite obtener los valores para la desviación estándar de dos listas
*/
package controller;

import java.text.DecimalFormat;
import java.util.LinkedList;

public final class Desviacion 
{
    double promedio_x;
    double promedio_y;
    
    double beta_1;
    double rxy;
    double r_cuadrado;
    double beta_0;
    double Yk;    
    
    // Metodo  para obtener la desviacion estandar a partir de unas listas y el estimado de LOC
    public void obtenerDesviacion(LinkedList lista_a, LinkedList lista_b, Double estimated_LOC)
    {
        Double tamano_lista = (double) lista_a.size();
        
        LinkedList lista_cuadrado_a = potenciaCuadrado(lista_a);
        LinkedList lista_cuadrado_b = potenciaCuadrado(lista_b);
        LinkedList lista_multiplicacion = multiplicacion(lista_a, lista_b);
        
        double sumatoria_a = sumatoria(lista_a);
        double sumatoria_b = sumatoria(lista_b);
        
        double sumatoria_cuadrado_a = sumatoria(lista_cuadrado_a);
        double sumatoria_cuadrado_b = sumatoria(lista_cuadrado_b);
        double sumatoria_multiplicacion = sumatoria(lista_multiplicacion);
        
        promedio_x = getPromedio(lista_a);
        promedio_y = getPromedio(lista_b);
        
        // Beta_1
        double numerador_beta_1 = ((sumatoria_multiplicacion) - (tamano_lista * (sumatoria_a/tamano_lista) * (sumatoria_b/tamano_lista)));
        double denominador_beta_1 = ((sumatoria_cuadrado_a) - (tamano_lista * Math.pow(promedio_x, 2)));
        beta_1 = numerador_beta_1 / denominador_beta_1;
        
        // r_x_y
        double numerador_rxy = (tamano_lista * sumatoria_multiplicacion) - (sumatoria_a * sumatoria_b);
        double denominador_rxy = Math.sqrt( ((tamano_lista * sumatoria_cuadrado_a) - Math.pow(sumatoria_a , 2)) * ((tamano_lista * sumatoria_cuadrado_b) - Math.pow(sumatoria_b , 2)) );
        rxy = numerador_rxy / denominador_rxy;
        
        // r cuadrado
        r_cuadrado = Math.pow(rxy, 2);
        
        // Beta_0
        beta_0 = (promedio_y) - (beta_1 * promedio_x);
        
        // Yk
        Yk = beta_0 + (beta_1 * estimated_LOC);
        
        DecimalFormat formato = new DecimalFormat("##0.0000");
        
        System.out.println( formato.format(beta_0) + " | " + formato.format(beta_1) + " | " + formato.format(rxy) + " | " + formato.format(r_cuadrado) + " | " + formato.format(Yk));
        
    }
    
    //
    public double getRxy()
    {
        return rxy;
    }
    
    public double getR2()
    {
        return r_cuadrado;
    }
    
    public double getBeta0()
    {
        return beta_0;
    }
    
    public double getBeta1()
    {
        return beta_1;
    }
    
    public double getYk()
    {
        return Yk;
    }
    
    public double getPromedioX()
    {
        return promedio_x;
    }
    
    public double getPromedioY()
    {
        return promedio_y;
    }
    
    public double getPromedio(LinkedList lista_promedio)
    {
        double promedio = 0.0;
        for(int i = 0; i < lista_promedio.size(); i++)
        {
            promedio += (Double) lista_promedio.get(i);
        }
        promedio = promedio / lista_promedio.size();        
        return promedio;
    }
    
    // Metodo para obtener la potencia al cuadrado
    public LinkedList potenciaCuadrado(LinkedList lista)
    {
        LinkedList lista_cuadrado = new LinkedList();
        for(int i = 0; i < lista.size(); i++)
        {
            lista_cuadrado.add( Math.pow( (Double) lista.get(i), 2));
        }
        return lista_cuadrado;
    }
    
    // Metodo para obtener la multiplicación de los dos factores de la desviación estándar
    public LinkedList multiplicacion(LinkedList lista_a, LinkedList lista_b)
    {
        LinkedList lista_multiplicada = new LinkedList();
        for(int i = 0; i < lista_a.size(); i++)
        {
            lista_multiplicada.add( (Double) lista_a.get(i) * (Double) lista_b.get(i) );
        }
        return lista_multiplicada;
    }
    
    // Metodo para sumar la lista
    public Double sumatoria(LinkedList lista)
    {
        Double suma = 0.0;
        for(int i = 0; i < lista.size(); i++)
        {
            suma += (Double) lista.get(i);
        }
        return suma;
    }
    
}
