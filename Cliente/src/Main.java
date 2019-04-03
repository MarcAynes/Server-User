import Controller.controller;
import View.Vista;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Vista vista = new Vista();
        try {

            controller Controller = new controller(vista);
        }catch(IOException e){
            System.out.println("Something goes wrong");

        }
    }
}
