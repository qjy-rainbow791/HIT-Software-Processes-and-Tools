import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUnitTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PersonTest.class);
        System.out.println(result.wasSuccessful()?"测试成功":"测试失败");
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}