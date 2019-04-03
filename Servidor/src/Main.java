import controller.controller;
import model.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        model modelo = new model();

        try {
            controller controlador = new controller(modelo);
        } catch (IOException e) {
            System.out.println("Error de conexion");
        }
    }
}
