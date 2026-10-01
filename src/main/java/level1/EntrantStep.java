package level1;

public interface EntrantStep {
    EntrantStep isVegan();
    EntrantStep isGlutenFree();

    MainCourseStep withMainCourse(String name);
}
