package application;

import java.util.Scanner;

public class Program {

	static Scanner scan = new Scanner(System.in);
	static Monoalfabetica calc = new Monoalfabetica();

	public static void main(String[] args) throws InterruptedException {
		
		String chaveString, sair, mensagem, cripMensagem, desCripMensagem;
		int chave;

		do {
			System.out.println("|-----------------------------------|");
			System.out.println("|    Calculadora Criptografica      |");
			System.out.println("|-----------------------------------|");
			System.out.println("|--- CIFRA DE CEZAR ----------------|");
			System.out.println("|                                   |");
			System.out.println("|(1) para criptografar mensagem     |");
			System.out.println("|(2) para descriptografar mensagem  |");
			System.out.println("|                                   |");
			System.out.println("|--- SUBSTITUIÇÃO MONOALFABETICA ---|");
			System.out.println("|                                   |");
			System.out.println("|(3) para criptografar mensagem     |");
			System.out.println("|(4) para descriptografar mensagem  |");
			System.out.println("|                                   |");
			System.out.println("|--- PLAYFAIR ----------------------|");
			System.out.println("|                                   |");
			System.out.println("|(5) para criptografar mensagem     |");
			System.out.println("|(6) para descriptografar mensagem  |");
			System.out.println("|                                   |");
			System.out.println("|--- POLIALFABETICA - VIGENERE -----|");
			System.out.println("|                                   |");
			System.out.println("|(7) para criptografar mensagem     |");
			System.out.println("|(8) para descriptografar mensagem  |");
			System.out.println("|                                   |");
			System.out.println("|--- PRODUTO - ENIGMA --------------|");
			System.out.println("|                                   |");
			System.out.println("|(9) para criptografar mensagem     |");
			System.out.println("|(10) para descriptografar mensagem |");
			System.out.println("|                                   |");
			System.out.println("|(0) para sair do software          |");
			System.out.println("|___________________________________|");
			sair = scan.next();
			scan.nextLine();
			//System.out.println(" ");
			// vguvgfqvguvg
			switch (sair) {

			case "1":

				System.out.println("Informe a mensagem que deseja cifrar");
				mensagem = scan.nextLine().replaceAll(" ", "");

				System.out.println("Informe a chave");
				chave = scan.nextInt();

				System.out.println("Resultado da cifragem: ");
				Cesar.cifrar(mensagem, chave);
				Thread.sleep(3000);
				break;

			case "2":

				System.out.println("Informe a mensagem que deseja decifrar");
				mensagem = scan.nextLine();

				System.out.println("Informe a chave");
				chave = scan.nextInt();

				System.out.println("Resultado: ");
				Cesar.decifrar(mensagem, chave);
				Thread.sleep(3000);

				break;

			case "3":
				
				
				System.out.println("Informe a mensagem que deseja cifrar");
				mensagem = scan.nextLine();
				mensagem = mensagem.toUpperCase();

				cripMensagem = calc.subsAlfabetica_CRIP(mensagem);
				System.out.println("Resultado: " + cripMensagem);
				Thread.sleep(3000);

				break;

			case "4":
				
				System.out.println("Informe a mensagem que deseja decifrar");
				mensagem = scan.nextLine();
				mensagem = mensagem.toUpperCase();

				desCripMensagem = calc.subsAlfabetica_DESCRIP(mensagem);
				System.out.println("Resultado: " + desCripMensagem);
				Thread.sleep(3000);

				break;
				
			case "5":

			
				System.out.println("Informe a mensagem que deseja cifrar");
				mensagem = scan.next().replaceAll(" ", "");
				System.out.println("Informe a chave");
				chaveString = scan.next();
				Playfair.encriptar(mensagem, chaveString);
				Thread.sleep(3000);
				
				break;
				
			case "6":

				
				System.out.println("Informe a mensagem que deseja decifrar");
				mensagem = scan.next().replaceAll(" ", "");
				System.out.println("Informe a chave");
				chaveString = scan.next();
				Playfair.desencriptar(mensagem, chaveString);
				Thread.sleep(3000);
				
				break;
				
			case "7":
				
				System.out.println("Informe a mensagem que deseja cifrar");
				mensagem = scan.nextLine();

				System.out.println("Informe a chave");
				chaveString = scan.next();
				Vigenere.gerarMatriz();
				System.out.println("Resultado da cifragem: ");
				Vigenere.cifrar(mensagem, chaveString);
				scan.nextLine();
				Thread.sleep(3000);
				break;

			case "8":

				System.out.println("Informe a mensagem que deseja decifrar");
				mensagem = scan.nextLine();

				System.out.println("Informe a chave");
				chaveString = scan.next();
				Vigenere.gerarMatriz();
				System.out.println("Resultado: ");
				Vigenere.decifrar(mensagem, chaveString);
				scan.nextLine();
				Thread.sleep(3000);
				break;
				
			case "9":
				System.out.println("Informe a mensagem que deseja cifrar");
				mensagem = scan.nextLine().replaceAll(" ", "");

				System.out.println("Informe a chave");
				chaveString = scan.next();
				
				Enigma.gerarRotores();
				Enigma.cifrar(mensagem, chaveString);
				Thread.sleep(3000);
				break;
				
			case "10":
				System.out.println("Informe a mensagem que deseja decifrar");
				mensagem = scan.nextLine().replaceAll(" ", "");

				System.out.println("Informe a chave");
				chaveString = scan.next();
				
				Enigma.gerarRotores();
				Enigma.decifrar(mensagem, chaveString);
				Thread.sleep(3000);
				break;
				
			case "0":
				break;
			default:
				System.out.println("Digite apenas valores de acordo com as opções listadas!!!");
			}

		} while (!sair.equals("0"));

	}

}
