/* 
    Nombre del programa: Program_3
    Nombre: Andrés David Mojica Ospina
    Fecha: 28-02-2016
    Descripción: Clase que permite realizar el reporte de los factores de la desviación
*/
package controller;

import java.util.LinkedList;
import model.AbrirDatos;

public class Reporte 
{
    String reporte;
    /**
    * Constructor de clase
    * @since incluido desde la version 1.0
    */
    public Reporte()
    {
        AbrirDatos abrir_datos = new AbrirDatos();
        
        LinkedList estimated = abrir_datos.llenarLista("src/main/java/data/estimated.txt");
        LinkedList plan_added = abrir_datos.llenarLista("src/main/java/data/plan_added.txt");
        LinkedList actual_added = abrir_datos.llenarLista("src/main/java/data/actual_added.txt");
        LinkedList actual_development = abrir_datos.llenarLista("src/main/java/data/actual_development.txt");
        
        reporte = "<table style='width:400px;border:1px solid black;'><tr><td>Beta 0</td><td>Beta 1</td><td>rxy</td><td>r2</td><td>Yk</td><td>Significancia</td><td>Rango</td><td>LPI</td><td>UPI</td></tr>";
                
        // 1
        Desviacion desviacion_1 = new Desviacion();
        desviacion_1.obtenerDesviacion(estimated, actual_added, 386.0);
        double rxy1 = desviacion_1.getRxy();
        double Yk_1 = desviacion_1.getYk();
        double r2_1 = desviacion_1.getR2();
        double beta_0_1 = desviacion_1.getBeta0();
        double beta_1_1 = desviacion_1.getBeta1();
        double x_promedio_1 = desviacion_1.getPromedioX();
        
        Significancia significancia = new Significancia(estimated, actual_added, beta_0_1, beta_1_1, x_promedio_1, Yk_1);
        double significancia_valor_1 = significancia.hallarSignificancia(rxy1);
        double rango_1 = significancia.hallarRango();
        double lpi_1 = significancia.getLPI();
        double upi_1 = significancia.getUPI();
        reporte += "<tr><td>" + beta_0_1 + "</td><td>" + beta_1_1 + "</td><td>" + rxy1 + "</td><td>" + r2_1 + "</td><td>" + Yk_1 + "</td><td>" + significancia_valor_1 + "</td><td>" + rango_1 + "</td><td>" + lpi_1 + "</td><td>" + upi_1 + "</td></tr>";
                
        // 2        
        Desviacion desviacion_2 = new Desviacion();
        desviacion_2.obtenerDesviacion(estimated, actual_development, 386.0);
        double rxy2 = desviacion_2.getRxy();
        double Yk_2 = desviacion_2.getYk();
        double r2_2 = desviacion_2.getR2();
        double beta_0_2 = desviacion_2.getBeta0();
        double beta_1_2 = desviacion_2.getBeta1();
        double x_promedio_2 = desviacion_2.getPromedioX();
        
        Significancia significancia_2 = new Significancia(estimated, actual_development, beta_0_2, beta_1_2, x_promedio_2, Yk_2);
        double significancia_valor_2 = significancia_2.hallarSignificancia(rxy2);
        double rango_2 = significancia_2.hallarRango();
        double lpi_2 = significancia_2.getLPI();
        double upi_2 = significancia_2.getUPI();
        reporte += "<tr><td>" + beta_0_2 + "</td><td>" + beta_1_2 + "</td><td>" + rxy2 + "</td><td>" + r2_2 + "</td><td>" + Yk_2 + "</td><td>" + significancia_valor_2 + "</td><td>" + rango_2 + "</td><td>" + lpi_2 + "</td><td>" + upi_2 + "</td></tr>";
        
        // 3        
        Desviacion desviacion_3 = new Desviacion();
        desviacion_3.obtenerDesviacion(plan_added, actual_added, 386.0);
        double rxy3 = desviacion_3.getRxy();
        double Yk_3 = desviacion_3.getYk();
        double r2_3 = desviacion_3.getR2();
        double beta_0_3 = desviacion_3.getBeta0();
        double beta_1_3 = desviacion_3.getBeta1();
        double x_promedio_3 = desviacion_3.getPromedioX();
        
        Significancia significancia_3 = new Significancia(plan_added, actual_added, beta_0_3, beta_1_3, x_promedio_3, Yk_3);
        double significancia_valor_3 = significancia_3.hallarSignificancia(rxy3);
        double rango_3 = significancia_3.hallarRango();
        double lpi_3 = significancia_3.getLPI();
        double upi_3 = significancia_3.getUPI();
        reporte += "<tr><td>" + beta_0_3 + "</td><td>" + beta_1_3 + "</td><td>" + rxy3 + "</td><td>" + r2_3 + "</td><td>" + Yk_3 + "</td><td>" + significancia_valor_3 + "</td><td>" + rango_3 + "</td><td>" + lpi_3 + "</td><td>" + upi_3 + "</td></tr>";
        
        // 4
        Desviacion desviacion_4 = new Desviacion();
        desviacion_4.obtenerDesviacion(plan_added, actual_development, 386.0);
        double rxy4 = desviacion_4.getRxy();
        double Yk_4 = desviacion_4.getYk();
        double r2_4 = desviacion_4.getR2();
        double beta_0_4 = desviacion_4.getBeta0();
        double beta_1_4 = desviacion_4.getBeta1();
        double x_promedio_4 = desviacion_4.getPromedioX();
        
        Significancia significancia_4 = new Significancia(plan_added, actual_development, beta_0_4, beta_1_4, x_promedio_4, Yk_4);
        double significancia_valor_4 = significancia_4.hallarSignificancia(rxy4);
        double rango_4 = significancia_4.hallarRango();
        double lpi_4 = significancia_4.getLPI();
        double upi_4 = significancia_4.getUPI();
        reporte += "<tr><td>" + beta_0_4 + "</td><td>" + beta_1_4 + "</td><td>" + rxy4 + "</td><td>" + r2_4 + "</td><td>" + Yk_4 + "</td><td>" + significancia_valor_4 + "</td><td>" + rango_4 + "</td><td>" + lpi_4 + "</td><td>" + upi_4 + "</td></tr>";
        reporte += "</table>";      
    }
    
    /**
     * Método que devuelve el reporte en forma de tabla html
     * @return reporte
     * @since incluido desde la version 1.0
    */
    public String getReporte()
    {
        return this.reporte;
    }
    
}
