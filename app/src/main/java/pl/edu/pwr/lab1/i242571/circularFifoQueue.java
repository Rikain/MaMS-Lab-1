package pl.edu.pwr.lab1.i242571;
import java.util.LinkedList;

public class circularFifoQueue<E> extends LinkedList<E>{
    private int limit;

    public circularFifoQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > limit) {
            super.remove();
        }
        return true;
    }
}
