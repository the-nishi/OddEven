/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uroot
 */
public class StudentTableController implements Initializable {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> departmentColumn;
    @FXML
    private TableColumn<Student, LocalDate> dobColumn;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<String> departmentComboBox;
    @FXML
    private DatePicker dobPicker;

    private final List<String> departmentList = Arrays.asList("CSE", "EEE", "BBA");

    private final File studentFile = new File("student.bin");
   
    private final Alert alert = new Alert(Alert.AlertType.WARNING);
    
    private final List<Student> studentList = new ArrayList<>();
    @FXML
    private Label label;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        departmentComboBox.getItems().addAll(departmentList);
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        
        loadStudents();
        studentTable.getItems().addAll(studentList);
    }    

    @FXML
    private void onAddNewStudent(ActionEvent event) {
        int id = Integer.parseInt(idTextField.getText());
        for(Student student: studentTable.getItems()) {
            if(student.getId() == id) {
                alert.setContentText("Student with the id " +  id + " already exists!");
                alert.showAndWait();
                return;
            }
        }
        
        String name = nameTextField.getText();
        String department = departmentComboBox.getValue();
        LocalDate dob = dobPicker.getValue();
        
        Student student = new Student(
                        id, 
                        name,
                        department,
                        dob
                );
        
        studentList.add(student);
        studentTable.getItems().add(student);
        storeStudent(student);
    }

    @FXML
    private void onShowOddIds(ActionEvent event) {
        List<Student> oddStudents = new ArrayList<>(); 
        for(Student student: studentList) {
            if(student.getId() % 2 != 0) {
                oddStudents.add(student);
            }
        }
        studentTable.getItems().clear();
        studentTable.getItems().addAll(oddStudents);
    }

    @FXML
    private void onShowEvenIds(ActionEvent event) {
        List<Student> evenStudents = new ArrayList<>(); 
        for(Student student: studentList) {
            if(student.getId() % 2 == 0) {
                evenStudents.add(student);
            }
        }
        studentTable.getItems().clear();
        studentTable.getItems().addAll(evenStudents);
    }
    
    private void storeStudent(Student student) {
        boolean append = studentFile.exists();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {    
            fos = new FileOutputStream(studentFile, append);
            if(append) {
                oos = new AppendableObjectOutputStream(fos);
            }else {
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(student);
            alert.setContentText("New Student Added Successfully...");
            alert.showAndWait();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(oos != null) {
                try{
                    fos.close();
                    oos.close();
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void loadStudents() {
        if(!studentFile.exists()) {
            return;
        }
        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(studentFile);
            ois = new ObjectInputStream(fis);
            while(true) {
                studentList
                        .add(
                                (Student)ois.readObject()
                        );
            }
        } catch(EOFException ignored) {
        
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(ois != null) {
                try{
                    fis.close();
                    ois.close();
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @FXML
    private void onReset(ActionEvent event) {
        studentTable.getItems().clear();
        studentTable.getItems().addAll(studentList);
    }

    @FXML
    private void onGetInsight(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("get-insight-view.fxml")); 
        Parent root = loader.load();
        GetInsightController controller = loader.getController();
        controller.setInitData(studentList);
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}