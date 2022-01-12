import java.util.Arrays;

class MST{
	 
	  static int findMaxVertex(boolean visited[], int weights[], Grafo graphoo)
	  {
		  int[][] graph = graphoo.matrix;
		  int V = graph.length;
	   
	    int index = -1;
	 
	    int maxW = Integer.MIN_VALUE; 
	    for (int i = V-1; i >=0; i--)
	    {
	      if (visited[i] == false && weights[i] > maxW)
	      {
	        maxW = weights[i];

	        index = i;
	      }
	    }
	    return index;
	  }

	  
	  
	  static void printMaximumSpanningTree(int graph[][], int parent[])
	  {
	 
	    int MST = 0;
	    int V = graph.length;

	    for (int i = V-2; i >=0; i--)
	    {
	      MST += graph[i][parent[i]];
	    }
	 
	    System.out.println("Weight of the maximum Spanning-tree "
	                       + MST);
	    System.out.println();
	    System.out.println("Edges \tWeight");

	    for (int i = V-2; i >=0; i--)
	    {
	      System.out.println(parent[i] + " - " + i + " \t"
	                         + graph[i][parent[i]]);
	    }
	  }
	 

	 static int[] maximumSpanningTree(Grafo graphoo){
	 int[][]graph = graphoo.matrix;
	 int V = graph.length;
	   
	    boolean[] visited = new boolean[V]; 
	    int[] weights = new int[V]; 
	    int[] parent = new int[V];
	    for (int i = 0; i < V; i++) {
	      visited[i] = false;
	      weights[i] = Integer.MIN_VALUE;
	    }

	    weights[V-1] = Integer.MAX_VALUE;
	    parent[V-1] = -1;
	 
	    for (int i = V-2; i >=0; i--) {
	 
	      int maxVertexIndex  = findMaxVertex(visited, weights, graphoo);

	      visited[maxVertexIndex] = true;
	   
	      for (int j = 0; j < V; j++) {
	        if (graph[j][maxVertexIndex] != 0 && visited[j] == false) {
	          if (graph[j][maxVertexIndex] > weights[j]) {
	            weights[j] = graph[j][maxVertexIndex];
	            parent[j] = maxVertexIndex;
	          }
	        }
	      }
	    }    
	    return parent;
	  }}

public class Grafo{
	int[][] matrix; 
	
	public Grafo(int n) {
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
	
	public static double info_mutua_cond(Amostra amostra, int X, int Y) {
		double info_mutua_condicional = 0; // == dependencia 
		double N = amostra.length();
		int N_xy; int N_x; int N_y;
		int [] var = {X,Y};
		
		
		int domainX = Amostra.domain_aux(amostra, X); // == valores que X pode tomar (se domainX = 3, pode tomar 0,1,2,3)
		int domainY = Amostra.domain_aux(amostra, Y);

		
		for ( int x = 0; x< domainX; x++) {
			int[] varX = {X};
			int[] valx = {x};
			
			for (int y = 0; y< domainY; y++) {
				
					//  tirar os prints quando se tiver a certeza disto 
				
				int[]val = {x,y};
				System.out.println(Arrays.toString(val));
				N_xy = amostra.count(var, val);
				double Pr_xy = N_xy / N;
				System.out.println("Pr_xy = " + Pr_xy);
				
				N_x = amostra.count(varX, valx); System.out.println("N_x = " + N_x);
				double Pr_x = N_x / N;
				System.out.println("Pr_x = " + Pr_x);
				
				int[] varY = {Y};
				int[] valy = {y};
				N_y = amostra.count(varY, valy); System.out.println("N_y = " + N_y);
				double Pr_y = N_y / N;
				System.out.println("Pr_y = " + Pr_y);
				
				if ( Pr_x!= 0 && Pr_y!=0) {
				double entrada = Pr_xy * Math.log(Pr_xy / (Pr_x * Pr_y));
				System.out.println("Entrada = "+ entrada);
				info_mutua_condicional = info_mutua_condicional + entrada;	
				}						
			}
		}
		
		return info_mutua_condicional;
	}
	
	
}
