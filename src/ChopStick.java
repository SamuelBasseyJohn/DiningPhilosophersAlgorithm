import java.util.concurrent.locks.ReentrantLock;

public class ChopStick{

    final private ReentrantLock lock = new ReentrantLock();

    public boolean pickUp() {
        return lock.tryLock();
    }

    public void drop(){
        lock.unlock();
    }

}