package sample.controllers;

import sample.comp.Player;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private Player player;

    public void addClient(Player player){
        this.player = player;
        System.out.println("Przekazano " + player.name);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
