package nl.craftsmen.demo;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static java.net.http.WebSocket.NORMAL_CLOSURE;

public class WebSocketDemo {

    public static void main(String[] args) {
        try {
            var server = new WebSocketServer().getWebSocket();
            server.sendPing(ByteBuffer.wrap("Ping: Client <--- Server".getBytes(Charset.forName("UTF-16"))));
            server.sendPing(ByteBuffer.wrap("Pong: Client <--- Server".getBytes(Charset.forName("UTF-16"))));
            server.sendText("Hello!", false);
            server.sendClose(NORMAL_CLOSURE, "Goodbye!");
        } catch (Exception e) {
            System.out.println("Failure:" + e.getClass().toString().replace("class", "") + " was thrown.\nMessage: " + e.getMessage());

            if (e.getMessage().contains("WebSocketHandshakeException")) {
                var ex = ((java.net.http.WebSocketHandshakeException) e.getCause()).getResponse();
                System.out.println("Body:\t" + ex.body());
                System.out.println("Headers:");
                ex.headers().map().forEach((k, v) -> System.out.println("\t" + k + ":  " + v));
                System.out.println("HTTP request:  " + ex.request());
                System.out.println("HTTP version:  " + ex.version());
                System.out.println("Previous Reponse?:  " + ex.previousResponse());
            }
        }
    }

    ;
};