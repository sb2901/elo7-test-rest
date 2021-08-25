package br.com.elo7.test.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import br.com.elo7.test.bean.Direction;
import br.com.elo7.test.bean.Mars;
import br.com.elo7.test.bean.Planet;
import br.com.elo7.test.bean.Probe;

/***
 * Classe responsável por interpretar os dados
 * e enviar as classes responsáveis
 * @author Susan Braun Rosa
 *
 */
public class NASAMarsController {

	private Planet planet;
	private List<Probe> listProbes;

	private static final String INPUT_REGEX = 
					"^[0-9]+\\s[0-9]+" 
					+ "(\\n[0-9]+\\s[0-9]+\\s[NSWE]{1}\\n" 
					+ "[LMR]*)*"
					+ "$";
	public static final String WRONG_PATTERN = "Os dados de entrada estão fora do padrão determinado";

	/**
	 * 
	 * @param witdh
	 * @param rigth
	 */
	private void init(int witdh, int rigth) {
		definePlanet(witdh, rigth);
		listProbes = new ArrayList<Probe>();
	}

	/**
	 * Responsavel por processar a lista de comandos de cada Sonda
	 * @param position
	 * @param actions
	 */
	private void processListAction(String[] position, String[] actions) {
		Probe probe = createProbe(Integer.valueOf(position[0]), Integer.valueOf(position[1]), position[2]);

		listProbes.add(probe);

		doProbeActions(probe, actions);
	}

	/**
	 * Proccessa os dados de entrada
	 * @param inputData
	 * @throws Exception
	 */
	public void process(String inputData) throws Exception {
		inputData = inputData.replace("\r", "");
		validadeInput(inputData);
		String[] lines = inputData.split("\n");
		String[] planetSize = lines[0].split(" ");

		init(Integer.valueOf(planetSize[0]), Integer.valueOf(planetSize[1]));

		for (int i = 1; i < lines.length; i = i + 2) {
			String probe = lines[i];
			String actions = i + 1 < lines.length ? lines[i + 1] : "";
			processListAction(probe.split(" "), actions.split(""));
		}

	}

	/***
	 * Valida se a entrada está no formato esperado
	 * @param inputData
	 * @throws Exception
	 */
	private void validadeInput(String inputData) throws Exception {
		Pattern pattern = Pattern.compile(INPUT_REGEX);
		Matcher matcher = pattern.matcher(inputData);

		if (!matcher.find()) {
			throw new Exception(WRONG_PATTERN);
		}
	}

	/**
	 * 
	 * @param width
	 * @param height
	 */
	private void definePlanet(int width, int height) {
		planet = new Mars(width, height);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * @return
	 */
	private Probe createProbe(int x, int y, String direction) {
		return new Probe(x, y, Direction.valueOf(direction), planet);
	}

	/***
	 * 
	 * @param probe
	 * @param actions
	 */
	private void doProbeActions(Probe probe, String[] actions) {
		for (String action : actions) {
			probe.doAction(action);
		}
	}

	/***
	 * Retorna o resultado do processamento
	 * @return
	 */
	public List<String> getResult() {
		return listProbes.stream().map(probe -> probe.showPosition()).collect(Collectors.toList());
	}

	/***
	 * 
	 * @return
	 */
	public String getResultAsString() {

		List<String> list = getResult();
		String s = "";
		for (String item : list) {
			s += item +"\n";
		}
		return s;
	}

}
