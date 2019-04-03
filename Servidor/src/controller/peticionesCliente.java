package controller;

import model.*;

import java.io.*;
import java.lang.invoke.SwitchPoint;
import java.net.Socket;
import java.util.ArrayList;

public class peticionesCliente extends Thread implements Runnable{

    private  model modelo;
    private final Socket sClient;
    private ArrayList<peticionesCliente> persones;
    private DataOutputStream doStream;
    private ObjectOutputStream doStream2;

    public peticionesCliente(model modelo, Socket sClient, ArrayList<peticionesCliente> persones){

        this.modelo = modelo;
        this.sClient = sClient;
        this.persones = persones;

    }

    @Override
    public void run(){

        try {
            DataOutputStream doStream = new DataOutputStream(sClient.getOutputStream());
            ObjectOutputStream doStream2 = new ObjectOutputStream(sClient.getOutputStream());

            this.doStream = doStream;
            this.doStream2 = doStream2;

            DataInputStream diStream = new DataInputStream(sClient.getInputStream());
            ObjectInputStream diStream2 = new ObjectInputStream(sClient.getInputStream());

            doStream.writeInt(modelo.getInformacio().size());

            for (int i = 0; modelo.getInformacio().size() > i; i++){

                doStream2.writeObject(modelo.getInformacio().get(i));
            }

            while(true){

                String j = String.valueOf(diStream.readUTF());

                switch (j){
                    case "1":

                        j = diStream.readUTF();
                        int p = Integer.parseInt(diStream.readUTF());
                        information aux = new information(j, p);

                        modelo.add(aux);

                        for (int x = 0; x < persones.size(); x++){

                            persones.get(x).actualitza();
                        }


                        break;
                    case "2":

                        int i = diStream.readInt();

                        modelo.getInformacio().remove(i);

                        for (int x = 0; x < persones.size(); x++){

                            persones.get(x).actualitza();
                        }

                        break;

                }
            }


        } catch (IOException e) {

            System.out.println("Somethere was an error");
        }finally {
            try {
                persones.remove(this);
                this.sClient.close();

            } catch (IOException e) {
                System.out.println("Error at dsconection");
            }
        }

    }

    public void actualitza() throws IOException{

        doStream.writeInt(modelo.getInformacio().size());

        for (int i = 0; modelo.getInformacio().size() > i; i++){

            doStream2.writeObject(modelo.getInformacio().get(i));
        }

    }
}
