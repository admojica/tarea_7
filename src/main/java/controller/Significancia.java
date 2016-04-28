/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.LinkedList;

/**
 * @author David Mojica
 * @version 1.0 27 de Abril de 2016
 */
public class Significancia 
{
    LinkedList lista_1;
    LinkedList lista_2;
    double beta_0;
    double beta_1;
    double Yk;
    double x_promedio;    
    double Xk = 386;
    
    /**
    * Constructor de clase
     * @param x
     * @param y
     * @param beta_cero
     * @param beta_uno
     * @param x_prom
     * @param Yka
    * @since incluido desde la version 1.0
    */
    public Significancia(LinkedList x, LinkedList y, double beta_cero, double beta_uno, double x_prom, double Yka)
    {
        lista_1 = x;
        lista_2 = y;
        beta_0 = beta_cero;
        beta_1 = beta_uno;
        Yk = Yka;
        x_promedio = x_prom;
    }
    
    /**
     * Método que halla la significancia a partir de la variable rxy
     * @param rxy
     * @return significancia 
     * @since incluido desde la version 1.0
    */
    public double hallarSignificancia(double rxy)
    {
        double significancia;
        double x = (Math.abs(rxy) * Math.sqrt(8.0)) / Math.sqrt(1 - (Math.pow(rxy, 2.0)));
        Distribucion distribucion_p = new Distribucion(x, 8.0);
        significancia = Double.valueOf(distribucion_p.getDistribucion());
        significancia = 1 - (2 * significancia);
        return significancia;
    }
    
    /**
     * Método para hallar el rango a partir de la significancia
     * @return rango
     * @since incluido desde la version 1.0
    */
    public double hallarRango()
    {
        CalculoVariableP calculo_p = new CalculoVariableP();
        double x = calculo_p.getResultado("0.35", "8").doubleValue();
        double desviacion = desviacionEstandar();
        double raiz_multiplicador = multiplicadorRaiz();
        
        double rango = x * desviacion * raiz_multiplicador;
        return rango;              
    }
    
    /**
     * Método para hallar el UPI
     * @return upi
     * @since incluido desde la version 1.0
    */
    public double getUPI()
    {
        double rango_70 = hallarRango() * 0.7;
        double upi = Yk + rango_70;
        return upi;
    }
    
    /**
     * Método para hallar el LPI
     * @return lpi
     * @since incluido desde la version 1.0
    */
    public double getLPI()
    {
        double rango_70 = hallarRango() * 0.7;
        double lpi = Yk - rango_70;
        return lpi;
    }
    
    /**
     * Método sumatoria denominador de la raiz del rango
     * @param lista_a
     * @return denominador_raiz
     * @since incluido desde la version 1.0
    */
    public double denominadorRaiz(LinkedList lista_a)
    {
        double denominador_raiz = 0;
        for (int i = 0; i < lista_a.size(); i++) 
        {
            denominador_raiz = denominador_raiz - Math.pow((double) lista_a.get(i) - x_promedio, 2);
        }
        return denominador_raiz;
    }
    
    /**
     * Método para hallar el multiplicador de raiz del rango
     * @return raiz
     * @since incluido desde la version 1.0
    */
    public double multiplicadorRaiz()
    {
        double sumatoria_denominador = denominadorRaiz(lista_1);
        double raiz = 0;
        raiz = Math.sqrt( 1 + 0.1 + Math.pow( (Xk - x_promedio) / (sumatoria_denominador)  , 2) );
        return raiz;        
    }
    
    /**
     * Metodo para hallar la sumatoria de y1-B0-B1x1^2
     * @param lista_x
     * @param lista_y
     * @param beta_0
     * @param beta_1
     * @return sumatoria_desviacion
     * @since incluido desde la version 1.0
    */
    public double sumatoriaDesviacion(LinkedList lista_x, LinkedList lista_y, double beta_0, double beta_1)
    {
        double sumatoria_desviacion = 0;
        for (int i = 0; i < lista_y.size(); i++) 
        {
            sumatoria_desviacion = sumatoria_desviacion + Math.pow((double)lista_y.get(i) - beta_0 - (beta_1 * (double) lista_x.get(i)), 2 );
        }
        return sumatoria_desviacion;
    }
    
    /**
     * Método para hallar desviacion estandar sigma
     * @return desviacion
     * @since incluido desde la version 1.0
    */
    public double desviacionEstandar()
    {        
        double multiplicador = sumatoriaDesviacion(lista_1, lista_2, 0, 0);
        double desviacion = Math.sqrt( (0.125) * multiplicador );
        return desviacion;
    }    
}
