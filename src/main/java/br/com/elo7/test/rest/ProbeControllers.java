package br.com.elo7.test.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.test.controller.NASAMarsController;

@RestController
public class ProbeControllers {

	@PostMapping(value ="/mars/probe/execute",
			consumes = "text/plain", produces = 
            "text/plain")
	public String execute(@RequestBody String data) {

		try {
			NASAMarsController controller = new NASAMarsController();
			controller.process(data.toString());
			return controller.getResultAsString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Ops, algo deu errado : " + e.getMessage();

		}

	}
}
