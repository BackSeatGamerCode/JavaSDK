import com.backseatgamer.javasdk.BSGJavaSDK;
import com.backseatgamer.javasdk.events.BaseEvent;
import com.backseatgamer.javasdk.events.HelloWorldEvent;
import com.backseatgamer.javasdk.models.Redemption;

public class BSGTestChild extends BSGJavaSDK {
    @Override
    protected void onRedemptionReceived(Redemption redemption, Object... args) {
        System.out.println(redemption.toMessage());
    }

    @Override
    protected BaseEvent getEvent(Redemption redemption) {
        return new HelloWorldEvent();
    }
}
