package controller;

import model.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class controller {

    private model modelo;
    private ArrayList<peticionesCliente> persones = new ArrayList<>();

    public controller(model modelo) throws IOException {

        ServerSocket sServer = new ServerSocket(5000);

        while(true) {

            Socket sClient = sServer.accept();

            peticionesCliente peticion = new peticionesCliente(modelo, sClient, persones);
            persones.add(peticion);
            peticion.start();

        }
    }
}
