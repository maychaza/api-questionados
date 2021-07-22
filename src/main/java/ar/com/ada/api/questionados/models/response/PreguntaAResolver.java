package ar.com.ada.api.questionados.models.response;

import java.util.ArrayList;
import java.util.List;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;

public class PreguntaAResolver {

    public Integer preguntaId;
    public String enunciado;
    public Categoria categoria;
    public List<OpcionPregunta> opciones = new ArrayList<>();
    

    public static PreguntaAResolver convertirDesde(Pregunta pregunta){

        PreguntaAResolver preguntaAResolver = new PreguntaAResolver();

        preguntaAResolver.preguntaId = pregunta.getPreguntaId();
        preguntaAResolver.enunciado = pregunta.getEnunciado();
        preguntaAResolver.categoria = pregunta.getCategoria();
        
        //lista vacia, se declara
        preguntaAResolver.opciones = new ArrayList<>();

        for ( Respuesta respuesta : pregunta.getOpciones()) {
            // se instancia la lista vacia
            OpcionPregunta opcion = new OpcionPregunta();
            opcion.respuestaId = respuesta.getRespuestaId();
            opcion.texto = respuesta.getTexto();

            preguntaAResolver.opciones.add(opcion);
        }

        return preguntaAResolver;
    }
}
