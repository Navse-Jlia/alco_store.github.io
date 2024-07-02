import com.vasilkov.model.dto.UserDto;
import com.vasilkov.model.enums.UserRole;
import com.vasilkov.service.ServiceApplication;
import com.vasilkov.service.model.User;
import com.vasilkov.service.repository.UserRepository;
import com.vasilkov.service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type User service test.
 */
@ExtendWith(SpringExtension.class)
//@Transactional
//@SpringBootTest(classes = ServiceApplication.class)
@ContextConfiguration(classes = ServiceApplication.class)
@DataJpaTest
public class IntegrationTest {

    @InjectMocks
    private UserService userService;

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user = new User(UUID.fromString("51fb579a-20a6-4b3b-8aa7-ccbcb2d7f7ec"), "Semen", "Autocadov",
                "semen.autocadov@gmail.com", "password12", UserRole.ROLE_USER, "");

        User user1 = new User(UUID.fromString("5f6a3b57-2d43-4c6d-8e0e-8a8e6d8f7a6b"), "Ivan", "Ivanov",
                "ivan.ivanov@gmail.com", "password123", UserRole.ROLE_USER, "");

        User user2 = new User(UUID.fromString("3b1c8d7a-5e2f-4b3a-9a1d-6f8b7e9f0a1d"), "Petr", "Petrov",
                "petr.petrov@gmail.com", "password1234", UserRole.ROLE_USER, "");

        User user3 = new User(UUID.fromString("7c3d4e6f-2b4a-4a5c-9a7e-1d6b8c9e0f2d"), "Anna", "Sidorova",
                "anna.sidorova@gmail.com", "password12345", UserRole.ROLE_USER, "");

        User user4 = new User(UUID.fromString("9f1b2a3c-4d5e-6f7a-8b9c-0e1f2a3d4e5b"), "Olga", "Smirnova",
                "olga.smirnova@gmail.com", "password123456", UserRole.ROLE_USER, "");

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }

    @Test
    public void getAll_success() {

        List<UserDto> dto = userService.getAll();
        assertEquals(5, dto.size());
    }
}