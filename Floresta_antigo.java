package floresta;

import java.util.Arrays;

class Grafoo{
	int[][] matrix; 
	
	public Grafoo(int n) {
		this.matrix = new int[n][n];
	}
	
	
	// nao orientado 
	public void add_edge( int m, int n, int p) {
		this.matrix[n][m] = p;
		this.matrix[m][n] = p;
	}

	@Override
	public String toString() {
		return "Grafo : Adj matrix = " + Arrays.deepToString(matrix);
	}

public static int findMaxVertex(boolean[] visited, int[] weights, Grafoo graph) {
	
	int v = graph.matrix.length;
	int index = -1;	
	int maxW = Integer.MIN_VALUE;
		
	for (int i =0; i<v; i++) {
			
		if (visited[i]== false && weights[i] > maxW)
			maxW = weights [i];
			index = i;
		}
	return index;
	}
	
public static void maximumSpanningTree(Grafoo graph) {
	int v = graph.matrix.length;
	
	boolean[] visited = new boolean [v];
	int[] weights = new int[v];
	int[] parent = new int[v];
	
	for(int i =0; i<v; i ++) {
		visited[i] =false;
		weights[i] = Integer.MIN_VALUE;
	}	
	
	// obriga primeiro elemento a ser a raiz
	weights[0] =Integer.MAX_VALUE;
	parent[0] = -1;
	
	for( int i = 0; i < v-1; i++) {
		
		int maxVertexIndex = findMaxVertex(visited, weights, graph);
		visited[maxVertexIndex] = true;
		 for (int j = 0; j < v; j++) {
			 if (graph.matrix[j][maxVertexIndex] != 0 && visited[j] == false) {
				 if (graph.matrix[j][maxVertexIndex] > weights[j]) {
					 weights[j] = graph.matrix[j][maxVertexIndex];
					 parent[j] = maxVertexIndex;
				 	}
			 	}
		 	}
		}			
	}
	}

public class Forest {
	int[] nos;
	//cada posicao representa um no e cada valor é o index do seu pai
	
	public Forest(int n) {
		this.nos = new int[n];
		for(int i =0; i<this.nos.length; i++) {
			this.nos[i] = -1;
		}
	}	
	
	// torna m o pai de n 
	public void set_parent(int n, int m) {
		this.nos[n] = m;
	}
	
	/**
	 * @mariaclaramenezes
	 * 
	 * todos os nós têm no máximo um pai, com excepção da raiz.
	 * a função set parent ja esta a assegurar parte disso, ver se a floresta é uma so arvore unica
	 * 
	 * ver se tem mais que uma raiz 
	 */
	
	
	public boolean treeQ() {
		int s = 0; 
		boolean b = true;
		
		for (int x: this.nos) {
			if (x == -1) s++;}
			
		if( s != 1 ) b = false; 
	
		return b;
	}
	
	@Override
	public String toString() {
		return "Forest [nos=" + Arrays.toString(nos) + "]";
	}
	public static void main(String[] args) {
		Forest forest = new Forest(15);
		System.out.println(forest);
		forest.set_parent(0,3);
		System.out.println(forest);
		System.out.println(forest.treeQ());
		
		Forest simple_forest = new Forest(5); // slide 14 , C=0
		simple_forest.set_parent(0,3);
		simple_forest.set_parent(2,1);
		simple_forest.set_parent(3,1);
		simple_forest.set_parent(4,1);
		System.out.println(simple_forest);
		System.out.println(simple_forest.treeQ());
		
		Grafoo graph = new Grafoo(5);
		
		graph.add_edge(1,0,2);
		graph.add_edge(3,0,6);
		graph.add_edge(2,1,3);
		graph.add_edge(2,4,7);
		graph.add_edge(3,1,8);
		graph.add_edge(3,4,9);
		graph.add_edge(4,1,5);
		
//		{ { 0, 2, 0, 6, 0 },
//		{ 2, 0, 3, 8, 5 },
//	      { 0, 3, 0, 0, 7 },
//	      { 6, 8, 0, 0, 9 },
//	      { 0, 5, 7, 9, 0 } };
		
		System.out.println(graph);
		Grafoo.maximumSpanningTree(graph);
		}	
	}
