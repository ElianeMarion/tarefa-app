package br.com.fiap.resource;

import br.com.fiap.dto.TarefaDto;
import br.com.fiap.service.TarefaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {

    @Inject
    TarefaService tarefaService;

    @GET
    public List<TarefaDto> listarTodas() {
        return tarefaService.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        TarefaDto tarefaDto = tarefaService.buscarPorId(id);
        if (tarefaDto != null) {
            return Response.ok(tarefaDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response criar(TarefaDto tarefaDto) {
        tarefaService.criar(tarefaDto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid TarefaDto tarefaDto) {
        tarefaService.atualizar(id, tarefaDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        tarefaService.deletar(id);
        return Response.noContent().build();
    }
}
