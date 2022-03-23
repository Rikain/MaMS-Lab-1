package pl.edu.pwr.lab1.i242571;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private BMI bmi_calc;

    @Before
    public void initObjects() {
        bmi_calc = new BMI();
    }

    @Test
    public void bmi_validate_isCorrect() {
        assertFalse(BMI.validateInput("5.3", ""));
        assertFalse(BMI.validateInput("", "8"));
        assertFalse(BMI.validateInput("", ""));
        assertTrue(BMI.validateInput("1.5", "9.3"));
        assertTrue(BMI.validateInput("5", "7"));
        assertTrue(BMI.validateInput("7", "8.3"));
        assertFalse(BMI.validateInput("0", "60"));
        assertTrue(BMI.validateInput("0.1", "40"));
        assertFalse(BMI.validateInput("0.0", "70"));
        assertTrue(BMI.validateInput("0.1", "0"));
    }

    @Test
    public void bmi_metric_calc_isCorrect() {
        assertEquals("21,6", bmi_calc.calculateBMI("180","70",true));
        assertEquals("25,5", bmi_calc.calculateBMI("140","50",true));
        assertEquals("15,0", bmi_calc.calculateBMI("200","60",true));
    }

    @Test
    public void bmi_imperial_calc_isCorrect() {
        assertEquals("23,4", bmi_calc.calculateBMI("60","120",false));
        assertEquals("27,5", bmi_calc.calculateBMI("53","110",false));
        assertEquals("14,1", bmi_calc.calculateBMI("64","82",false));
    }

}