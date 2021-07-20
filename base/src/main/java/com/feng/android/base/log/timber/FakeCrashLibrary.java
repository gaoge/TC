package com.feng.android.base.log.timber;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 10:01
 * @tips
 */
public class FakeCrashLibrary {
    public static void log(int priority, String tag, String message) {
        // TODO add log entry to circular buffer.
    }

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }
}
