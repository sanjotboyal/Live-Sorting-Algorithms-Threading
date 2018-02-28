/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortings;

/**
 *
 * @author sanjot
 */
public class MergeSort implements SortingsStrategy{

    
    public MergeSort(){}
    
    //Override the sort and call a helper function
    @Override
    public void Sort(int[] array) {
        sorting(array,0,(array.length)-1);
    }
    
    //Merge method
    public void merge(int[] array, int first, int mid, int last){
        int n1 = mid - first + 1;
        int n2 = last - mid;
        
        int L[] = new int [n1];
        int R[] = new int [n2];
        
        for(int i = 0; i<n1; ++i){
            L[i] = array[first+i];
        }
        for(int j=0; j<n2; ++j){
            R[j] = array[mid + 1 + j];
        }
        
        int i=0, j=0;
        
        int k = first;
        
        while(i < n1 && j <n2){
            
            if(L[i] <= R[j]){
                array[k] = L[i];
                i++;
            }else{
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while(i<n1){
            array[k] = L[i];
            i++;
            k++;
        }
        while(j<n2){
            array[k] = R[j];
            j++;
            k++;
        }
    }
    
    //The sort function
    public void sorting(int[] array,int first,int last){
        
        if(first<last){
            int mid =(first+last)/2;
            
            sorting(array,first,mid);
            
            sorting(array,mid+1, last);
                              
            merge(array,first,mid,last);
            
            //Sleep thread to show animation
             try{
                Thread.sleep(100);
            }catch(Exception e){
            }
        }
    }
    
}
