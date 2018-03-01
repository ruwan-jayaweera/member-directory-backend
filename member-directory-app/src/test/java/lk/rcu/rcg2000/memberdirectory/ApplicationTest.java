package lk.rcu.rcg2000.memberdirectory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations="classpath:test.properties")
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }

}
