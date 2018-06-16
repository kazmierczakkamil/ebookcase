package pl.java.ebookcase.model;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class AssertAnnotations {
    public AssertAnnotations() {
    }

    private static void assertAnnotations(List<Class> annotationClasses, List<Annotation> annotations) {
        if (annotationClasses.size() != annotations.size()) {
            throw new AssertionError(String.format("Expected %d annotations, but found %d", annotationClasses.size(), annotations.size()));
        } else {
            annotationClasses.forEach((ac) -> {
                long cnt = annotations.stream().filter((a) -> {
                    return a.annotationType().isAssignableFrom(ac);
                }).count();
                if (cnt == 0L) {
                    throw new AssertionError(String.format("No annotation of type %s found", ac.getName()));
                }
            });
        }
    }

    public static void assertType(Class c, Class... annotationClasses) {
        assertAnnotations(Arrays.asList(annotationClasses), Arrays.asList(c.getDeclaredAnnotations()));
    }

    public static void assertField(Class c, String fieldName, Class... annotationClasses) {
        try {
            assertAnnotations(Arrays.asList(annotationClasses), Arrays.asList(c.getDeclaredField(fieldName).getAnnotations()));
        } catch (NoSuchFieldException var4) {
            throw new AssertionError(var4);
        }
    }

    public static void assertMethod(Class c, String getterName, Class... annotationClasses) {
        try {
            assertAnnotations(Arrays.asList(annotationClasses), Arrays.asList(c.getDeclaredMethod(getterName).getAnnotations()));
        } catch (NoSuchMethodException var4) {
            throw new AssertionError(var4);
        }
    }
}

