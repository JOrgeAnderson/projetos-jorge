package br.com.jdev.cursojava.teste;

public class Matriz {

	public static void main(String[] args) {

		int notas[][] = new int[2][3];

		notas[0][0] = 80;
		notas[0][1] = 90;
		notas[0][2] = 70;

		notas[1][0] = 60;
		notas[1][1] = 50;
		notas[1][2] = 40;

		/*PERCORRER AS LINHAS*/
		for (int linha = 0; linha < notas.length; linha++) {
			System.out.println("================================");
			/*PERCORRER AS COLUNAS DE CADA LINHA*/
			for (int coluna = 0; coluna < notas[linha].length; coluna++) {
				System.out.println("Valor da Matriz: "+ notas[linha][coluna]);
			}
		}
	}
}
