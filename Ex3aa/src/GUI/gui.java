package GUI;


import GraphPack.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;



public class gui implements ActionListener {

    private DirectedWeightedGraphAlgorithms graph;
    JFrame frame;
    JMenuBar menu;
    CustomPaintComponent component;
    JMenuItem save;
    JMenuItem load;




    public gui(DirectedWeightedGraphAlgorithms algo){
        graph = algo;

        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2,(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.80));

        menu = new JMenuBar();

        save = new JMenuItem("Save");
        load = new JMenuItem("Load");


        menu.add(save);
        menu.add(load);




        frame.setJMenuBar(menu);
        // actions
        load.addActionListener(this);
        save.addActionListener(this);



        component = new CustomPaintComponent(graph);
        frame.add(component);


        frame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();


        if (command.equals("Load")){
            JFileChooser file = new JFileChooser("C:");
            file.showSaveDialog(null);
            String path = file.getSelectedFile().getAbsolutePath();
            this.graph.load(path);
            frame.remove(component);

            CustomPaintComponent new_Comp = new CustomPaintComponent(graph);

            frame.add(new_Comp);
            frame.setVisible(true);
            component = new_Comp;

        }

        if(command.equals("Save")){
            JFileChooser file = new JFileChooser("C:");
            file.showSaveDialog(null);

            String path = file.getSelectedFile().getAbsolutePath();
            graph.save(path);

        }


    }

    static class CustomPaintComponent extends Component {
        private final DirectedWeightedGraphAlgorithms graph = new DirectedWeightedGraphAlgorithmsImpl();
        private final int margin = 60;
        private double xmin = Double.MAX_VALUE;
        private double ymin = Double.MAX_VALUE;
        private double xmax = 0;
        private double ymax = 0;






        public CustomPaintComponent(DirectedWeightedGraphAlgorithms graph){

            this.graph.init(graph.getGraph());
        }


        public void paint(Graphics g) {

            Graphics2D two_d = (Graphics2D) g;

            two_d.setColor(Color.RED);

            two_d.setStroke(new BasicStroke(2));

            update_Values(graph.getGraph());

            Draw_Edges(two_d, graph.getGraph());

            drawNodes(two_d, graph.getGraph());

        }
        public void drawNodes(Graphics2D two_d, DirectedWeightedGraph graph) {
            int offset = 14;
            int nodesize = 10;

           DirectedWeightedGraphImpl graph2 = (DirectedWeightedGraphImpl) graph;
            Iterator<NodeData> it = graph2.nodeIter();

            while (it.hasNext()) {
                NodeData node = it.next();
                GeoLocation location = node.getLocation();
                two_d.setColor(Color.ORANGE);


                int x=(int)x_cordinate(location.x());
                int y=(int)Y_cordinate(location.y());

                two_d.fillOval(x - (nodesize /2) , y - (nodesize /2), nodesize, nodesize);

                two_d.setColor(Color.BLUE);



                two_d.drawString(""+node.getKey(),x - (nodesize /2) + offset,y - (nodesize /2) + offset + 8);
            }
        }
        public void Draw_Edges(Graphics2D two_d, DirectedWeightedGraph graph) {

            DirectedWeightedGraphImpl graph2 = (DirectedWeightedGraphImpl) graph;
            Iterator<EdgeData> itr = graph2.edgeIter();
            two_d.setColor(Color.RED);
            while (itr.hasNext()) {
                EdgeData edge = itr.next();

                GeoLocation src = graph.getNode(edge.getSrc()).getLocation();
                GeoLocation dest = graph.getNode(edge.getDest()).getLocation();

                int x_1 = (int)x_cordinate(src.x());
                int y_1 = (int)Y_cordinate(src.y());
                int x_2 = (int)x_cordinate(dest.x());
                int y_2 = (int)Y_cordinate(dest.y());

                two_d.drawLine(x_1, y_1, x_2, y_2);
                int d_x = x_2 - x_1;
                int  d_y = y_2 - y_1;
                int v =d_x*d_x;
                int v2= d_y*d_y;
                double d = Math.sqrt( v + v2);

                double  x_m = d - 10;
                double  x_n = x_m;
                double  y_m = 7;
                double  y_n = - 7;
                double  x;
                double sin = d_y / d;
                double cos = d_x / d;

                x = x_m*cos - y_m*sin + x_1;
                y_m = x_m*sin + y_m*cos + y_1;
                x_m = x;

                x = x_n*cos - y_n*sin + x_1;
                y_n = x_n*sin + y_n*cos + y_1;
                x_n = x;

                int[] x_points = {x_2, (int) x_m, (int) x_n};
                int[] y_points = {y_2, (int) y_m, (int) y_n};

                two_d.fillPolygon(x_points,y_points,3);
            }
        }
        public double x_cordinate(double x) {
            x = x - this.xmin;

           double x_Dis=this.xmax - this.xmin;

            x = (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 * 0.60) * (x / x_Dis);

            return x + margin;
        }
        public double Y_cordinate(double y) {
            y = y - this.ymin;

           double y_Dis= this.ymax - this.ymin;

            y = (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 * 0.60) * (y / y_Dis);

            return y + margin;
        }

        public void update_Values(DirectedWeightedGraph graph) {
            Iterator<NodeData> it = graph.nodeIter();

            while (it.hasNext()) {
                NodeData n = it.next();
                GeoLocation location = n.getLocation();


                if (this.xmin > location.x())
                    this.xmin = location.x();
                if (this.ymin > location.y())
                    this.ymin = location.y();
                if (this.xmax < location.x())
                    this.xmax = location.x();
                if (this.ymax < location.y())
                    this.ymax = location.y();
            }
        }
    }

}