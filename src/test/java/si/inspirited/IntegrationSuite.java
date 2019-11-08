package si.inspirited;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserRepositoryTests.class,
        MessageRepositoryTests.class,
        UserServiceTests.class,
        MessageServiceTests.class,
        UserControllerTests.class,
        MessageControllerTests.class,
})
public class IntegrationSuite {
}
