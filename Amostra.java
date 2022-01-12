
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Amostra {
	// Atributo / Field
		private ArrayList<int []> list;

		// Metodo construtor - faz uma amostra vazia
		public Amostra() {
			this.list = new ArrayList<int []>();
		}

		// Metodo construtor a partir de comma separated file -  ((OVERLOADING))
		
		public Amostra(String csvFile) {
			this.list = new ArrayList<int []>();;

			BufferedReader br = null;
			String line = "";
			String regex = ",";

			try {br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {

					String[] dado  = line.split(regex);
					
					//System.out.println(Arrays.toString(dado));
					int[] stringToIntVec = new int[dado.length];
					for (int i = 0; i < dado.length; i++)
						stringToIntVec[i] = Integer.parseInt(dado[i]);	
					
					add(stringToIntVec);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	/**
	 * METODOS 
		add: recebe um vector e acrescenta o vector à amostra
		length: retorna o comprimento da amostra;
		element: recebe uma posição e retorna o vector da amostra;
		domain: recebe uma amostra e um vector de posições e retorna o numero de elementos possıveis desse vector de posiçoes;
		count: recebe um vector de variiaveis e um vector de valores e retorna o numero de ocorrencias desses valores para essas varíaveis na amostra;
	*/ 	
		
		public void add (int[] v){
			list.add(v);
		}
		
		public int length() {
			return list.size();
		}
		
		public int [] element(int n) {
			return list.get(n);
		}
		
		public static int domain_aux(Amostra a, int position) {
	 		int max1 = 0; 
	 		for (int i = 0;  i< a.list.size(); i++){
	 				if (a.list.get(i)[position] > max1) {
	 					max1 = a.list.get(i)[position];
	 				}
	 			}
	 		return max1+1;
	 	}
	 	
	 	// meter exceptions?? 
	 	
	 	public static int domain(Amostra a, int[] v) {
	 		
	 		/**
	 		 * v = [variavel posiçao i, variavel posição j, posição k, etc]
	 		 * 
	 		 * resultado = nº elementos no dominio de variavel posição i x nº elementos no dominio de variavel posição j 
	 		 * 
	 		 */
	 		
	 		int s=1;
	 		
	 		for (int position : v ) {
	 			s = s* domain_aux(a, position);
	 		}
	 		return s;
	 	}
		
	
		@Override
		public String toString() {
			String s="[";
			if (list.size()>0) s+=Arrays.toString(list.get(0));
			for (int i=1; i<list.size();i++)
				s+=","+Arrays.toString(list.get(i));
			s+="]";
				
			return "Amostra = " + s;
		}
		
		public int count(int[] var, int[] val) {
			
			int r=0;
	
			for ( int i =0;  i<this.list.size(); i++ ) {
				int[] aux = new int[var.length];
				//System.out.println(Arrays.toString(this.list.get(i)));
				
				for (int x : var) {
					
					int k = Arrays.binarySearch(var, x);
					 aux[k] = this.list.get(i)[x];	
				}
				
				//System.out.println("Aux" + Arrays.toString(aux));
				//System.out.println("Val" + Arrays.toString(val));
				//System.out.println(Arrays.equals(aux, val));
				if (Arrays.equals(aux, val)) r++;
				
			}
			return r; 
		}

		public static void main(String[] args) {
			

			Amostra amostra = new Amostra("thyroid.csv");
			System.out.println(amostra);
			int[] var = {0,2};
			int[] val = {0,1};
			System.out.println("domain(amostra, var) = " + domain(amostra,var));
			System.out.println("amostra.count(var,val) = " + amostra.count(var,val));
			System.out.println();
			System.out.println("INFORMAÇÃO MUTUA");
			System.out.println("Dependencia 0 e 2 = " + Grafo.info_mutua_cond(amostra, 0,1));
			
			
			
			
			System.out.println();System.out.println();
			System.out.println("EXEMPLO SIMPLES");
			System.out.println();
			Amostra simple_amostra = new Amostra();
			int[] vetor1 = {0,0,1};
			int[] vetor2 = {1,1,0};
			int[] vetor3 = {2,3,1};
			int[] vetor4 = {0,3,1};
			
			simple_amostra.add(vetor1);
			simple_amostra.add(vetor2);
			simple_amostra.add(vetor3);
			simple_amostra.add(vetor4);
			System.out.println("Simple" + simple_amostra);	
			
			System.out.println("domain_aux(simple, 0) = " + domain_aux(simple_amostra, 0));
			System.out.println("domain_aux(simple, 2) = " + domain_aux(simple_amostra, 2));
			System.out.println("domain(simple, (0,2) ) = " + domain(simple_amostra, var));
			System.out.println("simple.count( (var = (0,2); val = (0,1) ) ) = " + simple_amostra.count(var, val));
			System.out.println();
		
		}
}
