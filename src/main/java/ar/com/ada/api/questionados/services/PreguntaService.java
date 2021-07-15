package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.repos.PreguntaRepository;

@Service
public class PreguntaService {

    @Autowired
    PreguntaRepository repo;

    public List<Pregunta> traerPreguntas(){
        return repo.findAll();
    }

    public Pregunta buscarEmpleadaPorId(Integer PreguntaId){

        Optional<Pregunta> resultado = repo.findById(PreguntaId);   // findbyId: busca por Id una pregunta, te devuelve 1 optional<pregunta>

        if(resultado.isPresent())
             return resultado.get();
        return null;     
    }
    
}
