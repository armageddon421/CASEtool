import static org.junit.Assert.*;

import java.util.ArrayList;

import model.FunctionPointMethod;
import model.Project;

import org.junit.Test;

import additional.Field;
import additional.Type;


public class FunctionPointMethodTest {

	@Test
	public void testCalculate() {
		FunctionPointMethod tester = new FunctionPointMethod();
		Project project = new Project("neu");
		project.addFunctionRequirement("f1");
		
		
		Field result = tester.calculate(project);
		float fp = (float) result.getChildren().get(0).getValue();
		double fpresult = (double) fp;
		
		float duration = (float) result.getChildren().get(1).getValue(); 
		double durResult = (double) duration;
		
		float manpower = (float) result.getChildren().get(2).getValue(); 
		double manResult = (double) manpower;
		
		float effort = (float)  result.getChildren().get(3).getValue(); 
		double effortResult = (double) effort;
		
		assertEquals("Gewichtete FP", 2.1, fpresult, 0.01);
		assertEquals("Dauer", 1.3455, durResult, 0.1);
		assertEquals("Personen", 0.014, manResult, 0.1);
		assertEquals("Aufwand", 0.018837, effortResult, 0.1);
	}

}
