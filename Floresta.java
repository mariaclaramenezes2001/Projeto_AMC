
import java.util.Arrays;

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
		System.out.println("NEW VERSION OF FLOREST");
		
		
		
		Grafo graph = new Grafo(5);
		
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
		System.out.println();
		
		System.out.println(graph);
		System.out.println();
	

		Forest mst = MST.maximumSpanningTree(graph);
		System.out.println("Maximum Spanning Tree  " + mst);
		System.out.println("Is this result a tree?: " + mst.treeQ());
		
		

		
		}	
	}

