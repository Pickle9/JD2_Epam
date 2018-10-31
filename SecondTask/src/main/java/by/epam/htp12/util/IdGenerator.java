package by.epam.htp12.util;

import by.epam.htp12.exception.LongOverflowException;

public class IdGenerator {

    private static long id = 0;

    private IdGenerator() {
    }

    public static long getId() {
        return id;
    }

    public static long getAndIncrementId() throws LongOverflowException {
        long tmp = id;
        incrementId();
        return tmp;
    }

    public static void incrementId() throws LongOverflowException {
        if (id + 1 > Integer.MAX_VALUE) {
            throw new LongOverflowException("IdGenerator class has too large id field. Long data type has been overflowed.");
        }
        id++;
    }
}
