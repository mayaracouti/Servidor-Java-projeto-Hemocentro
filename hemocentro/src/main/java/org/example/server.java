package org.example;

// comando desse projeto javac ->  -cp "Servidor-Java-projeto-Hemocentro-/lib/mysql5.jar" src/criar_campanha.java
//compile"-classpath" ".;mysql5.jar" -> no compile
// java "-classpath" ".;mysql5.jar" "%e" -> no execute
//javac -cp "Servidor-Java-projeto-Hemocentro-/lib/gson-2.8.2.jar" src/server.java


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class server{

    public static void main(String[] args) throws IOException {

        int serverPort = 8000; // definir numero de porta
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0); // cria um servidor HTTP o InetSocktAddress cria o endpoint que é o destino final de uma solicitação enviada para um servidor

        server.createContext("/Cadastro", new Cadastro()); //Este trecho de código em Java cria um contexto no servidor para lidar com requisições feitas a um endpoint específico, neste caso, "/receive-json", e associa um manipulador de JSON a esse contexto.
        server.setExecutor(null); // Usar executor padrão


        server.start();
        System.out.println("O servidor esta escutando na porta" + serverPort);

    }


    static class Cadastro implements HttpHandler {
        @Override
        //
        public void handle(HttpExchange exchange) throws IOException {
            if (exchange.getRequestMethod().equalsIgnoreCase("POST")) { //Esse trecho de código em Java está verificando se o método da requisição HTTP recebida é um POST.
                InputStream requestBody = exchange.getRequestBody(); //Ao chamar exchange.getRequestBody(), você está obtendo um InputStream que permite a leitura dos dados contidos no corpo da requisição
                String json = convertStreamToString(requestBody);

                System.out.println("recebendo JSON:");
                System.out.println(json);

                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();//l45 de inputString converte em string e depois em json

                String tipoSanguineo = jsonObject.get("tipoSanguineo").getAsString();
                String regiaoColeta = jsonObject.get("regiaoColeta").getAsString();
                String dataInicioStr = jsonObject.get("dataInicio").getAsString();
                String dataFimStr = jsonObject.get("dataFim").getAsString();

                String horarioPrimeiraColeta = jsonObject.get("horarioPrimeiraColeta").getAsString();
                String horarioUltimaColeta = jsonObject.get("horarioUltimaColeta").getAsString();
                String tempoColeta = jsonObject.get("tempoColeta").getAsString();
                String quantidadeAtendimentos = jsonObject.get("quantidadeAtendimentos").getAsString();
                String incentivoCheckbox = jsonObject.get("incentivoCheckbox").getAsString();
                String descricaoIncentivo = jsonObject.get("descricaoIncentivo").getAsString();

                // Converter String para LocalDate
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date dataInicio = null;
                Date dataFim = null;
                try {
                    dataInicio = (Date) sdf.parse(dataInicioStr);
                    dataFim = (Date) sdf.parse(dataFimStr);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                int quantidadeAtendimentos = jsonObject.get("quantidadeAtendimentos").getAsInt();
                boolean incentivoCheckbox = jsonObject.get("incentivoCheckbox").getAsBoolean();


                System.out.println("tipoSanguineo: " + tipoSanguineo);//verifica dados anteriores
                enviaCadastroDeCampanha.inserirDados ( tipoSanguineo, regiaoColeta,dataInicio,dataFim,horarioPrimeiraColeta,horarioUltimaColeta,tempoColeta,quantidadeAtendimentos,incentivoCheckbox,descricaoIncentivo);

                // Send a response (optional)
                String response = "Cadastro enviado com sucesso";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    private static String convertStreamToString(InputStream is) {
        try (java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A")) {
            return s.hasNext() ? s.next() : "";
        }
    }


}
