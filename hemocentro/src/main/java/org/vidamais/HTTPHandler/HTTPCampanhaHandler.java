package org.vidamais.HTTPHandler;

import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.vidamais.Core.MeuResultSet;
import org.vidamais.DAO.Campanhas;
import org.vidamais.DBMySQLServer;
import org.vidamais.DBO.Campanha;
import org.vidamais.DBO.TipoSanguineoCampanha;
import org.vidamais.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HTTPCampanhaHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Processando requisição...");

        handleGet(exchange);
        handlePost(exchange);
    }

    private void handleGet(HttpExchange exchange) {
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            try {
                List<Campanha> campanhas = Campanhas.obter();
                Gson gson = new Gson();
                String response = gson.toJson(campanhas);

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
            catch (Exception e) {
                System.err.println("Erro ao processar JSON: " + e.getMessage());
            }
        }
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            InputStream requestBody = exchange.getRequestBody();
            String json = Utils.convertStreamToString(requestBody);

            try {
                System.out.println("Recebendo JSON:");
                System.out.println(json);

                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

                int idHemocentro = jsonObject.get("idHemocentro").getAsInt();
                String dataInicioStr = jsonObject.get("dataInicio").getAsString();
                String dataFimStr = jsonObject.get("dataFim").getAsString();
                String horarioPrimeiraColeta = jsonObject.get("horarioPrimeiraColeta").getAsString();
                String horarioUltimaColeta = jsonObject.get("horarioUltimaColeta").getAsString();
                int tempoColeta = jsonObject.get("tempoColeta").getAsInt();
                int quantidadeAtendimentos = jsonObject.get("quantidadeAtendimentos").getAsInt();
                String descricaoIncentivo = jsonObject.get("descricaoIncentivo").getAsString();

                Campanha campanha = new Campanha(0, idHemocentro,
                        dataInicioStr, dataFimStr,
                        horarioPrimeiraColeta, horarioUltimaColeta,
                        tempoColeta, quantidadeAtendimentos,
                        descricaoIncentivo, false);

                JsonArray tiposSanguineos = jsonObject.get("tipoSanguineo").getAsJsonArray();
                List<Integer> ts = new ArrayList<>();
                for (JsonElement tipoSanguineo : tiposSanguineos)
                    ts.add(tipoSanguineo.getAsInt());

                Campanhas.incluir(campanha, ts);

                String response = "Cadastro enviado com sucesso!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            } catch (ParseException e) {
                System.err.println("Erro ao parsear data: " + e.getMessage());
                String response = "Erro no formato de data!";
                exchange.sendResponseHeaders(400, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                System.err.println("Erro ao processar JSON: " + e.getMessage());
                String response = "Erro no processamento da requisição!";
                exchange.sendResponseHeaders(500, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
