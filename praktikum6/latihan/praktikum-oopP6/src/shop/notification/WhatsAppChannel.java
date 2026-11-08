package shop.notification;

public class WhatsAppChannel extends AbstractChannel {

    @Override
    public String channelName() {
        return "WA";
    }

    @Override
    protected String resolveRecipient(CustomerContact contact) {
        String phone = contact.getPhone();
        if (phone != null && phone.length() >= 10 && (phone.startsWith("08") || phone.startsWith("+62"))) {
            return phone;
        }
        return null;
    }

    @Override
    protected String prepare(CustomerContact contact, String message) {
        return "[Toko UMKM] " + message;
    }

    @Override
    protected boolean deliver(String recipient, String preparedMessage) {
        System.out.println("[WA to " + recipient + "] " + preparedMessage);
        return true;
    }
}