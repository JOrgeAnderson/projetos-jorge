package br.com.jdev.cursojava.teste;

import java.util.Arrays;
import java.util.List;

public class SplitArray {

	public static void main(String[] args) {
		
		String texto = "Jorge, Java, 80, 70, 90, 89";
		
		
		String[] valoresArray = texto.split(",");//formando um array por cada virgula.
		
		System.out.println("Nome: "+valoresArray[0]);
		System.out.println("Curso: "+valoresArray[1]);
		System.out.println("Nota 1: "+valoresArray[2]);
		System.out.println("Nota 2: "+valoresArray[3]);
		System.out.println("Nota 3: "+valoresArray[4]);
		System.out.println("Nota 4: "+valoresArray[5]);
		
		System.out.println("=======================================");
		
		/* Convertendo um Array em uma lista*/
		List<String> lista = Arrays.asList(valoresArray);
		
		for (String valorString : lista) {
			System.out.println(valorString);
		}
		System.out.println("==========================================");
		/* Convertendo uma lista para um Array*/
		String[] conversaoArray = lista.toArray(new String[6]);
		
		for(int pos = 0; pos < conversaoArray.length; pos++) {
			System.out.println(conversaoArray[pos]);
		}
	}
}
