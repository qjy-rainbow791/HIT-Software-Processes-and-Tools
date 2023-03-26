import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class PersonTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void addStudentIfTest() throws Exception{
        Person me1 = new Person("wzy");
        Person me2 = new Person("yrs");
        Person student1 = new Person("wzy");
        Person student2 = new Person("zrj");
        Person student3 = new Person("yrs");
        // test case1
        expectedEx.expectMessage("不能判自己的哦");
        me1.addStudent(student1);
        //test case2
        me1.addStudent(student2);
        assertEquals(1,me1.getStudents().size());
        // test case3
        expectedEx.expectMessage("不能判自己的哦");
        me2.addStudent(student3);
        // test case4
        me2.addStudent(student1);
        assertEquals(1,me2.getStudents().size());
    }

    @Test
    public void addStudentRoundTest() throws Exception{
        Person me1 = new Person("wzy");
        Person student1 = new Person("yrs");
        Person student2 = new Person("zrj");
        // test case1
        assertEquals(0,me1.getStudents().size());
        me1.addStudent(student1);
        assertEquals(1, me1.getStudents().size());
        //test case2
        expectedEx.expectMessage("他已经判过了");
        me1.addStudent(student1);
        // test case3
        assertEquals(1, me1.getStudents().size());
        me1.addStudent(student2);
        assertEquals(2, me1.getStudents().size());
        // test case4
        expectedEx.expectMessage("他已经判过了");
        me1.addStudent(student2);
    }

    @Test
    public void testForwardTest() {
        Person p1 = new Person("zrj");
        String name1 = p1.toString();
        assertEquals("You pass the final exam，zrj.",name1);
        Person p2 = new Person("yrs");
        String name2 = p2.toString();
        assertEquals("You pass the final exam，yrs.",name2);
        Person p3 = new Person("wzy");
        String name3 = p3.toString();
        assertEquals("You pass the final exam，wzy.",name3);
    }
}