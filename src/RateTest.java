import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RateTest {
    ArrayList<Period> discountPeriods;
    ArrayList<Period> normalPeriods;
    ArrayList<Period> calculateChargeDiscountPeriod;
    ArrayList<Period> calculateChargeNormalPeriod;
    BigDecimal b;
    Period p;

    //Test 1: Check to see if CarParkFind Student is valid
    @org.junit.Test
    public void kindStudentIsValid() {
        Rate r = new Rate(CarParkKind.Student, new BigDecimal(4), new BigDecimal(3), discountPeriods, normalPeriods);
    }
    
    //Test 2: Check to see if CarParkFind staff is valid
    @org.junit.Test
    public void kindStaffIsValid() {
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4), new BigDecimal(3), discountPeriods, normalPeriods);
    }
    
    //Test 3: Check to see if CarParkFind management is valid
    @org.junit.Test
    public void kindManagementIsValid() {
        Rate r = new Rate(CarParkKind.Management, new BigDecimal(4), new BigDecimal(3), discountPeriods, normalPeriods);
    }
    
    //Test 4: Check to see if CarParkFind Visitor is valid
    @org.junit.Test
    public void kindVisitorIsValid() {
        Rate r = new Rate(CarParkKind.Visitor, new BigDecimal(4), new BigDecimal(3), discountPeriods, normalPeriods);
    }
    
    //Test 5: Check to see if CarParkFind null is valid
    @org.junit.Test
    public void kindNullIsValid() {
        Rate r = new Rate(null, new BigDecimal(4), new BigDecimal(3), discountPeriods, normalPeriods);
    }

    //Test 6: Check to see if normalRate is greater than 0
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateGreaterThanZero(){
        Rate r = new Rate(CarParkKind.Visitor, new BigDecimal(-1),new BigDecimal(5),discountPeriods,normalPeriods);
    }
    //Test 7: Check to see if NormalRate is less than the discountRate
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalLessThanDiscount(){
        Rate r = new Rate(CarParkKind.Management, new BigDecimal(5), new BigDecimal(10), discountPeriods, normalPeriods);
    }

    //Test 8: Check if normalRate is less than zero
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalLessThanZero(){
        Rate r = new Rate(CarParkKind.Management, new BigDecimal(-1), new BigDecimal(10), discountPeriods, normalPeriods);
    }
    
    //Test 9: Check if normalRate and discountRate are the same
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalSameAsDiscount(){
        Rate r = new Rate(CarParkKind.Management, new BigDecimal(10), new BigDecimal(10), discountPeriods, normalPeriods);
    }
    
    //Test 10: Check if normalRate is invalid entry
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalInvalidEntry(){
        Rate r = new Rate(CarParkKind.Management, new BigDecimal('z'), new BigDecimal(10), discountPeriods, normalPeriods);
    }
    
    //Test 11: Check if normalRate is greater than discount
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalGreaterThanDiscount(){
        Rate r = new Rate(CarParkKind.Management, new BigDecimal(15), new BigDecimal(10), discountPeriods, normalPeriods);
    }
    
    //Test 12: Check if normalRate and discount are the same
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalEqualsDiscount(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(11), new BigDecimal(11), discountPeriods, normalPeriods);
    }
    
    //Test 13: Check if normalRate is less than discount
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountGreaterThanNormal(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(5), new BigDecimal(8), discountPeriods, normalPeriods);
    }
    
    //Test 14: Check if normalRate is null
    @org.junit.Test (expected = NullPointerException.class)
    public void normalEqualsNull(){
        Rate r = new Rate(CarParkKind.Staff, null, new BigDecimal(11), discountPeriods, normalPeriods);
    }
    
    //Test 15: Check if discount is null
    @org.junit.Test (expected = NullPointerException.class)
    public void discountEqualsNull(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10), null, discountPeriods, normalPeriods);
    }
    
    //Test 16: Check if discount is invalid
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountInvalid(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10), new BigDecimal('z'), discountPeriods, normalPeriods);
    }
    
    //Test 17: Check if both less than zero
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void bothLessThanZero(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(-1), new BigDecimal(-2), discountPeriods, normalPeriods);
    }
    
    //Test 18: Check if discount is equal to zero
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountEqualsZero(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10), new BigDecimal(0), discountPeriods, normalPeriods);
    }
    
    //Test 19: Check if discount is less than zero
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountLessThanZero(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10), new BigDecimal(-1), discountPeriods, normalPeriods);
    }
    
    //Test 20: Check if normal is equal to zero
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalEqualsZero(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(0), new BigDecimal(10), discountPeriods, normalPeriods);
    }
    
  /*--------------------------------------------Calculator Tests-----------------------------------------------------*/
    
    // Test 21:  Check for free stay between hours of 1 and 7
    @org.junit.Test
    public void checkForFreeStay(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4),new BigDecimal(1), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(2,6);
        //BigDecimal value being difference of periods
        assertEquals(BigDecimal.valueOf(4), r.calculate(hoursStayed));
    }
    
    // Test 22:  Check for free stay and normal
    @org.junit.Test
    public void freeAndNormalStay(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4),new BigDecimal(2), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //construct for normalCharge
        calculateChargeNormalPeriod = new ArrayList<Period>() {{
            add(new Period(5, 10));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(9, 12);
        //BigDecimal value being difference of periods
        assertEquals(BigDecimal.valueOf(8), r.calculate(hoursStayed));
    }
    
    // Test 23:  Check for normal and discount stay
    @org.junit.Test
    public void normalAndDiscountStay(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4),new BigDecimal(1), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //construct for normalCharge
        calculateChargeNormalPeriod = new ArrayList<Period>() {{
            add(new Period(5, 10));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(3, 9);
        //BigDecimal value being difference of periods
        assertEquals(BigDecimal.valueOf(13), r.calculate(hoursStayed));
    }
    
    // Test 24:  Check for discount and free stay
    @org.junit.Test
    public void discountAndFreeStay(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4),new BigDecimal(1), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(6, 8);
        //BigDecimal value being difference of periods
        assertEquals(BigDecimal.valueOf(2), r.calculate(hoursStayed));
    }
    
    // Test 25: All three periods
    @org.junit.Test
    public void threePeriods(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4),new BigDecimal(1), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //construct for normalCharge
        calculateChargeNormalPeriod = new ArrayList<Period>() {{
            add(new Period(5, 10));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(6, 18);
        //BigDecimal value being difference of periods
        assertEquals(BigDecimal.valueOf(26), r.calculate(hoursStayed));
    }
    
    // Test 26: Check morning times
    @org.junit.Test
    public void checkMorning(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4),new BigDecimal(1), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(0, 3);
        //BigDecimal value being difference of periods
        assertEquals(BigDecimal.valueOf(3), r.calculate(hoursStayed));
    }
    
    // Test 27: Check night times
    @org.junit.Test
    public void checkNight(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(4),new BigDecimal(1), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(21, 23);
        //BigDecimal value being difference of periods
        assertEquals(BigDecimal.valueOf(0), r.calculate(hoursStayed));
    }
    
    // Test 28: Discount and normal crossover
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountNormalCross(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10),new BigDecimal(5), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //construct for normalCharge
        calculateChargeNormalPeriod = new ArrayList<Period>() {{
            add(new Period(5, 10));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(3, 9);
    }
    
    // Test 29: Over the hours (25) 
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void overHours(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10),new BigDecimal(5), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //construct for normalCharge
        calculateChargeNormalPeriod = new ArrayList<Period>() {{
            add(new Period(5, 10));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(22, 25);       
    }
    
    // Test 30: under the hours (-1) 
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void underHours(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10),new BigDecimal(5), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(1, 7));
        }};
        //construct for normalCharge
        calculateChargeNormalPeriod = new ArrayList<Period>() {{
            add(new Period(5, 10));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(-1, 5);       
    }
    
    // Test 31: normal and discount crossover
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalDiscountCross(){
        Rate r = new Rate(CarParkKind.Staff, new BigDecimal(10),new BigDecimal(5), calculateChargeDiscountPeriod,calculateChargeNormalPeriod);
        //construct for calculateCharge
        calculateChargeDiscountPeriod = new ArrayList<Period>() {{
            add(new Period(9, 12));
        }};
        //construct for normalCharge
        calculateChargeNormalPeriod = new ArrayList<Period>() {{
            add(new Period(5, 10));
        }};
        //hours stayed in car park
        Period hoursStayed = new Period(7, 13);
    }
}