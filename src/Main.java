public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Use: java -cp out Main --demo");
        }
    }

    private static void runDemo() {
        int passed = 0;

        passed += testT1() ? 1 : 0;
        passed += testT2() ? 1 : 0;
        passed += testT3() ? 1 : 0;
        passed += testT4() ? 1 : 0;
        passed += testT5() ? 1 : 0;

        System.out.println("SUMMARY: " + passed + "/5 PASS");
    }

    private static boolean testT1() {
        Channel email = new EmailChannel();

        Reminder reminder = new Reminder(
                "N1",
                "Meeting at 10:00",
                email
        );

        String actual = reminder.execute();
        String expected = "EMAIL: [Envelope] Meeting at 10:00";

        boolean pass = actual.equals(expected);

        printResult(
                "T1",
                pass,
                "Reminder + EmailChannel",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT2() {
        Channel sms = new SmsChannel();

        Reminder reminder = new Reminder(
                "N1",
                "Meeting at 10:00",
                sms
        );

        String actual = reminder.execute();
        String expected = "SMS: Meeting at 10:00";

        boolean pass = actual.equals(expected);

        printResult(
                "T2",
                pass,
                "Reminder + SmsChannel",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT3() {
        Channel email = new EmailChannel();

        UrgentAlert alert = new UrgentAlert(
                "N2",
                "Server is down",
                email
        );

        String actual = alert.execute();
        String expected = "EMAIL: [Envelope] URGENT: Server is down";

        boolean pass = actual.equals(expected);

        printResult(
                "T3",
                pass,
                "UrgentAlert + EmailChannel",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT4() {
        Channel sms = new SmsChannel();

        UrgentAlert alert = new UrgentAlert(
                "N2",
                "Server is down",
                sms
        );

        String actual = alert.execute();
        String expected = "SMS: URGENT: Server is down";

        boolean pass = actual.equals(expected);

        printResult(
                "T4",
                pass,
                "UrgentAlert + SmsChannel",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT5() {
        Channel email = new EmailChannel();
        Channel sms = new SmsChannel();

        Reminder reminder = new Reminder(
                "N3",
                "Project deadline is tomorrow",
                email
        );

        Notification originalObject = reminder;

        String before = reminder.execute();

        reminder.setImplementation(sms);

        String after = reminder.execute();

        boolean sameObject = originalObject == reminder;
        boolean stateUnchanged =
                reminder.getId().equals("N3")
                        && reminder.getMessage().equals("Project deadline is tomorrow");

        String expectedBefore =
                "EMAIL: [Envelope] Project deadline is tomorrow";

        String expectedAfter =
                "SMS: Project deadline is tomorrow";

        boolean pass =
                sameObject
                        && stateUnchanged
                        && before.equals(expectedBefore)
                        && after.equals(expectedAfter);

        System.out.println(
                "T5 " + (pass ? "PASS" : "FAIL")
                        + " | sameObject=" + sameObject
                        + " | stateUnchanged=" + stateUnchanged
                        + " | before=" + before
                        + " | after=" + after
        );

        if (!pass) {
            System.out.println(
                    "Expected: before=" + expectedBefore
                            + " | after=" + expectedAfter
            );
        }

        return pass;
    }

    private static void printResult(
            String testId,
            boolean pass,
            String classes,
            String actual,
            String expected) {

        System.out.println(
                testId + " " + (pass ? "PASS" : "FAIL")
                        + " | " + classes
                        + " | result=" + actual
        );

        if (!pass) {
            System.out.println("Expected: " + expected);
        }
    }
}