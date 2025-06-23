package projects;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import projects.dao.DbConnection;
import projects.entity.Project;
import projects.service.ProjectService;

public class Menu {

    private static Project curProject;  
    private static ProjectService projectService = new ProjectService();

    private static void printOperations() {
        System.out.println("These are the available selections. Press Enter to quit:");
        System.out.println("  1) Add a project");
        System.out.println("  2) List projects");
        System.out.println("  3) Select a project");
    }

    public static void main(String[] args) {
      try (Connection conn = DbConnection.getConnection()) {
          Scanner scanner = new Scanner(System.in);

          boolean done = false;
          while (!done) {
              printOperations();
              System.out.print("Enter a menu selection: ");
              String input = scanner.nextLine();

              if (input.isBlank()) {
                  System.out.println("\nExiting application.");
                  done = true;
                  continue;
              }

              try {
                  int selection = Integer.parseInt(input);
                  switch (selection) {
                      case 1:
                          createProject();
                          break;
                      case 2:
                          listProjects();
                          break;
                      case 3:
                          selectProject();
                          break;
                      default:
                          System.out.println("Invalid selection. Please try again.");
                  }
              } catch (NumberFormatException e) {
                  System.out.println("Invalid input. Please enter a number.");
              }
          }

          scanner.close();
      } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
          e.printStackTrace();
      }
  }


    private static void createProject() {
        Project project = new Project();
        project.setProjectName("Build Treehouse");
        project.setEstimatedHours(new BigDecimal("40.0"));
        project.setActualHours(new BigDecimal("42.5"));
        project.setDifficulty(3);
        project.setNotes("Buy more wood");

        System.out.println("You have successfully created project: " + project.getProjectName());
    }

    private static void listProjects() {
        List<Project> projects = projectService.fetchAllProjects();
        System.out.println("\nProjects:");
        for (Project project : projects) {
            System.out.println("  " + project.getProjectId() + ": " + project.getProjectName());
        }
    }

    private static void selectProject() {
        listProjects();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a project ID to view details: ");
        int projectId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        try {
            curProject = projectService.fetchProjectById(projectId);
            if (Objects.isNull(curProject)) {
                System.out.println("\nYou are not working with a project.");
            } else {
                printProjectDetails();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: Invalid project ID selected.");
        }
    }

    private static void printProjectDetails() {
      System.out.println("\nProject Details:\n");

      System.out.println("ID = " + curProject.getProjectId());
      System.out.println("Name = " + curProject.getProjectName());
      System.out.printf("Estimated Hours = %.2f\n", curProject.getEstimatedHours());
      System.out.printf("Actual Hours = %.2f\n", curProject.getActualHours());
      System.out.println("Difficulty = " + curProject.getDifficulty());
      System.out.println("Notes = " + curProject.getNotes());

      System.out.println("Materials:");
      curProject.getMaterials().forEach(material ->
          System.out.println("ID=" + material.getMaterialId()
              + ", materialName=" + material.getMaterialName()
              + ", numRequired=" + material.getNumRequired()
              + ", cost=" + material.getCost()));
    
      System.out.println("Steps:");
      curProject.getSteps().forEach(step ->
          System.out.println("ID=" + step.getStepId()
              + ", stepText=" + step.getStepText()));

      System.out.println("Categories:");
      curProject.getCategories().forEach(category ->
          System.out.println("ID=" + category.getCategoryId()
              + ", categoryName=" + category.getCategoryName()));

      System.out.print("\nEnter a menu selection:  ");
  }
}

