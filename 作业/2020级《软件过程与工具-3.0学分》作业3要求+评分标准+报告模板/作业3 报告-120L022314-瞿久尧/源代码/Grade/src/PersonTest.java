import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class PersonTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void addStudentIfTest() throws Exception{
        Person me1 = new Person("qjy");
        Person me2 = new Person("zzf");
        Person student1 = new Person("qjy");
        Person student2 = new Person("lwj");
        Person student3 = new Person("zzf");
        // test case1
        expectedEx.expectMessage("不能给自己打分");
        me1.addStudent(student1);
        //test case2
        me1.addStudent(student2);
        assertEquals(1,me1.getStudents().size());
        // test case3
        expectedEx.expectMessage("不能给自己打分");
        me2.addStudent(student3);
        // test case4
        me2.addStudent(student1);
        assertEquals(1,me2.getStudents().size());
    }

    @Test
    public void addStudentRoundTest() throws Exception{
        Person me1 = new Person("qjy");
        Person student1 = new Person("zzf");
        Person student2 = new Person("lwj");
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
        Person p1 = new Person("lwj");
        String name1 = p1.toString();
        assertEquals("You pass the final exam，lwj.",name1);
        Person p2 = new Person("zzf");
        String name2 = p2.toString();
        assertEquals("You pass the final exam，zzf.",name2);
        Person p3 = new Person("qjy");
        String name3 = p3.toString();
        assertEquals("You pass the final exam，qjy.",name3);
    }
}