package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
  private Scanner scanner = new Scanner(System.in);
  private ProjectService projectService = new ProjectService();
  private Project curProject;
  
  //@formatter:off
  private List<String> operations = List.of(
    "1) Add a project",
    "2) List projects",
    "3) Select a project",
    "4) Update project details",
    "5) Delete a project"
  );
  //@formatter:on

  public static void main(String[] args) {
    new ProjectsApp().processUserSelections();
  }

  private void processUserSelections() {
    boolean done = false;

    while (!done) {
      try {
        int selection = getUserSelection();

        switch (selection) {
          case -1:
            done = exitMenu();
            break;

          case 1:
            createProject();
            break;

          case 2:
            listProjects();
            break;

          case 3:
            selectProject();
            break;
            
          case 4:
            updateProjectDetails();  
            break;
            
          case 5:
            deleteProject();  
            break;
            
          default:
            System.out.println("\n" + selection + " is not a valid selection. Try again.");
            break;
        }
      } catch (Exception e) {
        System.out.println("\nError: " + e  + ". Try again.");
      
      }
    }
  }

  private void deleteProject() {
    listProjects();
    Integer projectId = getIntInput("Enter the ID of the project to delete");
    projectService.deleteProject(projectId);
    System.out.println("Project " + projectId + " was deleted successfully.");
    if(Objects.nonNull(curProject) && curProject.getProjectId().equals(projectId)) {
      curProject = null;
    }
     
    
  }

  private void updateProjectDetails() {
    if (Objects.isNull(curProject)) {
      System.out.println("\nPlease select a project.");
      return;
  }
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the project name [" + curProject.getProjectName() + "]: ");
    String name = scanner.nextLine();

    System.out.print("Enter the estimated hours [" + curProject.getEstimatedHours() + "]: ");
    String estHours = scanner.nextLine();

    System.out.print("Enter the actual hours [" + curProject.getActualHours() + "]: ");
    String actHours = scanner.nextLine();

    System.out.print("Enter the project difficulty (1-5) [" + curProject.getDifficulty() + "]: ");
    String difficulty = scanner.nextLine();

    System.out.print("Enter project notes [" + curProject.getNotes() + "]: ");
    String notes = scanner.nextLine();

    Project project = new Project();
  
 // Use new input if available, otherwise use existing value
 project.setProjectName(name.isBlank() ? curProject.getProjectName() : name);
 project.setEstimatedHours(estHours.isBlank() ? curProject.getEstimatedHours()
     : new BigDecimal(estHours));
 project.setActualHours(actHours.isBlank() ? curProject.getActualHours()
     : new BigDecimal(actHours));
 project.setDifficulty(difficulty.isBlank() ? curProject.getDifficulty()
     : Integer.parseInt(difficulty));
 project.setNotes(notes.isBlank() ? curProject.getNotes() : notes);

 // Set the ID so it knows which row to update
 project.setProjectId(curProject.getProjectId());

 projectService.modifyProjectDetails(project);
 
 curProject = projectService.fetchProjectById(curProject.getProjectId());


 }
  

  private void createProject() {
    String projectName = getStringInput("Enter the project name");
    BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
    BigDecimal actualHours = getDecimalInput("Enter the actual hours");
    Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
    String notes = getStringInput("Enter the project notes");

    Project project = new Project();
    project.setProjectName(projectName);
    project.setEstimatedHours(estimatedHours);
    project.setActualHours(actualHours);
    project.setDifficulty(difficulty);
    project.setNotes(notes);

    Project dbProject = projectService.addProject(project);
    System.out.println("You have successfully created project: " + dbProject);
  }

  private void listProjects() {
    List<Project> projects = projectService.fetchAllProjects();

    System.out.println("\nProjects:");

    projects.forEach(project -> 
      System.out.println("  " + project.getProjectId() + ": " + project.getProjectName())
    );
  }

 // private void printOperations() {
    //System.out.println("\nThese are the available selections. Press the Enter key to quit:");
   // operations.forEach(line -> System.out.println(" " + line));
 // }
  private void selectProject() {
    listProjects();
    Integer projectId = getIntInput("Enter a project ID to select a project");
    curProject = null;
    curProject = projectService.fetchProjectById(projectId);
  
    try {
      Project project = projectService.fetchProjectById(projectId);
      System.out.println("\nProject Details:\n");
      printProjectDetails(project);
    } catch (Exception e) {
      System.out.println("\nError: " + e.getMessage());
    }
   
  }
    
  private void printProjectDetails(Project project) {
    System.out.println("Project Details:\n");

    // Print basic project details
    System.out.println("ID = " + project.getProjectId());
    System.out.println("Name = " + project.getProjectName());
    System.out.println("Estimated Hours = " + project.getEstimatedHours());
    System.out.println("Actual Hours = " + project.getActualHours());
    System.out.println("Difficulty = " + project.getDifficulty());
    System.out.println("Notes = " + project.getNotes());

    // Print Materials with precise formatting
    System.out.println("Materials: ");
    project.getMaterials().forEach(material ->
        System.out.println(String.format("  ID=%d, materialName=%s, numRequired=%d, cost=%s",
            material.getMaterialId(),
            material.getMaterialName(),
            material.getNumRequired(),
            material.getCost() == null ? "null" : material.getCost()))  // Handle null cost
    );

   
    System.out.println("Steps: ");
    project.getSteps().forEach(step ->
        System.out.println(String.format("  ID=%d, stepText=%s", step.getStepId(), step.getStepText()))
    );

   
    System.out.println("Categories: ");
    project.getCategories().forEach(category ->
        System.out.println(String.format("  ID=%d, categoryName=%s", category.getCategoryId(), category.getCategoryName()))
    );
}


  private BigDecimal getDecimalInput(String prompt) {
    String input = getStringInput(prompt);
    if (Objects.isNull(input)) {
      return null;
    }

    try {
      return new BigDecimal(input).setScale(2);
    } catch (NumberFormatException e) {
      throw new DbException(input + " is not a valid decimal number. Try again.");
    }
  }

  private boolean exitMenu() {
    System.out.println("Exiting the application.");
    return true;
  }

  private int getUserSelection() {
    printOperations();
    Integer input = getIntInput("Enter a menu selection");
    return Objects.isNull(input) ? -1 : input;
  }

  private Integer getIntInput(String prompt) {
    String input = getStringInput(prompt);
    if (Objects.isNull(input)) {
      return null;
    }

    try {
      return Integer.valueOf(input);
    } catch (NumberFormatException e) {
      throw new DbException(input + " is not a valid number. Try again.");
    }
  }

  private String getStringInput(String prompt) {
    System.out.print(prompt + ": ");
    String input = scanner.nextLine();
    return input.isBlank() ? null : input.trim();
  }

  private void printOperations() {
    System.out.println("\nThese are the available selections. Press Enter to quit:");
    operations.forEach(line -> System.out.println("  " + line));
  }
}


 
