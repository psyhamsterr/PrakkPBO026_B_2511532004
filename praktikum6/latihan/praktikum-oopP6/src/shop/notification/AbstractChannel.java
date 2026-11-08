package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private int sentCount = 0;
    private String lastDelivered = "";

    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (message == null || message.isBlank()) {
            return false;
        }
        String recipient = resolveRecipient(contact);
        if (recipient == null || recipient.isBlank()) {
            return false;
        }
        String preparedMessage = prepare(contact, message);
        boolean success = deliver(recipient, preparedMessage);
        if (success) {
            sentCount++;
            lastDelivered = preparedMessage;
        }
        return success;
    }

    protected abstract String resolveRecipient(CustomerContact contact);

    protected String prepare(CustomerContact contact, String message) {
        return message;
    }

    protected abstract boolean deliver(String recipient, String preparedMessage);

    public int getSentCount() {
        return sentCount;
    }

    public String getLastDelivered() {
        return lastDelivered;
    }
}