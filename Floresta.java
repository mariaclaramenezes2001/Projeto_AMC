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
}


class MST{
  // Function to find index of max-weight
  // vertex from set of unvisited vertices
  static int findMaxVertex(boolean visited[], int weights[], Grafoo graphoo)
  {
	  int[][] graph = graphoo.matrix;
	  int V = graph.length;
    // Stores the index of max-weight vertex 
	  //from set of unvisited vertices
    int index = -1;
 
    // Stores the maximum weight
    // from the set of unvisited vertices
    int maxW = Integer.MIN_VALUE;
 
    // Iterate over all possible
    // nodes of a graph
    for (int i = 0; i < V; i++)
    {
 
      // If the current node is unvisited
      // and weight of current vertex is
      // greater than maxW
      if (visited[i] == false && weights[i] > maxW)
      {
 
        // Update maxW
        maxW = weights[i];
 
        // Update index
        index = i;
      }
    }
    return index;
  }

  
  
  // Function to find the maximum spanning tree
  static int[] maximumSpanningTree(Grafoo graphoo)
  {
 int[][]graph = graphoo.matrix;
 int V = graph.length;
    // visited[i]:Check if vertex i
    // is visited or not
    boolean[] visited = new boolean[V];
 
    // weights[i]: Stores maximum weight of
    // graph to connect an edge with i
    int[] weights = new int[V];
 
    // parent[i]: Stores the parent node
    // of vertex i
    int[] parent = new int[V];
 
    // Initialize weights as -INFINITE,
    // and visited of a node as false
    for (int i = 0; i < V; i++) {
      visited[i] = false;
      weights[i] = Integer.MIN_VALUE;
    }
 
    // Include 1st vertex in
    // maximum spanning tree
    weights[0] = Integer.MAX_VALUE;
    parent[0] = -1;
 
    // Search for other (V-1) vertices
    // and build a tree
    

    for (int i = 0; i < V - 1; i++) {
 
      // Stores index of max-weight vertex
      // from a set of unvisited vertex
      int maxVertexIndex  = findMaxVertex(visited, weights, graphoo);
 
      // Mark that vertex as visited
      visited[maxVertexIndex] = true;
 
      // Update adjacent vertices of
      // the current visited vertex
      for (int j = 0; j < V; j++) {
 
        // If there is an edge between j
        // and current visited vertex and
        // also j is unvisited vertex
        if (graph[j][maxVertexIndex] != 0 && visited[j] == false) {
 
          // If graph[v][x] is
          // greater than weight[v]
          if (graph[j][maxVertexIndex] > weights[j]) {
 
            // Update weights[j]
            weights[j] = graph[j][maxVertexIndex];
 
            // Update parent[j]
            parent[j] = maxVertexIndex;
          }
        }
      }
    }
 
    // Print maximum spanning tree
    return parent;
 
  }}






public class Forest {
	int[] list;
	//cada posicao representa um no e cada valor é o index do seu pai
	
	public Forest(int n) {
		this.list = new int[n];
		for(int i =0; i<this.list.length; i++) {
			this.list[i] = -1;
		}
	}	
	
	// torna m o pai de n 
	public void set_parent(int n, int m) {
		this.list[n] = m;
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
		
		for (int x: this.list) {
			if (x == -1) s++;}
			
		if( s != 1 ) b = false; 
	
		return b;
	}
	
	@Override
	public String toString() {
		return "Forest [list=" + Arrays.toString(list) + "]";
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
		
		System.out.println();
		System.out.println();
		
		Grafoo graph = new Grafoo(5);
		
		graph.add_edge(1,0,2);
		graph.add_edge(3,0,6);
		graph.add_edge(2,1,3);
		graph.add_edge(2,4,7);
		graph.add_edge(3,1,8);
		graph.add_edge(3,4,9);
		graph.add_edge(4,1,5);
		
//	  { { 0, 2, 0, 6, 0 },
//		{ 2, 0, 3, 8, 5 },
//	    { 0, 3, 0, 0, 7 },
//	    { 6, 8, 0, 0, 9 },
//	    { 0, 5, 7, 9, 0 } };
		
		System.out.println(graph);
		
		System.out.println(Arrays.toString(MST.maximumSpanningTree(graph)));
		
		Forest mst = new Forest(MST.maximumSpanningTree(graph).length);
		mst.list = MST.maximumSpanningTree(graph);
		System.out.println("Maximum Spanning Tree  " + mst);
		System.out.println(mst.treeQ());
		

		
		}	
	}
