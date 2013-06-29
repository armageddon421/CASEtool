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
		Field expected = new Field("FP result", Type.String, false, this, "FP Ergebnis");
		float weightedFP = 0;
		float duration = 0;
		float manpower = 0;
		float effort = 0;
		expected.addChild(new Field("Weighted FP", Type.Float, false, this, weightedFP));
		expected.addChild(new Field("Duration", Type.Float, false, this, duration));
		expected.addChild(new Field("Manpower", Type.Float, false, this, manpower));
		expected.addChild(new Field("Effort", Type.Float, false, this, effort));
		assertEquals("Dumm gelaufen", expected, tester.calculate(project));
	}

}
