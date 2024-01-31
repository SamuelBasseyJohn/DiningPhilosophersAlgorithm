import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ChopStick[] chopSticks = createChopSticks();

        ChopStick first_chopstick = chopSticks[0];
        ChopStick second_chopStick = chopSticks[1];
        ChopStick third_chopstick = chopSticks[2];
        ChopStick fourth_chopstick = chopSticks[3];
        ChopStick last_chopstick = chopSticks[4];

        ArrayList<Philosopher> philosophers = new ArrayList<>();

        philosophers.add(new Philosopher(1, first_chopstick, last_chopstick));
        philosophers.add(new Philosopher(2, second_chopStick, first_chopstick));
        philosophers.add(new Philosopher(3, third_chopstick, second_chopStick));
        philosophers.add(new Philosopher(4, fourth_chopstick, third_chopstick));
        philosophers.add(new Philosopher(5, last_chopstick, fourth_chopstick));

//        philosophers.add(new Philosopher(1, second_chopStick, first_chopstick));
//        philosophers.add(new Philosopher(2, third_chopstick, second_chopStick));
//        philosophers.add(new Philosopher(3, fourth_chopstick, third_chopstick));
//        philosophers.add(new Philosopher(4, last_chopstick, fourth_chopstick));
//        philosophers.add(new Philosopher(5, first_chopstick, last_chopstick));


        philosophers.forEach((philosopher) -> {
            createNewThread(philosopher);
        });
    }

    static void createNewThread(Philosopher philosopher) {
        new Thread(philosopher).start();
    }

    static ChopStick[] createChopSticks() {
        ChopStick[] chopSticks = new ChopStick[5];

        for (int i = 0; i < 5; i++) {
            chopSticks[i] = new ChopStick();
        }

        return chopSticks;
    }
}


