package poised_management_software;
// Imports
import java.util.*;
import java.io.*;

/**
 * 
 * @author Forge
 *
 */

public class ProjectsFile {
	// Methods
	/** Opens file for reading
	 * 
	 * @return Scanner Object for Projects.txt File 
	 */
	public static Scanner openFile() {
		try {
			File f = new File("src/Projects.txt");
			Scanner fsc = new Scanner(f);
			
			return fsc;
		} catch(FileNotFoundException e) {
			return null;
		}
	}
	
	/** Closes file
	 * 
	 * @param sc Scanner of file
	 */
	public static void closeFile(Scanner sc) {
		sc.close();
	}
	
	/** Reads Projects from Projects.txt file into a ArrayList
	 * 
	 * @return ArrayList of Project objects
	 */
	public static ArrayList<Project> getProjects() {
		// Variables
		ArrayList<Project> projects = new ArrayList<Project>();
		String lines = "";
		
		// Reading file
		Scanner sc = openFile();
		
		if(sc == null) {
			System.out.println("There are no projects yet. Try Creating one!");
			ArrayList<Project> empty = new ArrayList<Project>();
			return empty;
		}
		
		// Getting Projects
		while(sc.hasNext()){
			lines += sc.nextLine();
		}
		
		// Creating array of lines
		String[] linesArr = lines.split("#");
		
		try {
			// Populating Projects Array
			for(int i = 0;i < linesArr.length;i++) {
				// Splitting line into array
				String[] projectInfo = linesArr[i].split("~");
				Project project = new Project();
				// Assigning Project Values
				project.projName = projectInfo[0];
				project.projNum = projectInfo[1];
				project.buildingType = projectInfo[2];
				project.buildingAddress = projectInfo[3];
				project.erfNum = projectInfo[4];
				project.deadline = projectInfo[5];
				
				// Checking for valid values
				try {
				project.totalFee = Double.valueOf(projectInfo[6]);
				} catch(Exception e) {
					System.out.println("Total fee value for project " + i + " is not a valid amount");
				}
				try {
					project.totalPaidDate = Double.valueOf(projectInfo[7]);
				} catch(Exception e) {
					System.out.println("Total Paid to Date value for project " + i + " is not a valid amount");
				}
				
				// Assigning Project People 
				Person architect = new Person("Architect", projectInfo[8], projectInfo[9],projectInfo[10],projectInfo[11]);
				project.architect = architect;
				Person customer = new Person("Customer", projectInfo[12], projectInfo[13], projectInfo[14], projectInfo[15]);
				project.customer = customer;
				Person contractor = new Person("Contractor", projectInfo[16], projectInfo[17], projectInfo[18],projectInfo[19]);
				project.contractor = contractor;
				
				// Assigning finalized Status
				project.finalised = projectInfo[20];
				// Adding new project to list
				projects.add(project);
			}
		} catch(Exception e) {
			System.out.println("There are no projects yet. Try Creating one!");
			ArrayList<Project> empty = new ArrayList<Project>();
			return empty;
		}
		closeFile(sc);
		
		return projects;
	}
	
	/** Writes Projects to Projects.txt file */
	public static void writeProjectsToFile() {
		try {
		Formatter f = new Formatter("src/Projects.txt");
		// Writing each Projects info to file
		for(int i = 0; i < Menu.projects.size(); i++) {
			f.format(Menu.projects.get(i).projName + "~");
			f.format(Menu.projects.get(i).projNum + "~");
			f.format(Menu.projects.get(i).buildingType + "~");
			f.format(Menu.projects.get(i).buildingAddress + "~");
			f.format(Menu.projects.get(i).erfNum + "~");
			f.format(Menu.projects.get(i).deadline + "~");
			f.format(Menu.projects.get(i).totalFee + "~");
			f.format(Menu.projects.get(i).totalPaidDate + "~");
			f.format(Menu.projects.get(i).architect.name + "~");
			f.format(Menu.projects.get(i).architect.phoneNum + "~");
			f.format(Menu.projects.get(i).architect.email + "~");
			f.format(Menu.projects.get(i).architect.address + "~");
			f.format(Menu.projects.get(i).customer.name + "~");
			f.format(Menu.projects.get(i).customer.phoneNum + "~");
			f.format(Menu.projects.get(i).customer.email + "~");
			f.format(Menu.projects.get(i).customer.address + "~");
			f.format(Menu.projects.get(i).contractor.name + "~");
			f.format(Menu.projects.get(i).contractor.phoneNum + "~");
			f.format(Menu.projects.get(i).contractor.email + "~");
			f.format(Menu.projects.get(i).contractor.address + "~");
			f.format(Menu.projects.get(i).finalised + "#");
			f.format("\n");
		}
		f.close();
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
	}
}