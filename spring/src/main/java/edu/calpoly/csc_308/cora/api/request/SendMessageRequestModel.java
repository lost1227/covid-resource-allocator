package edu.calpoly.csc_308.cora.api.request;

public class SendMessageRequestModel implements RequestModel {
    public Long receiverId;
    public String messageText;

    @Override
    public String toString() {
        return String.format("SendMessageRequestModel(receiverId=%d,messageText=\"%s\")", receiverId, messageText);
    }
}
