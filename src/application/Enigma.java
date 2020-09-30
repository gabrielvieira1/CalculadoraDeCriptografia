package application;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Enigma {

	public static List<String> codigosList;
	public static List<String> alfabeto;
	public static List<Rotor> rotorList;
	public static Rotor rotor1;
	public static Rotor rotor2;
	public static Rotor rotor3;
	public static Scanner sc;

	public Enigma() {

		codigosList = new ArrayList<String>();
		codigosList.add("E");
		codigosList.add("K");
		codigosList.add("M");
		codigosList.add("F");
		codigosList.add("L");
		codigosList.add("G");
		codigosList.add("D");
		codigosList.add("Q");
		codigosList.add("V");
		codigosList.add("Z");
		codigosList.add("N");
		codigosList.add("T");
		codigosList.add("O");
		codigosList.add("W");
		codigosList.add("Y");
		codigosList.add("H");
		codigosList.add("X");
		codigosList.add("U");
		codigosList.add("S");
		codigosList.add("P");
		codigosList.add("A");
		codigosList.add("I");
		codigosList.add("B");
		codigosList.add("R");
		codigosList.add("C");
		codigosList.add("J");
		rotor1 = new Rotor(codigosList, 1);
		codigosList = new ArrayList<String>();
		codigosList.add("A");
		codigosList.add("J");
		codigosList.add("D");
		codigosList.add("K");
		codigosList.add("S");
		codigosList.add("I");
		codigosList.add("R");
		codigosList.add("U");
		codigosList.add("X");
		codigosList.add("B");
		codigosList.add("L");
		codigosList.add("H");
		codigosList.add("W");
		codigosList.add("T");
		codigosList.add("M");
		codigosList.add("C");
		codigosList.add("Q");
		codigosList.add("G");
		codigosList.add("Z");
		codigosList.add("N");
		codigosList.add("P");
		codigosList.add("Y");
		codigosList.add("F");
		codigosList.add("V");
		codigosList.add("O");
		codigosList.add("E");
		rotor2 = new Rotor(codigosList, 2);
		codigosList = new ArrayList<String>();
		codigosList.add("B");
		codigosList.add("D");
		codigosList.add("F");
		codigosList.add("H");
		codigosList.add("J");
		codigosList.add("L");
		codigosList.add("C");
		codigosList.add("P");
		codigosList.add("R");
		codigosList.add("T");
		codigosList.add("X");
		codigosList.add("V");
		codigosList.add("Z");
		codigosList.add("N");
		codigosList.add("Y");
		codigosList.add("E");
		codigosList.add("I");
		codigosList.add("W");
		codigosList.add("G");
		codigosList.add("A");
		codigosList.add("K");
		codigosList.add("M");
		codigosList.add("U");
		codigosList.add("S");
		codigosList.add("Q");
		codigosList.add("O");
		rotor3 = new Rotor(codigosList, 3);
		rotorList = new ArrayList<Rotor>();
		rotorList.add(rotor1);
		rotorList.add(rotor2);
		rotorList.add(rotor3);

	}

	public static void cifrar(String mensagem, String rotorConfiguracao) {

		if (checarParametros(mensagem, rotorConfiguracao))
			return;

		EnigmaInicio inicioEnigma = new EnigmaInicio(rotorList, rotorConfiguracao, mensagem);
		inicioEnigma.codificar();

	}

	public static void decifrar(String mensagem, String rotorConfiguracao) {

		if (checarParametros(mensagem, rotorConfiguracao))
			return;

		EnigmaInicio inicioEnigma = new EnigmaInicio(rotorList, rotorConfiguracao, mensagem);
		inicioEnigma.decodificar();

	}

	public static boolean checarParametros(String mensagem, String rotorConfiguration) {
		Pattern padrao = Pattern.compile("[^A-Za-z]");
		Matcher combinarMensagem = padrao.matcher(mensagem);
		Matcher combinarRotor = padrao.matcher(rotorConfiguration);
		boolean mensagemErrada = combinarMensagem.find();
		boolean rotorConfigErrada = combinarRotor.find();
		boolean permissao = false;
		if (mensagemErrada)
			System.out.println("O formato da mensagem não é compatível");

		if (rotorConfigErrada)
			System.out.println("O formato de configuração do rotor não é compatível");
		if (rotorConfiguration.length() != 3) {
			rotorConfigErrada = true;
			System.out.println("A configuração do rotor esperada é de tamanho -> 3");
		}
		permissao = (mensagemErrada | rotorConfigErrada);
		return permissao;
	}

	public static void imprimir() {
		String codigo = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
		StringBuilder codigoR = new StringBuilder(codigo);
		StringBuilder imprimirValores = new StringBuilder();
		for (int i = 0; i < codigoR.length(); i++) {
			System.out.print("reflectorBList.add(\"" + codigoR.charAt(i) + "\");");
		}
	}

}
