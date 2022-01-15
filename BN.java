
public class BN {
	public Forest arvore;
	public ArrayList<double[][]> DFOvs;
	public double[] DFOc;
	
	public static double[][] DFO_var(Forest MTS, Amostra amostra, int no, double s) {
		//numero de linhas = numero de valores possiveis para o pai do no = domain_aux
		
		int pai = MTS.list[no];
		int m = Amostra.domain_aux(amostra, pai);
		
		//numero de colunas = numero de possiveis valores do no
		int n = Amostra.domain_aux(amostra,no);
		
		double[][] dfo_var = new double[m][n];
		
		int[] XY = {no, pai};
		//int[] Y = {pai};
		
	
		for (int i = 0; i < m; i++ ) {
			for ( int j = 0; j < n ; j++) {
				
				
				int[] xy = {j,i};
				
				dfo_var[i][j] = (amostra.count(XY , xy) + s) / (amostra.count(pai, i) + s*n );
			}
		}
		return dfo_var;}
		
		
		public double[] DFO_class(Amostra amostra, double s) {
			double[] dfo_class = new double[2];
			int[] class_index = {amostra.getList().get(0).length-1};
			int[] class0 = {0};
			int[] class1 = {1};
			
			dfo_class[0] = (amostra.count(class_index, class0) + s) / (  amostra.length()+ 2*s);
			dfo_class[1] = (amostra.count(class_index, class1) + s) / (  amostra.length() + 2*s);

			return dfo_class;			
		}
		

		public BN(Forest MSTree, Amostra amostra, double s){
			this.arvore = MSTree;
			this.DFOc = DFO_class(amostra,s);
			
			int colunas = amostra.element(0).length;
			
			ArrayList<double[][]> dfovs = new ArrayList<double[][]>(colunas);		
			for ( int i = 0; i <colunas-1; i ++) {
				dfovs.add(DFO_var(MSTree, amostra, i, s));
			}
			
			this.DFOvs = dfovs;		
			
			
		}
		
		
	

		@Override
		public String toString() {
			
			StringBuilder toPrint = new StringBuilder();
			toPrint.append("BN\n [Arvore=" + arvore);
			
			for ( double[][] table : DFOvs) {
				toPrint.append(Arrays.deepToString(table));
			}
			toPrint.append(Arrays.toString(DFOc) + "]");
			return toPrint.toString();
		}


		public static void main(String[] args) {
			
			Amostra amostra_tiroide = new Amostra("thyroid.csv");
			Grafo grafoP_tiroide = Grafo.grafoP(amostra_tiroide);
			Forest MSTree_tiroide = MST.maximumSpanningTree(grafoP_tiroide);

			
			BN bn_tiroide = new BN(MSTree_tiroide, amostra_tiroide,0.5);
			System.out.println( bn_tiroide);		
		}		
}
