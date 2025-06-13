package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Popup extends Frame {
    
    Menu menu;
    Panel panel;
    JTextField[] dimensions = new JTextField[2];
    JButton create;

    public Popup (ActionEvent d, Menu menu, Panel panel, boolean isMatrix)
    {
        super();
        this.menu = menu;
        this.panel = panel;
        frame.setLayout(new BorderLayout());
        initialize();
        if (isMatrix)
            create.addActionListener(e -> addMatrix(e));
        else
            create.addActionListener(f -> addVector(f));
        
        frame.setVisible(true);
    }

    public void initialize () 
    {
        dimensions[0] = new JTextField("0", 5);
        dimensions[1] = new JTextField("0", 5);
        create = new JButton("Create");

        frame.add(dimensions[0], BorderLayout.WEST);
        frame.add(dimensions[1], BorderLayout.EAST);
        frame.add(create, BorderLayout.SOUTH);
        frame.pack();
    }

    public void addMatrix (ActionEvent e)
    {
        if (menu.input[0] == null)
        {
            frame.setVisible(false);
            menu.input[0] = new Matrix(Integer.parseInt(dimensions[0].getText()), Integer.parseInt(dimensions[1].getText()), menu);
        }
        else
        {
            frame.setVisible(false);
            menu.input[1]  = new Matrix(Integer.parseInt(dimensions[0].getText()), Integer.parseInt(dimensions[1].getText()), menu);
        }
    }

    public void addVector (ActionEvent e)
    {
        if (menu.input[0] == null)
        {
            frame.setVisible(false);
            menu.input[0] = new Vector(Integer.parseInt(dimensions[0].getText()));
        }
        else
        {
            frame.setVisible(false);
            menu.input[1] = new Vector(Integer.parseInt(dimensions[0].getText()));
        }
    }
}
