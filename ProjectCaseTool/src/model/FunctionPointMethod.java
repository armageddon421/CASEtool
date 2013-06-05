package model;

import java.util.ArrayList;

import additional.ComplexityEnum;
import additional.Field;
import additional.FunctionPointEnum;
import additional.Type;

public class FunctionPointMethod extends AbstractCalculationMethod {

	private Project _project;
	
	@Override
	public Field calculate(Project project) {
		Field resultField = new Field("FP result", Type.String, false, this, "FP Ergebnis");
		this._project = project;
		float regularFP = sumRegularFP();
		float influences = sumInfluences();
		float weightedFP = (float) ((influences * 0.01 + 0.7) * regularFP);
		float duration = (float) Math.pow(weightedFP, 0.4);
		float manpower = Math.round(weightedFP / 150);
		float effort = duration * manpower;
		resultField.addChild(new Field("Weighted FP", Type.Float, false, this, weightedFP));
		resultField.addChild(new Field("Duration", Type.Float, false, this, duration));
		resultField.addChild(new Field("Manpower", Type.Float, false, this, manpower));
		resultField.addChild(new Field("Effort", Type.Float, false, this, effort));
		return resultField;
	}
	
	/**
	 * @return Sum of all function point of the project.
	 */
	private float sumRegularFP(){
		ArrayList<Field> allFunctionReqs =  _project.getFunctionRequirements().getChildren();
		float functionPoints = 0;
		for(Field fReq : allFunctionReqs){
			FunctionPointEnum fpEnum = null;	
			ComplexityEnum complEnum = null;
			for(Field reqAttribute : fReq.getChildren()){
				if(reqAttribute.getType() == Type.FunctionPointEnum){
					fpEnum = (FunctionPointEnum) reqAttribute.getValue();
				}
				else if(reqAttribute.getType() == Type.ComplexityEnum){
					complEnum = (ComplexityEnum) reqAttribute.getValue();
				}
			}
			if(fpEnum != null && complEnum != null){
				functionPoints = functionPoints + calcFPforReq(fpEnum, complEnum);
			}
		}
		return functionPoints;
	}
	
	/**
	 * Calculates the value of a function point for a given function point type, complexity constellation.
	 * @param fpEnum function point type (e.g. input/output/...) of the requirement.
	 * @param complEnum complexity of the requirement.
	 * @return Value of the function point. 
	 */
	private float calcFPforReq(FunctionPointEnum fpEnum, ComplexityEnum complEnum){
		float fpValue = 0;
		if(fpEnum == FunctionPointEnum.Input){
			if(complEnum == ComplexityEnum.Easy){
				fpValue = getFPValue("Input", "Easy");
			} 
			else if(complEnum == ComplexityEnum.Medium){
				fpValue = getFPValue("Input", "Medium");
			}
			else if(complEnum == ComplexityEnum.Complex){
				fpValue = getFPValue("Input", "Complex");
			}
		}
		else if(fpEnum == FunctionPointEnum.ExternalData){
			if(complEnum == ComplexityEnum.Easy){
				fpValue = getFPValue("External Data", "Easy");
			} 
			else if(complEnum == ComplexityEnum.Medium){
				fpValue = getFPValue("External Data", "Medium");
			}
			else if(complEnum == ComplexityEnum.Complex){
				fpValue = getFPValue("External Data", "Complex");
			}
		}
		else if(fpEnum == FunctionPointEnum.InternalData){
			if(complEnum == ComplexityEnum.Easy){
				fpValue = getFPValue("Internal Data", "Easy");
			} 
			else if(complEnum == ComplexityEnum.Medium){
				fpValue = getFPValue("Internal Data", "Medium");
			}
			else if(complEnum == ComplexityEnum.Complex){
				fpValue = getFPValue("Internal Data", "Complex");
			}
		}
		else if(fpEnum == FunctionPointEnum.Output){
			if(complEnum == ComplexityEnum.Easy){
				fpValue = getFPValue("Output", "Easy");
			} 
			else if(complEnum == ComplexityEnum.Medium){
				fpValue = getFPValue("Output", "Medium");
			}
			else if(complEnum == ComplexityEnum.Complex){
				fpValue = getFPValue("Output", "Complex");
			}
		}
		else if(fpEnum == FunctionPointEnum.Query){
			if(complEnum == ComplexityEnum.Easy){
				fpValue = getFPValue("Query", "Easy");
			} 
			else if(complEnum == ComplexityEnum.Medium){
				fpValue = getFPValue("Query", "Medium");
			}
			else if(complEnum == ComplexityEnum.Complex){
				fpValue = getFPValue("Query", "Complex");
			}
		}
		return fpValue;
	}
	
	/**
	 * Checks the function point parameters of the project for the given constellation of 
	 * function point type and complexity and returns the function point value.
	 * @param category
	 * @param complexity
	 * @return Value of the function point.
	 */
	private float getFPValue(String category, String complexity){
		float fpValue = 0;
		ArrayList<Field> fpParams = _project.getFPParameters().getChildren();
		for(Field categoryField : fpParams){
			if(categoryField.getName() == category){
				ArrayList<Field> complexityFieldList = categoryField.getChildren();
				for(Field compField : complexityFieldList){
					if (compField.getName() == complexity){
						fpValue = (float) compField.getValue();
					}
				}
			}
		}
		return fpValue;
	}
	
	/**
	 * @return Sum of all influence factors of the project.
	 */
	private float sumInfluences(){
		float influence = 0;
		ArrayList<Field> fpParams = _project.getFPParameters().getChildren();
		for(Field param : fpParams){
			if(param.getName() == "Influences"){
				ArrayList<Field> allInfluences = param.getChildren();
				for(Field influenceField : allInfluences){
					if(influenceField.getType() == Type.Float){
						influence = influence + (float) influenceField.getValue();
					}
				}
			}
		}
		return influence;
	}
}
