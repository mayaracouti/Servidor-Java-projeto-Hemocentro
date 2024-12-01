package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;

public class WebSocketServer {

    public static void main(String[] args) {
        try {
            // Inicia o servidor WebSocket
            new Thread(WebSocketServer::startWebSocketServer).start();

            // Inicia o servidor HTTP
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
            httpServer.createContext("/cadastro", new Cadastro());
            httpServer.start();
            System.out.println("Servidor HTTP rodando na porta 8000...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startWebSocketServer() {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Servidor WebSocket rodando na porta 8000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream output = clientSocket.getOutputStream()
        ) {
            // Lê a requisição inicial do cliente
            String line;
            String websocketKey = null;

            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                if (line.startsWith("Sec-WebSocket-Key:")) {
                    websocketKey = line.split(": ")[1];
                }
            }

            if (websocketKey != null) {
                String acceptKey = Base64.getEncoder().encodeToString(
                        (websocketKey + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(StandardCharsets.UTF_8)
                );

                String handshakeResponse = "HTTP/1.1 101 Switching Protocols\r\n" +
                        "Upgrade: websocket\r\n" +
                        "Connection: Upgrade\r\n" +
                        "Sec-WebSocket-Accept: " + acceptKey + "\r\n\r\n";

                output.write(handshakeResponse.getBytes(StandardCharsets.UTF_8));
                output.flush();

                while (true) {
                    byte[] buffer = new byte[1024];
                    int bytesRead = clientSocket.getInputStream().read(buffer);

                    if (bytesRead == -1) {
                        break;
                    }

                    String message = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                    System.out.println("Mensagem recebida do cliente: " + message);

                    // Redireciona para a lógica de cadastro
                   // Cadastro.processarCadastro(message);

                    String response = "Cadastro recebido e processado.";
                    output.write(encodeWebSocketMessage(response));
                    output.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] encodeWebSocketMessage(String message) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] frame = new byte[2 + messageBytes.length];
        frame[0] = (byte) 0x81;
        frame[1] = (byte) messageBytes.length;
        System.arraycopy(messageBytes, 0, frame, 2, messageBytes.length);
        return frame;
    }

    // Classe Cadastro como exemplo
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dataInicioTemp = null;
                java.util.Date dataFimTemp = null;

                try {
                    dataInicioTemp = sdf.parse(dataInicioStr);
                    dataFimTemp = sdf.parse(dataFimStr);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                java.sql.Date dataInicio = new java.sql.Date(dataInicioTemp.getTime());
                java.sql.Date dataFim = new java.sql.Date(dataFimTemp.getTime());

                // Date dataInicio = null;
                //Date dataFim = null;
                //try {
                //  dataInicio = (Date) sdf.parse(dataInicioStr);
                //dataFim = (Date) sdf.parse(dataFimStr);
                //} catch (ParseException e) {
                //  throw new RuntimeException(e);
                //}

                // int quantidadeAtendimentos = jsonObject.get("quantidadeAtendimentos").getAsInt();
                //boolean incentivoCheckbox = jsonObject.get("incentivoCheckbox").getAsBoolean();


                //  System.out.println("tipoSanguineo: " + tipoSanguineo);//verifica dados anteriores
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



    static String convertStreamToString(InputStream is) {
        try (java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A")) {
            return s.hasNext() ? s.next() : "";
        }
    }

}


