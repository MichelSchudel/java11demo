package nl.craftsmen.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

import static java.net.http.WebSocket.NORMAL_CLOSURE;

public class WebSocketDemo {

    public static void main(String[] args) throws Exception {
            WebSocketClient client = new WebSocketClient();
            CompletableFuture<WebSocket> server_cf = HttpClient.newHttpClient().newWebSocketBuilder().buildAsync(URI.create("ws://localhost:4567/echo"), client);
            WebSocket server = server_cf.join();

            server.sendPing(ByteBuffer.wrap("Ping: Client <--- Server".getBytes(Charset.forName("UTF-16"))));
            server.sendPing(ByteBuffer.wrap("Pong: Client <--- Server".getBytes(Charset.forName("UTF-16"))));
            server.sendText("Hello!", true);
            server.sendClose(NORMAL_CLOSURE, "Goodbye!");
            Thread.sleep(5000);
    }


}