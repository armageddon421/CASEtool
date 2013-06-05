package model;

/**
 * Interface for Classes that can Export Projects
 * 
 * @author Robert
 * 
 */
public interface IExport {
	/**
	 * Export a project
	 * 
	 * @param filename
	 *            Filename or Path to the target XML File
	 * @param project
	 *            project to export
	 */
	public void exportProject(String filename, Project project);
}
