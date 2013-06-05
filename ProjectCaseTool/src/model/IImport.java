package model;


public interface IImport {
	/**
	 * Import a project
	 * 
	 * @param filename
	 *            File to import
	 * @param emptyProject
	 *            pass an empty project that will be filled by the importer.
	 */
	public void importProject(String filename, Project emptyProject);
}
