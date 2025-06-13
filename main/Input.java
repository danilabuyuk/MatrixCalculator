package main;

import java.awt.GridLayout;
import javax.swing.*;

public abstract class Input extends Panel {

    JPanel matrix;
    JTextField[] entries;
    double[] values;
    int rows;
    int cols;
    Menu menu;

    
    public Input (int rows, int cols)
    {
        super();
        this.rows = rows;
        this.cols = cols;
        matrix = new JPanel(new GridLayout(this.rows, this.cols));
        enter = new JButton("Input");
        values = new double[this.cols * this.rows];
        entries = new JTextField[this.cols * this.rows];
    }

    public Input (int rows)
    {
        this(rows, 1);
    }
}
