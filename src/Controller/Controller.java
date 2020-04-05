package Controller;

import Operations.Operations;
import Model.Polynomial;
import Operations.OperationsInterface;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Controller implements ActionListener {
    private View view;


    public Controller(View view) {
        this.view = view;
    }

    public boolean validation(String firstPolynomial, String secondPolynomial) {
        firstPolynomial = firstPolynomial.replace("-", "+-");
        if (firstPolynomial.startsWith("+-"))
            firstPolynomial = firstPolynomial.substring(1, firstPolynomial.length());
        secondPolynomial = secondPolynomial.replace("-", "+-");
        if (secondPolynomial.startsWith("+-"))
            secondPolynomial = secondPolynomial.substring(1, secondPolynomial.length());
        String regex = "[+]";
        List<String> monomialsOfFirstPolynomial = new ArrayList<>();
        List<String> monomialsOfSecondPolynomial = new ArrayList<>();
        monomialsOfFirstPolynomial = Arrays.asList(firstPolynomial.split(regex));
        monomialsOfSecondPolynomial = Arrays.asList(secondPolynomial.split(regex));
        for (String iterator : monomialsOfFirstPolynomial) {
            if ((!iterator.matches("-?[1-9]\\d*[x](\\^[1-9]\\d*)?")) && (!iterator.matches("0")) && (!iterator.matches("-?[1-9]\\d*")) && (!iterator.matches("-?[x](\\^[1-9]\\d*)?"))) {
                return false;
            }

        }
        for (String iterator1 : monomialsOfSecondPolynomial) {
            if ((!iterator1.matches("-?[1-9]\\d*[x](\\^[1-9]\\d*)?")) && (!iterator1.matches("0")) && (!iterator1.matches("-?[1-9]\\d*")) && (!iterator1.matches("-?[x](\\^[1-9]\\d*)?"))) {
                return false;
            }
        }
        return true;


    }


    public void openPopUp(String firstNumber, String secondNumber) {
        JOptionPane.showMessageDialog(null, "Please introduce valid polynomials", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        OperationsInterface operations = new Operations();
        Object source = actionEvent.getSource();
        if (source == view.getButtonAnotherOperation()) {
            view.getFirsPolynomial().setText("");
            view.getSecondPolynomial().setText("");
            view.getLabel().setText("The result will be displayed here");
        }
        String firstPolynomial, secondPolynomial;
        firstPolynomial = view.getFirsPolynomial().getText();
        secondPolynomial = view.getSecondPolynomial().getText();
        if (validation(firstPolynomial, secondPolynomial) == false && (source == view.getAdder() || source == view.getMultiplier() || source == view.getDivider() || source == view.getSubtraction())) {
            openPopUp(firstPolynomial, secondPolynomial);
            view.getFirsPolynomial().setText("");
            view.getSecondPolynomial().setText("");
            view.getLabel().setText("The result will be displayed here");
        } else if (validation(firstPolynomial, firstPolynomial) == false && (source == view.getDerivative() || source == view.getIntegrator())) {
            openPopUp(firstPolynomial, firstPolynomial);
            view.getFirsPolynomial().setText("");
            view.getSecondPolynomial().setText("");
            view.getLabel().setText("The result will be displayed here");
        } else {
            Polynomial polynomial1 = new Polynomial(firstPolynomial);
            Polynomial polynomial2 = new Polynomial(secondPolynomial);
            if (source == view.getAdder()) {
                view.getLabel().setText(operations.addition(polynomial1, polynomial2).toString());
            }
            if (source == view.getSubtraction()) {
                view.getLabel().setText(operations.difference(polynomial1, polynomial2).toString());
            }
            if (source == view.getMultiplier()) {
                view.getLabel().setText(operations.multiplication(polynomial1, polynomial2).toString());
            }
            if (source == view.getDivider()) {
                List<Polynomial> results = new LinkedList<>();
                results = operations.division(polynomial1, polynomial2);
                if (results != null)
                    view.getLabel().setText("Quotient: " + results.get(0).toString() + "     " + "Remainder: " + results.get(1).toString());
            }
            if (source == view.getDerivative()) {
                view.getLabel().setText(operations.derivate(polynomial1).toString());
            }
            if (source == view.getIntegrator()) {
                view.getLabel().setText(operations.integrate(polynomial1).toString());
            }
        }
    }

}

