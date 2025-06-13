package main;

import java.awt.BorderLayout;

import javax.swing.*;

public class Vector extends Input {
    
    public Vector (int rows, Menu menu)
    {
        super(rows);
        panel = new JPanel(new BorderLayout());
        this.rows = rows;
        this.menu = menu;
        initialize();
    }

    public void initialize ()
    {
        matrix.setLayout(new BoxLayout(matrix, BoxLayout.Y_AXIS));
        for(int i = 0; i < entries.length; i++)
        {
            entries[i] = new JTextField("0", 5);
            matrix.add(entries[i]);
        }
        panel.add(matrix, BorderLayout.NORTH);
        panel.add(enter, BorderLayout.SOUTH);
    }
}
