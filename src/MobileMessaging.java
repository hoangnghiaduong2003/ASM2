import java.util.Scanner;
public class MobileMessaging {

    public static class TextMessage {
        private String content;

        public TextMessage(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    public static class MobileServiceProvider {
        public boolean isMessageValid(TextMessage message) {
            return message.getContent().length() <= 250; // Giới hạn đến 250 ký tự
        }

        public void sendMessageToMobileNetwork(TextMessage message) {
            System.out.println("Message sent to mobile network: " + message.getContent());
        }
    }

    public static class MobileNetwork {
        public void sendMessageToRecipient(TextMessage message) {
            System.out.println("Message sent to recipient: " + message.getContent());
        }
    }

    public static void main(String[] args) {
        // Sử dụng Scanner để nhập từ bàn phím
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập nội dung tin nhắn (tối đa 250 ký tự): ");
        String userInput = scanner.nextLine();

        // Kiểm tra nếu người dùng không nhập gì hoặc nhập quá 250 ký tự
        if (userInput.isEmpty() || userInput.length() > 250) {
            System.out.println("Nội dung tin nhắn không hợp lệ.");
            return;
        }

        TextMessage userMessage = new TextMessage(userInput);

        MobileServiceProvider senderServiceProvider = new MobileServiceProvider();

        if (senderServiceProvider.isMessageValid(userMessage)) {
            senderServiceProvider.sendMessageToMobileNetwork(userMessage);

            MobileNetwork mobileNetwork = new MobileNetwork();
            MobileServiceProvider recipientServiceProvider = new MobileServiceProvider();

            if (recipientServiceProvider.isMessageValid(userMessage)) {
                mobileNetwork.sendMessageToRecipient(userMessage);
            } else {
                System.out.println("Nhà cung cấp dịch vụ của người nhận từ chối tin nhắn.");
            }
        } else {
            System.out.println("Nhà cung cấp dịch vụ của bạn từ chối tin nhắn.");
        }

        // Đóng Scanner sau khi sử dụng
        scanner.close();
    }
}
