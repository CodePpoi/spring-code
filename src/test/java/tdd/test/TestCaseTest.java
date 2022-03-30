package tdd.test;

import org.springframework.util.Assert;
import tdd.test.annotation.After;
import tdd.test.annotation.Before;
import tdd.test.annotation.Test;

import java.lang.reflect.InvocationTargetException;

public class TestCaseTest extends TestCase {

    public WasRun test;


    public TestCaseTest(String methodName) {
        super(methodName);
    }

    @Test
    public void testTemplateMethod() throws InvocationTargetException, IllegalAccessException {
        this.test = new WasRun("testMethod");
        test.run(result);
        Assert.isTrue(test.log .equals( "setUp testMethod tearDown "));
    }

    @Test
    public void testResult() throws InvocationTargetException, IllegalAccessException {
        this.test = new WasRun("testMethod");
        test.run(result);
        Assert.isTrue(result.summary() .equals( "1 run, 0 failed"));
    }

    @Test
    public void testFailedResultFormatting() throws InvocationTargetException, IllegalAccessException {
        result.testStarted();
        result.testFailed();
        Assert.isTrue(result.summary() .equals( "1 run, 1 failed"));
    }

    @Test
    public void testFailedResult() throws InvocationTargetException, IllegalAccessException {
        this.test = new WasRun("testBrokenMethod");
        test.run(result);
        Assert.isTrue(result.summary() .equals( "1 run, 1 failed"));
    }

    @Test
    public void testSuite() throws InvocationTargetException, IllegalAccessException {
        TestSuite suite = new TestSuite();
        suite.add(new WasRun("testMethod"));
        suite.add(new WasRun("testBrokenMethod"));

        TestResult result = new TestResult();
        suite.run(result);
        Assert.isTrue(result.summary() .equals( "2 run, 1 failed"));
    }

    @Before
    public void before() {
        result = new TestResult();
    }

    @After
    public void after() {
        System.out.println("run after in " + getClass().getName());
    }



    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        TestSuite suite = new TestSuite();
        //suite.addTests(WasRun.class);
        suite.addTests(TestCaseTest.class);
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println(result.summary());
    }
}
