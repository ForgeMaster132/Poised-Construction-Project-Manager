package poised_management_software;
// Imports
import java.util.*;

/**
 * 
 * @author Forge
 *
 */

public class Program{
	public static void main(String[] args) {
		
		// Project Variables
		int choice = 0;
		Scanner input = new Scanner(System.in);
		while(true) {
			// Variables
			int projectSelection = 0;
			boolean projectSelected = false;
			// Prints Main Menu
			Menu.printMainMenu();
			// Handles Menu selection
			choice = Menu.selectedOption();
			
			// Picking a Project
			if(choice == 1) {
				try {
					System.out.println("Pick a Project from the list using it's Number");
					Menu.printAllProjectsNames();
					projectSelection = Menu.selectedOption() - 1;
					if(projectSelection < Menu.projects.size() && projectSelection >= 0) {
						projectSelected = true;
						Menu.printMenu();
					} else {
						System.out.print("There is no project at that number\n");
					}
				} catch(Exception e) {
					System.out.println("No projects exist yet");
				}
			}
			// Creating Project
			if(choice == 2) {
				// Creating Project Object
				Menu.projects.add(Menu.createProject());
				// Updating Projects File
				ProjectsFile.writeProjectsToFile();
			}
			// Displays all Projects
			if(choice == 3) {
				try {
					Menu.printAllProjects();
				} catch(Exception e) {
					System.out.println("No projects exist yet");
				}
			}
			// Displays Projects that are Unfinished
			if(choice == 4) {
				try {
					Menu.printUnfinishedProjects();
				} catch(Exception e) {
					System.out.println("No projects exist yet");
				}
			}
			// Displays Projects which are overdue
			if(choice == 5) {
				try {
					Menu.printOverdueProjects();
				} catch(Exception e) {
					System.out.println("No projects exist yet");
				}
			}
			// Exits Program
			if(choice == 0) {
				input.close();
				break;
			}
			while(projectSelected) {
				choice = Menu.selectedOption();
				
				if(choice == 1) {
					try {
						Menu.printSelectedProject(projectSelection);
					} catch(Exception e) {
						System.out.println("No Projects exist yet");
					}
				}
				// Changes a Projects Deadline
				if(choice == 2) {
					try {
						Menu.projects.get(projectSelection).changeDate();
						// Updating Projects File
						ProjectsFile.writeProjectsToFile();
					} catch(Exception e) {
						System.out.println("No projects exist yet");
					}
				}
				// Change Total Paid to Date
				if(choice == 3) {
					try {
						Menu.projects.get(projectSelection).changeAmountPaid();
						// Updating Projects File
						ProjectsFile.writeProjectsToFile();
					} catch(Exception e) {
						System.out.println("No projects exist yet");
					}
				}
				// Updates Contractor Details
				if(choice == 4) {
					try {
						Menu.projects.get(projectSelection).updateContractor();
						// Updating Projects File
						ProjectsFile.writeProjectsToFile();
					} catch(Exception e) {
						System.out.println("No projects exist yet");
					}
				}
				// Finalize Project
				if(choice == 5) {
					try {
						// Creates Invoice
						String [] invoice = Menu.projects.get(projectSelection).finaliseProject(
							Menu.projects.get(projectSelection).totalFee, Menu.projects.get(projectSelection).totalPaidDate);
						// Updating Finalised Status in Projects List
						if(invoice[3] == "Finalised") {
							Menu.projects.get(projectSelection).finalised = "Finalised";
						}
						// Outputs Invoice
						for(int i = 0;i < 5;i++) {
							System.out.println(invoice[i]);
						}
						// Updating Projects File
						ProjectsFile.writeProjectsToFile();
					} catch(Exception e) {
						System.out.println("No projects exist yet");
					}
				}
				if(choice == 0) {
					break;
				}
				Menu.printMenu();
			}	
		}
	}
}