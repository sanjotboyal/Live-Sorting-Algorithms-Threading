/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortings;

import javafx.scene.layout.Pane;




/**
 *
 * @author sanjot
 */
public class SelectionSort implements SortingsStrategy {
    
    public SelectionSort(){}
    
    //Override Sort within interface 
    @Override
    public void Sort(int[] array) {
        
        for(int i = 0; i < array.length-1; i++){
            //Find min element
            int minimum = i;
            for(int x = i+1; x<array.length;x++){
                if(array[x] < array[minimum]){
                    minimum = x;
                }
            }
                //swap found minimum with first element
            int temporary = array[minimum];
            array[minimum] = array[i];
            array[i] = temporary;       
            
            //Sleep the thread
            try{
                Thread.sleep(100);
            }catch(Exception e){
            }
        }
    }
    
    
}
