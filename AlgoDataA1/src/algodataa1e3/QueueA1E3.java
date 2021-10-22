/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e3;

/**
 *
 * @author Vera
 */
public interface QueueA1E3<Datatype> extends Iterable<Datatype> {
    
    boolean isEmpty();
    void dequeue();
    void enqueue(Datatype item);
    void print();
}
