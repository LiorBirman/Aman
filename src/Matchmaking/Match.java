package Matchmaking;

public class Match {
    private String name_1;
    private String name_2;
    private int score;

    public Match() {
        this.score = 0;
    }

    public String getName_1() {
        return name_1;
    }

    public void setName_1(String name_1) {
        this.name_1 = name_1;
    }

    public String getName_2() {
        return name_2;
    }

    public void setName_2(String name_2) {
        this.name_2 = name_2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score++;
    }

    @Override
    public String toString() {
        return "Match: " + name_1 + "& " + name_2 + ", score = " + score + "\n";
    }
}
