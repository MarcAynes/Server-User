package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Vista extends JFrame {


    private JTabbedPane menuses = new JTabbedPane();

    private JPanel General = new JPanel();

    private JPanel relleno1 = new JPanel();
    private JPanel relleno2 = new JPanel();

    private JPanel info = new JPanel();
    private JTextField nom = new JTextField( 22);
    private JLabel producto = new JLabel("Product Name");

    private String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private JLabel units = new JLabel("Product units");
    private JComboBox unitats = new JComboBox(numeros);

    private JButton Add = new JButton("Add product");

    private JPanel info2 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel relleno3 = new JPanel();
    private JPanel relleno4 = new JPanel();

    private JLabel remove = new JLabel("Product");
    private JComboBox<String> eliminar = new JComboBox<>();

    private JButton removeButton = new JButton("Remove Product");

    private JPanel Consulta = new JPanel();
    private DefaultListModel modelo = new DefaultListModel();

    private JList llista = new JList(modelo);


    public Vista(){

        General.setLayout(new BoxLayout(General, BoxLayout.PAGE_AXIS));

        //General.add(relleno1);

        General.add(producto);
        nom.setMaximumSize(new Dimension(300, 30));
        General.add(nom);
        General.add(units);
        unitats.setMaximumSize(new Dimension(300, 30));
        General.add(unitats);
        General.add(Add);
        //General.add(relleno2);

        menuses.addTab("Add product", General);

        info2.add(remove);
        info2.add(eliminar);

        eliminar.setMaximumSize(new Dimension(300, 20));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.add(relleno3);
        panel2.add(info2);
        panel2.add(removeButton);
        panel2.add(relleno4);

        menuses.addTab("Remove Panel", panel2);
        llista.setLayoutOrientation(JList.VERTICAL);
        llista.setVisibleRowCount(-1);
        Consulta.add(llista);
        menuses.addTab("List Products", Consulta);
        add(menuses);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);

    }

    public void registerListener1(ActionListener al){

        Add.addActionListener(al);
        Add.setActionCommand("SEND");
    }

    public void registerListener2(ActionListener al){

        removeButton.addActionListener(al);
    }

    public String getText(){

        return nom.getText();
    }

    public int getQuantitat(){

        return unitats.getSelectedIndex() + 1;
    }

    public void resetList(){

        modelo.clear();
        eliminar.removeAllItems();
    }

    public void setList(String a, int b){

        modelo.addElement(a + "(" + b + ")");
        eliminar.addItem(a);
        revalidate();
    }

    public int eliminarIndex(){

        return eliminar.getSelectedIndex();
    }

}
