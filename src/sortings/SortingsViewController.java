/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortings;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;




/**
 *
 * @author Sanjot (250911639)
 * @Co-author Arpit (250910750)
 */
public class SortingsViewController implements Initializable {
   
    //FX UI Elements
    @FXML
    private ComboBox algorithm;
    
    @FXML
    private Label arraySize;
  
    @FXML
    private Slider slidingBar;
    
    @FXML
    private Pane view;
    
    //Model 
    Model _model;
    //Sorting Strategy 
    SortingsStrategy sortingsMethod;
    
    //Upon initialization 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Options added to a list
        List<String> options = new ArrayList<String>();
        options.add("Merge Sort");
        options.add("Selection Sort");
       
        ObservableList opList = FXCollections.observableList(options);
        
        //Populate combo box with List options "for merge and selection"
        algorithm.setItems(opList);
        
        //Inverts the view scale (default is upside down...)
        view.setScaleY(-1);
        
        //Automatically selects the combobox options to the first one
        algorithm.getSelectionModel().selectFirst();
        
        //reference to model 
        _model = new Model((int)slidingBar.getValue());  
        
        //Background thread: calls draw function repeatedly in the background
        Thread Update = new Thread(()->{
            try{
                while(true){
                    Platform.runLater(()->{ 
                        draw();
                        //_model.print();
                    });
                    Thread.sleep(20);
                }
            }catch(InterruptedException e){
                System.out.println("Interrupt");
            }
        });
       
        //Set as background and start
        Update.setDaemon(true);
        Update.start();
        
    }    
    
    //Sort button method
    public void sortBtn_Click(){
        //Sets the sorting strategy based on User Selection
        setSortStrategy();
        
        //A thread that calls the respective sortingMethods sort function 
        Thread sortingThread = new Thread(()->{
            sortingsMethod.Sort(_model.getUnSortedList());
        });
        sortingThread.start();
        
    }
    //Reset Button Method
    public void resetBtn_Click(){
        //Calls the reset function inside the model
        _model.reset((int)slidingBar.getValue());
        //redraws the array 
        draw();
    }
    
    //Size slider changing: Have an observer
    public void arraySizeSlider_ValueChanged(){
        slidingBar.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                //calls the reset array function with the sliding bar value
                _model.reset((int)slidingBar.getValue());
               //Set text label to the sliding bar value
                arraySize.setText(String.valueOf((int)slidingBar.getValue()));
            }
        }); 
    }
    
    //Sorting Method set based on combo box
    
    public void setSortStrategy(){
        //Gets value from combo-box
        String value = algorithm.getValue().toString();
        
        if(value.equals("Selection Sort")){
            sortingsMethod = new SelectionSort();
            
        }else{
            sortingsMethod = new MergeSort();
        }
    }
    
    
    //Draw Method: Clear pane 
    public void draw(){
        view.getChildren().clear();
        
        //For loops through the randomized array
        
        for(int i = 0; i< _model.getArraySize();i++){
           
            //width scaling
            double availSpaceX = view.getWidth()/(_model.getArraySize());
            double width = availSpaceX/2;
            double space = i*availSpaceX;
            
            //get element value in array 
            Double valueHeight = Array.getDouble(_model.getUnSortedList(),i);
            
            //max Height Factor (scale each rectangle height based on max height possible and the value at that index
            double availSpaceY = view.getHeight()/(_model.getArraySize());
            //Height of rectangle
            double height = availSpaceY * valueHeight;
          
            //Create a rectangle shape
            Rectangle r = new Rectangle(space,10,width,height);
            
            //Adding rectangle to view pane
            view.getChildren().add(r);
            
            
        }
    }
}
    

