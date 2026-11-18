package org.example;

public class Alumno {
    private int id;
    private String name;
    private int age;
    private String mail;

    public Alumno(int id, String name, int age, String mail){
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public int getAge() {return age;}

    public String getName(){
        return name;
    }

    public String getMail(){return mail;}
}
