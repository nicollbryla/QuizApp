package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Main;
import sample.model.Database;
import sample.model.Question;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EditQuestionAdmin extends QuizController {
    @FXML
    private ListView<String> listView;

    @FXML
    private TextField question;

    @FXML
    private TextField ans0;

    @FXML
    private TextField ans1;

    @FXML
    private TextField ans2;

    @FXML
    private TextField ans3;

    private HashMap<String, Question> questions;
    String selectedQId;
    ObservableList<String> items;

    private void fillListView()
    {
        Database db = null;
        ResultSet dbres;
        try {
            db = new Database();
            dbres = db.dbselect("SELECT id as id, cat as cat, content as content, ans0 as ans0, ans1 as ans1, ans2 as ans2, ans3 as ans3 from questions order by id");
            items = FXCollections.observableArrayList();
            while (dbres.next()) {
                items.add(dbres.getString("id") +". "+dbres.getString("content"));
                questions.put(dbres.getString("id"), new Question(dbres));
            }
            listView.setItems(items);
            db.close();
        } catch (Exception e) {
            try {
                assert db != null;
                db.close();
            } catch (Exception ignored) {
            }
            e.printStackTrace();
            System.out.println("Problem z bazą danych");
        }
    }

    private void fillRightPanel() {
        String selectedQ = listView.getSelectionModel().getSelectedItem();
        selectedQId = selectedQ.substring(0,selectedQ.indexOf('.'));
        Question questionHM = questions.get(selectedQId);

        question.setText(questionHM.content);
        ans0.setText(questionHM.ans[0]);
        ans1.setText(questionHM.ans[1]);
        ans2.setText(questionHM.ans[2]);
        ans3.setText(questionHM.ans[3]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questions = new HashMap<>();
            fillListView();
            listView.setOnMouseClicked(e -> {
                fillRightPanel();
            });
            if(!listView.getItems().isEmpty()) {
                listView.getSelectionModel().select(0);
                fillRightPanel();
            }
    }

    public void saveChanges(ActionEvent actionEvent) throws IOException {
        Database db = null;
        try {
            db = new Database();
            db.executeUpdate("update questions set content = '" +question.getText() + "', ans0 = '"+ans0.getText()  + "', ans1 = '"+ans1.getText()  + "', ans2 = '"+ans2.getText() + "', ans3 = '"+ans3.getText()+"'where id = '"+selectedQId + "';");
            try {
                ResultSet rs  = db.dbselect("SELECT id as id, cat as cat, content as content, ans0 as ans0, ans1 as ans1, ans2 as ans2, ans3 as ans3 from questions where id = '"+selectedQId + "';");
                if (rs.next()) {
                    Question q = new Question(rs);
                    questions.put(selectedQId, q);
                    for(int i = 0; i < items.size(); ++i) {
                        String cur = items.get(i);
                        String curId = cur.substring(0, cur.indexOf('.'));
                        if (curId.equals(selectedQId)) {
                            items.set(i, selectedQId + ". " + q.content);
                            break;
                        }
                    }
                }
                listView.setItems(items);
            } catch (Database.db_exception db_exception) {
                db_exception.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            try {
                assert db != null;
                db.close();
            } catch (Exception ignored) {
            }
            e.printStackTrace();
            System.out.println("Problem z bazą danych");
        }
    }

    public void goToMainWindowAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainWindowAdmin.fxml"));
        Main.changeWindow(actionEvent, player, null, loader, null);
    }
}
