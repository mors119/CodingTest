package runner;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("usage: java runner.Main <group/problem>");
            System.err.println("example: java runner.Main 1/1245");
            System.exit(1);
        }

        String problem = args[0];
        String[] parts = problem.split("/");

        if (parts.length != 2) {
            System.err.println("invalid problem format: " + problem);
            System.err.println("expected format: <group/problem>");
            System.exit(1);
        }

        String group = parts[0];
        String number = parts[1];

        Path rootDir = Path.of("").toAbsolutePath();
        Path inputPath = rootDir.resolve("input").resolve(group).resolve(number + ".txt");

        if (!Files.exists(inputPath)) {
            System.err.println("input file not found: " + inputPath);
            System.exit(1);
        }

        String className = "script.p" + group + ".p" + number + ".Main";
        String text = Files.readString(inputPath);

        Class<?> clazz = Class.forName(className);
        Method solveMethod = clazz.getMethod("solve", String.class);

        Object result = solveMethod.invoke(null, text);
        System.out.println(result);
    }
}