package model;

public class CalculationFactory {
	public AbstractCalculationMethod createCalculationMethod(CalculationMethod method) {
		switch(method){
			case COCOMO:
				//not implemented
				return null;
		case FunctionPoint:
				return new FunctionPointMethod();
		default:
				return null;
		
		}
	}
}
