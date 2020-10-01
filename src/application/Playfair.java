package application;

public class Playfair {

	public static void encriptar(String mensagem, String chave) {

		char[][] matriz = new char[5][5];
		String matrizChars = "";
		String alfabeto = chave + "abcdefghijklmnopqrstuvwxyz";

		for (int i = 0; i < alfabeto.length(); i++) {

			char c = alfabeto.charAt(i);

			if (c != 'j' && matrizChars.indexOf(c) == -1) {
				matrizChars += c;
			}
			if (matrizChars.length() >= 25)
				break;
		}

		System.out.println("MATRIZ: ");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matriz[i][j] = matrizChars.charAt(i * 5 + j);
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		StringBuilder construtorTexto = new StringBuilder(mensagem);
		for (int i = 0; i < construtorTexto.length() - 1; i += 2) {
			
			char c1 = construtorTexto.charAt(i);
			char c2 = construtorTexto.charAt(i + 1);

			if (c1 == c2) {
				if (c1 == 'x') {
					construtorTexto.insert(i + 1, 'z');
				} else {
					construtorTexto.insert(1 + 1, 'x');
				}
			}
		}

		if (construtorTexto.length() % 2 != 0) {
			if (construtorTexto.charAt(construtorTexto.length() - 1) == 'x') {
				construtorTexto.append('z');
			} else {
				construtorTexto.append('x');
			}
		}

		System.out.println("Mensagem: ");
		for (int i = 0; i < construtorTexto.length() - 1; i += 2) {
			char c1 = construtorTexto.charAt(i);
			char c2 = construtorTexto.charAt(i + 1);
			System.out.print(c1 + "" + c2 + " ");
		}
		System.out.println();

		StringBuilder resultado = new StringBuilder();
		for (int i = 0; i < construtorTexto.length() - 1; i += 2) {
			
			char c1 = construtorTexto.charAt(i);
			char c2 = construtorTexto.charAt(i + 1);

			int[] proximo1 = acharProximo(matriz, c1);
			int[] proximo2 = acharProximo(matriz, c2);

	char res1, res2;
	if (proximo1[0] == proximo2[0]) {
		
		res1 = matriz[proximo1[0]][Math.floorMod(proximo1[1] + 1, 5)];
		res2 = matriz[proximo2[0]][Math.floorMod(proximo2[1] + 1, 5)];
		
	} else if (proximo1[1] == proximo2[1]) {
		
		res1 = matriz[Math.floorMod(proximo1[0] + 1, 5)][proximo1[1]];
		res2 = matriz[Math.floorMod(proximo2[0] + 1, 5)][proximo2[1]];
		
	} else {
				
				res1 = matriz[proximo1[0]][proximo2[1]];
				res2 = matriz[proximo2[0]][proximo1[1]];
			}
			
			resultado.append(res1);
			resultado.append(res2);
			
		}

		System.out.println("Encriptada:");
		System.out.println(resultado.toString());
	} 
	
	public static void desencriptar(String mensagem, String chave) {

		char[][] matriz = new char[5][5];
		String matrizChars = "";
		String alfabeto = chave + "abcdefghijklmnopqrstuvwxyz";

		for (int i = 0; i < alfabeto.length(); i++) {

			char c = alfabeto.charAt(i);

			if (c != 'j' && matrizChars.indexOf(c) == -1) {
				matrizChars += c;
			}
			if (matrizChars.length() >= 25)
				break;
		}

		System.out.println("MATRIZ: ");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matriz[i][j] = matrizChars.charAt(i * 5 + j);
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		StringBuilder construtorTexto = new StringBuilder(mensagem);
		for (int i = 0; i < construtorTexto.length() - 1; i += 2) {
			
			char c1 = construtorTexto.charAt(i);
			char c2 = construtorTexto.charAt(i + 1);

			if (c1 == c2) {
				if (c1 == 'x') {
					construtorTexto.insert(i + 1, 'z');
				} else {
					construtorTexto.insert(1 + 1, 'x');
				}
			}
		}

		if (construtorTexto.length() % 2 != 0) {
			if (construtorTexto.charAt(construtorTexto.length() - 1) == 'x') {
				construtorTexto.append('z');
			} else {
				construtorTexto.append('x');
			}
		}

		System.out.println("Mensagem: ");
		for (int i = 0; i < construtorTexto.length() - 1; i += 2) {
			char c1 = construtorTexto.charAt(i);
			char c2 = construtorTexto.charAt(i + 1);
			System.out.print(c1 + "" + c2 + " ");
		}
		System.out.println();

		StringBuilder resultado = new StringBuilder();
		for (int i = 0; i < construtorTexto.length() - 1; i += 2) {
			
			char c1 = construtorTexto.charAt(i);
			char c2 = construtorTexto.charAt(i + 1);

			int[] proximo1 = acharProximo(matriz, c1);
			int[] proximo2 = acharProximo(matriz, c2);

			char res1, res2;
			if (proximo1[0] == proximo2[0]) {
				
				res1 = matriz[proximo1[0]][Math.floorMod(proximo1[1] - 1, 5)];
				res2 = matriz[proximo2[0]][Math.floorMod(proximo2[1] - 1, 5)];
				
			} else if (proximo1[1] == proximo2[1]) {
				
				res1 = matriz[Math.floorMod(proximo1[0] - 1, 5)][proximo1[1]];
				res2 = matriz[Math.floorMod(proximo2[0] - 1, 5)][proximo2[1]];
				
			} else {
				
				res1 = matriz[proximo1[0]][proximo2[1]];
				res2 = matriz[proximo2[0]][proximo1[1]];
			}
			
			resultado.append(res1);
			resultado.append(res2);
			
		}

		System.out.println("Desencriptada:");
		System.out.println(resultado.toString());
	}

	static int[] acharProximo(char[][] matriz, char c) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matriz[i][j] == c)
					return new int[] { i, j };
			}
		}
		return new int[] {};
	}

}
