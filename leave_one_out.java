package amostra;

public class leave_one_out {

	
	
	public static double leaveOneOut(Amostra amostra) {
		double s = 0;
		double ss = 0; 
		
		for(int i = 0; i< amostra.length()-1; i ++) {
			ss++;
			
			
			Amostra amostraMinusOne = Amostra.clone(amostra);		

			amostraMinusOne.getList().remove(i);
			
//			System.out.println(amostra.length() + "\t " + amostraMinusOne.length());
			
			int[] v = amostra.element(i);
			
			Grafo grafo = Grafo.grafoP(amostraMinusOne);
			Forest mstree = MST.maximumSpanningTree(grafo);
			BN rede = new BN(mstree, amostraMinusOne, 0.5);
			
			
			int[] vv = new int[v.length-1];
			
			for ( int j = 0; j < v.length-1; j ++) {
				vv[j] = v[j];
			}
			if (amostraMinusOne.possibleQ(v)) {
				if ( BN.classifica(vv, rede) == v[v.length-1]) s++;
			}
		}
		return s/ss *100 ;
	}
	
	public static double isOne(Amostra amostra) {
		double s = 0;
		double ss = 0; 
		
		for(int i = 0; i< amostra.length()-1; i ++) {
			ss++;
			Amostra amostraMinusOne = Amostra.clone(amostra);		

			amostraMinusOne.getList().remove(i);
			
//			System.out.println(amostra.length() + "\t " + amostraMinusOne.length());
			
			int[] v = amostra.element(i);
			
			Grafo grafo = Grafo.grafoP(amostraMinusOne);
			Forest mstree = MST.maximumSpanningTree(grafo);
			BN rede = new BN(mstree, amostraMinusOne, 0.5);
			
			
			int[] vv = new int[v.length-1];
			
			for ( int j = 0; j < v.length-1; j ++) {
				vv[j] = v[j];
			}
		
			if (amostraMinusOne.possibleQ(v)) {
				if ( BN.classifica(vv, rede) == 1) s++;
			}
			
		}
		return s/ss *100 ;
	}
	
	public static double percentOfZeros(Amostra amostra) {
		double s = 0;
		double ss = 0; 
		int last = amostra.element(0).length -1;
		for(int i = 0; i< amostra.length()-1; i ++) {
			ss++;
			
			if( amostra.element(i)[last] == 0) s++;
			
		}
		return s/ss *100 ;
	}
	
	public static void main( String[] args) {
		
		Amostra a = new Amostra("bcancer.csv");
		System.out.println("Accuracy bcancer.csv = " + leaveOneOut(a) + "%"); 
		System.out.println("Classifies as 1 One bcancer.csv = " + isOne(a) + "%"); 
		System.out.println("percentOfZeros bcancer.csv = " + percentOfZeros(a) + "%"); System.out.println();
		
		
		Amostra b = new Amostra("diabetes.csv");
		System.out.println("Accuracy diabetes.csv = " + leaveOneOut(b) + "%");
		System.out.println("Classifies as 1 diabetes.csv = " + isOne(b) + "%"); 
		System.out.println("percentOfZeros diabetes.csv = " + percentOfZeros(b) + "%");System.out.println();
		
		Amostra c = new Amostra("hepatitis.csv");
		System.out.println("Accuracy hepatitis.csv = " + leaveOneOut(c) + "%");
		System.out.println("Classifies as 1 hepatitis.csv = " + isOne(c) + "%");
    	System.out.println("percentOfOnes hepatitis.csv = " + (100-percentOfZeros(c)) + "%"); System.out.println();
/*	
		Amostra d = new Amostra("thyroid.csv");
		System.out.println("Accuracy thyroid.csv = " + leaveOneOut(d) + "%");
		System.out.println("Classifies as 1 thyroid.csv = " + isOne(d) + "%");
		System.out.println("percentOfZeros thyroid.csv = " + percentOfZeros(d) + "%"); System.out.println();
*/		
	
	}

}
