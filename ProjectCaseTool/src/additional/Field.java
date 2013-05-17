package additional;

public class Field {
	private final String	_name;
	private final Type		_type;
	private final Boolean	_editable;
	private final Object	_owner;
	private Object			_value;
	
	/**
	 * @param name
	 *            Name of field.
	 * @param type
	 *            Type of value.
	 * @param editable
	 *            If the value of the field is editable.
	 * @param owner
	 *            Owner of field.
	 * @param value
	 *            Whatever value the field is supposed to hold.
	 */
	public Field(final String name, final Type type, final Boolean editable, final Object owner,
			final Object value) {
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
	 * 
	 * @param _value
	 *            New value, the value field of the Field is to be set to.
	 */
	public void setValue(final Object value) {
		if (_editable == true) {
			this._value = value;
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
