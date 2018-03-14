import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class Task2_DraggableTable {
    public Task2_DraggableTable(String t1[], String t2[]) {
        final MobileContainerPanel mcp = new MobileContainerPanel();

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        for (String s : t1)
            model.addColumn(s);

        table.setBorder(BorderFactory.createEtchedBorder());
        mcp.addNext(new JScrollPane(table));

        DefaultTableModel model2 = new DefaultTableModel();
        JTable table2 = new JTable(model2);
        for (String s : t2)
            model2.addColumn(s);

        table2.setBorder(BorderFactory.createEtchedBorder());
        mcp.addNext(new JScrollPane(table2));

        JPanel north = new JPanel();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(north, "North");
        f.getContentPane().add(mcp);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        f.setLocation(200, 200);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Task2_DraggableTable(new String[]{"one", "two", "three"}, new String[]{"one", "three", "two"});
    }
}

class MobileContainerPanel extends JPanel {
    final int PAD = 10;
    List componentList;
    ComponentWrangler wrangler;

    public MobileContainerPanel() {
        componentList = new ArrayList();
        wrangler = new ComponentWrangler();
        setLayout(null);
    }

    public void addNext(Component c) {
        componentList.add(c);
        c.addMouseListener(wrangler);
        c.addMouseMotionListener(wrangler);
        add(c);
        Dimension d = c.getPreferredSize();
        Point p = getNextLocation(d);
        c.setBounds(p.x, p.y, d.width, d.height);
        repaint();
    }

    private Point getNextLocation(Dimension d) {
        int maxX = 0, maxY = 0;
        Component c, last = null;
        Rectangle r;
        // find level of lowest component(s)
        for (int j = 0; j < componentList.size(); j++) {
            c = (Component) componentList.get(j);
            r = c.getBounds();
            if (r.y + r.height > maxY) {
                maxY = r.y + r.height;
                last = c;
            }
        }
        // find last (in row) of lowest components
        for (int j = 0; j < componentList.size(); j++) {
            c = (Component) componentList.get(j);
            r = c.getBounds();
            if (r.y + r.height == maxY && r.x + r.width > maxX) {
                maxX = r.x + r.width;
                last = c;
            }
        }
        // determine location of next component based on location of last
        Point p = new Point();
        if (last == null) {
            p.x = PAD;
            p.y = PAD;
            return p;
        }
        r = last.getBounds();
        int x, y;
        if (r.x + r.width + PAD + d.width < getWidth()) {
            p.x = r.x + r.width + PAD;
            p.y = r.y;
        } else {
            p.x = PAD;
            p.y = r.y + r.height + PAD;
        }
        return p;
    }

    public void clear() {
        removeAll();
        componentList.clear();
        repaint();
    }

    /**
     * select and drag components with the mouse
     */
    private class ComponentWrangler extends MouseInputAdapter {
        Component selectedComponent;
        Point offset;
        boolean dragging;

        public ComponentWrangler() {
            dragging = false;
        }

        public void mousePressed(MouseEvent e) {
            selectedComponent = (Component) e.getSource();
            offset = e.getPoint();
            dragging = true;
        }

        public void mouseReleased(MouseEvent e) {
            dragging = false;
        }

        public void mouseDragged(MouseEvent e) {
            if (dragging) {
                Rectangle r = selectedComponent.getBounds();
                r.x += e.getX() - offset.x;
                r.y += e.getY() - offset.y;
                selectedComponent.setBounds(r);
            }
        }
    }
}