package fieldworks.DSA.thread;

class Task1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Task 1 is starting...");
            Thread.sleep(2000); // Simulate some work (e.g., 2 seconds)
            System.out.println("Task 1 is completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Task 2 is starting...");
            Thread.sleep(1000); // Simulate some work (e.g., 1 second)
            System.out.println("Task 2 is completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task3 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Task 3 is starting...");
            Thread.sleep(1500); // Simulate some work (e.g., 1.5 seconds)
            System.out.println("Task 3 is completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Thread1 {

    public static void main(String[] args) {
        // Create threads
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();

        // Start all threads
        task1.start();
        task2.start();
        task3.start();

        // Main thread continues to run concurrently
        System.out.println("Main thread is running while tasks are processing.");
    }

}
