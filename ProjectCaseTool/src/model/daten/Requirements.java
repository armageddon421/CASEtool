package model.daten;

public abstract class Requirements {
	
	private String name;
	private String description;
	
	
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getName(){
		return name;		
	}
	
	public void setDescription(String newDescription){
		description = newDescription;
	}
	
	public String getDescritpion(){
		return description;
	}

}
