package com.servico;

import com.entidade.Mensagem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author osmar
 */
public class MensagemServico {

    /**
     * Operação de Web service
     *
     * @return
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public String getMensagem() throws IOException {
        String retorno = "";

        //Url do serviço
        String url = "http://localhost:8080/webservice_mensagem_jaxrs_rest/rest/mensagem";

        //Criar instância do cliente JAX-RS
        Client clienteJAXRS = ClientBuilder.newClient();

        //Criar o parser do JSON para o objeto Mensagem
        ObjectMapper objectMapper = new ObjectMapper();

        //Requisição GET do serviço        
        Response resposta = clienteJAXRS.target(url).request(MediaType.APPLICATION_JSON).get();

        // Verificar o código de resposta
        if (resposta.getStatus() == 200) {
            //Resposta bruta do servidor
            String jsonResposta = resposta.readEntity(String.class);

            System.out.println("Texto resposta do servidor: " + jsonResposta);

            //Preenche os objeto mensagem com os dados do JSON
            Mensagem mensagem = objectMapper.readValue(jsonResposta, Mensagem.class);

            //Mensagem de retorno
            retorno = mensagem.getMensagem();

        } else {
            System.out.println("Falha ao obter a mensagem. Código de resposta: " + resposta.getStatus());
        }

        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @param novaMensagem
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public void setMensagem(String novaMensagem) throws JsonProcessingException {

        //Url do serviço
        String url = "http://localhost:8080/webservice_mensagem_jaxrs_rest/rest/mensagem";

        //Criar instância do cliente JAX-RS
        Client clienteJAXRS = ClientBuilder.newClient();

        //Criar o parser do JSON para o objeto Mensagem
        ObjectMapper objectMapper = new ObjectMapper();

        //Instancia o objeto mensagem 
        Mensagem mensagem = new Mensagem(novaMensagem);

        // Converter o objeto Mensagem para uma string JSON
        String jsonInputString = objectMapper.writeValueAsString(mensagem);
        //System.out.println("jsonstring:" + jsonInputString);

        // Enviar a requisição POST
        Response resposta = clienteJAXRS.target(url).request(MediaType.APPLICATION_JSON).post(Entity.json(jsonInputString));

        // Verificar o código de resposta
        if (resposta.getStatus() == 200) {
            System.out.println("Mensagem criada com sucesso!");
            System.out.println("Resposta do servidor: " + resposta.readEntity(String.class));
        } else {
            System.out.println("Falha ao criar a mensagem. Código de resposta: " + resposta.getStatus());
        }
    }
}
