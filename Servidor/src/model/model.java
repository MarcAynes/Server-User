package model;

import java.util.ArrayList;

public class model {
    private ArrayList<information> informacio = new ArrayList<>();

    public model(){


    }

    public ArrayList<information> getInformacio(){

        return informacio;
    }

    public void add(information aux){

        informacio.add(aux);
    }
}
