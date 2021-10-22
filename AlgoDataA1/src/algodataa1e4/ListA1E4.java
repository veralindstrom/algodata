/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1e4;

/**
 *
 * @author Vera
 * @param <Datatype>
 */
public interface ListA1E4<Datatype> extends Iterable<Datatype> {
    void enqueueToFront(Datatype item);
    void enqueueToBack(Datatype item);
    void dequeueFromFront();
    void dequeueFromBack();
    boolean isEmpty();
    void print();
    
}
