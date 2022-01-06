package floresta;

import java.util.Arrays;

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
		
	}
}
