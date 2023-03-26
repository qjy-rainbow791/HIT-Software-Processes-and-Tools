import java.util.HashSet;
import java.util.Set;

public class Person {
    private String name;
    private Set<Person> students=new HashSet<Person>();

    /**
     * Constructor with parameters
     * @param name the name field of the object to be constructed
     *
     * the default value of the flag field is false
     *
     */
    public Person(String name) {
        this.name=name;
    }

    /**
     * the Getter method of name.
     */
    public String getName(){
        return name;
    }

    /**
     * the Getter method of friends.
     */
    public Set<Person> getStudents(){
        return students;
    }

    /**
     * Add a student to the current Person class object.
     *
     * @param  student An object of the Person class,
     * which needed to be added to the student Set of the current object.
     * @throws Exception the possible exception in the process
     */
    public void addStudent(Person student) throws Exception{
        //不可以给自己打分
        if (student.getName().equals(this.getName())) {
            throw new Exception("不能判自己的哦");
        }
        //不可以重复给一个人打分
        for(Person x:this.getStudents()) {
            if (x.getName().equals(student.getName())) {
                throw new Exception("他已经判过了");
            }
        }
        students.add(student);
    }

    @Override
    public  String toString(){
        String result;
        result = "You pass the final exam，"+ this.getName()+".";
        return result;
    }
}
