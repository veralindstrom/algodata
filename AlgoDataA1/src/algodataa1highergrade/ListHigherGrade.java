/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa1highergrade;

/**
 *
 * @author Vera
 */
public interface ListHigherGrade<Datatype> extends Iterable<Datatype> {
    void enqueue(Datatype item);
    void checkForMatches(Datatype a, Datatype b);
    boolean isEmpty();
    void print();
}