package br.com.elo7.test.main;

import br.com.elo7.test.manage.NASAMarsController;

public class MainFixedData {

	String data = "5 5\n" 
			+ "1 2 N\n" 
			+ "LMLMLMLMM\n" 
			+ "3 3 E\n" 
			+ "MMRMMRMRRM";

	public static void main(String... args) {
		new MainFixedData().init();

	}

	public void init() {
		try {
			NASAMarsController controller = new NASAMarsController();
			controller.process(data);
			System.out.println(controller.getResultAsString());
		} catch (Exception e) {
			System.err.println("Ops, algo deu errado : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
