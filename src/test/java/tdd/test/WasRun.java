package tdd.test;

import tdd.test.annotation.After;
import tdd.test.annotation.Before;
import tdd.test.annotation.Test;

public class WasRun extends TestCase {
    public Boolean wasRun ;

    public String log ;

    @Before
    public void before() {
        result = new TestResult();
    }

    @After
    public void after() {
        System.out.println("run after in " + getClass().getName());
    }

    public void setUp() {
        wasRun = null;
        log = "setUp ";
    }



    public void tearDown() {
        log += "tearDown ";
    }


    public WasRun(String methodName) {
        super(methodName);
    }



    @Test
    public String testMethod() {
        wasRun = true;
        log += "testMethod ";
        return "pass";
    }

    @Test
    public String testBrokenMethod() {
        throw new IllegalStateException();
    }


}
