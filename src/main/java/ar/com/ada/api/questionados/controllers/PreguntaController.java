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

        Pregunta pregunta = service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.opciones, preguntaNueva.categoria);

        respuesta.isOk = true;
        respuesta.id = preguntaNueva.getPreguntaPorId();
        respuesta.message = "LA pregunta fue creada con éxito";

        return ResponseEntity.ok(preguntaNueva);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Pregunta>> traerPreguntas(){
        return ResponseEntity.ok(service.traerPreguntas());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<?> getPreguntaPorId(@PathVariable Integer id){

        Pregunta pregunta = service.buscarEmpleadaPorId(id);
        
        if (pregunta == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(pregunta);
    }
    
}
