package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;
import ar.com.ada.api.questionados.repos.PreguntaRepository;

@Service
public class PreguntaService {

    @Autowired
    PreguntaRepository repo;

    @Autowired
    CategoriaService categoriaService;

    public List<Pregunta> traerPreguntas(){
        return repo.findAll();
    }

    public Pregunta buscarPreguntaPorId(Integer PreguntaId){

        Optional<Pregunta> resultado = repo.findById(PreguntaId);   // findbyId: busca por Id una pregunta, te devuelve 1 optional<pregunta>

        if(resultado.isPresent())
             return resultado.get();
        return null;     
    }
    
    public Pregunta crearPregunta(String enunciado, Integer categoriaId, List<Respuesta> opciones){
        
        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado(enunciado);

        Categoria categoria = categoriaService.buscarCategoria(categoriaId);
        
        pregunta.setCategoria(categoria);
        
        for (Respuesta respuesta: opciones){   //se ejecuta, se llama a recorrer la lista y a c/ respuesta se le setea la preg
            respuesta.setPregunta(pregunta);
        }
        
        repo.save(pregunta);
        return pregunta;
    }
}
