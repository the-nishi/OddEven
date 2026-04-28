/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uroot
 */
public class GetInsightController implements Initializable {
    @FXML
    private BarChart<String, Number> barChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setInitData(List<Student> studentList) {
        // Data Extraction
        Map<String, Integer> departmentMap = new HashMap<>();
        for(Student student: studentList) {
            String department = student.getDepartment();
            if(departmentMap.containsKey(department)) {
                departmentMap.put(department, departmentMap.get(department) + 1);
            }else {
                departmentMap.put(department, 1);
            }
        }
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(String department: departmentMap.keySet()) {
            series.getData()
                    .add(
                            new XYChart.Data<>(department, departmentMap.get(department))
                    );
        }
        
        barChart.getXAxis().setLabel("Departments");
        barChart.getYAxis().setLabel("Number of Students");
        barChart.getData().add(series);
    }
    
    @FXML
    private void onGoBack(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-table-view.fxml")); 
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
