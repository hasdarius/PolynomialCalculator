package Test;

import Operations.Operations;
import Model.Polynomial;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OperationsTest {

    @Test
    public void addition() {
        Polynomial polynomial1=new Polynomial("-7x^5+4x^8-x^3-x-1");
        Polynomial polynomial2=new Polynomial("3x^4+3x^6-x^7+x+12");
        Polynomial result=new Operations().addition(polynomial1,polynomial2);
        Assert.assertEquals(result.toString(),"4x^8-x^7+3x^6-7x^5+3x^4-x^3+11");
        Polynomial polynomial3=new Polynomial("-x^2-x-1");
        Polynomial polynomial4=new Polynomial("-2x^2+x-2");
        Polynomial result2= new Operations().addition(polynomial3,polynomial4);
        Assert.assertEquals(result2.toString(),"-3x^2-3");
    }

    @Test
    public void difference() {
        Polynomial polynomial1=new Polynomial("-7x^5+4x^8-x^3-x-1");
        Polynomial polynomial2=new Polynomial("3x^4+3x^6-x^7+x+12");
        Polynomial result=new Operations().difference(polynomial1,polynomial2);
        Assert.assertEquals(result.toString(),"4x^8+x^7-3x^6-7x^5-3x^4-x^3-2x-13");
        Polynomial polynomial3=new Polynomial("-x^2-x-1");
        Polynomial polynomial4=new Polynomial("-2x^2+x-2");
        Polynomial result2= new Operations().difference(polynomial3,polynomial4);
        Assert.assertEquals(result2.toString(),"x^2-2x+1");
    }

    @Test
    public void multiplication() {
        Polynomial polynomial1=new Polynomial("-7x^5+4x^8-x^3-x-1");
        Polynomial polynomial2=new Polynomial("3x^4+3x^6-x^7+x+12");
        Polynomial result=new Operations().multiplication(polynomial1,polynomial2);
        Assert.assertEquals(result.toString(),"-4x^15+12x^14+19x^12-2x^11+x^10-20x^9+49x^8-5x^7-10x^6-87x^5-4x^4-12x^3-x^2-13x-12");
        Polynomial polynomial3=new Polynomial("-x^2-x-1");
        Polynomial polynomial4=new Polynomial("-2x^2+x-2");
        Polynomial result2= new Operations().multiplication(polynomial3,polynomial4);
        Assert.assertEquals(result2.toString(),"2x^4+x^3+3x^2+x+2");
    }

    @Test
    public void division() {
        Polynomial polynomial1=new Polynomial("-7x^5+4x^8-x^3-x-1");
        Polynomial polynomial2=new Polynomial("3x^4+3x^6-x^7+x+12");
        List<Polynomial> result=new Operations().division(polynomial1,polynomial2);
        Assert.assertEquals(result.get(0).toString(),"-4x-12"); //quotient
        Assert.assertEquals(result.get(1).toString(),"36x^6+5x^5+36x^4-x^3+4x^2+59x+143");//remainder
        Polynomial polynomial3=new Polynomial("-x^2-x-1");
        Polynomial polynomial4=new Polynomial("-2x^2+x-2");
        List<Polynomial> result2=new Operations().division(polynomial3,polynomial4);
        Assert.assertEquals(result2.get(0).toString(),"0.5"); //quotient
        Assert.assertEquals(result2.get(1).toString(),"-1.5x"); //remainder
    }

    @Test
    public void integrate() {
        Polynomial polynomial1=new Polynomial("-7x^5+4x^8-x^3-x-1");
        Polynomial polynomial2=new Polynomial("3x^4+3x^6-x^7+x+12");
        Polynomial polynomial3=new Polynomial("1");
        Polynomial result1=new Operations().integrate(polynomial1);
        Polynomial result2=new Operations().integrate(polynomial2);
        Polynomial result3=new Operations().integrate(polynomial3);
        Assert.assertEquals(result1.toString(),"0.44x^9-1.17x^6-0.25x^4-0.5x^2-x");
        Assert.assertEquals(result2.toString(),"-0.13x^8+0.43x^7+0.6x^5+0.5x^2+12x");
        Assert.assertEquals(result3.toString(),"x");

    }

    @Test
    public void derivate() {
        Polynomial polynomial1=new Polynomial("-7x^5+4x^8-x^3-x-1");
        Polynomial polynomial2=new Polynomial("3x^4+3x^6-x^7+x+12");
        Polynomial polynomial3=new Polynomial("1");
        Polynomial result1=new Operations().derivate(polynomial1);
        Polynomial result2=new Operations().derivate(polynomial2);
        Polynomial result3=new Operations().derivate(polynomial3);
        Assert.assertEquals(result1.toString(),"32x^7-35x^4-3x^2-1");
        Assert.assertEquals(result2.toString(),"-7x^6+18x^5+12x^3+1");
        Assert.assertEquals(result3.toString(),"0");
    }
}