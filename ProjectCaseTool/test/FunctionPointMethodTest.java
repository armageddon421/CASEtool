import static org.junit.Assert.assertEquals;
import model.FunctionPointMethod;
import model.Project;

import org.junit.Test;

import additional.ComplexityEnum;
import additional.Field;
import additional.FunctionPointEnum;


public class FunctionPointMethodTest {

	@Test
	public void testCalculate() {
		
		/* Testing 1 function requirement with default values */
		/* Default value Easy Query 3FP */
		Project p1 = new Project("neu");
		p1.addFunctionRequirement("f1");
		testingVariants(2.1, 1.3455,1, 1.3455, 0.01, p1);
		
		/* Testing 1 data requirement with default values */
		/* Default value Easy Internal Data 3FP */
		Project p2 = new Project("P2");
		p2.addDataRequirement("d1");
		testingVariants(2.1, 1.3455, 1, 1.3455, 0.01, p2);
		
		/* Testing 1 data req and 1 function req with default values */
		/* Default value Easy Internal Data 3FP and Easy Query 3FP */
		Project p3 = new Project("P3");
		p3.addDataRequirement("d1");
		p3.addFunctionRequirement("f1");
		testingVariants(4.2, 1.7745, 1, 1.7745, 0.01, p3);
		
		/* Testing all possible complexity / type combinations for function req */
		/* Default values Easy:3, Medium:4, Complex:6 */
		Project p4 = new Project("P4");
		allComplexityFunctionReq(p4);
		testingVariants(45.5, 4.6047, 1, 4.6047, 0.01, p4);

		/* Testing all possible complexity / type combinations for function req */
		/* Default values Easy:3, Medium:4, Complex:6 */
		Project p5 = new Project("P5");
		allComplexityDataReq(p5);
		testingVariants(45.5, 4.6047, 1, 4.6047, 0.01, p5);
		
		/* Testing all possible complexity / type combinations for function req */
		/* Complexity values Easy:4, Medium:5, Complex:7 */
		Project p6 = new Project("P6");
		incAllComplexityValues(p6, 1.0f);
		allComplexityFunctionReq(p6);
		testingVariants(56, 5.0035, 1, 5.0035, 0.01, p6);
		
		/* Testing all possible complexity / type combinations for function req */
		/* Complexity values Easy:4, Medium:5, Complex:7 */
		/* All influences set to 1 */
		Project p7 = new Project("P7");
		incAllComplexityValues(p7, 1.0f);
		allComplexityFunctionReq(p7);
		setAllInfluences(1.0f, p7);
		testingVariants(64, 5.278, 1, 5.278, 0.01, p7);
		
	}
	
	/* ***************************************************************************************** */
	private void allComplexityFunctionReq(Project project){
		for (int i = 0; i < 15; i++){
			project.addFunctionRequirement(Integer.toString(i));
		}
		Field requirements = project.getFunctionRequirements();
		allComplTypePossibilities(requirements);

	}
	
	private void allComplexityDataReq(Project project){
		for (int i = 0; i < 15; i++){
			project.addDataRequirement(Integer.toString(i));
		}
		Field requirements = project.getDataRequirements();
		allComplTypePossibilities(requirements);
	}
	
	private void allComplTypePossibilities(Field requirements){
		setChildren(0, ComplexityEnum.Easy, FunctionPointEnum.Input, requirements);
		setChildren(1, ComplexityEnum.Medium, FunctionPointEnum.Input, requirements);
		setChildren(2, ComplexityEnum.Complex, FunctionPointEnum.Input, requirements);
		setChildren(3, ComplexityEnum.Easy, FunctionPointEnum.Query, requirements);
		setChildren(4, ComplexityEnum.Medium, FunctionPointEnum.Query, requirements);
		setChildren(5, ComplexityEnum.Complex, FunctionPointEnum.Query, requirements);
		setChildren(6, ComplexityEnum.Easy, FunctionPointEnum.Output, requirements);
		setChildren(7, ComplexityEnum.Medium, FunctionPointEnum.Output, requirements);
		setChildren(8, ComplexityEnum.Complex, FunctionPointEnum.Output, requirements);
		setChildren(9, ComplexityEnum.Easy, FunctionPointEnum.InternalData, requirements);
		setChildren(10, ComplexityEnum.Medium, FunctionPointEnum.InternalData, requirements);
		setChildren(11, ComplexityEnum.Complex, FunctionPointEnum.InternalData, requirements);
		setChildren(12, ComplexityEnum.Easy, FunctionPointEnum.ExternalData, requirements);
		setChildren(13, ComplexityEnum.Medium, FunctionPointEnum.ExternalData, requirements);
		setChildren(14, ComplexityEnum.Complex, FunctionPointEnum.ExternalData, requirements);
	}
	
	private void setChildren(int child, ComplexityEnum complexity, FunctionPointEnum type, Field requirements){
		requirements.getChildren().get(child).getChildren().get(4).setValue(type);
		requirements.getChildren().get(child).getChildren().get(3).setValue(complexity);
	}
	
	private void incAllComplexityValues(Project project, float increaseBy){
		Field parameters = project.getFPParameters();
		for(Field type : parameters.getChildren()){
			for(int i = 1; i < 4; i++){
				type.getChildren().get(i).setValue((float) type.getChildren().get(i).getValue() + increaseBy);
			}
		}
	}
	
	private void setAllInfluences (float setInfluencesTo, Project project){
		for (Field influence : project.getFPInfluences().getChildren()){
			influence.getChildren().get(1).setValue(setInfluencesTo);
		}
	}
	
	/* ***************************************************************************************** */
	
	private void testingVariants(double fpExpect, double durExpect, 
			double manExpect, double effExpect, double delta, Project project){
		
		FunctionPointMethod tester = new FunctionPointMethod();
		Field result = tester.calculate(project);
		float fp = (float) result.getChildren().get(0).getValue();
		double fpresult = (double) fp;
		
		float duration = (float) result.getChildren().get(1).getValue(); 
		double durResult = (double) duration;
		
		float manpower = (float) result.getChildren().get(2).getValue(); 
		double manResult = (double) manpower;
		
		float effort = (float)  result.getChildren().get(3).getValue(); 
		double effortResult = (double) effort;
		
		assertEquals("Gewichtete FP", fpExpect, fpresult, delta);
		assertEquals("Dauer", durExpect, durResult, delta);
		assertEquals("Personen", manExpect, manResult, delta);
		assertEquals("Aufwand", effExpect, effortResult, delta);
	}

}
