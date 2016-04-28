/* 
    Nombre del programa: Program_5
    Nombre: Andrés David Mojica Ospina
    Fecha: 29-03-2016
    Descripción: Permite obtener la distribucion t a partir de ciertos valores
*/
package controller;

import java.util.LinkedList;
import java.util.Locale;

/**
 * @author David Mojica
 * @version 1.0 29 de Marzo de 2016
 */
public final class Distribucion 
{
    double num_seg = 10.0;    
    double W;
    double E = 0.00001;
    double dof;
    double resultado;    
    LinkedList multiplicador = new LinkedList();
        
    /**
    * Constructor de clase     
    * @param x tipo double
    * @param degrees_of_freedom tipo double
    * @since incluido desde la versión 1.0
    */
    public Distribucion(double x, double degrees_of_freedom)
    {
        dof = degrees_of_freedom;
        W = x/num_seg;
        
        multiplicador.add(1.0);
        multiplicador.add(4.0);
        multiplicador.add(2.0);
        multiplicador.add(4.0);
        multiplicador.add(2.0);
        multiplicador.add(4.0);
        multiplicador.add(2.0);
        multiplicador.add(4.0);
        multiplicador.add(2.0);
        multiplicador.add(4.0);
        multiplicador.add(1.0);
        
        LinkedList lista_i = crearListaI();
        LinkedList lista_xi = crearListaXi(lista_i);
        LinkedList lista_suma = obtenerSuma(lista_xi);
        LinkedList lista_potencia = obtenerPotencia(lista_suma);
        double division_gamma = obtenerDivisionGamma();
        LinkedList Lista_FXi = obtenerFXi(lista_potencia, division_gamma);
        LinkedList lista_terms = obtenerTerms(Lista_FXi);
        resultado = realizarSumatoria(lista_terms);
    }
    
    /**
     * Método que devuelve el resultado con el formato "##0.00000"
     * @return una variable tipo String
     * @since incluido desde la version 1.0
    */
    public String getDistribucion()
    {
        
        String impresion = String.format(Locale.ENGLISH, "%.5f", this.resultado);
        return impresion;
    }
    
    /**
     * Método que crea una lista desde 0 hasta el valor de la variable num_seg
     * @return un objeto de tipo LinkedList
     * @since incluido desde la version 1.0
    */
    public LinkedList crearListaI()
    {
        LinkedList lista = new LinkedList();
        for(int i = 0; i <= num_seg; i++)
        {
            lista.add(i);
        }
        return lista;
    }
    
    /**
     * Método que crea una lista desde 0 hasta el valor de num_seg 
     * sumando el valor de W = ancho del segmento para la distribución
     * @param lista Linkedlist con los valores y el tamaño de la lista 
     * @return un objeto de tipo LinkedList
     * @since incluido desde la version 1.0
    */
    public LinkedList crearListaXi(LinkedList lista)
    {
        double Xi = 0;
        LinkedList lista_xi = new LinkedList();
        for(int i = 0; i < lista.size(); i++)
        {
            lista_xi.add(Xi);
            Xi = Xi + this.W;
        }
        return lista_xi;
    }
    
    /**
     * Método que obtiene el resultado de la ecuación 1 + (Xi * Xi)/dof
     * para cada elemento de una lista ligada
     * @param lista Linkedlist con los valores Xi
     * @return un objeto de tipo LinkedList
     * @since incluido desde la version 1.0
    */
    public LinkedList obtenerSuma(LinkedList lista)
    {
        LinkedList lista_suma = new LinkedList();
        double suma;
        for(int i = 0; i < lista.size(); i++)
        {
            suma = 1 + (Math.pow((double) lista.get(i), 2)/dof);
            lista_suma.add(suma);
        }
        return lista_suma;
    }
    
    /**
     * Método que obtiene el resultado de la ecuación 1 + (Xi * Xi)/dof
     * elevado a -(dof+1) / 2
     * para cada elemento de una lista ligada
     * @param lista Linkedlist con los valores de l1 + (Xi * Xi)/dof
     * @return un objeto de tipo LinkedList
     * @since incluido desde la version 1.0
    */
    public LinkedList obtenerPotencia(LinkedList lista)
    {
        LinkedList lista_potencia = new LinkedList();
        double potencia = 0.0;
        for(int i = 0; i < lista.size(); i++)
        {
            potencia = Math.pow( (double) lista.get(i), (-(dof + 1) / 2) );
            lista_potencia.add(potencia);
        }
        return lista_potencia;
    }
    
    
    /**
     * Método que obtiene la función gamma de un número
     * @param numero double del que se obtiene la función gamma
     * @return un double resultado de la función gamma
     * @since incluido desde la version 1.0
    */
    public double obtenerGamma(double numero)
    {
        double gamma = 0;
        if(!String.valueOf(numero).contains(".5"))
        {
            numero -= 1;
            gamma = 1.0; // Resultado del factorial
            for (double i = 1.0; i <= numero; i++) 
            {
                gamma *= i;
            }
        }
        else
        {
            double factor = numero;
            gamma = 1.0;        
            for (double i = 1.0; i < numero; i++) 
            {
                factor -= 1;
                gamma *= factor;
            }
            gamma *= Math.sqrt(Math.PI);
        }
        return gamma;
    }
    
    /**
     * Método que obtiene el primer multiplicador para hallar la distribución t
     * por medio de la regla de Simpson
     * @return un double que representa el primer factor de la regla de Simpson
     * @since incluido desde la version 1.0
    */
    public double obtenerDivisionGamma()
    {
        double division_gamma = obtenerGamma(((dof + 1.0) / 2.0)) / (Math.pow((dof * Math.PI), 0.5) * obtenerGamma(dof/2.0));
        return division_gamma;        
    }
    
    /**
     * Método que obtiene F(Xi)
     * @param division_gamma double primer factor para la regla de Simpson
     * @param lista_potencia Linkedlist segundo factor para la regla de Simpson
     * @return un objeto de tipo LinkedList
     * @since incluido desde la version 1.0
    */
    public LinkedList obtenerFXi(LinkedList lista_potencia, Double division_gamma)
    {
        LinkedList lista_FXi = new LinkedList();
        Double suma_FXi = 0.0;
        for(int i = 0; i < lista_potencia.size(); i++)
        {   
            suma_FXi = (double) lista_potencia.get(i) * division_gamma;
            lista_FXi.add(suma_FXi);
        }
        return lista_FXi;
    }
    
    /**
     * Método que obtiene los términos finales para la sumatoria que brinda
     * el resultado final
     * @param lista_FXi Linkedlist de F(Xi) de la regla de Simpson
     * @return un objeto de tipo LinkedList
     * @since incluido desde la version 1.0
    */
    public LinkedList obtenerTerms(LinkedList lista_FXi)
    {
        LinkedList lista_terms = new LinkedList();
        for(int i = 0; i < lista_FXi.size(); i++)
        {
            //System.out.println("-" + (W / 3) +"-"+ (double) multiplicador.get(i)+ "-" +(double) lista_FXi.get(i));
            double term = (W / 3) * (double) multiplicador.get(i) * (double) lista_FXi.get(i);
            lista_terms.add(term);
            
        }
        return lista_terms;
    }
    
    /**
     * Método que permite realizar la sumatoria total de una lista ligada
     * @param lista Linkedlist que se va a sumar
     * @return un double que representa la sumatoria realizada
     * @since incluido desde la version 1.0
    */
    public double realizarSumatoria(LinkedList lista)
    {
        double sumatoria = 0.0;
        for(int i = 0; i < lista.size(); i++)
        {
            sumatoria += (double) lista.get(i);
        }
        return sumatoria;
    }
}