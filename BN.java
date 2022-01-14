
public class BN {
	Forest MST;
	// lista de tabelas com tetas ? 
	
	
	public double[][] DFO_var(Forest MTS, Amostra amostra, int no, int s) {
		//numero de linhas = numero de valores possiveis para o pai do no = domain_aux
		int pai = MTS.list[no];
		int m = Amostra.domain_aux(amostra,pai);
		
		//numero de colunas = numero de possiveis valores do no
		int n = Amostra.domain_aux(amostra,no);
		
		double[][] dfo_var = new double[m][n];
		
		// pode ser evitado se fizer overload de count para valores unicos
		int[] var = {no, pai};
		int[] var_pai = {pai};
		
		for (int i = 0; i < m; i++ ) {
			for ( int j = 0; j < n ; j++) {
				
				int[] val_pai = {i};
				int[] val = {i,j};
				dfo_var[i][j] = (amostra.count(var,val) + s) / (amostra.count(var_pai, val_pai) + s*n );
			}
		}
		return dfo_var;}
		
		
		public double[] DFO_class(Amostra amostra, int s) {
			double[] dfo_class = new double[2];
			int[] class_index = {amostra.getList().get(0).length-1};
			int[] class0 = {0};
			int[] class1 = {1};
			
			dfo_class[0] = (amostra.count(class_index, class0) + s) / ( 2*s);
			dfo_class[1] = (amostra.count(class_index, class1) + s) / ( 2*s);
			
			return dfo_class;
		}
		
		
	
}
