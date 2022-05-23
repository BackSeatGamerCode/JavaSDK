# JavaSDK
The generic SDK for games written in Java (we are looking at you, Minecraft)

## Setup
This SDK requires [BackSeatGamer Reverse Proxy](https://github.com/BackSeatGamerCode/ReverseProxy) to be running in `TCP/IP Broadcast` Mode using `JSON` format. By default, port 29175 will be used. In the future, we plan on adding a way for the user to change this if necessary.

This SDK was designed to be as generic as possible, for any game with modding in Java, however, more specific SDKs for various games may be released soon. When they are complete, they will be listed here. Feel free to fork the repository to create a more specific Java SDK.

## Usage
Due to the generic nature of this SDK, instructions on how to install mods, set up the working environment, and resources for more information can not be included.
All you need is the `com.backseatgamer.javasdk` Java package. Be sure to copy everything, and that the destination package is `com.backseatgamer.javasdk` so there are no compatibility issues. 
While copying a library source code is by no means the best way to add a Java dependency, this is the only supported method at this time.

### Setup
Once everything is set up, development can begin! To start, simply create a new class which extends `BSGJavaSDK`. It will require `onRedemptionReceived` and `getEvent` to be implemented like so:
```java
public class MyBSGClass extends BSGJavaSDK {
    @Override
    protected void onRedemptionReceived(Redemption redemption, Object... args) {
        System.out.println(redemption.toMessage());
    }

    @Override
    protected BaseEvent getEvent(Redemption redemption) {
        return new HelloWorldEvent();
    }
}
```

`onRedemptionReceived` is called whenever a person redeems a reward. You can simply omit the print statement within the method for no action to be taken. This method is called BEFORE the event is executed. The second parameter (`Object... args`) will be explained soon.
The `toMessage` method of the reward is called, which returns a string in the format `{guest} has redeemed the reward {name}`. This method is called in the main thread of the application.

The next method `getEvent` is responsible for converting a redemption to an event. This would generally be implemented via case/switch statement. Nothing too fancy here.
This section uses the Gang of Four's Command pattern. The method should return the object, and so the signature of the constructor can be whatever you want it to be. Feel free to call as many methods of the object as you wish.
All you need to do is return the object when it is ready. This method is called in the main thread of the application.

### Implementation
To run the mod functionality, simply instantiate your class which extends the `BSGJavaSDK` class. On each game loop/tick, simply call the `poll` method of the object. Easy. 
The `poll` method does not perform any networking or other laborious activities, so it is safe to call every game loop without performance taking a hit.

The `poll` method can take a variable number of arguments. These arguments are passed to all methods which have a parameter `Object... args` (`onRedemptionReceived` in your mod class which extends `BSGJavaSDK`, and `execute` in the custom `Event` class).

### Custom Events
To create a custom event, simply create a class which extends `BaseEvent`. This abstract class requires the implementation of the `execute` method, which takes `Object... args` as its only argument (see above for explanation).

Feel free to add a constructor and other supporting methods. You will be responsible for instantiating the object in the `getEvent` method of your class which extends the `BSGJavaSDK` class.

The actual execution of the event should be implemented in the `execute` method.

The following is the source code of the built-in `HelloWorldEvent`, which prints `Hello, World!` to the console when executed:
```java
package com.backseatgamer.javasdk.events;

public class HelloWorldEvent extends BaseEvent{
    @Override
    public void execute(Object... args) {
        System.out.println("Hello, World!");
    }
}
```

### The `Redemption` Object
The `Redemption` Object has three getters, `getCommand()` which returns the command of the reward, `getName()` returns the display name of the reward, and `getGuest()` returns the name of the guest who redeemed the reward.

The `Redemption` Object also has a method called `toMessage` which returns a string in the format `{guest} has redeemed the reward {name}`. 

## Issues/Feedback
If you encounter any problems, or have suggestions for future updates, feel free to leave them over in the [Issue Tracker](https://github.com/BackSeatGamerCode/JavaSDK/issues). Alternatively, if you have questions or want to discuss something with your fellow Java modders, then check out our [Discussions](https://github.com/BackSeatGamerCode/JavaSDK/discussions). Thank you for using Java modding SDK, and good luck with your mod!