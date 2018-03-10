import javax.swing.*;
import java.awt.*;

public class Task2_DraggableTable extends JFrame {

    private String[] t1;
    private String[] t2;

    public Task2_DraggableTable(String[] t1, String[] t2) {

        this.t1 = t1; this.t2 = t2;

        this.setTitle("Task2: Draggable Table");
        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 0));

        Object[][] data = new Object[0][];
        JTable table = new JTable(data, t1);

        this.add(new JScrollPane(table));

        JTable table2 = new JTable(data, t2);
        this.add(new JScrollPane(table2));

        this.pack();
        this.setVisible(true);
    }

    public static void main (String[] args) {

        String s1[] = {"one", "two", "three"};
        String s2[] = {"one", "three", "two"};

        new Task2_DraggableTable(s1, s2);
    }



}