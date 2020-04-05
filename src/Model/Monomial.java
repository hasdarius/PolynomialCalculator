package Model;

public class Monomial implements Comparable<Monomial> {
    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    private Double coefficient;
    private Double power;

    @Override
    public int compareTo(Monomial monomial) {
        if (this.getPower() > monomial.getPower())
            return 1;
        else if (this.getPower() < monomial.getPower())
            return -1;
        else if (this.getCoefficient() > monomial.getCoefficient())
            return 1;
        else if (this.getCoefficient() < monomial.getCoefficient())
            return -1;
        else
            return 0;
    }
}
