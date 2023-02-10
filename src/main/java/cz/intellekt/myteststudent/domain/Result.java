package cz.intellekt.myteststudent.domain;

public class Result {

    private int score=0;
    private int wrongs=0;

    private int answers;

    public Result(int answers) {
        this.answers = answers;
    }

    public int getScore() {
        return score;
    }

    public int getWrongs() {
        return wrongs;
    }

    public void addScore(){
        score++;
    }

    public void addWrong(){
        wrongs++;
    }

    public double getPercent() {
        return ((double) score/(double)answers) * 100.0;
    }
}
