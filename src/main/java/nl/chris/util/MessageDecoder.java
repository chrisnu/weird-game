package nl.chris.util;

import com.google.gson.Gson;
import nl.chris.communication.*;
import nl.chris.communication.client.MessageLogin;
import nl.chris.communication.client.MessageShot;
import nl.chris.communication.server.MessageEnd;
import nl.chris.communication.server.MessageHit;
import nl.chris.communication.server.MessageQueued;
import nl.chris.communication.server.MessageStart;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decodes incoming message from client
 */
public class MessageDecoder implements Decoder.Text<Message> {

    private static Gson gson = new Gson();

    @Override
    public Message decode(String s) {
        Message message = gson.fromJson(s, Message.class);

        switch (message.getType()) {
            case LOGIN:
                return gson.fromJson(s, MessageLogin.class);
            case START:
                return gson.fromJson(s, MessageStart.class);
            case QUEUED:
                return gson.fromJson(s, MessageQueued.class);
            case END:
                return gson.fromJson(s, MessageEnd.class);
            case SHOT:
                return gson.fromJson(s, MessageShot.class);
            case HIT:
                return gson.fromJson(s, MessageHit.class);
        }

        throw new IllegalArgumentException(String.format("Message type is not defined. Sent message: %s", s));
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
