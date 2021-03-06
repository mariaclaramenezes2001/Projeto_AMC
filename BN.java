import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class BN implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public Forest arvore;
	public ArrayList<double[][]> DFOvs;
	public double[] DFOc;
	
	public static double[][] DFO_var(Forest MTS, Amostra amostra, int no, double s) {
		//numero de linhas = numero de valores possiveis para o pai do no = domain
		
		int pai = MTS.list[no];
		int m = Amostra.domain(amostra, pai);
		
		//numero de colunas = numero de possiveis valores do no
		int n = Amostra.domain(amostra,no);
		
		double[][] dfo_var = new double[m][n];
		
		int[] XY = {no, pai};
		
	
		for (int i = 0; i < m; i++ ) {
			for ( int j = 0; j < n ; j++) {
				
				
				int[] xy = {j,i};
				
				dfo_var[i][j] = (amostra.count(XY , xy) + s) / (amostra.count(pai, i) + s*n );
			}
		}
		return dfo_var;}
		
	
		public double[] DFO_class(Amostra amostra, double s) {
			int class_index = amostra.element(0).length-1;
			int domain = Amostra.domain(amostra, class_index);
			double[] dfo_class = new double[domain];
			
			for (int i=0; i<domain;i++) {
				int classi = i;
				dfo_class[i] = (amostra.count(class_index, classi) + s) / (  amostra.length()+ domain*s);
			}
	
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
		
		public static double prob ( int[] vec, BN rede) {
			double prob = 1;
			
			for (int i = 0; i<vec.length-1; i++) {
				
				int valor_no = vec[i];
				int pai = rede.arvore.list[i];
				int valor_pai = vec[pai];
				prob = prob * rede.DFOvs.get(i)[valor_pai][valor_no];
				
			}
			//para a classe
			prob = prob * rede.DFOc[ vec[vec.length-1]  ];
				
				
			return prob;			
		}
		
		public static int classifica(int[] vec_inc, BN rede) {
			
			int[] veci = new int[vec_inc.length+1];			
			double max_prob = Integer.MIN_VALUE;
			int r = Integer.MIN_VALUE;	
			for (int j=0; j<vec_inc.length; j++) {
				veci[j] = vec_inc[j];
			}
				
			for (int  i = 0; i < rede.DFOc.length; i ++) {
					
				veci[veci.length-1] = i;
				if (prob(veci,rede)>max_prob) {
					max_prob = prob(veci,rede);
					r = veci[veci.length-1];
				}
			}			
			return r;
		}
	

		@Override
		public String toString() {
			
			StringBuilder toPrint = new StringBuilder();
			toPrint.append("BN\n [Arvore=" + arvore+ "\n DFO: ");
			
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
			
			int[] vec0 = {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0};
			System.out.println();
			int[] vec1 = {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1};
			System.out.println(prob(vec0, bn_tiroide));
			System.out.println(prob(vec1, bn_tiroide));
			System.out.print("Prob of class being 0 > prob of class being 1 for vector {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0,class}:  " );
			System.out.println(prob(vec0, bn_tiroide) > prob(vec1, bn_tiroide));
			
			
			int[] vec_inc0 = {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0};
			
			
			System.out.println(classifica(vec_inc0, bn_tiroide));		
			
		}		
}

