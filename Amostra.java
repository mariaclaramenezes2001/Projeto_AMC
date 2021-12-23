
import java.util.Arrays;
/**
 * 
Amostra

add: recebe um vector e acrescenta o vector `a amostra;
length: retorna o comprimento da amostra;
element: recebe uma posição e retorna o vector da amostra;
domain: recebe uma amostra e um vector de posições e retorna o número de elementos possíveis desse vector de posições;
count: recebe um vector de variaveis e um vector de valores e retorna o nu ́mero de ocorrências desses valores para essas variáveis na amostra;
 *
 */

public class Amostra {

	
	
	int[][] amostra;
	
	//metodo construtor;
 	public Amostra(){
 		this.amostra = new int[0][0];
 	}
 	
 	//add: recebe um vector e acrescenta o vector à amostra;
 	public void add(int[] vetor) {
 		int m = this.amostra.length;
 		int colunas = vetor.length; // colunas = n+1
 		
 		int [][] amostra_atualizada = new int[m+1][colunas];
 		for (int i = 0; i < this.amostra.length; ++i) {

	            amostra_atualizada[i] = new int[this.amostra[i].length]; //mantêm-se numero de colunas 
	            
	            /**
	             * para cada linha da nova amostra copia-se da amostra original(this.amostra[i], da entrada zero até a ultima (copia-se tudo))
	             */
	            System.arraycopy(this.amostra[i], 0, amostra_atualizada[i], 0, amostra_atualizada[i].length);
	        }
 		
 		//atualizar a amostra para a matriz com mais uma linha, para já ainda vazia
 		this.amostra = amostra_atualizada;	
 	
 		// "adicionar" o vetor novo
 		this.amostra[this.amostra.length-1]= vetor;
 	}
 	
 	
 	public int length() {
 		return this.amostra.length;
 	}
 	
 	
 	//element: recebe uma posição e retorna o vector da amostra;
 	public int[] element(int position) {
 		if (position <= this.amostra.length-1) {
 			return this.amostra[position];
 		}
 		else {
 			throw new RuntimeException("Posição fora dos limites da amostra atual");}
 		}

 	
 	
 	public static int domain_aux(Amostra a, int position) {
 		int max1 = 0; 
 		for (int i = 0;  i< a.amostra.length; i++){
 				if (a.amostra[i][position] > max1) {
 					max1 = a.amostra[i][position];
 				}
 			}
 		return max1+1;
 	}
 	
 	// meter exceptions?? 
 	
 	public static int domain(Amostra a, int[] v) {
 		
 		/*
 		 * v = [variavel posiçao i, variavel posição j, posição k?, etc]
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

 	
 	
 	
 	
 	
 	
 	
 	
	public static void main (String[] args) {
		
		// perceber matrizes segundo implementação int[][]
 		int[][] a1 = new int[1][1];
 		System.out.println("a1 é uma matriz int[1][1] sem preencher nada = " + Arrays.deepToString(a1));
 		System.out.println();
 		
 		int[][] a2 = {{0,0,99},{1,1,0}};
 		System.out.println("Matriz a2 = " + Arrays.deepToString(a2));
 		System.out.println("Numero de linhas a2   = a2.length  = " + a2.length);
 		System.out.println("Numero de colunas a2  = a2[0].length = " + a2[0].length);
 		System.out.println("Entrada a2[0][2]  = "  +     a2[0][2]);
 		
 		System.out.println();
 		
 		// testar construtor
 		Amostra a = new Amostra();
 		
 		//testar método add (int[] v)
 		int [] v1 = {0,0,1};
 		a.add(v1);
 		System.out.println(Arrays.deepToString(a.amostra));
 		int [] v2 = {1,1,0};
 		int [] v3 = {2,3,1};
 		int [] v4 = {0,3,1};
 		a.add(v2);
 		a.add(v3);
 		a.add(v4);
 		System.out.println("Amostra a = " + Arrays.deepToString(a.amostra));
 		
 		// testar método length()
 		System.out.println("a.length = " + a.length());
 		System.out.println();
 		
 		// testar método element(int n)
 		
 		System.out.println(a.element(0));
 		System.out.println(Arrays.toString(a.element(0)));
 				//System.out.println(Arrays.toString(a.element(13))); - ver exception;
 		
 		
 		// testar método domain(amostra a, int[] v)
 		
 		System.out.println(Arrays.deepToString(a.amostra));
 		int[] vetor = {0,2};
 		System.out.println("domain_aux(a, 0) = " +domain_aux(a, 0));
 		System.out.println("domain_aux(a, 2) = " +domain_aux(a, 2));
 		System.out.println("domain(a, [0,2]) = " + domain(a, vetor));
 		
 		int[] vetor2 = {0,2,1};
 		System.out.println("domain(a, [0,2,1]) = " + domain(a, vetor2));
 		
 	}
}
