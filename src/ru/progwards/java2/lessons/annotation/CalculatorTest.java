package ru.progwards.java2.lessons.annotation;

import org.junit.Assert;

public class CalculatorTest {

    private SimpleCalculator simpleCalculator;

    @Before
    public void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @Test(priority = 1)
    public void testSum() {
        Assert.assertEquals(8, SimpleCalculator.mult(5, 3));
    }

    @After
    public void deleteInstance() {
        simpleCalculator = null;
    }
}
