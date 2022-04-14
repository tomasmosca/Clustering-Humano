package tp2;

import grafo.Grafo;

import grafo.Vertice;
import java.util.HashMap;
import java.util.List;

public class AlgoritmoSimilaridad {
	
	//Informacion de las personas en la tabla
	private HashMap<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();
	private int[] grafoOrdenado;
	
	//algoritmo para calculo de similaridad, devuelve un arreglo de vertices
	public Vertice[] algoritmoSimilaridad(int vertices) {
		verificarVertice(vertices);
		Grafo grafo = new Grafo(vertices);
		
		//construye un grafo completo (vertice por persona,arista entre cada par de persona y con indice de similaridad)
		for(int i=0;i<vertices;i++) {
			for(int j=i+1;j<vertices;j++) {
				grafo.agregarAristaMatriz(i, j, similaridad(i,j));
			}
		}
		
		//construye un arbol generador minimo del anterior grafo y se elimina la arista de mayor peso
		grafo.ArbolGenMin();
		
		//se le asigna nombre a cada vertice
		for(int i=0;i<vertices;i++) {
			Object clave = hashMap.keySet().toArray()[i];
			grafo.getVertices1()[i].nombre = (String) clave;
		}
		//recorre el grafo anterior (devuelve las dos componentes conexas)
		grafo.BFS(0, grafo.getVertices1());
		grafoOrdenado = grafo.getVerticesBFS();
		return grafo.getVerticeArbolMin();
	}
	
	private int similaridad(int i, int j) {
		int res = 0;
		
		//obtiene la info de las personas (valores para calculo de similaridad)
		Object clave1 = hashMap.keySet().toArray()[i];
		Object clave2 = hashMap.keySet().toArray()[j];
		List<Integer> valor1 = hashMap.get(clave1);
		List<Integer> valor2 = hashMap.get(clave2);
		
		//calculo de similaridad
		for(int k=0;k<valor1.size();k++) {
			res+=Math.abs(valor1.get(k) - valor2.get(k));
		}
		return res;
	}

	//obtiene info de cada persona y la añade al hashmap
	public void obtenerData(Object object, List<Integer> list2) {
		
		if(object instanceof String) {
			hashMap.put((String) object, list2);
		}else {
			list2.add((Integer) object);
		}
	}
	
	private void verificarVertice(int i){
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
	}

	public int[] getGrafoOrdenado() {
		return grafoOrdenado;
	}
	
}
