/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e6;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public interface QueueA1E6<Datatype> extends Iterable<Datatype> {
    void operation(Datatype item);
    void dequeueKthElement(int k);
    boolean isEmpty();
    void print();
    
    
}
