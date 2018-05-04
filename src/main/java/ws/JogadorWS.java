package ws;

import entity.Jogador;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import rn.JogadorRN;

/**
 * REST Web Service
 *
 * @author ibrum
 */
@Path("jogador")
public class JogadorWS {

    JogadorRN jogadorRN;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of JogadorWS
     */
    public JogadorWS() {
        jogadorRN = new JogadorRN();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Jogador> getJogadores(){
        return (jogadorRN.listar());
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jogador getJogadorPorId(@PathParam("id") Long id) {
        return jogadorRN.buscarPorId(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Jogador adicionar(Jogador jogador, @Context HttpServletResponse response) {

        jogadorRN.inserir(jogador);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return jogador;
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Jogador atualiza(@PathParam("id") Long id, Jogador jogador){
        jogador.setId(id);
        Jogador jogadorAtualizado = jogadorRN.atualizar(jogador);
        return jogadorAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jogador deletar(@PathParam("id") Long id){
        Jogador jogadorDeletado = jogadorRN.deletar(id);
        return jogadorDeletado;
    }
}
