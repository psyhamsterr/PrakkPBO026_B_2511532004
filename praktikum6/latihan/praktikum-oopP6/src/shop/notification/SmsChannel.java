package shop.notification;

public class SmsChannel extends AbstractChannel {
    private final int maxLength;

    public SmsChannel(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String channelName() {
        return "SMS";
    }

    @Override
    protected String resolveRecipient(CustomerContact contact) {
        String phone = contact.getPhone();
        if (phone != null && phone.startsWith("08")) {
            return phone;
        }
        return null;
    }

    @Override
    protected String prepare(CustomerContact contact, String message) {
        if (message.length() > maxLength) {
            int targetLength = maxLength - 3;
            if (targetLength < 0) targetLength = 0;
            return message.substring(0, targetLength) + "...";
        }
        return message;
    }

    @Override
    protected boolean deliver(String recipient, String preparedMessage) {
        System.out.println("[SMS to " + recipient + "] " + preparedMessage);
        return true;
    }
}