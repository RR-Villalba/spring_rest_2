package py.edu.facitec.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;


@Controller
public class HomeController {
	//URL a responder
		@RequestMapping("/")
		public String home() {
			System.out.println("Ingrese a la pagina de inicio");
			return "usuario/login";
	
		}
		@RequestMapping("/form")
		public String formsuscrito() {
			
			
			System.out.println("Cargando página de suscritos");
		    return "suscrito/form";
		}

}
