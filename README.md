Assignment 3 — Bridge Pattern



Student Information



Name: Nazym

Group: SE-2528

Topic: B — Notifications

Pattern: Bridge Pattern

Language: Java

JDK: 17



Repository



GitHub repository: https://github.com/knazymb/Assignment3



Base commit:



324a9a0e234f4aef1a1c1a6af131bc847d884320



Extension commit:



3e37816e24e42b04e8826a58afb49ae4a34ac93c



1. Pattern Structure



This project demonstrates the Bridge Design Pattern using a notification system.



The pattern separates:



Abstraction hierarchy —      different types of notifications.

Implementation hierarchy — different notification delivery channels.



Abstraction hierarchy



Notification — abstract base class

Reminder — normal reminder

UrgentAlert — urgent notification



Implementor hierarchy



Channel — implementation interface

EmailChannel — sends notifications by email

SmsChannel — sends notifications by SMS

PushChannel — sends notifications by push notification



The two hierarchies are connected through composition.



2. Bridge Field



The abstraction stores an interface-typed reference:



protected Channel channel;



The reference is supplied through the constructor:



public Notification(String id, String message, Channel channel) {



this.id = id;



this.message = message;



this.channel = channel;



}



The abstraction does not create concrete channel objects and does not use type checks.



3. execute()



Each notification has an execute() method.



Reminder sends the original message:



@Override



public String execute() {



return channel.send(message);



}



UrgentAlert adds the URGENT: marker before sending:



@Override



public String execute() {



return channel.send("URGENT: " + message);



}



The actual delivery operation is delegated to the Channel interface.



4. setImplementation()



The implementation can be changed at runtime:



public void setImplementation(Channel channel) {



this.channel = channel;



}



For example, the same Reminder object can first use EmailChannel and then SmsChannel.



5. T5 Same-Object Proof



T5 verifies that the same notification object can switch its implementation.



The test checks:



object identity using ==;

that the ID remains unchanged;

that the message remains unchanged;

that the output changes after switching the      channel.



The result is:



T5 PASS | sameObject=true | stateUnchanged=true | before=EMAIL: [Envelope] Project deadline is tomorrow | after=SMS: Project deadline is tomorrow



This demonstrates that the abstraction object remains the same while its implementation can be changed.



6. Extension: PushChannel



The base implementation initially contained:



Channel

EmailChannel

SmsChannel

Notification

Reminder

UrgentAlert



After the base commit, the project was extended with PushChannel.



PushChannel implements the existing Channel interface:



public class PushChannel implements Channel {



@Override



public String send(String message) {



return "PUSH: [Notification] " + message;



}



}



Main.java was also updated to add T6 and T7.



The existing abstraction and implementation classes were not changed during the extension.



7. Tests



The demo contains seven tests:



T1 — Reminder + Email

T2 — Reminder + SMS

T3 — UrgentAlert + Email

T4 — UrgentAlert + SMS

T5 — Same-object implementation      switch

T6 — Reminder + Push

T7 — UrgentAlert + Push



The final result is:



SUMMARY: 7/7 PASS



8. How to Compile



From the project root:



javac --release 17 -encoding UTF-8 -d out "@sources.txt"



9. How to Run



Run the demo with:



java -cp out Main --demo



The program does not require interactive input.



10. Expected Result



The final demo should show:



T1 PASS | Reminder + EmailChannel



T2 PASS | Reminder + SmsChannel



T3 PASS | UrgentAlert + EmailChannel



T4 PASS | UrgentAlert + SmsChannel



T5 PASS | sameObject=true | stateUnchanged=true



T6 PASS | Reminder + PushChannel



T7 PASS | UrgentAlert + PushChannel



SUMMARY: 7/7 PASS



11. Required Files



The submission contains:



src/



sources.txt



README.md



report.pdf



demo-output.txt



extension.diff



The project demonstrates the Bridge Pattern by separating notification types from notification delivery channels. This allows either hierarchy to be extended independently.