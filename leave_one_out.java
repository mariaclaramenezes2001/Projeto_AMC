
public class leave_one_out {
	
	
	public static double leaveOneOut(Amostra amostra) {
		double s = 0;
		double ss = 0; 
		
		for(int i = 0; i< amostra.length()-1; i ++) {
			ss++;
			
			Amostra amostraMinusOne = amostra;
			amostraMinusOne.getList().remove(i);
			int[] v = amostra.getList().get(i);
			
			Grafo grafo = Grafo.grafoP(amostraMinusOne);
			Forest mstree = MST.maximumSpanningTree(grafo);
			BN rede = new BN(mstree, amostraMinusOne, 0.5);
			
			
			int[] vv = new int[v.length-1];
			
			for ( int j = 0; j < v.length-1; j ++) {
				vv[j] = v[j];
			}
		
			if ( BN.classifica(vv, rede) == v[v.length-1]) s++;
			
		}
		return s/ss *100 ;
	}
	
	
	public static void main( String[] args) {
		Amostra a = new Amostra("bcancer.csv");
		System.out.println("Accuracy bcancer.csv = " + leaveOneOut(a) + "%"); System.out.println();
		
		Amostra b = new Amostra("diabetes.csv");
		System.out.println("Accuracy diabetes.csv = " + leaveOneOut(b) + "%");System.out.println();
		
		Amostra c = new Amostra("hepatitis.csv");
		System.out.println("Accuracy hepatitis.csv = " + leaveOneOut(c) + "%");System.out.println();
		
		Amostra d = new Amostra("thyroid.csv");
		System.out.println("Accuracy thyroid.csv = " + leaveOneOut(d) + "%");System.out.println();
		
	}

}
