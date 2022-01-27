
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Forest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	int[] list;
	int[] domains; 

	
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
	
	
	// lista de vertices que sao filhos do vertice o 
	public LinkedList<Integer> offspring(int o){
				
		LinkedList<Integer> offspring = new LinkedList<Integer>();
		for (int d= 0; d<this.list.length ;d++ ) {
			if (this.list[d] == o) 
				offspring.add(d);
		}
		return offspring;
	}	

	public LinkedList<Integer> BFS (int o){
		LinkedList<Integer> visited = new LinkedList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(o);	
		while(!queue.isEmpty()) {
			int first  = queue.remove();
			if (!visited.contains(first)) {
				visited.add(first);
				queue.addAll(offspring(first));
			}
		}
		visited.removeFirst();
		return visited; }
	
	public boolean cylicQ(int o) {
		LinkedList<Integer> desc = BFS(o);
		return desc.contains(o);
	}
	
	
	// so uma raiz 
		//  - excepção: casos com dominio = 1
	// nao ter ciclos : impossivel aceder ao vertice de origem atraves das arestas sucessivas - bfs
		
	public boolean treeQ() {
		int s = 0; 
		boolean b = true;	
		for (int i = 0 ; i < this.list.length; i ++) {
			if (this.list[i] == -1 || ( this.list[i] == i && this.domains[i] > 1)) {
				s++;  
				}
			if (this.domains[i] > 1 && cylicQ(i) ) b = false; }					
		if( s != 1 ) {
				b = false;	 
			}					
		return b;
	}
	
	@Override
	public String toString() {
		return "Forest [list=" + Arrays.toString(list) + "]";
	}
	public static void main(String[] args) {
		
		Amostra amostra_tiroide = new Amostra("thyroid.csv");
		Grafo grafoP_tiroide = Grafo.grafoP(amostra_tiroide);
		Forest MSTree_tiroide = MST.maximumSpanningTree(grafoP_tiroide);
		System.out.println(MSTree_tiroide.treeQ());
		
		Amostra amostra_bcb= new Amostra("bcancerboost.csv");
		Grafo grafoP_bcb = Grafo.grafoP(amostra_bcb);
		Forest MSTree_bcb = MST.maximumSpanningTree(grafoP_bcb);
		System.out.println(MSTree_bcb.treeQ());
		
		Amostra amostra_d = new Amostra("diabetes.csv");
		Grafo grafoP_d = Grafo.grafoP(amostra_d);
		Forest MSTree_d = MST.maximumSpanningTree(grafoP_d);
		System.out.println(MSTree_d.treeQ());
		
		Amostra amostra_soy = new Amostra("soybean-large.csv");
		Grafo grafoP_soy = Grafo.grafoP(amostra_soy);
		Forest MSTree_soy = MST.maximumSpanningTree(grafoP_soy);
		System.out.println(MSTree_soy.treeQ());
		
		
		}	
	}
