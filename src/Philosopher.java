public class Philosopher extends Thread{
    final int id;
    final Fork leftFork;
    final Fork rightFork;

    Philosopher(int id, Fork leftFork, Fork rightFork){
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;

    }

    @Override
    public void run() {

    }

    public void think() throws InterruptedException{
        System.out.println("Professor" + id + " is thinking..");
        Thread.sleep((int)(Math.random() * 1000));
    }
    public void eat() throws InterruptedException{
        System.out.println("Professor" + id + " is eating..");
        Thread.sleep((int)(Math.random() * 1000));
    }
}
