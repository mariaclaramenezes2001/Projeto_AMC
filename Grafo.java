package amostra;

import java.io.Serializable;
import java.util.Arrays;


class Prim{
	
	static int[] BestMatch(Grafo graph, boolean[] visited){
		int[] bestmatch= new int[2];
		double bestweight = Integer.MIN_VALUE;
		bestmatch[0]=-1;
		bestmatch[1]=-1;
		double[][] ma = graph.matrix;
		
		for (int i=0; i< visited.length;i++) {
			if (visited[i]) {
				for (int j=0; j<visited.length;j++) {
					if (!visited[j] && ma[i][j]>bestweight) {
							bestmatch[0]=i;
							bestmatch[1]=j;
							bestweight = ma[i][j];
					}
				}
			}
		}
		return bestmatch;
	}
	
	static Forest MaximumSpanningTree(Grafo graph) {
		int dim = graph.matrix.length;
		boolean[] visited = new boolean[dim];
		int[] parents = new int[dim];
		for (int i=0; i<dim;i++) visited[i]=false;
		visited[dim-1]=true;
		parents[dim-1]=-1;
		
		boolean[] alltrue = new boolean[dim];
		for (int i=0; i<dim;i++) alltrue[i]=true;
		
		while (!Arrays.equals(alltrue, visited)) {
			int[] bestmatch= BestMatch(graph,visited);
			visited[bestmatch[1]]=true;
			parents[bestmatch[1]]=bestmatch[0];
		}
		Forest MST = new Forest(dim);
		MST.list= parents;
		return MST;
	}
	
}

class MST{
	 
	  static int findMaxVertex(boolean visited[], double weights[], Grafo graphoo)
	  {
		  double[][] graph = graphoo.matrix;
		  int V = graph.length;
	   
	    int index = -1;
	 
	    Double maxW = Double.MIN_VALUE; 
	    for (int i = V-1; i >=0; i--)
	    {
	      if (!visited[i] && weights[i] >= maxW)
	      {
	    	
	        maxW = weights[i];       
	        index = i;      
	      }
	  
	    }    
	    return index;
	    
	  }
  
	  
	  static void printMaximumSpanningTree(double graph[][], int parent[])
	  { 
	    double MST = 0;
	    int V = graph.length;

	    for (int i = V-2; i >=0; i--)
	    {
	      MST += graph[i][parent[i]];
	    }
	 
	    System.out.println("Weight of the maximum Spanning-tree "+ MST);
	    System.out.println();
	    System.out.println("Edges \tWeight");

	    for (int i = V-2; i >=0; i--)
	    {
	      System.out.println(parent[i] + " - " + i + " \t" + graph[i][parent[i]]);
	    }
	  }
	 

	 static Forest maximumSpanningTree(Grafo graphoo){
	 double[][]graph = graphoo.matrix;
	 int V = graph.length;
	   
	    boolean[] visited = new boolean[V]; 
	    double[] weights = new double[V]; 
	    int[] parent = new int[V];
	    
	    for (int i = 0; i < V-2; i++) {
	      visited[i] = false;
	      weights[i] = Double.MIN_VALUE;}

	    weights[V-1] = Double.MAX_VALUE;
	    parent[V-1] = -1;
	 
	    for (int i = V-2; i >=0; i--) {
	 
	      int maxVertexIndex  = findMaxVertex(visited, weights, graphoo);
	      

	      visited[maxVertexIndex] = true;
	   
	      for (int j = 0; j < V; j++) {
	        if (graph[j][maxVertexIndex] != 0 && ! visited[j]) {
	          if (graph[j][maxVertexIndex] > weights[j]) {
	            weights[j] = graph[j][maxVertexIndex];
	            parent[j] = maxVertexIndex;
	          }
	        }
	      }
	    }    
	    //printMaximumSpanningTree(graph, parent);
	    Forest MST_parents = new Forest(parent.length);
	    MST_parents.list = parent;
	    return MST_parents;
	  }}

public class Grafo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	double[][] matrix; 
	
	public Grafo(int n) {
		this.matrix = new double[n][n];
	}
	
	
	// nao orientado 
	public void add_edge( int m, int n, double p) {
		this.matrix[n][m] = p;
		this.matrix[m][n] = p;
	}

	
	@Override
	public String toString() {
		return "Grafo : Adj matrix = " + Arrays.deepToString(matrix);
	}
	
	
	
	
	public double[][] getMatrix() {
		return matrix;
	}



	public static double info_mutua_cond(Amostra amostra, int X, int Y) {
		
		double info_mutua_condicional = 0; // == dependencia 
		double N = amostra.length();
		int N_xy; int N_x; int N_y;
		int [] var = {X,Y};
			
		int domainX = Amostra.domain(amostra, X); // == valores que X pode tomar (se domainX = 3, pode tomar 0,1,2,3)
		int domainY = Amostra.domain(amostra, Y);
		
		for ( int x = 0; x < domainX; x++) {
			//int[] varX = {X};
			//int[] valx = {x};
			
			for (int y = 0; y < domainY; y++) {
				
				
				int[]val = {x,y};
													//System.out.println(Arrays.toString(val));
				N_xy = amostra.count(var, val);
				double Pr_xy = N_xy / N;
													//System.out.println("Pr_xy = " + Pr_xy);
				
				N_x = amostra.count(X, x); 	//System.out.println("N_x = " + N_x);
				double Pr_x = N_x / N;
													//System.out.println("Pr_x = " + Pr_x);
				
				//int[] varY = {Y};
				//int[] valy = {y};
				N_y = amostra.count(Y, y); 	//System.out.println("N_y = " + N_y);
				double Pr_y = N_y / N;
													//System.out.println("Pr_y = " + Pr_y);
				
				if ( Pr_xy!=0) {
				double entrada = Pr_xy * Math.log(Pr_xy / (Pr_x * Pr_y));
													//System.out.println("Entrada = "+ entrada);
				info_mutua_condicional = info_mutua_condicional + entrada;	}									
			}
		}		
		return info_mutua_condicional;
	}
	
	
	public static Grafo grafoP(Amostra amostra) {
		int n_1 = amostra.element(0).length; 
		Grafo grafoP = new Grafo(n_1);
		
		
		
		for ( int variavel1 = 0; variavel1 < n_1; variavel1++) {
			for ( int variavel2 = variavel1 +1; variavel2 < n_1; variavel2++) {
				
				if (variavel1 != variavel2) {
					double peso = info_mutua_cond(amostra, variavel1, variavel2);
					grafoP.add_edge(variavel1, variavel2, peso);
					
				}
			}
		}		
	return grafoP;		
	}
	
	
	public static void main(String[] args) {
		Amostra amostra_tiroide = new Amostra("thyroid.csv");
		System.out.println("Amostra Tiroide = " + amostra_tiroide);
		System.out.println();
		System.out.println("info mutua condicional (amostra_tiroide, 0,1 ) = " + info_mutua_cond(amostra_tiroide, 0, 1));
		System.out.println();
		int[] class_index = {amostra_tiroide.element(0).length-1};
		int[] class1 = {1};
		System.out.println(amostra_tiroide.count(class_index, class1));
		
		System.out.println("Grafo pesado de Am.Tiroide , arestas são dependecias_");
		Grafo grafoP = grafoP(amostra_tiroide);
		System.out.println(grafoP);
		System.out.println();
		
	
		Forest MSTree = MST.maximumSpanningTree(grafoP);
		
		System.out.println();
		System.out.println("Maximum Spanning Tree do Grafo Pesado da Amostra:");
		System.out.println(MSTree);
		System.out.println(MSTree.treeQ());
		
		
		
		System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
		
		
		Amostra amostra_bcancer = new Amostra("hepatitis.csv");
		System.out.println("Amostra BCancer = " + amostra_bcancer);
		System.out.println();
		System.out.println("info mutua condicional (amostra_bcancer, 0,1 ) = " + info_mutua_cond(amostra_bcancer, 0, 1));
		System.out.println();
		
		System.out.println("Grafo pesado de Am.BrCancer , arestas são dependecias_");
		Grafo grafoP_bc = grafoP(amostra_bcancer);
		System.out.println(grafoP_bc);
		System.out.println();
		
		
		Forest MSTree_bc = MST.maximumSpanningTree(grafoP_bc);
		
		System.out.println();
		System.out.println("Maximum Spanning Tree do Grafo Pesado da Amostra:");
		System.out.println(MSTree_bc);
		System.out.println(MSTree_bc.treeQ());
		
		Forest MSTree_bc1 = Prim.MaximumSpanningTree(grafoP_bc);
		
			System.out.println();
			System.out.println("Maximum Spanning Tree do Grafo Pesado da Amostra:");
			System.out.println(MSTree_bc1);
			System.out.println(MSTree_bc1.treeQ());
		
	
	}
	
}
