package org.vidamais;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import org.vidamais.HTTPHandler.HTTPCampanhaHandler;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Diretório de trabalho atual: " + System.getProperty("user.dir"));

        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        server.createContext("/campanha", new HTTPCampanhaHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("O servidor está escutando na porta: " + serverPort);
    }
}
