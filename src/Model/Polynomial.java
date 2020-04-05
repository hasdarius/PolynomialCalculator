package Model;

import java.util.*;

public class Polynomial {
    public List<Monomial> getListOfMonomials() {
        return polynomial;
    }

    private List<Monomial> polynomial;

    public Polynomial() {
        polynomial = new LinkedList<Monomial>();
    }

    public Polynomial(String string) {
        polynomial = new LinkedList<Monomial>();
        string = string.replace("-", "+-");
        if (string.startsWith("+-"))
            string = string.substring(1);
        List<String> monomials;
        monomials = Arrays.asList(string.split("[+]"));
        for (String iterator : monomials) {
            List<String> arguments = new ArrayList();
            Monomial monomial = new Monomial();
            if (iterator.contains("x^")) {
                arguments = Arrays.asList(iterator.split("[x]\\^"));
                if (arguments.get(0).isEmpty())
                    monomial.setCoefficient(1D);
                else if (arguments.get(0).equals("-"))
                    monomial.setCoefficient(-1D);
                else
                    monomial.setCoefficient(Double.parseDouble(arguments.get(0)));
                monomial.setPower(Double.parseDouble(arguments.get(1)));
            } else if (iterator.contains("x")) {
                arguments = Arrays.asList(iterator.split("[x]"));
                monomial.setPower(1D);
                if (arguments.isEmpty())
                    monomial.setCoefficient(1D);
                else if (arguments.get(0).equals("-"))
                    monomial.setCoefficient(-1D);
                else
                    monomial.setCoefficient(Double.parseDouble(arguments.get(0)));
            } else {
                if (iterator.isEmpty())
                    monomial.setCoefficient(0D);
                else monomial.setCoefficient(Double.parseDouble(iterator));
                monomial.setPower(0D);
            }
            if (!monomial.getCoefficient().equals(0D) && !monomial.getCoefficient().equals(-0D))
                polynomial.add(monomial);
        }
        polynomial.sort(Monomial::compareTo);
        Collections.reverse(polynomial);
        this.avoidDuplicates();
    }

    public List<Monomial> addElement(Monomial monomial) {
        polynomial.add(monomial);
        return polynomial;
    }

    public String toString() {
        String polynomialToString = new String();
        for (Monomial monomial : this.getListOfMonomials()) {
            if (monomial.getCoefficient() % 1 != 0) {
                polynomialToString += monomial.getCoefficient().toString();
            } else {
                polynomialToString += monomial.getCoefficient().longValue();
            }
            if (monomial.getPower() != 0)
                if (monomial.getPower() != 1)
                    polynomialToString += "x^" + monomial.getPower().longValue();
                else
                    polynomialToString += "x";
            polynomialToString += "+";
        }
        if (!polynomialToString.isEmpty()) {
            polynomialToString = polynomialToString.substring(0, polynomialToString.length() - 1);
            polynomialToString = polynomialToString.replace("+-", "-");
            polynomialToString = polynomialToString.replace("1x", "x");
        } else polynomialToString += "0";
        return polynomialToString;
    }

    public void avoidDuplicates() {
        Integer iterator = 0;
        while (iterator < getListOfMonomials().size() - 1) {
            if (this.getListOfMonomials().get(iterator).getPower().equals(this.getListOfMonomials().get(iterator + 1).getPower())) {
                this.getListOfMonomials().get(iterator).setCoefficient(this.getListOfMonomials().get(iterator).getCoefficient() + this.getListOfMonomials().get(iterator + 1).getCoefficient());
                this.getListOfMonomials().remove(this.getListOfMonomials().get(iterator + 1));
            } else iterator++;
        }
    }

}
