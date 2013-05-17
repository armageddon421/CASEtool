package model;

public class Field {
	private String _name;
	private Type _type;
	private Boolean _editable;
	private Object _owner;
	private Object _value;
	
	/**
	 * @param name Name of field.
	 * @param type Type of value.
	 * @param editable If the value of the field is editable.
	 * @param owner Owner of field.
	 * @param value Whatever value the field is supposed to hold.
	 */
	public Field(String name, Type type, Boolean editable, Object owner, Object value){
		this._name = name;
		this._type = type;
		this._editable = editable;
		this._owner = owner;
		this._value = value;
	}

	public Object getValue() {
		return _value;
	}

	/**
	 * Allows the value of the field to be changed, if it is editable.
	 * @param _value New value, the value field of the Field is to be set to.
	 */
	public void setValue(Object _value) {
		if(_editable == true){
			this._value = _value;
		}
	}

	public String getName() {
		return _name;
	}

	public Type getType() {
		return _type;
	}

	public Boolean getEditable() {
		return _editable;
	}

	public Object getOwner() {
		return _owner;
	}
	
	
}
