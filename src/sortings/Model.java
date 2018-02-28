/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortings;

import java.util.Random;

/**
 *
 * @author sanjot
 */

//Model Class
public class Model {
    //Array and Array Size variables
    private int intArray[];
    private int arraySize;
    
    //Random instance
    Random rng = new Random();
    
    //Constructor
    public Model(int size){
        //Set array size 
        arraySize = size;
        //intArray = new int[size];
        
        //Call Reset to set array 
        reset(size);
    }
    
    //Reset Function
    public void reset(int size){
        //Sets new size 
        setArraySize(size);
        
        //Creates an array
        for(int i=0; i<size; i++){
            intArray[i] = (i+1);
        }
        //Shuffles array to randomize
        for(int i = (size-1);i>0; i--){
            int index = rng.nextInt(i+1);
            int a = intArray[index];
            intArray[index] = intArray[i];
            intArray[i] = a;
        }
    }
    
    //resturn array
    public int[] getUnSortedList(){
        return intArray;
    }
    
    //get array size
    public int getArraySize(){
        return arraySize;
    }
    
    //set array size
    public void setArraySize(int size){
        arraySize = size;
        intArray = new int[arraySize];
    }
    
    
    
}
