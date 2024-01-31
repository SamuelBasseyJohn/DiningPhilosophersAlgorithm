import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophersWithLocks {

    private static final int NUM_PHILOSOPHERS = 5;
    private final Lock[] forks = new ReentrantLock[NUM_PHILOSOPHERS];
    private final Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

    public DiningPhilosophersWithLocks() {
        // Initialize the locks for the forks
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }

        // Initialize and start philosopher threads
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS]);
            new Thread(philosophers[i], "Philosopher " + i).start();
        }
    }

    public static void main(String[] args) {
        new DiningPhilosophersWithLocks();
    }

    // Philosopher class representing each philosopher
    static class Philosopher implements Runnable {
        private final int id;
        private final Lock leftFork;
        private final Lock rightFork;

        public Philosopher(int id, Lock leftFork, Lock rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    think();
                    if (pickUpForks()) {
                        eat();
                        putDownForks();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        private void think() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " is thinking.");
            Thread.sleep(((int) (Math.random() * 1000)));
        }

        private void eat() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " is eating.");
            Thread.sleep(((int) (Math.random() * 1000)));
        }

        private boolean pickUpForks() {
            if (leftFork.tryLock()) {
                if (rightFork.tryLock()) {
                    return true;
                }
                leftFork.unlock(); // Release left fork if right fork is not available
            }
            return false;
        }

        private void putDownForks() {
            leftFork.unlock();
            rightFork.unlock();
        }
    }
}