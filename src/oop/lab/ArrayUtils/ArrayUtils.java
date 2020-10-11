package oop.lab.ArrayUtils;

public class ArrayUtils {
    public static<E> boolean contains(E[] array, E containObject) throws Exception {
        if (array == null) {
            throw new Exception("Array is null.");
        }

        for (E o : array) {
            if (o == containObject) {
                return true;
            }
        }

        return false;
    }
}
