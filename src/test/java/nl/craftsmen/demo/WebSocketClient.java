package nl.craftsmen.demo;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class WebSocketClient implements WebSocket.Listener {

    @Override
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        System.out.println(data);
        webSocket.request(1);
        return new CompletableFuture().completeAsync(() -> "onText() completed.").thenAccept(System.out::println);
    }

    @Override
    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        return null;
    }

    @Override
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completeAsync(() -> "Ping completed.").thenAccept(System.out::println);
    }

    @Override
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completeAsync(() -> "Pong completed.").thenAccept(System.out::println);
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        // The status code is an integer from the range 1000 <= code <= 65535. The reason is a string which has a UTF-8 representation not longer than 123 bytes.
        System.out.println("WebSocket Listener has been closed with statusCode(" + statusCode + ").");
        System.out.println("Cause: " + reason);
        webSocket.sendClose(statusCode, reason);
        return new CompletableFuture<Void>();
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }
}
