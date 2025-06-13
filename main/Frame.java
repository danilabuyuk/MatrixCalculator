package main;

import javax.swing.*;
import java.awt.Dimension;

public abstract class Frame {
   
    JFrame frame;

    
    public abstract void initialize ();


    public Frame (int width, int height)
     {
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }

     public Frame ()
     {
        this(200, 200);
     }
}
