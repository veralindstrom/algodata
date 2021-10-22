/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estellesTestFolder;

/**
 *
 * @author Vera
 */
public interface Queue<Datatype> extends Iterable<Datatype> {
    void enqueue1(Datatype item);
    Datatype dequeue();
    void print();
    boolean isEmpty();
}
