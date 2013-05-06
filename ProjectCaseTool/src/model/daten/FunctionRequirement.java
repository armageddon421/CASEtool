package model.daten;

import java.util.ArrayList;

public class FunctionRequirement extends Requirements {
		ArrayList<String> actors;
		String process;
		String cateqory;
		int LoC;
		String preCondition;
		String postCondition;
		
		
		/**
		 * @return the actors
		 */
		public ArrayList<String> getActors() {
			return actors;
		}
		/**
		 * @param actors the actors to set
		 */
		public void setActors(ArrayList<String> actors) {
			this.actors = actors;
		}
		/**
		 * @return the process
		 */
		public String getProcess() {
			return process;
		}
		/**
		 * @param process the process to set
		 */
		public void setProcess(String process) {
			this.process = process;
		}
		/**
		 * @return the cateqory
		 */
		public String getCateqory() {
			return cateqory;
		}
		/**
		 * @param cateqory the cateqory to set
		 */
		public void setCateqory(String cateqory) {
			this.cateqory = cateqory;
		}
		/**
		 * @return the loC
		 */
		public int getLoC() {
			return LoC;
		}
		/**
		 * @param loC the loC to set
		 */
		public void setLoC(int loC) {
			LoC = loC;
		}
		
		
		
}
