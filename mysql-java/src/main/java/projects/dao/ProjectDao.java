package projects.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projects.exception.DbException;
import projects.entity.Project;
import projects.util.DbConnection;

public class ProjectDao {

    private static final String INSERT_PROJECT_SQL = 
        "INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) " +
        "VALUES (?, ?, ?, ?, ?)";

    public Project insertProject(Project project) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PROJECT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, project.getProjectName());
            stmt.setBigDecimal(2, project.getEstimatedHours());
            stmt.setBigDecimal(3, project.getActualHours());
            stmt.setInt(4, project.getDifficulty());
            stmt.setString(5, project.getNotes());

            stmt.executeUpdate();

            return project;

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
