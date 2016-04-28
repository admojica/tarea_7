/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.LinkedList;

/**
 *
 * @author David
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
    
    public Significancia(LinkedList x, LinkedList y, double beta_cero, double beta_uno, double x_prom, double Yka)
    {
        lista_1 = x;
        lista_2 = y;
        beta_0 = beta_cero;
        beta_1 = beta_uno;
        Yk = Yka;
        x_promedio = x_prom;
    }
    
    public double hallarSignificancia(double rxy)
    {
        double significancia;
        double x = (Math.abs(rxy) * Math.sqrt(8.0)) / Math.sqrt(1 - (Math.pow(rxy, 2.0)));
        Distribucion distribucion_p = new Distribucion(x, 8.0);
        significancia = Double.valueOf(distribucion_p.getDistribucion());
        significancia = 1 - (2 * significancia);
        return significancia;
    }
    
    public double hallarRango()
    {
        CalculoVariableP calculo_p = new CalculoVariableP();
        double x = calculo_p.getResultado("0.35", "8").doubleValue();
        double desviacion = desviacionEstandar();
        double raiz_multiplicador = multiplicadorRaiz();
        
        double rango = x * desviacion * raiz_multiplicador;
        return rango;              
    }
    
    public double getUPI()
    {
        double rango_70 = hallarRango() * 0.7;
        double upi = Yk + rango_70;
        return upi;
    }
    
    public double getLPI()
    {
        double rango_70 = hallarRango() * 0.7;
        double lpi = Yk - rango_70;
        return lpi;
    }
    
    // metodo sumatoria denominador de la raiz del rango
    public double denominadorRaiz(LinkedList lista_a)
    {
        double denominador_raiz = 0;
        for (int i = 0; i < lista_a.size(); i++) 
        {
            denominador_raiz = denominador_raiz - Math.pow((double) lista_a.get(i) - x_promedio, 2);
        }
        return denominador_raiz;
    }
    
    // Metodo para hallar el multiplicador de raiz del rango
    public double multiplicadorRaiz()
    {
        double sumatoria_denominador = denominadorRaiz(lista_1);
        double raiz = 0;
        raiz = Math.sqrt( 1 + 0.1 + Math.pow( (Xk - x_promedio) / (sumatoria_denominador)  , 2) );
        return raiz;        
    }
    
    // Metodo sumatoria y1-B0-B1x1^
    public double sumatoriaDesviacion(LinkedList lista_x, LinkedList lista_y, double beta_0, double beta_1)
    {
        double sumatoria_desviacion = 0;
        for (int i = 0; i < lista_y.size(); i++) 
        {
            sumatoria_desviacion = sumatoria_desviacion + Math.pow((double)lista_y.get(i) - beta_0 - (beta_1 * (double) lista_x.get(i)), 2 );
        }
        return sumatoria_desviacion;
    }
    
    // Metodo para hallar desviacion estandar sigma
    public double desviacionEstandar()
    {        
        double multiplicador = sumatoriaDesviacion(lista_1, lista_2, 0, 0);
        double desviacion = Math.sqrt( (0.125) * multiplicador );
        return desviacion;
    }
    
}
