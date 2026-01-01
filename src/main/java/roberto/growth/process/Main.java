package roberto.growth.process;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        while (true) {
            String currentTime = LocalDateTime.now().format(formatter);
            System.out.println("changxian --- " + currentTime);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }
}