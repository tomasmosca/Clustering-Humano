package grafo;

import static org.junit.Assert.*;


import org.junit.*;

import org.junit.Test;

public class GrafoTest {

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoMatriz() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(-2, 3, 2);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoListaAdyacencia() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaListaAdyacencia(-2, 3);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoMatriz() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(3, -1, 2);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoListaAdyacencia() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaListaAdyacencia(3, -1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoMatriz() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(6, 3, 2);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoListaAdyacencia() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaListaAdyacencia(6, 3);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoMatriz() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(1, 5, 2);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoListaAdyacencia() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaListaAdyacencia(1, 5);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopMatrizTest(){
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(2, 2, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopListaTest(){
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaListaAdyacencia(3, 3);
	}
	
	@Test
	public void aristaExistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(2, 3, 2);
		assertEquals( grafo.existeArista(2, 3),2 );
	}

	@Test
	public void aristaOpuestaTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(2, 3, 4);
		assertEquals( grafo.existeArista(3, 2),4 );
	}

	@Test
	public void aristaInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(2, 3, 1);
		assertNotEquals(grafo.existeArista(1, 4),1);
	}

	@Test
	public void agregarAristaDosVecesTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(2, 3, 2);
		grafo.agregarAristaMatriz(2, 3, 2);

		assertEquals( grafo.existeArista(2, 3),2);
	}
	
	@Test
	public void eliminarAristaExistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(2, 4, 3);
		
		grafo.eliminarAristaMatriz(2, 4);
		assertNotEquals( grafo.existeArista(2, 4) ,3);
	}

	@Test
	public void eliminarAristaInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.eliminarAristaMatriz(2, 4);
		assertEquals( grafo.existeArista(2, 4),0 );
	}
	
	@Test
	public void eliminarAristaDosVecesTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(2, 4, 3);
		
		grafo.eliminarAristaMatriz(2, 4);
		grafo.eliminarAristaMatriz(2, 4);
		assertEquals( grafo.existeArista(2, 4),0 );
	}

	@Test
	public void testArbolGenMin() {
		
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(0, 1, 9);
		grafo.agregarAristaMatriz(0, 2, 9);
		grafo.agregarAristaMatriz(0, 3, 9);
		grafo.agregarAristaMatriz(0, 4, 5);
		grafo.agregarAristaMatriz(1, 2, 0);
		grafo.agregarAristaMatriz(1, 3, 6);
		grafo.agregarAristaMatriz(1, 4, 8);
		grafo.agregarAristaMatriz(2, 3, 6);
		grafo.agregarAristaMatriz(2, 4, 8);
		grafo.agregarAristaMatriz(3, 4, 4);
		
		grafo.ArbolGenMin();
		
		int[] esperado = {-1, 3, 1, 4, 0};
		Assert.iguales(esperado, grafo.getVerticesPadres());
	}

	@Test
	public void testBFS() {
		Grafo grafo = new Grafo(5);
		grafo.agregarAristaMatriz(0, 1, 9);
		grafo.agregarAristaMatriz(0, 2, 9);
		grafo.agregarAristaMatriz(0, 3, 9);
		grafo.agregarAristaMatriz(0, 4, 5);
		grafo.agregarAristaMatriz(1, 2, 0);
		grafo.agregarAristaMatriz(1, 3, 6);
		grafo.agregarAristaMatriz(1, 4, 8);
		grafo.agregarAristaMatriz(2, 3, 6);
		grafo.agregarAristaMatriz(2, 4, 8);
		grafo.agregarAristaMatriz(3, 4, 4);
		
		grafo.ArbolGenMin();
		grafo.BFS(0, grafo.getVertices1());
		
		int[] esperado = {0, 4, 3};
		Assert.iguales(esperado, grafo.getVerticesBFS());
		
	}

}
