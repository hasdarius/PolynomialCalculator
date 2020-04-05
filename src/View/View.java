package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    //public static final long serialVersionUID=1L;
    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    public JButton getButtonAnotherOperation() {
        return buttonAnotherOperation;
    }

    public JButton getAdder() {
        return adder;
    }

    public JButton getSubtraction() {
        return subtraction;
    }

    public JButton getMultiplier() {
        return multiplier;
    }

    public JButton getDivider() {
        return divider;
    }

    public JButton getIntegrator() {
        return integrator;
    }

    public JButton getDerivative() {
        return derivative;
    }

    private JButton adder = new JButton("+");
    private JButton subtraction = new JButton("-");
    private JButton multiplier = new JButton("x");
    private JButton divider = new JButton("/");
    private JButton integrator = new JButton("∫");
    private JButton derivative = new JButton("∂");
    private JButton buttonAnotherOperation = new JButton("Another Operation");


    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getFirsPolynomial() {
        return firsPolynomial;
    }

    public void setFirsPolynomial(JTextField firsPolynomial) {
        this.firsPolynomial = firsPolynomial;
    }

    public JTextField getSecondPolynomial() {
        return secondPolynomial;
    }

    public void setSecondPolynomial(JTextField secondPolynomial) {
        this.secondPolynomial = secondPolynomial;
    }

    private JTextField firsPolynomial = new JTextField(80);
    private JTextField secondPolynomial = new JTextField(80);
    private JLabel label = new JLabel("The result will be displayed here");

    Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        c.gridx = 1;
        c.gridy = 0;
        pane.add(adder, c);
        adder.addActionListener(controller);

        c.gridx = 2;
        c.gridy = 0;
        pane.add(subtraction, c);
        subtraction.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 1;
        pane.add(multiplier, c);
        multiplier.addActionListener(controller);

        c.gridx = 2;
        c.gridy = 1;
        pane.add(divider, c);
        divider.addActionListener(controller);

        c.gridx = 1;
        c.gridy = 2;
        pane.add(derivative, c);
        derivative.addActionListener(controller);

        c.gridx = 2;
        c.gridy = 2;
        pane.add(integrator, c);
        integrator.addActionListener(controller);

        c.gridx = 0;
        c.gridy = 0;
        pane.add(firsPolynomial, c);

        c.gridx = 0;
        c.gridy = 1;
        pane.add(secondPolynomial, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(label, c);

        c.gridx = 0;
        c.gridy = 3;
        pane.add(buttonAnotherOperation, c);
        buttonAnotherOperation.addActionListener(controller);

        this.add(pane);

    }

}
