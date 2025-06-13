package main;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.*;

public class Matrix extends Input {

    int rows;
    int cols;
    Menu menu;

    public Matrix (int rows, int cols, Menu menu)
    {
        super(rows, cols);
        panel = new JPanel(new BorderLayout());
        this.cols = cols;
        this.rows = rows;
        this.menu = menu;
        initialize();
    }

    public void initialize ()
    {
        
        for ( int i = 0; i < cols * rows; i++)
        {
            entries[i] = new JTextField("0", 5);
            matrix.add(entries[i]);
        }
        panel.add(matrix, BorderLayout.NORTH);
        panel.add(enter, BorderLayout.SOUTH);
    }

    public void actionPerformed (ActionEvent e)
    {
        for (int i = 0; i < rows * cols; i++)
        {
            values[i] = Double.parseDouble(entries[i].getText());
        }
    }


}
