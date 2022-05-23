import com.backseatgamer.javasdk.BSGJavaSDK;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BSGJavaSDK javaSDK = new BSGTestChild();

        while (true){
            javaSDK.poll();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
