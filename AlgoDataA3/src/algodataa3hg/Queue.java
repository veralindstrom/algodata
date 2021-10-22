/**
 * README.
 * Interface of a Queue.
 */
package algodataa3hg;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public interface Queue<Datatype> extends Iterable<Datatype> {
    void enqueue(Datatype item);
    void dequeue();
    boolean isEmpty();
    void print();
}
