package main.java.sample.controller;

import javafx.fxml.Initializable;
import main.java.sample.model.Admin;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowAdmin extends QuizController {
    private Admin admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setAdmin(Admin a){
        admin = a;
        System.out.println("Administrator: " + admin.name + " " + admin.surname);
    }
}
