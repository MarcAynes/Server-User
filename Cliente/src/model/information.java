package model;

import java.io.Serializable;

public class information implements Serializable {
    private String nom;
    private int num;


    public information(String a, int b){

        this.nom = a;
        this.num = b;
    }
    public void setNom(String nom){

        this.nom = nom;
    }

    public void setNum(int num){

        this.num = num;
    }

    public String getNom(){

        return nom;
    }

    public int getNum(){

        return num;
    }
}
