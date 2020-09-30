package application;

public class Vigenere {

	static char[][] tabela;

	static char[] alfabetoArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	static char[] mensagemArray;
	static char[] chaveArray;
	static char cifrado;

	static char[] msgDecript;
	static char[] chaDecript;
	static char decifrado;
	static StringBuilder textoCifrado = new StringBuilder();
	static StringBuilder textoIgualado = new StringBuilder();

	public static void gerarMatriz() {

		tabela = new char[26][26];

		for (int i = 0; i <= 25; i++) {
			for (int j = 0; j <= 25; j++) {

				if (i > 0 && j <= 25) { // i > 0
					if (i + j > 25) { // j == z

						tabela[i][j] = alfabetoArray[i + j - 26];
					//	System.out.print(tabela[i][j] + " ");
					} else { // i até 25

						tabela[i][j] = alfabetoArray[i + j];
					//	System.out.print(tabela[i][j] + " ");
					}
				} else { // i == 0
					tabela[i][j] = alfabetoArray[i + j];
				//	System.out.print(tabela[i][j] + " ");
				}
			}
		//	System.out.println();
		}
	}

	public static void cifrar(String mensagem, String chave) {
		mensagemArray = mensagem.toCharArray();
		chaveArray = chave.toCharArray();

		if (mensagem.length() > chave.length()) {
			igualar();
		}

		for (int i = 0; i < mensagem.length(); i++) {

			cifrado = tabela[acharLinha(i)][acharColuna(i)];
			textoCifrado.append(cifrado);
		}
		System.out.println(textoCifrado.toString());
	}

	public static int acharLinha(int valor) {
		int linha = 0;

		for (int i = 0; i < mensagemArray.length; i++) {
			for (int j = 0; j < alfabetoArray.length; j++) {

				if (mensagemArray[valor] == alfabetoArray[j]) {
					linha = j;
					break;
				}
			}
			break;
		}
		return linha;
	}

	public static int acharColuna(int valor) {
		int coluna = 0;

		for (int i = 0; i < chaveArray.length; i++) {
			for (int j = 0; j < alfabetoArray.length; j++) {

				if (textoIgualado.charAt(valor) == alfabetoArray[j]) {
					coluna = j;
					break;
				}
			}
			break;
		}
		return coluna;
	}

	public static void igualar() {

		textoIgualado.insert(0, chaveArray);

		int tamMensagem = mensagemArray.length;
		int tamChave = chaveArray.length;
		for (int i = 0; textoIgualado.length() < tamMensagem; i++) {

			if (i != tamChave) {
				textoIgualado.append(chaveArray[i]);

			} else {
				i = -1;
			}
		}

		//System.out.println(textoIgualado.toString());
	}

	public static void igualarDecript() {

		textoIgualado.insert(0, chaDecript);

		int tamMensagem = msgDecript.length;
		int tamChave = chaDecript.length;
		for (int i = 0; textoIgualado.length() < msgDecript.length; i++) {

			if (i != chaDecript.length) {
				textoIgualado.append(chaDecript[i]);
			} else {
				i = -1;
			}
		}

	//	System.out.println(textoIgualado.toString());
	}

	public static void decifrar(String mensagem, String chave) {
		msgDecript = mensagem.toCharArray();
		chaDecript = chave.toCharArray();

		if (mensagem.length() > chave.length()) {
			igualarDecript();
		}

		for (int i = 0; i < msgDecript.length; i++) {
			char chaveChar = textoIgualado.charAt(i);

			char encriptMsg = msgDecript[i % msgDecript.length];

			int posicaoChave = (int) chaveChar - 97;

			for (int j = 0; j < tabela.length; j++) {
				if (tabela[posicaoChave][j] == encriptMsg) {
					System.out.print(tabela[0][j]);
				}
			}
		}
		System.out.println("\n");
	} 
}
