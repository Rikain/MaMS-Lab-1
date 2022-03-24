package pl.edu.pwr.lab1.i242571;
import java.util.LinkedList;

public class CircularFifoQueue<E> extends LinkedList<E>{
    private int limit;

    public CircularFifoQueue(int limit) {
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
