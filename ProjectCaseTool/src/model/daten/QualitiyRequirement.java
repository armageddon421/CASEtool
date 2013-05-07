package model.daten;

public class QualitiyRequirement {
	Qualitylevel functionality;
	Qualitylevel reliability;
	Qualitylevel usability;
	Qualitylevel efficiency;
	Qualitylevel changeability;
	Qualitylevel portability;
	
	
	public QualitiyRequirement(Qualitylevel functionality,
			Qualitylevel reliability, Qualitylevel usability,
			Qualitylevel efficiency, Qualitylevel changeability,
			Qualitylevel portability) {
		super();
		this.functionality = functionality;
		this.reliability = reliability;
		this.usability = usability;
		this.efficiency = efficiency;
		this.changeability = changeability;
		this.portability = portability;
	}


	/**
	 * @return the functionality
	 */
	public Qualitylevel getFunctionality() {
		return functionality;
	}


	/**
	 * @param functionality the functionality to set
	 */
	public void setFunctionality(Qualitylevel functionality) {
		this.functionality = functionality;
	}


	/**
	 * @return the reliability
	 */
	public Qualitylevel getReliability() {
		return reliability;
	}


	/**
	 * @param reliability the reliability to set
	 */
	public void setReliability(Qualitylevel reliability) {
		this.reliability = reliability;
	}


	/**
	 * @return the usability
	 */
	public Qualitylevel getUsability() {
		return usability;
	}


	/**
	 * @param usability the usability to set
	 */
	public void setUsability(Qualitylevel usability) {
		this.usability = usability;
	}


	/**
	 * @return the efficiency
	 */
	public Qualitylevel getEfficiency() {
		return efficiency;
	}


	/**
	 * @param efficiency the efficiency to set
	 */
	public void setEfficiency(Qualitylevel efficiency) {
		this.efficiency = efficiency;
	}


	/**
	 * @return the changeability
	 */
	public Qualitylevel getChangeability() {
		return changeability;
	}


	/**
	 * @param changeability the changeability to set
	 */
	public void setChangeability(Qualitylevel changeability) {
		this.changeability = changeability;
	}


	/**
	 * @return the portability
	 */
	public Qualitylevel getPortability() {
		return portability;
	}


	/**
	 * @param portability the portability to set
	 */
	public void setPortability(Qualitylevel portability) {
		this.portability = portability;
	}
	
}
