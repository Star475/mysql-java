package week5;

public class SpacedLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println(addSpaces(message));
    }

    @Override
    public void error(String message) {
        System.out.println("ERROR: " + addSpaces(message));
    }

    // Helper method to add spaces between each character
    private String addSpaces(String text) {
        StringBuilder spaced = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            spaced.append(text.charAt(i)).append(" ");
        }
        return spaced.toString().trim(); // Trim trailing space
    }
}


