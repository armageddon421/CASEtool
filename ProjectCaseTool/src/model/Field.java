package model;

public class Field {
	private String name;
	private Type type;
	private Boolean editable;
	private Object owner;
	private Object value;
	
	/**
	 * @param name Name of field.
	 * @param type Type of value.
	 * @param editable If the value of the field is editable.
	 * @param owner Owner of field.
	 * @param value Whatever value the field is supposed to hold.
	 */
	public Field(String name, Type type, Boolean editable, Object owner, Object value){
		this.name = name;
		this.type = type;
		this.editable = editable;
		this.owner = owner;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		if(editable == true){
			this.value = value;
		}
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public Boolean getEditable() {
		return editable;
	}

	public Object getOwner() {
		return owner;
	}
	
	
}
