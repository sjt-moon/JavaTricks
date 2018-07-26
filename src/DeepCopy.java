import java.lang.reflect.Field;

/**
 * Deep copy with Java reflection.
 */
public class DeepCopy {
    static <T> void deepCopy(T from, T to) throws Exception {
        System.out.println(from.getClass().getFields().length);
        for (Field field: from.getClass().getFields()) {
            if (isPrimitive(field.getType())) {
                field.set(to, field.get(from));
            }
            else {
                deepCopy(field.get(from), field.get(to));
            }
        }
    }

    static boolean isPrimitive(Class c) {
        return c.isPrimitive() || c == String.class || c == Boolean.class
                || c == Byte.class || c == Short.class || c == Character.class
                || c == Integer.class || c == Float.class || c == Double.class
                || c == Long.class;
    }

    public static void main(String[] args) {
        // copy a2 into a1
        A a1 = new A(1, new B(2, "first b"));
        A a2 = new A(21, new B(23, "second b"));

        // before deep copy
        System.out.println(a1);

        try {
            deepCopy(a2, a1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // after deep copy
        System.out.println(a1);
    }
}

/**
 * A self-defined class to be deep copied.
 */
class A {
    public int x;
    public B b;
    A(int x, B b) {
        this.x = x;
        this.b = b;
    }

    @Override
    public String toString() {
        return "A.Integer: " + x + ", " + b;
    }
}

/**
 * A self-defined class to be deep copied.
 */
class B {
    public int y;
    public String s;
    B(int y, String s) {
        this.y = y;
        this.s = s;
    }

    @Override
    public String toString() {
        return "B.Integer: " + y + ", String: " + s;
    }
}
