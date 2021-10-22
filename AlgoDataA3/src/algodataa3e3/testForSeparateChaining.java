/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodataa3e3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Vera
 */
public class testForSeparateChaining {
    public static void main(String[] args) throws FileNotFoundException{  
        File file = new File("/Users/admin/NetBeansProjects/AlgoDataA3/src/algodataa3e3/output_interpret.txt");
        Scanner input = new Scanner(file); 
        
        SeparateChainingHash_A3E3<String, Integer> st = new SeparateChainingHash_A3E3<>(5);
        //141491 words
        //9944 uniqe (with toLowerCase)
        
        //read file word by word and store in array
        while (input.hasNext()) {
            String key = input.next().toLowerCase();
            
            if (key.length() < 1) continue;
            
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
            }
        }
        
        for(int i = 0; i < 5; i++){
            System.out.println("In collision #" + i + "  there are  " + st.sizeOfList(i) + " nodes");
        }
    }
}


/*
            int hashValue = (word.hashCode() & 0x7fffffff) % M;
            
            sc.putModified(hashValue, word);
            */
            
            //sc.putModified(hashValue, word);
            
            //sc.put(hashValue, word);
            //System.out.println("Hash #" + hashValue + ": " + sc.get(hashValue) + " ");
            //if (sc.contains(hashValue) ) {  //if hash-value has appeared before
                // System.out.println("Hash #" + hashValue + ": " + sc.get(hashValue) + " ");
                /*
                for (Node x = sc.getLinkedList(hashValue); x != null; x = x.next) {
                if (key.equals(x.key)) return (Value) x.val;
                }
                 */
                //System.out.println("Linked list " +  sc.getLinkedList(hashValue));
                //  sc.getLinkedList(hashValue);
                
                //if collision has already been counted
                /*
                if(collidedWords[0] != null){
                for (int i = 0; i < k; i++) {
                    if (collidedWords[i].equals(word)) {
                        flag = true;
                        break;
                    }
                }
                }*/
                //System.out.println("Array collided words: " + collidedWords[k]);
                //System.out.println("Key: " + word + " => flag: " + flag);
              
                //hashValue == ((temp[0].hashCode() & 0x7fffffff) % M) && 
                //if collision occured and collision has not already been counted
               // if(sc.contains(hashValue) && !sc.get(hashValue).equals(word)){
                   /* collidedWords[k] = sc.get(hashValue);
                    System.out.println("Array collided words: ");
                    for (int i = 0; i < k; i++) {
                     System.out.println(collidedWords[i]);
                    }
                    
                    k++;
                    collision++;
                        System.out.println("Collision #" + collision + " with hashcode #" + hashValue
                        + ". Both [" + word + "] and [" + sc.get(hashValue) + "] have the same hash code. "
                                + "Word number " + counter);
                       
                        
                        
                      /*  temp[i + 1] = word;
                        st.put(hashValue, temp); */
                    
                    /*   
                    else if (temp.length > 0){
                        System.arraycopy(copyTemp, 0, temp, 0, temp.length);
                       copyTemp[temp.length] = word;
                       bst.put(hashValue, copyTemp);
                    }*/
                    
                //}
            //}
            
            
            
            
          /*  else if (!sc.contains(hashValue)) { //if it doesn't exist, add to the BST
                //System.out.println("Word is added for the first time: " + word);
               
                sc.putModified(hashValue, word);
            } */
            
            /*
            else if (!bst.get(hashValue).equals(word)) { //if the values are different but have the same hashCode
                collision++;
                System.out.println("Collision number " + collision + " with hashcode " + hashValue
                        + ". Both [" + bst.get(hashValue) + "] and [" + word + "] have the same hash code. "
                                + "Word number " + counter);
                
            }
*/

/*
        while (scan.hasNext()) {
            String input = scan.next();
            int hashValue = input.hashCode();
            counter++;
            if (!BinarySearchST.contains(hashValue)) { //if it doesn't exist, add to the ST
                BinarySearchST.put(hashValue, input);
            } else if (!BinarySearchST.getValue(hashValue).equals(input)) { //if the values are different but have the same hashCode
                collison++;
                System.out.println("Collision number " + collison + " with hashcode " + hashValue
                        + ". Both " + BinarySearchST.getValue(hashValue) + " and " + input + " have the same hash code. Word number " + formatBigNumbers(counter));
            }
        }
*
        System.out.println();
        System.out.println("Collisions: " + collison +" / " + counter );
*/
    
        /*
        //read file word by word and store in array
        int i = 0;
        while (input.hasNext()) {
            words[i] = input.next();
            hashes[i] = (words[i].hashCode() & 0x7fffffff) % M;
            
            bst.put(hashes[i], words[i]);
            i++;
        }
        
        int count = 0;
        for (int j = 0; j < words.length; j++) {
            if(bst.get(hashes[j]) != null && !bst.get(hashes[j]).equals(words[j])){
                count++;
                System.out.println("Collision #" + count + " Occured with hashcode: " + hashes[j]
                    + " which belongs to [" + bst.get(hashes[j]) + "] and [" + words[j] + "]");
                bst.delete(hashes[j]);
            }
                
        }
*/


/*
        while (scan.hasNext()) {
            String input = scan.next();
            int hashValue = input.hashCode();
            counter++;
            if (!BinarySearchST.contains(hashValue)) { //if it doesn't exist, add to the ST
                BinarySearchST.put(hashValue, input);
            } else if (!BinarySearchST.getValue(hashValue).equals(input)) { //if the values are different but have the same hashCode
                collison++;
                System.out.println("Collision number " + collison + " with hashcode " + hashValue
                        + ". Both " + BinarySearchST.getValue(hashValue) + " and " + input + " have the same hash code. Word number " + formatBigNumbers(counter));
            }
        }
*
        System.out.println();
        System.out.println("Collisions: " + collison +" / " + counter );
*/
    
        /*
        //read file word by word and store in array
        int i = 0;
        while (input.hasNext()) {
            words[i] = input.next();
            hashes[i] = (words[i].hashCode() & 0x7fffffff) % M;
            
            bst.put(hashes[i], words[i]);
            i++;
        }
        
        int count = 0;
        for (int j = 0; j < words.length; j++) {
            if(bst.get(hashes[j]) != null && !bst.get(hashes[j]).equals(words[j])){
                count++;
                System.out.println("Collision #" + count + " Occured with hashcode: " + hashes[j]
                    + " which belongs to [" + bst.get(hashes[j]) + "] and [" + words[j] + "]");
                bst.delete(hashes[j]);
            }
                
        }
*/