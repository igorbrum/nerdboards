package ws;

import entity.Mesa;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rn.MesaRN;

/**
 * REST Web Service
 *
 * @author ibrum
 */
@Path("mesa")
public class MesaWS {

    MesaRN mesaRN;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MesaWS
     */
    public MesaWS() {
        mesaRN = new MesaRN();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mesa> getMesas(){
        return (mesaRN.listar());
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa getJogadorPorId(@PathParam("id") Long id) {
        return mesaRN.buscarPorId(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa adicionar(Mesa mesa, @Context HttpServletResponse response) {

        mesaRN.inserir(mesa);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return mesa;
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa atualiza(@PathParam("id") Long id, Mesa mesa){
        mesa.setId(id);
        Mesa mesaAtualizada = mesaRN.atualizar(mesa);
        return mesaAtualizada;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa deletar(@PathParam("id") Long id){
        Mesa mesaDeletada = mesaRN.deletar(id);
        return mesaDeletada;
    }
}
