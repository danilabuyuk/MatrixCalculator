package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends Frame {

    JPanel[] panel;
    double[][][] matrix;
    
    public Menu ()
    {
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 3, 5, 5));
        panel = new JPanel[3];
        matrix = new double[3][][];
        initializeInitial(0);
        panel[1] = null;
        initializeInitial(2);
        if (panel[0] != null) frame.add(panel[0]);
        if (panel[1] != null) frame.add(panel[1]);
        if (panel[2] != null) frame.add(panel[2]);
        frame.pack();
        frame.setVisible(true);
    }

    public void initialize ()
    {   
        frame.setVisible(false);
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 3, 5, 5));
        if (matrix[1] == null) {
            if (matrix[0] != null && matrix[2] != null) {
                initializeMiddle();
            }
        }
        if (panel[0] != null)
            {frame.add(panel[0]);}
        if (panel[1] != null) 
            {frame.add(panel[1]);}
        if (panel[2] != null) 
            {frame.add(panel[2]);}
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeInitial (int i) {
        panel[i] = new JPanel(new BorderLayout());
        JButton addMatrix = new JButton("Add Matrix");
        panel[i].add(addMatrix);
        initialize();
        addMatrix.addActionListener(e -> initializeAddMatrix(i));
    }

    private void initializeAddMatrix (int i) {
        panel[i] = new JPanel(new BorderLayout());
        JTextField[] dimensions = new JTextField[2];
            dimensions[0] = new JTextField("0", 5);
            dimensions[1] = new JTextField("0", 5);
        JButton create = new JButton("Add");
            create.addActionListener(e -> initializeMatrix(i, Integer.parseInt(dimensions[0].getText()), Integer.parseInt(dimensions[1].getText())));

        panel[i].add(dimensions[0], BorderLayout.WEST);
        panel[i].add(dimensions[1], BorderLayout.EAST);
        panel[i].add(create, BorderLayout.SOUTH);
        initialize();
    }

    private void initializeMatrix (int i, int rows, int cols) {
        matrix[i] = new double[rows][];
        for (int j = 0; j < rows; j++) {
            matrix[i][j] = new double[cols];
            }
        panel[i] = new JPanel(new BorderLayout());
        JPanel matrix = new JPanel(new GridLayout(rows, cols));
        JButton enter = new JButton("Input");
        JTextField[] entries = new JTextField[rows * cols];

        for (int j = 0; j < rows * cols; j++) {
            entries[j] = new JTextField("0", 5);
            matrix.add(entries[j]);
        }

        enter.addActionListener(e -> collectValues(i, entries, rows, cols));
        panel[i].add(matrix, BorderLayout.NORTH);
        panel[i].add(enter, BorderLayout.SOUTH);
        initialize();
    }

    private void collectValues (int i, JTextField[] entries, int rows, int cols) {
        for (int k = 0; k < rows; k++) {
            for (int l = 0; l < cols; l++) {
                matrix[i][k][l] = Double.parseDouble(entries[(k * cols) + l].getText());
            }
        }
        initializeMatrixOutput(i, rows, cols);
    }


    private void initializeMatrixOutput (int i, int rows, int cols) {
        panel[i] = new JPanel(new BorderLayout());
        JPanel output = new JPanel(new GridLayout(rows, cols, 1, 1));
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        JLabel[] result = new JLabel[rows * cols];

        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                result[(j * cols) + k] = new JLabel(Double.toString(matrix[i][j][k]));
                result[(j * cols) + k].setPreferredSize(new Dimension(25, 25));
                result[(j * cols) + k].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                output.add(result[(j * cols) + k]);
            }
        }

        JButton remove = new JButton("Remove");
            buttons.add(remove);
            remove.addActionListener(e -> initializeInitial(i));
        JButton edit = new JButton("Edit");
            buttons.add(edit);
            edit.addActionListener(e -> editMatrix(i, rows, cols));
        JButton trs = new JButton("Transpose");
            buttons.add(trs);
            trs.addActionListener(e -> transpose(i));
        JButton sqr = new JButton("A^2");
            sqr.addActionListener(e -> squared(i));
            buttons.add(sqr);

        panel[i].add(output, BorderLayout.NORTH);
        panel[i].add(buttons, BorderLayout.SOUTH);
        initialize();
    }

    private void editMatrix (int i, int rows, int cols) {
        panel[i] = new JPanel(new BorderLayout());
        JPanel input = new JPanel(new GridLayout(rows, cols));
        JButton enter = new JButton("Input");
        JTextField[] entries = new JTextField[rows * cols];

        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                entries[(j * cols) + k] = new JTextField(Double.toString(matrix[i][j][k]), 5);
                input.add(entries[(j * cols) + k]);
            }
        }

        enter.addActionListener(e -> collectValues(i, entries, rows, cols));
        panel[i].add(input, BorderLayout.NORTH);
        panel[i].add(enter, BorderLayout.SOUTH);
        initialize();
    }

    private void squared (int i) {
        double[][] squared = new double[matrix[i].length][matrix[i][0].length];
        JFrame frame = new JFrame();
        JPanel output = new JPanel(new GridLayout(matrix[i].length, matrix[i][0].length, 1, 1));
        JLabel[] result = new JLabel[matrix[i].length * matrix[i][0].length];
        for (int j = 0; j < matrix[i].length; j++) {
            for (int k = 0; k < matrix[i][0].length; k++) {
                for (int l = 0; l < matrix[i].length; l++) {
                    squared[j][k] += matrix[i][j][l] * matrix[i][l][k];
                }
            }
        }
        for (int j = 0; j < matrix[i].length; j++) {
            for (int k = 0; k < matrix[i][0].length; k++) {
                result[(j * matrix[i][0].length) + k] = new JLabel(Double.toString(squared[j][k]));
                result[(j * matrix[i][0].length) + k].setPreferredSize(new Dimension(25, 25));
                result[(j * matrix[i][0].length) + k].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                output.add(result[(j * matrix[i][0].length) + k]);
            }
        }
        frame.add(output);
        frame.pack();
        frame.setVisible(true);
    }

    private void transpose (int i) {
        JFrame frame = new JFrame();
        JPanel output = new JPanel(new GridLayout(matrix[i][0].length, matrix[i].length, 1, 1));
        JLabel[] result = new JLabel[matrix[i].length * matrix[i][0].length];
        double[][] transpose = new double[matrix[i][0].length][matrix[i].length];
        for (int j = 0; j < transpose.length; j++) {
            for (int k = 0; k < transpose[0].length; k++) {
                transpose[j][k] = matrix[i][k][j];
            }
        }
        for (int j = 0; j < matrix[i][0].length; j++) {
            for (int k = 0; k < matrix[i].length; k++) {
                result[(j * matrix[i].length) + k] = new JLabel(Double.toString(transpose[j][k]));
                result[(j * matrix[i].length) + k].setPreferredSize(new Dimension(25, 25));
                result[(j * matrix[i].length) + k].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                output.add(result[(j * matrix[i].length) + k]);
            }
        }
        frame.add(output);
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeMiddle () {
        panel[1] = new JPanel(new GridLayout(4, 1));
        JButton add = new JButton("Add");
            panel[1].add(add);
            add.addActionListener(e -> add());
        JButton sub = new JButton("Subtract");
            panel[1].add(sub);
            sub.addActionListener(e -> subtract());
        JButton mult = new JButton("Multiply");
            panel[1].add(mult);
            mult.addActionListener(e -> multiply());
        JButton swap = new JButton("Swap");
            panel[1].add(swap);
            swap.addActionListener(e -> swap());
    }

    private void middleResult () {
        panel[1] = new JPanel(new BorderLayout());
        JPanel output = new JPanel(new GridLayout(matrix[1].length, matrix[1][0].length));
        JPanel buttons = new JPanel(new GridLayout(3, 1));
        JLabel[] result = new JLabel[matrix[1].length * matrix[1][0].length];

        for (int j = 0; j < matrix[1].length; j++) {
            for (int k = 0; k < matrix[1][0].length; k++) {
                result[(j * matrix[1][0].length) + k] = new JLabel(Double.toString(matrix[1][j][k]));
                result[(j * matrix[1][0].length) + k].setPreferredSize(new Dimension(25, 25));
                result[(j * matrix[1][0].length) + k].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                output.add(result[(j * matrix[1][0].length) + k]);
            }
        }
        JButton remove = new JButton("Remove");
            buttons.add(remove);
            remove.addActionListener(e -> {initializeMiddle(); initialize();});
        JButton trs = new JButton("Transpose");
            buttons.add(trs);
            trs.addActionListener(e -> transpose(1));
        JButton sqr = new JButton("A^2");
            buttons.add(sqr);
            sqr.addActionListener(e -> squared(1));

        panel[1].add(output, BorderLayout.NORTH);
        panel[1].add(buttons, BorderLayout.SOUTH);
        initialize();
    }

    private void swap () {
        double[][] temp = matrix[0];
        matrix[0] = matrix[2];
        matrix[2] = temp;

            initializeMatrixOutput(0, matrix[0].length, matrix[0][0].length);
            initializeMatrixOutput(2, matrix[2].length, matrix[2][0].length);
    }

    public void add () {
        if (matrix[0].length == matrix[2].length && matrix[0][0].length == matrix[2][0].length)
        {
            matrix[1] = new double[matrix[0].length][matrix[0][0].length];
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[0][0].length; j++) {
                    matrix[1][i][j] = matrix[0][i][j] + matrix[2][i][j];
                }
            }
            middleResult();
        } else {
            throwError("Cannot add matrices, because their dimensions are not equal!");
        }
    }

    public void subtract () {
        if (matrix[0].length == matrix[2].length && matrix[0][0].length == matrix[2][0].length)
        {
            matrix[1] = new double[matrix[0].length][matrix[0][0].length];
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[0][0].length; j++) {
                    matrix[1][i][j] = matrix[0][i][j] - matrix[2][i][j];
                }
            }
            middleResult();
        } else {
            throwError("Cannot subtract matrices, because their dimensions are not equal!");
        }
    }

    public void multiply () {
        if (matrix[0][0].length == matrix[2].length) {
            matrix[1] = new double[matrix[0].length][matrix[2][0].length];
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[2][0].length; j++) {
                    for (int k = 0; k < matrix[2].length; k++) {
                        matrix[1][i][j] += matrix[0][i][k] * matrix[2][k][j];
                    }
                }
            }
            middleResult();
        } else {
            throwError("Cannot multiply matrices, because the number of columns in the first matrix does not equal the number of rows in the second matrix!");
        }
    }

    private void throwError (String message) {
        JFrame frame = new JFrame();
        JLabel error = new JLabel(message);
        frame.add(error);
        frame.pack();
        frame.setVisible(true);
    }
}
