package application;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Enigma {

	static Scanner scan = new Scanner(System.in);
	static List<String> codigosList;
	static List<String> listAlfabeto1 = new ArrayList<String>(Arrays.asList("H,P,G,J,S,Z,X,A,I,M,N,B,V,R,L,Y,Q,U,C,E,O,F,W,D,T,K".split(",")));
	static List<String> listAlfabeto2 = new ArrayList<String>(Arrays.asList("N,V,X,J,Y,L,M,H,S,B,T,C,F,I,R,Z,D,G,O,W,E,Q,P,K,A,U".split(",")));
	static List<String> listAlfabeto3 = new ArrayList<String>(Arrays.asList("K,Y,G,F,D,Q,M,B,L,X,V,N,C,Z,U,H,T,W,R,I,E,P,S,O,A,J".split(",")));

	static List<String> alfabeto;
	static List<Rotor> rotorList;
	static Rotor rotor1;
	static Rotor rotor2;
	static Rotor rotor3;
	
	String posicaoInicialRotor;
	String mensagem;
	List<String> alfabetoList = new ArrayList<String>(Arrays.asList("A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",")));
	List<String> refletorBList = new ArrayList<String>(Arrays.asList("Y,R,U,H,Q,S,L,D,P,X,N,G,O,K,M,I,E,B,F,Z,C,W,V,J,A,T".split(",")));
	
	int[] posicaoGeralRotor;
	StringBuilder decodificado;
	boolean rotacionar = false;
	
	public Enigma() {
		
	}

	public static void gerarRotores() {

		codigosList = new ArrayList<String>(listAlfabeto1);
 		rotor1 = new Rotor(codigosList, 1);

		codigosList = new ArrayList<String>(listAlfabeto2);
		rotor2 = new Rotor(codigosList, 2);

		codigosList = new ArrayList<String>(listAlfabeto3);
		rotor3 = new Rotor(codigosList, 3);

		rotorList = new ArrayList<Rotor>();
		rotorList.add(rotor1);
		rotorList.add(rotor2);
		rotorList.add(rotor3);
	}

	public static void cifrar(String mensagem, String rotorConfiguracao) {

		if (checarParametros(mensagem, rotorConfiguracao))
			return;

		Enigma inicioEnigma = new Enigma(rotorList, rotorConfiguracao, mensagem);
		inicioEnigma.codificar();

	}

	public static void decifrar(String mensagem, String rotorConfiguracao) {

		if (checarParametros(mensagem, rotorConfiguracao))
			return;

		Enigma inicioEnigma = new Enigma(rotorList, rotorConfiguracao, mensagem);
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
	
	
	Enigma(List<Rotor> rotorList, String posicaoInicialRotor, String mensagem) {

		posicaoGeralRotor = new int[] { 0, 0, 0 };// Rotor 1,2,3

		this.rotorList = rotorList;
		StringBuilder sb = new StringBuilder(posicaoInicialRotor);
		this.posicaoInicialRotor = sb.toString().toUpperCase();
		
		this.mensagem = mensagem.toUpperCase();
		System.out.println(mensagem + " é a mensagem " + mensagem.length());
		
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
		System.out.println("Mensagem Cifrada : " + mensagem.toString());
		System.out.println("Mensagem Decifrada : " + decodificado.toString());
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
		System.out.println("Mensagem Cifrada : " + decodificado.toString());
		System.out.println("Mensagem Original : " + mensagem.toString());
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