package nl.craftsmen.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WebSocketServer {
    private final CompletableFuture<WebSocket> server_cf;
    private final WebSocket server;
    private final WebSocket.Listener client;
    // private final String ENDPOINT = "ws://127.0.0.1:8080"; // /websocket
    private final String ENDPOINT = "ws://localhost:8080"; // /websocket

    WebSocketServer () throws InterruptedException, ExecutionException {
        client = new WebSocketClient();
        server_cf = HttpClient.newHttpClient().newWebSocketBuilder().buildAsync(URI.create(ENDPOINT), client);
        server = server_cf.join();
        // server = server_cf.get();
    }

    WebSocket getWebSocket() { return this.server; };
}