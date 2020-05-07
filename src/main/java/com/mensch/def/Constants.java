package com.mensch.def;

import java.util.concurrent.TimeUnit;

public class Constants {

    public static final int MAX_RETRY_FAILED_TESTS = 3;

    public static final int DEFAULT_TIMEOUT = 60;
    public static final int DEFAULT_TIMEOUT_LONG = 120;
    public static final TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.SECONDS;
    public static final int DEFAULT_POLLING_INTERVAL = 200;
    public static final TimeUnit DEFAULT_POLLING_UNIT = TimeUnit.MILLISECONDS;
    public static final int DEFAULT_IMPLICIT_WAIT_INTERVAL = 0;
}
