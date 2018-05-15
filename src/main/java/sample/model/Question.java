package main.java.sample.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Question {
    public int id;
    public String category;
    public String content;
    public String[] ans;
    public int correctAnswer;

    public Question(ResultSet rs) throws SQLException {
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

    public static List<Question> loadQuestions() {
        List<Question> ret = new ArrayList<>();
        Database db = new Database();
        try {
            ResultSet rs = db.dbselect("select * from questions;");
            while(rs.next())
                ret.add(new Question(rs));
        } catch (Database.db_exception | SQLException db_exception) {
            db_exception.printStackTrace();
        }
        return ret;
    }
}
