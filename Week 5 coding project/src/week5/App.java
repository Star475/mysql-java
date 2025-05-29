package week5;

public class App {

  public static void main(String[] args) {
    // Create an instance of AsteriskLogger using the Logger interface reference
    Logger asteriskLogger = new AsteriskLogger();

    // Create an instance of SpacedLogger using the Logger interface reference
    Logger spacedLogger = new SpacedLogger();

    // Print a header before calling the log method on AsteriskLogger
    System.out.println("AsteriskLogger log:");
    // Call the log method on asteriskLogger with the string "Star"
    asteriskLogger.log("Star");

    // Print a header before calling the error method on AsteriskLogger
    System.out.println("\nAsteriskLogger error:");
    // Call the error method on asteriskLogger with the string "Star"
    asteriskLogger.error("Star");

    // Print a header before calling the log method on SpacedLogger
    System.out.println("\nSpacedLogger log:");
    // Call the log method on spacedLogger with the string "Star"
    spacedLogger.log("Star");

    // Print a header before calling the error method on SpacedLogger
    System.out.println("\nSpacedLogger error:");
    // Call the error method on spacedLogger with the string "Star"
    spacedLogger.error("Star");
  }
} 


// Logger Interface
interface Logger {
    void log(String message);
    void error(String message);
}

// AsteriskLogger class
class AsteriskLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("***" + message + "***");
    }

    @Override
    public void error(String message) {
        String errorMessage = "***Error: " + message + "***";
        String border = "".repeat(errorMessage.length());
        for (int i = 0; i < errorMessage.length(); i++) {
            border += "*";
        }
        System.out.println(border);
        System.out.println(errorMessage);
        System.out.println(border);
    }
}

// SpacedLogger class
class SpacedLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println(addSpaces(message));
    }

    @Override
    public void error(String message) {
        System.out.println("ERROR: " + addSpaces(message));
    }

    private String addSpaces(String text) {
        StringBuilder spaced = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            spaced.append(text.charAt(i)).append(" ");
        }
        return spaced.toString().trim();
    }
}
