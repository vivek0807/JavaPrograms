//package $Expertise.DesignPatternCode;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.LinkedBlockingQueue;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//class Message{
//    /**
//     * A message will only contain the string content
//     */
//    String content;
//}
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//class Topic{
//    /**
//     * A Topic has its name and list of subscribers that are subscribed to it
//     * It will also have the subscribe method within itself where we add a subscriber to the list of subscribers
//     */
//    String name;
//
//    public Topic(String name) {
//        this.name = name;
//    }
//
//    CopyOnWriteArrayList<SubscriberWorker> listOfSubscriber= new CopyOnWriteArrayList<>();
//
//    void subscribe(SubscriberWorker subscriber){
//        listOfSubscriber.add(subscriber);
//    }
//
//}
//
//interface Subscriber{
//    void consume(Message message);
//    String getId();
//}
//
//
///**
// * This class is responsible for managing all the subscriber's retry logic and implements the technique
// * using which subscribers will consume a message and it will also have a method that support to send
// * since each topic will have its own subscriberworker and subscriber method, so we dont have to define topic explicitely
// */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//class SubscriberWorker implements Runnable{
//    static  int MAX_RETRIES=3;
//    Subscriber subscriber;
//    BlockingQueue<Message> queue= new LinkedBlockingQueue<>();
//    Thread thread;
//
//    void addMessage(Message message){
//        queue.offer(message);
//    }
//
//    SubscriberWorker(Subscriber subscriber){
//        this.subscriber=subscriber;
//        this.thread= new Thread(this);
//        this.thread.start();
//
//    }
//
//    @Override
//    public void run(){
//        while (true){
//            try {
//                Message message= queue.take();
//                int attempts=0;
//                boolean isfetchSuccessFul= false;
//                while (attempts<MAX_RETRIES && !isfetchSuccessFul){
//                    attempts++;
//                    Thread.sleep(100);
//                }
//                subscriber.consume(message);
//            }
//            catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    String getId(){
//        return subscriber.getId();
//    }
//
//}
//
///**
// * This class just focuses on implementing what to do after message has been consumed
// * The how to consume part has been kept separately
// */
//@Data
//class SimpleSubscriber implements Subscriber{
//  public String id;
//
//    public SimpleSubscriber(String id) {
//        this.id = id;
//    }
//
//    @Override
//  public void consume(Message message){
//      System.out.println(message.toString());
//  }
//
//  public String getId(){
//      return id;
//  }
//}
//
///**
// * This class handles the subscription to a topic, creation of a new topic and publish of a new message
// */
//class Broker{
//   ConcurrentHashMap<String,Topic> topicMap= new ConcurrentHashMap<>();
//
//   public  void createTopic(String topic){
//       Topic topic1= new Topic(topic);
//
//       topicMap.putIfAbsent(topic,topic1);
//
//   }
//   void subscribe(String topicName, Subscriber subscriber){
//
//
//       if (topicMap.get(topicName)!=null){
//           SubscriberWorker subscriberWorker = new SubscriberWorker(subscriber);
//           topicMap.get(topicName).subscribe(subscriberWorker);
//       }
//   }
//
//   void publish(String topicName, Message message){
//       Topic topic= topicMap.get(topicName);
//       if (topic!=null){
//           for (SubscriberWorker subscriberWorker: topic.getListOfSubscriber()){
//               subscriberWorker.addMessage(message);
//           }
//       }
//   }
//}
//public class PUBSUBLLD {
//    public static void main(String[] args) throws InterruptedException {
//        Broker broker = new Broker();
//        broker.createTopic("updates");
//        Subscriber s1= new SimpleSubscriber("s1");
//        Subscriber s2= new SimpleSubscriber("s2");
//
//        broker.subscribe("updates",s1);
//        broker.subscribe("updates",s2);
//        for (int i = 0; i < 5; i++) {
//            broker.publish("updates",new Message("Message "+i));
//        }
//
//        Thread.sleep(3000);
//    }
//}
