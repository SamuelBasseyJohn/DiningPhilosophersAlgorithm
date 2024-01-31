public class Philosopher implements Runnable {
    final String name;
    final int id;
    final ChopStick leftChopStick;
    final ChopStick rightChopStick;

    Philosopher(int id, ChopStick leftFork, ChopStick rightChopStick){
        this.name = "Philosopher_" + id;
        this.id = id;
        this.rightChopStick = rightChopStick;
        this.leftChopStick = leftFork;

    }

    public void think() throws InterruptedException{
        System.out.println("Philosopher" + id + " is thinking..");
        Thread.sleep((int) (Math.random() * 1000));
    }
    public void eat() throws InterruptedException{
        System.out.println("Philosopher" + id + " is eating..");
        Thread.sleep((int) (Math.random() * 1000));
    }

    @Override
    public synchronized void  run() {
        try {
            while (true) {
                think();
                if (this.leftChopStick.pickUp()) {
                    System.out.println(this.name + " picked up the left chop stick.");

                    if(this.rightChopStick.pickUp()){
                        System.out.println(this.name + " picked up the right chop stick.");

                        this.eat();
                        this.leftChopStick.drop();
                        this.rightChopStick.drop();
                    }else{
                        System.out.println(this.name + " cannot pick up the right chop stick.");
                        System.out.println(this.name + "waiting for the right chop stick");
                    }
                }else {
                    System.out.println(this.name + " cannot pick up the left chop stick.");
                    System.out.println(this.name + " waiting for the left chop stick");

                    this.leftChopStick.drop();

                    this.think();
                }
            }

        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}