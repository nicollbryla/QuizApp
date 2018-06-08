package sample.model;

public class ModelTable {

    private String login, name, surname;
    private Integer score;


    public ModelTable(String login, String name, String surname, Integer score) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.score = score;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }*/
}
