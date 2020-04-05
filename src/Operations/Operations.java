package Operations;

import Model.Monomial;
import Model.Polynomial;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Operations implements OperationsInterface {

    public Polynomial addition(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        Integer sizeOfFirstPolynomial = polynomial1.getListOfMonomials().size();
        Integer sizeOfSecondPolynomial = polynomial2.getListOfMonomials().size();
        Integer iterator1 = 0;
        Integer iterator2 = 0;
        while (iterator1 < sizeOfFirstPolynomial && iterator2 < sizeOfSecondPolynomial) {
            Monomial monomial = new Monomial();
            if (polynomial1.getListOfMonomials().get(iterator1).getPower() > polynomial2.getListOfMonomials().get(iterator2).getPower()) {
                monomial = polynomial1.getListOfMonomials().get(iterator1++);
            } else if (polynomial1.getListOfMonomials().get(iterator1).getPower() < polynomial2.getListOfMonomials().get(iterator2).getPower()) {
                monomial = polynomial2.getListOfMonomials().get(iterator2++);
            } else {
                monomial = polynomial1.getListOfMonomials().get(iterator1);
                monomial.setCoefficient(polynomial1.getListOfMonomials().get(iterator1++).getCoefficient() + polynomial2.getListOfMonomials().get(iterator2++).getCoefficient());
            }
            if (monomial.getCoefficient() != 0)
                result.addElement(monomial);
        }
        while (iterator1 < sizeOfFirstPolynomial || iterator2 < sizeOfSecondPolynomial) {
            Monomial monomial = new Monomial();
            if (iterator1 < sizeOfFirstPolynomial) {
                monomial = polynomial1.getListOfMonomials().get(iterator1++);
            } else {
                monomial = polynomial2.getListOfMonomials().get(iterator2++);
            }
            if (monomial.getCoefficient() != 0)
                result.addElement(monomial);
        }
        return result;
    }


    public  Polynomial difference(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        Integer sizeOfFirstPolynomial = polynomial1.getListOfMonomials().size();
        Integer sizeOfSecondPolynomial = polynomial2.getListOfMonomials().size();
        Integer iterator1 = 0;
        Integer iterator2 = 0;
        while (iterator1 < sizeOfFirstPolynomial && iterator2 < sizeOfSecondPolynomial) {
            Monomial monomial = new Monomial();
            if (polynomial1.getListOfMonomials().get(iterator1).getPower() > polynomial2.getListOfMonomials().get(iterator2).getPower()) {
                monomial = polynomial1.getListOfMonomials().get(iterator1++);
            } else if (polynomial1.getListOfMonomials().get(iterator1).getPower() < polynomial2.getListOfMonomials().get(iterator2).getPower()) {
                monomial = polynomial2.getListOfMonomials().get(iterator2);
                monomial.setCoefficient(0 - polynomial2.getListOfMonomials().get(iterator2++).getCoefficient());
            } else {
                monomial = polynomial1.getListOfMonomials().get(iterator1);
                monomial.setCoefficient(polynomial1.getListOfMonomials().get(iterator1++).getCoefficient() - polynomial2.getListOfMonomials().get(iterator2++).getCoefficient());
            }
            if (monomial.getCoefficient() != 0)
                result.addElement(monomial);
        }
        while (iterator1 < sizeOfFirstPolynomial || iterator2 < sizeOfSecondPolynomial) {
            Monomial monomial = new Monomial();
            if (iterator1 < sizeOfFirstPolynomial) {
                monomial = polynomial1.getListOfMonomials().get(iterator1++);
            } else {
                monomial = polynomial2.getListOfMonomials().get(iterator2);
                monomial.setCoefficient(0 - polynomial2.getListOfMonomials().get(iterator2++).getCoefficient());
            }
            if (monomial.getCoefficient() != 0)
                result.addElement(monomial);
        }
        return result;
    }

    public Polynomial multiplication(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial1 : polynomial1.getListOfMonomials()) {
            for (Monomial monomial2 : polynomial2.getListOfMonomials()) {
                Monomial monomialToAdd = new Monomial();
                monomialToAdd.setCoefficient(monomial1.getCoefficient() * monomial2.getCoefficient());
                monomialToAdd.setPower(monomial1.getPower() + monomial2.getPower());
                result.addElement(monomialToAdd); //we assured that the product of the coefficients is not 0 in the verification where only monomials with non-zero coefficients are
                //added to the polynomial
            }
        }
        result.getListOfMonomials().sort(Monomial::compareTo);
        Collections.reverse(result.getListOfMonomials());
        result.avoidDuplicates();

        return result;
    }

    public List<Polynomial> division(Polynomial polynomial1, Polynomial polynomial2) {
        List<Polynomial> results = new LinkedList<>();
        if (polynomial2.getListOfMonomials().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cannot divide by 0!!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        while (polynomial1.getListOfMonomials().size() != 0 && polynomial1.getListOfMonomials().get(0).getPower() >= polynomial2.getListOfMonomials().get(0).getPower()) {
            Monomial monomial = new Monomial();
            monomial.setPower(polynomial1.getListOfMonomials().get(0).getPower() - polynomial2.getListOfMonomials().get(0).getPower());
            monomial.setCoefficient(polynomial1.getListOfMonomials().get(0).getCoefficient() / polynomial2.getListOfMonomials().get(0).getCoefficient());
            Polynomial partialQuotient = new Polynomial();
            partialQuotient.addElement(monomial);
            quotient = this.addition(partialQuotient, quotient);
            remainder = this.multiplication(partialQuotient, polynomial2);
            polynomial1 = this.difference(polynomial1, remainder);

        }
        remainder = polynomial1;
        results.add(quotient);
        results.add(remainder);
        return results;
    }

    public Polynomial integrate(Polynomial polynomial1) {
        for (Monomial monomial : polynomial1.getListOfMonomials()) {
            monomial.setPower((monomial.getPower() + 1));
            BigDecimal newCoefficient = BigDecimal.valueOf(monomial.getCoefficient() / monomial.getPower());
            newCoefficient = newCoefficient.setScale(2, RoundingMode.HALF_UP);
            monomial.setCoefficient(newCoefficient.doubleValue());
        }
        return polynomial1;
    }

    public Polynomial derivate(Polynomial polynomial1) {
        for (Monomial monomial : polynomial1.getListOfMonomials()) {
            monomial.setCoefficient(monomial.getCoefficient() * monomial.getPower());
            monomial.setPower((monomial.getPower() - 1));
            if (monomial.getPower() < 0)
                polynomial1.getListOfMonomials().remove(monomial);
        }
        return polynomial1;
    }
}
