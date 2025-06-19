package projects.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private Integer projectId;
    private String projectName;
    private BigDecimal estimatedHours;
    private BigDecimal actualHours;
    private Integer difficulty;
    private String notes;

    // NEW FIELDS
    private List<Material> materials = new ArrayList<>();
    private List<Step> steps = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    // Getters and Setters
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(BigDecimal estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public BigDecimal getActualHours() {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override 
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("\nID = ").append(projectId)
               .append("\nName = ").append(projectName)
               .append("\nEstimated Hours = ").append(estimatedHours)
               .append("\nActual Hours = ").append(actualHours)
               .append("\nDifficulty = ").append(difficulty)
               .append("\nNotes = ").append(notes)
               .append("\nMaterials:                                                                                                                                            ");

        if (materials != null && !materials.isEmpty()) {
            for (Material material : materials) {
                details.append("ID=").append(material.getMaterialId())
                       .append(", materialName=").append(material.getMaterialName())
                       .append(", numRequired=").append(material.getNumRequired())
                       .append(", cost=").append(material.getCost())
                       .append("                                                       ");
            }
        }

        details.append("\nSteps:                                                                                                                                                 ");
        if (steps != null && !steps.isEmpty()) {
            for (Step step : steps) {
                details.append("ID=").append(step.getStepId())
                       .append(", stepText=").append(step.getStepText())
                       .append("       ");
            }
        }

        details.append("\nCategories:                                                                                                                                        ");
        if (categories != null && !categories.isEmpty()) {
            for (Category category : categories) {
                details.append("ID=").append(category.getCategoryId())
                       .append(", categoryName=").append(category.getCategoryName())
                       .append("                                                                                   ");
            }
        }

        return details.toString();
    }
}
