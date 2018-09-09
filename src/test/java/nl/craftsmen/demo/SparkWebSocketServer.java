package nl.craftsmen.demo;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

import static spark.Spark.init;
import static spark.Spark.webSocket;

@WebSocket
public class SparkWebSocketServer {

    public static void main(String[] args) {
        webSocket("/echo", SparkWebSocketServer.class);
        init();
    }

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) throws IOException {
        user.getRemote().sendString("response for " + message);
    }
}
