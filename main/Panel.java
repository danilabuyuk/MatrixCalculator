package main;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class Panel {
    JPanel panel;
    Menu menu;
    JButton addVector;
    JButton addMatrix;
    JButton create;
    JButton enter;
    JTextField[] dimensions;
    boolean isMatrix;
    JTextField[] entries;
    double[] values;
    int rows;
    int cols;

    public Panel ()
    {
        panel = new JPanel();
    }

    public Panel (Menu menu)
    {
        this.menu = menu;
        panel = new JPanel(new BorderLayout());

        addVector = new JButton("Add Vector");
            addVector.setPreferredSize(new Dimension(400, 200));
        addMatrix = new JButton("Add Matrix");
            addMatrix.setPreferredSize(new Dimension(400, 200));

        panel.add(addVector, BorderLayout.NORTH);
        panel.add(addMatrix, BorderLayout.SOUTH);
    }

    public Panel (Menu menu, double[] values, int rows, int cols) 
    {
        this.menu = menu;
        panel = new JPanel(new BorderLayout());
        JLabel[] result = new JLabel[rows * cols];

        JPanel output = new JPanel(new GridLayout(rows, cols));
        JPanel buttons = new JPanel(new GridLayout(3, 2));

        JButton copy = new JButton("Copy");
            buttons.add(copy);
        JButton add = new JButton("Add");
            buttons.add(add);
        JButton sub = new JButton("Subtract");
            buttons.add(sub);
        JButton mult = new JButton("Multiply");
            buttons.add(mult);
        JButton swap = new JButton("Swap");
            buttons.add(swap);
        JButton stats = new JButton("Stats");
            buttons.add(stats);

        for (int i = 0; i < rows * cols; i++)
        {
            result[i] = new JLabel(Double.toString(values[i]));
            output.add(result[i]);
        }

        panel.add(output, BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.SOUTH);
    }

    public <T extends Input> Panel (T input)
    {
        panel = new JPanel(new BorderLayout());
        JLabel result[] = new JLabel[input.rows * input.cols];
        
        JPanel output = new JPanel(new GridLayout(input.rows, input.cols));
        JPanel buttons = new JPanel(new GridLayout(3, 2));

        for (int i = 0; i < input.rows * input.cols; i++)
        {
            result[i] = new JLabel(Double.toString(input.values[i]));
            result[i].setPreferredSize(new Dimension(25, 25));
            result[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            output.add(result[i]);
        }

        JButton remove = new JButton("Remove");
            buttons.add(remove);
        JButton edit = new JButton("Edit");
            buttons.add(edit);
        JButton inv = new JButton("Inverse");
            buttons.add(inv);
        JButton trs = new JButton("Transpose");
            buttons.add(trs);
        JButton det = new JButton("Determinant");
            buttons.add(det);
        JButton sqr = new JButton("A^2");
            buttons.add(sqr);

        panel.add(output, BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.SOUTH);
    }

    public Panel (ActionEvent e, boolean isMatrix)
    {
        panel = new JPanel(new BorderLayout());
        dimensions = new JTextField[2];
        this.isMatrix = isMatrix;

        create = new JButton("Add");
        dimensions[0] = new JTextField("0", 5);
        dimensions[1] = new JTextField("0", 5);

        panel.add(dimensions[0], BorderLayout.WEST);
        panel.add(dimensions[1], BorderLayout.EAST);
        panel.add(create, BorderLayout.SOUTH);
    }
}
