package projects;

import java.math.BigDecimal;
import java.sql.Connection;

import projects.dao.DbConnection;
import projects.entity.Project;

public class Menu {

    public static void main(String[] args) {
        try (Connection conn = DbConnection.getConnection()) {
            Project project = new Project();
            project.setProjectName("Build Treehouse");
            project.setEstimatedHours(new BigDecimal("40.0"));
            project.setActualHours(new BigDecimal("42.5"));
            project.setDifficulty(3);
            project.setNotes("Buy more wood");

           
            System.out.println("Project created:");
            System.out.println("Name: " + project.getProjectName());
            System.out.println("Estimated Hours: " + project.getEstimatedHours());
            System.out.println("Actual Hours: " + project.getActualHours());
            System.out.println("Difficulty: " + project.getDifficulty());
            System.out.println("Notes: " + project.getNotes());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

