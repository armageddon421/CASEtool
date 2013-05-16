package model;

public class CalculationFactory {
	public AbstractCalculationMethod createCalculationMethod(CalculationMethod method) {
		AbstractCalculationMethod returnMethod;
		
		switch(method){
			case COCOMO:
				//not implemented
				returnMethod = null;
				break;
			case FunctionPoint:
				returnMethod = new FunctionPointMethod();
				break;
			default:
				returnMethod = null;
				break;
		
		}		
		return returnMethod;
	}
}
