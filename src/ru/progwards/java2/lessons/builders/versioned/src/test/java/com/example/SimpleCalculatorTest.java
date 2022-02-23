package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {

    @RunWith(Parameterized.class)
    public static class SumCalculatorTest {
        @Parameterized.Parameter
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 0, 0},
                    {5, 0, 5},
                    {-5, -5, -10},
                    {34, 55, 89},
                    {-34, -55, -89}
            });
        }

        @Test
        public void testWithParams() {
            Assert.assertEquals(expected, SimpleCalculator.sum(val1, val2));
        }
    }

    public static class CheckExceptionSumTest {

        @Test(expected = ArithmeticException.class)
        public void CheckSumException() {
            SimpleCalculator.sum(Integer.MAX_VALUE, 1);
        }
    }

    @RunWith(Parameterized.class)
    public static class DiffCalculatorTest {
        @Parameterized.Parameter
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) - ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 0, 0},
                    {5, 0, 5},
                    {-5, -5, 0},
                    {34, 55, -21},
                    {-34, -55, 21}
            });
        }

        @Test
        public void testWithParams() {
            Assert.assertEquals(expected, SimpleCalculator.diff(val1, val2));
        }
    }

    public static class CheckExceptionDiffTest {

        @Test(expected = ArithmeticException.class)
        public void CheckSumException() {
            SimpleCalculator.diff(Integer.MIN_VALUE, 1);
        }
    }

    @RunWith(Parameterized.class)
    public static class MulCalculatorTest {
        @Parameterized.Parameter
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) * ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {1, 0, 0},
                    {5, 0, 0},
                    {-5, -5, 25},
                    {34, -55, -1870},
                    {-34, -55, 1870}
            });
        }

        @Test
        public void testWithParams() {
            Assert.assertEquals(expected, SimpleCalculator.mult(val1, val2));
        }
    }

    public static class CheckExceptionMulTest {

        @Test(expected = ArithmeticException.class)
        public void CheckSumException() {
            SimpleCalculator.mult(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
    }

    @RunWith(Parameterized.class)
    public static class DivCalculatorTest {
        @Parameterized.Parameter
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) / ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {-5, -5, 1},
                    {55, 55, 1},
                    {100, 2, 50},
                    {100, 3, 33}
            });
        }

        @Test
        public void testWithParams() {
            Assert.assertEquals(expected, SimpleCalculator.div(val1, val2));
        }
    }

    public static class CheckExceptionDivTest {

        @Test(expected = ArithmeticException.class)
        public void CheckSumException() {
            SimpleCalculator.div(5, 0);
        }
    }
}

