package org.example.module2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallerInfoUtilTest {
    @Test
    public void testCallingInfo() {
        CallerInfoUtil.CallingInfo info = CallerInfoUtil.callingInfo();

        assertEquals("org.example.tasks_2_7.CallerInfoUtilTest", info.className());
        assertEquals("testCallingInfo", info.methodName());
        assertTrue(info.lineNumber() > 0);
    }

    @Test
    public void testCallingInfoFromOtherMethod() {
        methodThatCallsCallingInfo();
    }

    private void methodThatCallsCallingInfo() {
        CallerInfoUtil.CallingInfo info = CallerInfoUtil.callingInfo();

        assertEquals("org.example.tasks_2_7.CallerInfoUtilTest", info.className());
        assertEquals("methodThatCallsCallingInfo", info.methodName());
        assertTrue(info.lineNumber() > 0);
    }
}
