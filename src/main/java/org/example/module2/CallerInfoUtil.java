package org.example.module2;

public class CallerInfoUtil {
    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StackTraceElement caller = stackTrace[2];

        return new CallingInfo(caller.getClassName(), caller.getMethodName(), caller.getLineNumber());
    }

    public static record CallingInfo(String className, String methodName, int lineNumber) {
    }
}
