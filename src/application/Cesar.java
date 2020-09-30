package application;


public class Cesar {
	
	static StringBuilder sb = new StringBuilder("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	
	public static void cifrar(String texto, int chave) {
		
		char[] alfabeto = new char[sb.length()];
		sb.getChars(0, sb.length(), alfabeto, 0);
		
		char[] decifrado = new char[texto.length()];
		
		for (int i = 0; i < texto.length(); i++) {
			
			for (int j = 0; j < alfabeto.length; j++) {
				
				if (texto.charAt(i) == alfabeto[j]) {
					
					if (j >= 0 && j <= 25) {
						
						if ((j + chave) >= 25) {
							
							decifrado[i] = alfabeto[((j - 26) + chave)];
						} else {
							
							decifrado[i] = alfabeto[j + chave];
						}
						
					} else {
						
						if ((j + chave) >= 50) {
							decifrado[i] = alfabeto[((j - 26) + chave)];
						} else {
							
							decifrado[i] = alfabeto[j + chave];
						}
					}
				}
			}
		}
		String novo = new String(decifrado);
		for (char d : decifrado) {
			System.out.print(d);
			
		}
		System.out.println();
		decifrar(novo, chave);
	}
	
	public static void decifrar(String texto, int chave) {
		
		char[] alfabeto = new char[sb.length()];
		sb.getChars(0, sb.length(), alfabeto, 0);
		
		char[] decifrado = new char[texto.length()];
		
		for (int i = 0; i < texto.length(); i++) {
			
			for (int j = 0; j < alfabeto.length; j++) {
				
				if (texto.charAt(i) == alfabeto[j]) {
					
					if (j >= 0 && j <= 25) {
						
						if ((j - chave) >= 25) {
							
							decifrado[i] = alfabeto[((j - 26) - chave)];
						} else {
							
							decifrado[i] = alfabeto[j - chave];
						}
						
					} else {
						
						if ((j + chave) >= 50) {
							decifrado[i] = alfabeto[((j - 26) - chave)];
						} else {
							
							decifrado[i] = alfabeto[j - chave];
						}
					}
				}
			}
		}
		
		for (char d : decifrado) {
			System.out.print(d);
		}
		
	}
}
