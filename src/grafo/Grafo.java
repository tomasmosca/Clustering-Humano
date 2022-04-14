package grafo;

import java.util.Iterator;

import java.util.LinkedList;

public class Grafo {
	
	private int cantVertices;
    private int matriz[][];
    private Vertice[] vertices;
    private Vertice[] verticeArbolMin; //arreglo de vertices arbol gen min
    private LinkedList<Integer>[] listaAdyacencia;
    private int[] verticesPadres;
    private int[] verticesBFS;
    
    @SuppressWarnings("unchecked")
	public Grafo(int vertice) { //inicializa grafo con cantvertices,matriz con vertices(origen,destino), lista adyacencia
        this.cantVertices = vertice;
        matriz = new int[vertice][vertice];
        listaAdyacencia = new LinkedList[cantVertices];
        for (int i = 0; i <cantVertices ; i++) {
            listaAdyacencia[i] = new LinkedList<>();
        }
        verticesPadres = new int[cantVertices];
    }
    
    public void agregarAristaMatriz(int origen, int destino, int peso) {
    	
    	verificarVertice(origen);
		verificarVertice(destino);
		verificarDistintos(origen, destino);
        //agrega arista
        matriz[origen][destino] = peso;

        //agrega arista posterior para grafo no dirigido
        matriz[destino][origen] = peso;
    }
    
    public void agregarAristaListaAdyacencia(int origen, int destino) {

    	verificarVertice(origen);
    	verificarVertice(destino);
    	verificarDistintos(origen, destino);
    	listaAdyacencia[origen].add(destino);
        listaAdyacencia[destino].add(origen);
    }
    
    public void eliminarAristaMatriz(int origen, int destino){ 
    	
    	verificarVertice(origen);
		verificarVertice(destino);
		verificarDistintos(origen, destino);
		matriz[destino][origen] = 0;
		matriz[origen][destino] = 0;
		
    }
    
    public void eliminarAristaListaAdyacencia(int origen, int destino) {
    	
    	// recorre la primera lista de vertices y elimina el segundo elemento
    	for (int i = 0; i < listaAdyacencia[origen].size(); i++){ 
    		if (listaAdyacencia[origen].get(i) == destino){ 
    			listaAdyacencia[origen].remove(i); 
    			break; 
    		}
    	}
    	// recorre la segunda lista de vertices y elimina el primer elemento
    	for (int i = 0; i < listaAdyacencia[destino].size(); i++){ 
    		if (listaAdyacencia[destino].get(i) == origen){ 
    			listaAdyacencia[destino].remove(i); 
    			break; 
    		}
    	}
    }
    
    //obtiene el vértice con mínimo que no está incluida en arbol min
    private int obtenerMinimoVertice(boolean [] arbolMin, int [] peso){
        int minpeso = Integer.MAX_VALUE;
        int vertice = -1;
        for (int i = 0; i <cantVertices ; i++) {
            if(arbolMin[i]==false && minpeso>peso[i]){
                minpeso = peso[i];
                vertice = i;
            }
        }
        return vertice;
    }
    
    public void ArbolGenMin(){
    	
        boolean[] arbolMin = new boolean[cantVertices];
        vertices = new Vertice[cantVertices];
        int [] peso = new int[cantVertices];

        //Inicializa todo el peso al infinito y inicializa vertices1 para todos los vértices
        for (int i = 0; i <cantVertices ; i++) {
            peso[i] = Integer.MAX_VALUE;
            vertices[i] = new Vertice();
            vertices[i].vertice = i;
        }

        //empieza desde el vértice 0
        peso[0] = 0;
        vertices[0] = new Vertice();
        vertices[0].padre = -1;
        
        //crea arbolMin
        for (int i = 0; i <cantVertices; i++) {

            //consigue el vértice con el peso mínimo
            int vertice = obtenerMinimoVertice(arbolMin, peso);
            
            //incluye este vértice en arbolMin
            arbolMin[vertice] = true;
            
            // itera a través de todos los vértices adyacentes del vértice anterior y actualiza el peso
            for (int j = 0; j <cantVertices ; j++) {
                // control de arista
                if(matriz[vertice][j]>=0){
                    //comprueba si este vértice 'j' ya está en arbolMin y si no, comprueba si el peso necesita una actualizacion o no
                    if(arbolMin[j]==false && matriz[vertice][j]<peso[j]){
                        //actualiza peso
                        peso[j] = matriz[vertice][j];
                        //actualiza el conjunto de resultados
                        vertices[j].padre = vertice;
                        vertices[j].peso = peso[j];
                    }
                }
            }
        }
        verticesPadres[0] = vertices[0].padre;
        //agrega a la lista de adyacencia el arbol gen min
        for(int k=1;k<vertices.length;k++) {
        	verticesPadres[k] = vertices[k].padre;
        	listaAdyacencia[k].add(vertices[k].padre);
            listaAdyacencia[vertices[k].padre].add(k);
        }
        //elimina la arista de mayor peso
        eliminarMayor(vertices);
    }
    
    private void eliminarMayor(Vertice[] vertices1){ 
        // Inicializa elemento máximo
        int max = vertices1[0].peso;
        boolean iguales = true;
       
        // recorre matriz desde el segundo y compara cada elemento con el máximo actual   
        for (int i = 1; i < vertices1.length; i++) {
            if (vertices1[i].peso > max) {
            	max = vertices1[i].peso;
            	iguales = false;
            }else if(vertices1[i].peso < max) {
            	iguales = false;
            }
        }
        // elimina arista
        for (int k = 1; k < vertices1.length; k++) {
            if (vertices1[k].peso == max && iguales == false) {
            	eliminarAristaListaAdyacencia(vertices1[k].vertice,vertices1[k].padre);
            	break;
            }
        }
    }
    
    public void BFS(int n,Vertice[] vertices1){ 
        // marca todos los vertices como no visitados
        boolean visitado[] = new boolean[cantVertices]; 
  
        verticeArbolMin = new Vertice[cantVertices];
        for (int i = 0; i <cantVertices ; i++) {
        	verticeArbolMin[i] = new Vertice();
        }
        // crea una cola para BFS
        LinkedList<Integer> cola = new LinkedList<Integer>(); 
  
        // marca el nodo actual como visitado y lo coloca
        visitado[n]=true; 
        cola.add(n); 
        int cont = 0;
  
        while (cola.size() != 0){ 
            // retira un vertice de la cola
            n = cola.poll(); 
            
            verticeArbolMin[cont] = vertices1[n];
            cont++;
  
            // obtiene todos los vertices adyacentes del vertice quitado de la cola s, si un adyacente no fue visitado, lo marca como visitado y lo coloca 
            Iterator<Integer> i = listaAdyacencia[n].listIterator(); 
            while (i.hasNext()){
                int num = i.next(); 
                if (!visitado[num]){ 
                    visitado[num] = true; 
                    cola.add(num); 
                }
            }
        }
        
        verticesBFS = new int[cont];
        for(int j=0;j<cont;j++) {
        	verticesBFS[j] = verticeArbolMin[j].vertice;
        }
        
        //separa los grupos
        boolean esta = false;
        for(int i=0;i<vertices1.length;i++) {
        	esta = false;
        	for(int j=0;j<verticeArbolMin.length;j++) {
        		if(vertices1[i].vertice == verticeArbolMin[j].vertice && esta == false) {
        			esta = true;
        		}
        	}
        	if(esta == false) {
        		vertices1[i].padre = -2;
        		verticeArbolMin[cont] = vertices1[i];
        		cont++;
        	}
        }  
    }
    
    private void verificarVertice(int i){
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= matriz.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j){
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	public int existeArista(int origen, int destino){
		verificarVertice(origen);
		verificarVertice(destino);
		verificarDistintos(origen, destino);

		return matriz[origen][destino];
	}

	public Vertice[] getVertices1() {
		return vertices;
	}
	
	public Vertice[] getVerticeArbolMin() {
		return verticeArbolMin;
	}

	public int[] getVerticesPadres() {
		return verticesPadres;
	}

	public int[] getVerticesBFS() {
		return verticesBFS;
	}
}
