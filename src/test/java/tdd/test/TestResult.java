package tdd.test;

public class TestResult {
    public String summary() {
        return runCount + " run, "+ errorCount + " failed";
    }
    public int runCount = 0;
    public int errorCount = 0;
    public void testStarted() {
        runCount++;
    }
    public void testFailed() {
        errorCount++;
    }
}
