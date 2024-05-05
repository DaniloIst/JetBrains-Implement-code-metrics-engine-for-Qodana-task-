import java.util.*;
import java.util.stream.Collectors;
import java.nio.file.*;


public class Main {
    static boolean hadError = false;
    static LinkedList<FunctionInformation> functions = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        if (args.length > 1) {
            System.out.println("Wrong number of arguments!");
            System.exit(-1);
        } else if (args.length == 1) {
            Path dir = Paths.get(args[0]);
            if (!Files.isDirectory(dir)) {
                throw new IllegalArgumentException("Path must be a directory!");
            }
            List<Path> KotlinFiles = Files.walk(dir)
                    .filter(path -> path.toString().endsWith(".kt"))
                    .collect(Collectors.toList());
            for (Path KotlinFile : KotlinFiles) {
                String content = new String(Files.readAllBytes(KotlinFile));
                String fileName = KotlinFile.getFileName().toString();
                LinkedList<FunctionInformation> res = run(content, fileName);
                if (res != null) {
                    functions.addAll(res);
                }
                res.clear();
            }

        }
        PrintHighestComplexity();
        CheckCamelCase();


    }

    private static void CheckCamelCase() {
        int totalMethods = functions.size();
        int nonCompliantMethods = 0;

        for (FunctionInformation f : functions) {
            String FunctionName = f.getFunctionName();
            if(!isCamelCase(FunctionName)){
                nonCompliantMethods++;
                System.out.println("Function '" + FunctionName + "' in file '" + f.getFileName() + "' does not follow camel case convention.");
            }

        }

        if (totalMethods > 0) {
            double percentage = (double) nonCompliantMethods / totalMethods * 100;
            System.out.printf("Percentage of methods not adhering to camel case convention: %.2f%%\n", percentage);
        } else {
            System.out.println("No methods were analyzed.");
        }
        System.out.println();
    }


    private static boolean isCamelCase(String input) {
        String camelCasePattern = "^[a-z]+(?:[A-Z][a-z]*)*$";
        return input.matches(camelCasePattern);
    }

    public static void PrintHighestComplexity() {
        List<FunctionInformation> sortedFunctions = functions.stream()
                .sorted(Comparator.comparing(FunctionInformation::getComplexityCount).reversed())
                .collect(Collectors.toList());

        List<FunctionInformation> topThreeFunctions = sortedFunctions.stream()
                .limit(3)
                .collect(Collectors.toList());

        for (FunctionInformation f : topThreeFunctions) {
            System.out.println("Function Name: " + f.getFunctionName() + " and its Complexity Score is: " + f.getComplexityCount());
        }
        System.out.println();
    }


    private static LinkedList<FunctionInformation> run(String source, String filename) {
        Scanner scanner = new Scanner(source, filename);
        scanner.scanTokens();
        scanner.saveLastFunctionComplexity();
        return scanner.getFunctions();
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where,String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }


}
