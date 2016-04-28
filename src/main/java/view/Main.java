/* 
    Nombre del programa: Program_7
    Nombre: Andrés David Mojica Ospina
    Fecha: 27-04-2016
    Descripción: Permite ejecutar la aplicación en la web de heroku
*/
package view;

import controller.Reporte;
import static spark.Spark.*;
import static spark.Spark.get;

/**
 * @author David Mojica
 * @version 1.0 6 de Abril de 2016
 */
public class Main 
{
    /**
     * Método de set-up del programa
     * @param  args Arreglo de tipo string
     * @since incluido desde la version 1.0
    */
    public static void main(String[] args)
    {
      port(Integer.valueOf(System.getenv("PORT")));
      staticFileLocation("/public");

      Reporte reporte = new Reporte();
      String resultado = reporte.getReporte();

      get("/", (req, res) -> resultado);
    }
}
