package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.dao.IUserRepository;
import si.inspirited.persistence.dao.impl.UserRepository;
import si.inspirited.persistence.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@RestClientTest(UserRepository.class)
public class UserRepositoryTests {

    @Autowired
    IUserRepository userRepository;

    @Test
    public void addNewUser_whenReturnsMapWithOneExpectedUser_thenCorrect() {
        User user = new User();
        String name = user.setDefaultName();
        Map<String, User> res = userRepository.addNewUser(name);
        User userReturned = res.get(name);

        assertEquals(res.size(), 1);
        assertEquals(user, userReturned);
    }

    @Test
    public void addTwoUsersWithSameNames_whenReturnsMapWithTwoUsers_oneExpectedUserAndOtherWithAutogeneratedName_thenCorrect() {
        String commonName = "Dejavu";
        userRepository.addNewUser(commonName);
        Map<String, User> res = userRepository.addNewUser(commonName);
        assertEquals(res.size(), 2);

        User userReturned_1 = res.get(commonName);
        res.remove(commonName);
        List<User> values = new ArrayList<>(res.values());
        User userReturned_2 = values.get(0);
        assertTrue(userReturned_2.name.startsWith("User_"));
        assertNotEquals(userReturned_1, userReturned_2);
    }

    @Test
    public void addUserWithNullInsteadOfNameParam_whenReturnsMapWithOneAutogeneratedNamedUser_thenCorrect() {
        Map<String, User> res = userRepository.addNewUser(null);
        List<User> values = new ArrayList<>(res.values());
        User userReturned = values.get(0);

        assertEquals(res.size(), 1);
        assertNotEquals(userReturned, new User(null));
        assertTrue(userReturned.name.startsWith("User_"));
    }

    @Test
    public void addUserWithEmptyStringInsteadOfNameParam_whenReturnsMapWithOneAutogeneratedNamedUser_thenCorrect() {
        Map<String, User> res = userRepository.addNewUser("");
        List<User> values = new ArrayList<>(res.values());
        User userReturned = values.get(0);

        assertEquals(res.size(), 1);
        assertNotEquals(userReturned, new User(""));
        assertTrue(userReturned.name.startsWith("User_"));
    }

    @Test
    public void addUserWithSpecialSymbolsIncludedToName_whenReturnsMapWithOneAutogeneratedNamedUser_thenCorrect() {
        Map<String, User> res = userRepository.addNewUser("/\\***2@\n\t");
        List<User> values = new ArrayList<>(res.values());
        User userReturned = values.get(0);

        assertEquals(res.size(), 1);
        assertNotEquals(userReturned, new User("/\\***2@\n\t"));
        assertTrue(userReturned.name.startsWith("User_"));
    }
}
