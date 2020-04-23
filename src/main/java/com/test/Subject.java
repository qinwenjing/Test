package com.test;

public class Subject {
    private String sname;
    private int score;

    public Subject() {
    }

    public Subject(String sname, int score) {
        this.sname = sname;
        this.score = score;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void printself(){
        System.out.println("i = " + "  name is = "+ sname +"   scorce is = "+score);
    }

    public void printself(String i){
        System.out.println("i = "+ i + "  name is = "+ sname +"   scorce is = "+score);
    }
}
