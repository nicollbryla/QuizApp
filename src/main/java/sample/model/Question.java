package sample.model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Question {
    public int id;
    public String category;
    public String content;
    public String[] ans;
    public int correctAnswer;

    private Question(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        category = rs.getString("cat");
        content = rs.getString("content");
        ans = new String[4];
        for(int i=0;i<4;i++)
            ans[i] = rs.getString("ans" + Integer.toString(i));
        correctAnswer = 0;
    }

    public void shuffle(Random r){
        List<Integer> l = Arrays.asList(0,1,2,3);
        Collections.shuffle(l, r);
        String[] tmp = new String[4];
        for(int i=0;i<4;i++)
            tmp[l.get(i)] = ans[i];
        ans = tmp;
        correctAnswer = l.get(correctAnswer);
    }

    public static List<Question> loadQuestions(String tableName) {
        List<Question> ret = new ArrayList<>();
        Database db = new Database();
        try {
            ResultSet rs = db.dbselect("select * from "+tableName+";");
            while(rs.next())
                ret.add(new Question(rs));
        } catch (Database.db_exception | SQLException db_exception) {
            db_exception.printStackTrace();
        }
        return ret;
    }

    public static int askForQuestions(){
        int amount;
        while(true) {
            List<Question> list = Question.loadQuestions("questions");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Pytania");
            dialog.setHeaderText("Wybierz ilość pytań w grze (max. " + list.size() + ")");
            dialog.setContentText("Podaj wybraną ilość: ");
            Optional<String> result = dialog.showAndWait();
            amount = Integer.parseInt(result.get());
            if(amount > 0 && amount <= list.size()){
                break;
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Podano niepoprawną ilość.");
            alert.showAndWait();
        }
        return amount;
    }
}
