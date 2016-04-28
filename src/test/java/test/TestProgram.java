/* 
    Nombre del programa: Program_6
    Nombre: Andrés David Mojica Ospina
    Fecha: 11-04-2016
    Descripción: Clase que permite probar los metodos del programa 6
*/
package test;

import controller.CalculoVariableP;
import controller.Distribucion;
import controller.Reporte;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestProgram 
{
    Distribucion distribucion = new Distribucion(0.55338, 6);
    Reporte reporte = new Reporte();
    CalculoVariableP calculo_p = new CalculoVariableP();
    
    String resultado = "";
    String resultado_reporte = "";
    double resultado_variable_p;
    
    @Before
    public void setUp() 
    {
        resultado = distribucion.getDistribucion();
        resultado_reporte = reporte.getReporte();
        resultado_variable_p = calculo_p.getResultado("0.2","6").doubleValue();
    }
    
    @Test
    public void testDistribucion()
    {
        assertEquals("0.20000", resultado);
    }
    
    @Test
    public void testCalculoVariableP()
    {
        assertEquals("0.55337", String.valueOf(resultado_variable_p));        
    }
    
    @Test
    public void testReporte()
    {
        assertEquals("<table style='width:400px;border:1px solid black;'><tr><td>p</td><td>dof</td><td>x</td></tr><tr><td>0.2</td><td>5</td><td>0.55337</td></tr><tr><td>0.45</td><td>15</td><td>1.75300</td></tr><tr><td>0.495</td><td>4</td><td>4.60686</td></tr></table>", resultado_reporte );
    }    
}
