package grafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

public class Assert
{
	// Verifica que sean iguales como conjuntos
	public static void iguales(int[] esperado, int[] obtenido)
	{
		assertEquals(esperado.length, obtenido.length);
		
		for(int i=0; i<esperado.length; ++i)
			assertTrue( obtenido[i] == esperado[i] );
	}
}
