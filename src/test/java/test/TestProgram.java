/* 
    Nombre del programa: Program_7
    Nombre: Andrés David Mojica Ospina
    Fecha: 27-04-2016
    Descripción: Clase que permite probar los metodos del programa 7
*/
package test;


import controller.CalculoVariableP;
import controller.Desviacion;
import controller.Distribucion;
import controller.Reporte;
import controller.Significancia;
import java.util.LinkedList;
import model.AbrirDatos;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestProgram 
{
    Distribucion distribucion = new Distribucion(0.55338, 6);
    Reporte reporte = new Reporte();
    CalculoVariableP calculo_p = new CalculoVariableP();
    
    AbrirDatos abrir_datos = new AbrirDatos();
    LinkedList estimated = abrir_datos.llenarLista("src/main/java/data/estimated.txt");
    LinkedList actual_added = abrir_datos.llenarLista("src/main/java/data/actual_added.txt");
    Desviacion desviacion_1 = new Desviacion();
    
    
    
    String resultado = "";
    String resultado_reporte = "";
    double resultado_variable_p;
    double significancia_valor_1;
    
    @Before
    public void setUp() 
    {
        resultado = distribucion.getDistribucion();
        resultado_reporte = reporte.getReporte();
        resultado_variable_p = calculo_p.getResultado("0.2","6").doubleValue();
        
        desviacion_1.obtenerDesviacion(estimated, actual_added, 386.0);
        double rxy1 = desviacion_1.getRxy();
        double Yk_1 = desviacion_1.getYk();
        double r2_1 = desviacion_1.getR2();
        double beta_0_1 = desviacion_1.getBeta0();
        double beta_1_1 = desviacion_1.getBeta1();
        double x_promedio_1 = desviacion_1.getPromedioX();
        Significancia significancia = new Significancia(estimated, actual_added, beta_0_1, beta_1_1, x_promedio_1, Yk_1);
                
        significancia_valor_1 = significancia.hallarSignificancia(rxy1);
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
    public void testSignificancia()
    {
        assertEquals("0.006020000000000025", String.valueOf(significancia_valor_1));
    }
    
    @Test
    public void testReporte()
    {
        assertEquals("<table style='width:400px;border:1px solid black;'><tr><td>Beta 0</td><td>Beta 1</td><td>rxy</td><td>r2</td><td>Yk</td><td>Significancia</td><td>Rango</td><td>LPI</td><td>UPI</td></tr><tr><td>-22.552532752034267</td><td>1.727932426206986</td><td>0.9544965741046826</td><td>0.9110637099775758</td><td>644.4293837638623</td><td>0.006020000000000025</td><td>1133.128037467915</td><td>-148.76024246367808</td><td>1437.619009991403</td></tr><tr><td>-4.038881574687551</td><td>0.16812664988162895</td><td>0.9333068981405511</td><td>0.871061766116737</td><td>60.858005279621224</td><td>0.0011999999999999789</td><td>109.69396803488111</td><td>-15.927772344795542</td><td>137.643782904038</td></tr><tr><td>-23.92388825291539</td><td>1.430966943551199</td><td>0.9631140931490527</td><td>0.9275887564223222</td><td>528.4293519578474</td><td>0.012419999999999987</td><td>1133.1280386687529</td><td>-264.7602751102795</td><td>1321.6189790259743</td></tr><tr><td>-4.6037454233089505</td><td>0.14016352638883628</td><td>0.9480329874300507</td><td>0.8987665452555467</td><td>49.49937576278185</td><td>0.0035800000000000276</td><td>109.69396815112982</td><td>-27.286401943009025</td><td>126.28515346857273</td></tr></table>", resultado_reporte );
    } 
}