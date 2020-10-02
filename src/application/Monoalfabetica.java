package application;

import java.util.Random;

public class Monoalfabetica {
	
	public static String alfabeto = gerarAlfabeto();

	public Monoalfabetica() {
		alfabeto = gerarAlfabeto();
		System.out.print(alfabeto);
	}

	public String subsAlfabetica_DESCRIP(String mensagem) {

		StringBuilder textoDesCrip = new StringBuilder();

		for (int i = 0; i < mensagem.length(); i++) {

			if (mensagem.charAt(i) == ' ') {
				textoDesCrip.append(" ");
				continue;
			}

			for (int j = 0; j < alfabeto.length(); j++) {
				if (mensagem.charAt(i) == alfabeto.charAt(j)) {
					textoDesCrip.append((char) (j + 65));
					break;
				}
			}
		}

		return textoDesCrip.toString();
	}

	public String subsAlfabetica_CRIP(String mensagem) {
		StringBuilder textoCrip = new StringBuilder();

		for (int i = 0; i < mensagem.length(); i++) {
			if (mensagem.charAt(i) != ' ') {
				textoCrip.append(alfabeto.charAt((int) mensagem.charAt(i) - 65));
			} else {
				textoCrip.append(" ");
			}
		}

		return textoCrip.toString();
	}

	private static String gerarAlfabeto() {
		StringBuilder cifra = new StringBuilder();
		Random random = new Random();
		int n;

		for (int i = 0; i < 26; i++) {
			n = random.nextInt(26) + 65;

			if (i == 0) {
				cifra.append((char) n);
				continue;
			}

			for (int j = 0; j < cifra.length(); j++) {

				if (cifra.charAt(j) != n) {

					if (j == cifra.length() - 1) {
						cifra.append((char) n);
						break;
					}

				} else {
					--i;
					break;
				}
			}

		}
		return cifra.toString();
	}
}
