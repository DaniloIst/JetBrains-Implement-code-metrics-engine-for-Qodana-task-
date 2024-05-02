public class FunctionInformation {

    private String FileName;
    private String FunctionName;
    private int ComplexityCount;

    public FunctionInformation(String fileName, String functionName, int complexityCount) {
        FileName = fileName;
        FunctionName = functionName;
        ComplexityCount = complexityCount;
    }

    public String getFileName() {
        return FileName;
    }

    public String getFunctionName() {
        return FunctionName;
    }

    public int getComplexityCount() {
        return ComplexityCount;
    }

    @Override
    public String toString() {
        return "FunctionInformation{" +
                "FileName='" + FileName + '\'' +
                ", FunctionName='" + FunctionName + '\'' +
                ", ComplexityCount=" + ComplexityCount +
                '}';
    }

}
