package Controller;

import View.Vista;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class controller {

    private Vista vista;


    public controller(Vista vista) throws IOException {

        this.vista = vista;

        Socket sServidor = new Socket("localhost", 5000);

        DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
        ObjectOutputStream doStream2 = new ObjectOutputStream(sServidor.getOutputStream());

        DataInputStream diStream = new DataInputStream(sServidor.getInputStream());
        ObjectInputStream diStream2 = new ObjectInputStream(sServidor.getInputStream());

        vista.registerListener1(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                elsSocketsNoFuncionenEnElActionListener(doStream);


            }
        });

        vista.registerListener2(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){

                try {

                    doStream.writeUTF("2");

                    doStream.writeInt(vista.eliminarIndex());

                } catch (IOException e1) {

                    System.out.println("Error al eliminar informacio del servidor");
                }
            }

        });

        while(true) {

            information aux;
            try {
                int j = diStream.readInt();
                vista.resetList();
                for (int i = 0; j > i; i++) {

                    aux = (information) diStream2.readObject();
                    vista.setList(aux.getNom(), aux.getNum());
                }

            } catch (IOException e1) {
                System.out.println("Error al enviar la informacion al servidor");
            } catch (ClassNotFoundException e1) {
                System.out.println("Error en la classe rebuda");
            }
        }
    }

    private void elsSocketsNoFuncionenEnElActionListener(DataOutputStream doStream){

        information aux = new information(vista.getText(), vista.getQuantitat());
        try {
            doStream.writeUTF("1");   //envio un 1 per sebar si escric o elimino

            doStream.writeUTF(aux.getNom());
            doStream.writeUTF(String.valueOf(aux.getNum()));

        } catch (IOException e1) {
            System.out.println("Error al enviar la informacion al servidor");
        }
    }
}
