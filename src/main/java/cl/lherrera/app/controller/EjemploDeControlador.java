package cl.lherrera.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EjemploDeControlador {
	
	@RequestMapping(value = "/unaURL", method = RequestMethod.GET)
	public String miNombreNoImporta() {
		// todo el procesamiento de la petición
		System.out.println("Se ejecuta el procesamiento de la petición");
		
		return "unaVista";
	}

}
