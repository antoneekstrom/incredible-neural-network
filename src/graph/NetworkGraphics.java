package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import network.Layer;
import network.NeuralNetwork;

public class NetworkGraphics {

    NeuralNetwork network;

    public NetworkGraphics(NeuralNetwork network) {
        this.network = network;
    }

    JFrame frame;
    JPanel panel;

    protected static interface Paintable {
        public void paint(Graphics2D g);
    }

    protected static class GLayer implements Paintable {

        private Layer layer;
        private Point pos;

        public GLayer(Layer layer, Point pos) {
            this.layer = layer;
        }

        @Override
        public void paint(Graphics2D g) {

            int i = 0;
            for (double val : layer.getValues()) {

                g.setColor(Color.cyan);
                g.fillOval(pos.x, pos.y, 100, 100);
                g.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                g.drawString(Double.toString(val), pos.x + i * 125, pos.y);

                i++;
            }
        }
    }

    @SuppressWarnings("serial")
    public void start() {

        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                super.paint(g2d);
                paint(g2d);
            }
        };

        frame = new JFrame("Network");
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        frame.revalidate();
        frame.setVisible(true);
    }

    public void update() {
        panel.repaint();
    }

    public void paint(Graphics2D g) {
        GLayer l = new GLayer(network.getInputLayer(), new Point(50, 50));
        l.paint(g);
    }

}