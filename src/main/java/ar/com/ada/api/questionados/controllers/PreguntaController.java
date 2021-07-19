package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.InfoPreguntaNueva;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.services.PreguntaService;

@RestController
public class PreguntaController {
    
    @Autowired
    PreguntaService service;

    @PostMapping("/preguntas")
    public ResponseEntity<?> crearPregunta(@RequestBody InfoPreguntaNueva preguntaNueva){

        GenericResponse respuesta = new GenericResponse();

        Pregunta pregunta = service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.categoriaId, preguntaNueva.opciones);

        respuesta.isOk = true;
        respuesta.id = pregunta.getPreguntaId();
        respuesta.message = "LA pregunta fue creada con éxito";

        return ResponseEntity.ok(preguntaNueva);
    }

    @GetMapping("/preguntas")
    public ResponseEntity<List<Pregunta>> traerPreguntas(){
        return ResponseEntity.ok(service.traerPreguntas());
    }

    @GetMapping("/preguntas/{id}")
    public ResponseEntity<?> getPreguntaPorId(@PathVariable Integer id){

        Pregunta pregunta = service.buscarPreguntaPorId(id);
        
        if (pregunta == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(pregunta);
    }
    
}
