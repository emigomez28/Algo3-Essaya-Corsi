import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Rot13Test {
    @Test
    public void test1() {
        // seccion 1: arrange
        String cadena = "a";
        String esperado = "n";

        //seccion 2: act
        String resultado = new Rot13().cifrar(cadena);

        //seccion 3: assert

        assertEquals(esperado, resultado);
    }

    @Test
    public void test2() {
        // seccion 1: arrange
        String cadena = "hello";
        String esperado = "uryyb";

        //seccion 2: act
        String resultado = new Rot13().cifrar(cadena);

        //seccion 3: assert

        assertEquals(esperado, resultado);

    }

    @Test
    public void testCadenaVacia() {
        // seccion 1: arrange
        String cadena = "";
        String esperado = "";

        //seccion 2: act
        String resultado = new Rot13().cifrar(cadena);

        //seccion 3: assert

        assertEquals(esperado, resultado);

    }
    
}