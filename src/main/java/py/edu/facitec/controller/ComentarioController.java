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


import py.edu.facitec.Repository.ComentarioRepository;
import py.edu.facitec.model.Comentario;

@RestController  //Arquitectura Rest

@RequestMapping("/comentarios")//servidor

public class ComentarioController {
	
	@Autowired //inicializa dentro del contexto Spring
	private ComentarioRepository comentarioRepository;
	@GetMapping //Respondera al verbo GET
	
	public ResponseEntity<List<Comentario>> getAll(){
		//realizamos la consulta y cargamos el objeto comentarios
		List<Comentario> comentarios=comentarioRepository.findAll();
		
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}
                                            //indicar que los datos viajan dentro del request
	                                        //datos que vienen del cliente es el objeto request
	//verbo Post
	@PostMapping
	public ResponseEntity<Comentario> create( @RequestBody Comentario comentarioLlega){
		
		System.out.println(comentarioLlega.toString());
		
		try {
			
			Comentario comentarioRegistrado=comentarioRepository.save(comentarioLlega);
			
			System.out.println(comentarioRegistrado.toString());
			
			return new ResponseEntity<Comentario>(comentarioRegistrado, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			
		}
		//POSTMAN
		
	}
	
	@GetMapping(value="/{codigo}")
	public ResponseEntity<Comentario> getOne(@PathVariable Long codigo){
		Optional<Comentario> comentarioConsulta=comentarioRepository.findById(codigo);
		
		if (comentarioConsulta.isPresent()) {
			return new ResponseEntity<Comentario>(comentarioConsulta.get(), HttpStatus.OK);
			
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	}
	
	@DeleteMapping(value="/{codigo}")
	public ResponseEntity<Comentario> deleteById(@PathVariable Long codigo){
		//logica para eliminar a traves de una tabla 
		
	try {
		comentarioRepository.deleteById(codigo);
		return new ResponseEntity<>(HttpStatus.OK);
	} catch (Exception e) {
		
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}


	}
}



