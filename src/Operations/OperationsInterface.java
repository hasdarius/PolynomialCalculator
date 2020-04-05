package Operations;

import Model.Polynomial;

import java.util.List;

public interface OperationsInterface {
    public Polynomial addition(Polynomial polynomial1, Polynomial polynomial2);
    public  Polynomial difference(Polynomial polynomial1, Polynomial polynomial2);
    public Polynomial multiplication(Polynomial polynomial1, Polynomial polynomial2);
    public List<Polynomial> division(Polynomial polynomial1, Polynomial polynomial2);
    public Polynomial integrate(Polynomial polynomial1);
    public Polynomial derivate(Polynomial polynomial1);

}
