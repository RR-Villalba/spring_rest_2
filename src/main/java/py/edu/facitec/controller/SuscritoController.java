package py.edu.facitec.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import py.edu.facitec.Repository.SuscritoRepository;
import py.edu.facitec.model.Suscrito;

@RestController  //Arquitectura Rest

@RequestMapping("/suscritos")//servidor

public class SuscritoController {
	
	@Autowired //inicializa dentro del contexto Spring
	private SuscritoRepository suscritoRepository;
	@GetMapping //Respondera al verbo GET
	
	public ResponseEntity<List<Suscrito>> getAll(){
		//realizamos la consulta y cargamos el objeto suscritos
		List<Suscrito> suscritos=suscritoRepository.findAll();
		
		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}
                                            //indicar que los datos viajan dentro del request
	                                        //datos que vienen del cliente es el objeto request
	//verbo Post
	@PostMapping
	public ResponseEntity<Suscrito> create( @RequestBody Suscrito suscritoLlega){
		
		System.out.println(suscritoLlega.toString());
		
		try {
			
			Suscrito suscritoRegistrado=suscritoRepository.save(suscritoLlega);
			
			System.out.println(suscritoRegistrado.toString());
			
			return new ResponseEntity<Suscrito>(suscritoRegistrado, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			
		}
		//POSTMAN
		
	}
	
	@GetMapping(value="/{codigo}")
	public ResponseEntity<Suscrito> getOne(@PathVariable Long codigo){
		Optional<Suscrito> suscritoConsulta=suscritoRepository.findById(codigo);
		
		if (suscritoConsulta.isPresent()) {
			return new ResponseEntity<Suscrito>(suscritoConsulta.get(), HttpStatus.OK);
			
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	}
	
	@DeleteMapping(value="/{codigo}")
	public ResponseEntity<Suscrito> deleteById(@PathVariable Long codigo){
		//logica para eliminar a traves de una tabla 
		
	try {
		suscritoRepository.deleteById(codigo);
		return new ResponseEntity<>(HttpStatus.OK);
	} catch (Exception e) {
		
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}


	}
}



