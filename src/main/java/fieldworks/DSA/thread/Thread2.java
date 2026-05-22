package fieldworks.DSA.thread;

//1. Thread Create Karna (Extending Thread Class)
//class MyThread extends Thread {
//
//	@Override
//	public void run() {
//
//		for (int i = 1; i <= 5; i++) {
//			System.out.println("Child Thread : " + i);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}
//}

//2. Runnable Interface Se Thread Banana
//Interview Question
//Why Runnable is preferred over Thread class?
//Answer
//Java multiple inheritance support nahi karta.
//Agar class already kisi class ko extend kar rahi ho to Runnable better h.

//class MyRunnable implements Runnable {
//	@Override
//	public void run() {
//		for (int i = 1; i <= 5; i++) {
//			System.out.println("Runnable Thread : " + i);
//		}
//	}
//}

////3. Thread Join()
//join() kya karta h?
//Answer
//Ek thread dusre thread ke complete hone ka wait karta h.

//class MyThread extends Thread {
//
//	@Override
//	public void run() {
//
//		for (int i = 1; i <= 5; i++) {
//
//			System.out.println("Child Thread");
//
//			try {
//				Thread.sleep(500);
//			} catch (Exception e) {
//
//			}
//		}
//	}
//}

//4. Synchronization Problem
//Interview Question
//Synchronization kyun use karte h?
//Answer
//Multiple threads same resource access kare to data inconsistency aa sakti h.

////4.1: Without Synchronization Problem
//class Counter {
//	int count = 0;
//
//	public void increment() {
//		count++;
//	}
//}

////4.2: Synchronization Solution
//class Counter {
//
//	int count = 0;
//
//	public synchronized void increment() {
//		count++;
//	}
//}

//5. Producer Consumer Problem
//Most Asked Interview Question

////
//Producer ---> item banaya ---> notify()
//
//Consumer wait se utha
//
//Consumer ---> item use kiya ---> notify()
//
//Producer wait se utha
////

//class Box {
//
//    int item;
//    boolean available = false;
//
//    // Producer
//    synchronized void produce(int value) {
//
//        if (available) {
//            try {
//                wait();
//            } catch (Exception e) {
//            }
//        }
//
//        item = value;
//        available = true;
//
//        System.out.println("Produced : " + item);
//
//        notify();
//    }
//
//    // Consumer
//    synchronized void consume() {
//
//        if (!available) {
//            try {
//                wait();
//            } catch (Exception e) {
//            }
//        }
//
//        System.out.println("Consumed : " + item);
//
//        available = false;
//
//        notify();
//    }
//}

public class Thread2 {

	public static void main(String[] args) throws Exception {
//		1.
//		MyThread t1 = new MyThread();
//		t1.start();
//		for (int i = 1; i <= 5; i++) {
//			System.out.println("Main Thread : " + i);
//
//		}

////		2.
//		MyRunnable obj = new MyRunnable();
//		Thread t1 = new Thread(obj);
//		t1.start();

////		3.
//		MyThread t1 = new MyThread();
//        t1.start();
//        t1.join();
//        System.out.println("Main Thread Finished");

////		4.
//		Counter c = new Counter();
//
//		Thread t1 = new Thread(() -> {
//			for (int i = 1; i <= 1000; i++) {
//				c.increment();
//			}
//		});
//
//		Thread t2 = new Thread(() -> {
//			for (int i = 1; i <= 1000; i++) {
//				c.increment();
//			}
//		});
//
//		t1.start();
//		t2.start();
//
//		t1.join();
//		t2.join();
//
//		System.out.println(c.count);

////		5.
//		Box b = new Box();
//
//        // Producer Thread
//        Thread producer = new Thread(() -> {
//
//            for (int i = 1; i <= 5; i++) {
//
//                b.produce(i);
//
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                }
//            }
//        });
//
//        // Consumer Thread
//        Thread consumer = new Thread(() -> {
//
//            for (int i = 1; i <= 5; i++) {
//
//                b.consume();
//
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                }
//            }
//        });
//
//        producer.start();
//        consumer.start();
	}
}
