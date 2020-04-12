package ro.pub.cs.systems.eim.practicaltest01;

class Constants {
    public static final int RESULT_OKAY = 1;
    public static final int RESULT_CANCEL = 2;
    public static final String NO_CLICKS = "babaiaga";
    public static final int SECONDARY_CODE = 3;
    public static final long SLEEP_TIME = 5000;
    public static final String BROADCAST_CODE = "Boss";
    public static final int THRESHOLD = 10;
    public static final String BROADCAST_TAG = "[BROADCAST MESSAGE] ";
    public static String LEFT_COUNTER = "left";
    public static String RIGHT_COUNTER = "right";
    final public static String ACTION_STRING = "ro.pub.cs.systems.eim.lab05.startedservice.string";
    final public static String ACTION_INTEGER = "ro.pub.cs.systems.eim.lab05.startedservice.integer";
    final public static String ACTION_ARRAY_LIST = "ro.pub.cs.systems.eim.lab05.startedservice.arraylist";

    public static String actions[] = {
            ACTION_STRING,
            ACTION_INTEGER,
            ACTION_ARRAY_LIST,
    };
}
