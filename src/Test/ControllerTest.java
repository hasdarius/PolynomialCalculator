package Test;

import Controller.Controller;
import View.View;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void validation() {
        //some tests for the validation method
        boolean result= new Controller(new View("")).validation("0x","0x");
        Assert.assertEquals(result,false); //0 is not permitted as a coefficient
        result= new Controller(new View("")).validation("1x","x^3-7x^4+x^5+2");
        Assert.assertEquals(result,true); //1 is perrmitted as a coefficient and the polynomial doesn't have to be ordered by power
        result= new Controller(new View("")).validation("012","012");
        Assert.assertEquals(result,false); //a number cannot begin with 0
        result= new Controller(new View("")).validation("01x","01x");
        Assert.assertEquals(result,false); //a coeeficient cannot begin with 0
        result= new Controller(new View("")).validation("x^02","x^02");
        Assert.assertEquals(result,false); // a power of x cannot begin with 0 ors be 0
        // the method validation is implemented with two strings as parameters because most operations make use of two polynomials
        //to demonstrate functionality, I tried to use the same string as both parameters in some cases
        result= new Controller(new View("")).validation("-7x^5+4x^8-x^3-x-1","3x^4+3x^6-x^7+x+12");
        Assert.assertEquals(result,true); // these pair of polynomials are valid
    }
}