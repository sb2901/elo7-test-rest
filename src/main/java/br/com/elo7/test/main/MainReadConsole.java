package br.com.elo7.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.elo7.test.manage.NASAMarsController;
/**
 * 
 * @author Susan Braun Rosa
 *
 */
public class MainReadConsole {
	
	public static void main(String[] args) {
		new MainReadConsole().init();
	}

	public void init() {
		initData(readConsole());
	}

	private StringBuilder readConsole() {

		Scanner input = null;
		try {
			input = new Scanner(System.in);
			List<String> lines = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();

			String lineNew;

			System.out.print("Insira os dados. Digite uma linha em branco para finalizar:");
			while (input.hasNextLine()) {
				lineNew = input.nextLine();
				if (lineNew.isEmpty()) {
					break;
				}
				sb.append(lineNew + "\n");
				lines.add(lineNew);
			}

			return sb;
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	public void initData(StringBuilder data) {
		try {
			NASAMarsController controller = new NASAMarsController();
			controller.process(data.toString());
			System.out.println(controller.getResultAsString());
		} catch (Exception e) {
			System.err.println("Ops, algo deu errado : " + e.getMessage());
			e.printStackTrace();
		}
	}
}