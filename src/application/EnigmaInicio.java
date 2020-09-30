package application;

import java.util.*;

public class EnigmaInicio {

	List<Rotor> rotorList;
	String posicaoInicialRotor;
	String mensagem;
	List<String> alfabetoList;
	List<String> refletorBList;
	int[] posicaoGeralRotor;
	StringBuilder decodificado;
	boolean rotacionar = false;

	EnigmaInicio(List<Rotor> rotorList, String posicaoInicialRotor, String mensagem) {

		posicaoGeralRotor = new int[] { 0, 0, 0 };// Rotor 1,2,3

		this.rotorList = rotorList;
		StringBuilder sb = new StringBuilder(posicaoInicialRotor);
		this.posicaoInicialRotor = sb.toString().toUpperCase();
		refletorBList = new ArrayList<String>();
		refletorBList.add("Y");
		refletorBList.add("R");
		refletorBList.add("U");
		refletorBList.add("H");
		refletorBList.add("Q");
		refletorBList.add("S");
		refletorBList.add("L");
		refletorBList.add("D");
		refletorBList.add("P");
		refletorBList.add("X");
		refletorBList.add("N");
		refletorBList.add("G");
		refletorBList.add("O");
		refletorBList.add("K");
		refletorBList.add("M");
		refletorBList.add("I");
		refletorBList.add("E");
		refletorBList.add("B");
		refletorBList.add("F");
		refletorBList.add("Z");
		refletorBList.add("C");
		refletorBList.add("W");
		refletorBList.add("V");
		refletorBList.add("J");
		refletorBList.add("A");
		refletorBList.add("T");
		this.mensagem = mensagem.toUpperCase();
		System.out.println(mensagem + " é a mensagem " + mensagem.length());
		alfabetoList = new ArrayList<String>();
		alfabetoList.add("A");
		alfabetoList.add("B");
		alfabetoList.add("C");
		alfabetoList.add("D");
		alfabetoList.add("E");
		alfabetoList.add("F");
		alfabetoList.add("G");
		alfabetoList.add("H");
		alfabetoList.add("I");
		alfabetoList.add("J");
		alfabetoList.add("K");
		alfabetoList.add("L");
		alfabetoList.add("M");
		alfabetoList.add("N");
		alfabetoList.add("O");
		alfabetoList.add("P");
		alfabetoList.add("Q");
		alfabetoList.add("R");
		alfabetoList.add("S");
		alfabetoList.add("T");
		alfabetoList.add("U");
		alfabetoList.add("V");
		alfabetoList.add("W");
		alfabetoList.add("X");
		alfabetoList.add("Y");
		alfabetoList.add("Z");
		decodificado = new StringBuilder();
		configuracaoGeralPosicaoRotor();

	}

	public void decodificar() {
		configuracaoRotor();

		for (int i = 0; i < mensagem.length(); i++) {
			String letra = String.valueOf(mensagem.charAt(i));
			clique(0);
			for (int a = 0; a < rotorList.size(); a++) {
				letra = acharProximo(rotorList.get(a), letra, a);
			}
			letra = acharRefletor(letra);
			for (int a = 2; a > -1; a--) {
				letra = buscaReversa(rotorList.get(a), letra, a);
			}
			decodificado.append(letra);
		}
		System.out.println("Mensagem Cifrada : " + mensagem.toString() + " -----------------------------------------");
		System.out.println("Mensagem Decifrada : " + decodificado.toString() + " -----------------------------------------");
	}

	public void codificar() {
		configuracaoRotor();

		for (int i = 0; i < mensagem.length(); i++) {
			String letra = String.valueOf(mensagem.charAt(i));
			clique(0);
			for (int a = 0; a < rotorList.size(); a++) {
				letra = acharProximo(rotorList.get(a), letra, a);
			}
			letra = acharRefletor(letra);
			for (int a = 2; a > -1; a--) {
				letra = buscaReversa(rotorList.get(a), letra, a);
			}
			decodificado.append(letra);
		}
		System.out.println("Mensagem Cifrada : " + decodificado.toString() + " -----------------------------------------");
		System.out.println("Mensagem Original : " + mensagem.toString() + " -----------------------------------------");
	}

	public int pegarIndiceCircular(int indice, int tamanho) {
		if (indice < 0) {
			indice = tamanho + indice;
		} else if (indice > tamanho) {
			indice = indice - tamanho;
		} else if (indice == tamanho) {
			indice = 0;
		}
		return indice;
	}

	public String buscaReversa(Rotor rotor, String letra, int a) {

		int posicao = rotor.configRotor.indexOf(letra);
		String entradaLetra;
		if (a == 0) {
			entradaLetra = alfabetoList.get(posicao);
		} else {
			entradaLetra = alfabetoList.get(pegarIndiceCircular(posicao + posicaoGeralRotor[a - 1], 26));
		}
		return entradaLetra;
	}

	public String acharRefletor(String letra) {
		int posicaoAlfa = alfabetoList.indexOf(letra);
		String refletido = refletorBList.get(posicaoAlfa);
		refletido = alfabetoList.get(pegarIndiceCircular(
				alfabetoList.indexOf(refletido) + posicaoGeralRotor[posicaoGeralRotor.length - 1], 26));
		return refletido;
	}

	public String acharProximo(Rotor rotor, String letra, int a) {
		int posicao = alfabetoList.indexOf(String.valueOf(letra));

		int posicaoDoCodigo = alfabetoList.indexOf(rotor.configRotor.get(posicao)) - posicaoGeralRotor[a];

		String codigoLetra = alfabetoList.get(pegarIndiceCircular(posicaoDoCodigo, 26));
		return codigoLetra;
	}

	public void configuracaoGeralPosicaoRotor() {
		for (int i = 0; i < posicaoInicialRotor.length(); i++) {
			String posicao = String.valueOf(posicaoInicialRotor.charAt(i));
			int posicaoRotor = alfabetoList.indexOf(posicao);
			if (i + 1 == 2) {
				if (posicao.equals("E")) {
					rotacionar = true;
				}
			}
			posicaoGeralRotor[i] = posicaoRotor;
		}
	}

	public void configuracaoRotor() {

		for (int i = 0; i < rotorList.size(); i++) {
			Rotor rotor = rotorList.get(i);
			rotor.configRotor = pegarPosicaoArray(rotor.configRotor, String.valueOf(posicaoInicialRotor.charAt(i)));

		}
	}

	public List<String> pegarPosicaoArray(List<String> codigo, String posicao) {

		int posAlfabeto = alfabetoList.indexOf(posicao);
		if (posAlfabeto == 0) {
			return codigo;
		}

		for (int i = 0; i < posAlfabeto; i++) {
			codigo.add(codigo.get(0));
			codigo.remove(0);
		}
		return codigo;
	}

	public void clique(int numeroAtualRotor) {

		int numeroRealRotor = rotorList.get(numeroAtualRotor).numeroRotor;
		posicaoGeralRotor[numeroAtualRotor] = posicaoGeralRotor[numeroAtualRotor] + 1;

		if (posicaoGeralRotor[numeroAtualRotor] == 26) {
			posicaoGeralRotor[numeroAtualRotor] = 0;
		}

		if (numeroAtualRotor + 1 == 1) {
			if (rotacionar) {
				clique(numeroAtualRotor + 1);
				rotacionar = false;
			}
			if (posicaoGeralRotor[numeroAtualRotor] == 17) {
				clique(numeroAtualRotor + 1);
			}
		}

		if (numeroAtualRotor + 1 == 2) {
			if (posicaoGeralRotor[numeroAtualRotor] == 4) {
				rotacionar = true;
			}
			if (posicaoGeralRotor[numeroAtualRotor] == 5) {
				clique(numeroAtualRotor + 1);
			}
		}
		if (numeroAtualRotor + 1 == 3) {
			if (posicaoGeralRotor[numeroAtualRotor] == 21) {
			}
		}

		List<String> listaA = rotorList.get(numeroAtualRotor).configRotor;
		String temporario = listaA.get(0);
		listaA.remove(0);
		listaA.add(temporario);
		rotorList.get(numeroAtualRotor).configRotor = listaA;
	}
}