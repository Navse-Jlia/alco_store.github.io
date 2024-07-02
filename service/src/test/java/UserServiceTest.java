import com.google.common.base.Stopwatch;
import com.vasilkov.model.dto.UserDto;
import com.vasilkov.model.enums.UserRole;
import com.vasilkov.service.mapper.UserMapper;
import com.vasilkov.service.model.User;
import com.vasilkov.service.repository.UserRepository;
import com.vasilkov.service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type User service test.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    //    @Mock
    //    private UserMapperImpl userMapper;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    //    @Autowired
    private UserService userService;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Gets user by email success.
     *
     * @throws Exception the exception
     */
    @Test
    public void getUserByEmail_success() {
        String email = "semen.autocadov@gmail.com";
        User user = new User(UUID.fromString("51fb579a-20a6-4b3b-8aa7-ccbcb2d7f7ec"), "Semen", "Autocadov",
                "semen.autocadov@gmail.com", "password12", UserRole.ROLE_USER, "");

        UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), user.getAvatar(), user.getRole());

        //        when(userService.getMapper().toDto(user)).thenReturn(userDto);
        when(userService.findByEmail(email)).thenReturn(userDto);
        Stopwatch stopwatch = Stopwatch.createStarted();

        for (int i = 0; i < 500000; i++) {
            UserDto foundUser = userService.findByEmail(email);
            //            assertNotNull(foundUser);
        }
        stopwatch.stop();
        long elapsedTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        System.out.println("Total time for " + 500000 + " requests: " + elapsedTime + " ms");

    }

    /**
     * Gets all success.
     */
    @Test
    public void getAll_success() {
        List<User> duplicate = new ArrayList<>();

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

        duplicate.add(user);
        duplicate.add(user1);
        duplicate.add(user2);
        duplicate.add(user3);
        duplicate.add(user4);

        List<UserDto> dtos = new ArrayList<>();
        for (User u : duplicate) {
            UserDto userDto = new UserDto(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(),
                    u.getPassword(), u.getAvatar(), u.getRole());
            dtos.add(userDto);
        }

        when(userService.getRepository().findAll()).thenReturn(duplicate);
        //        when(mapper.toDtoList(duplicate)).thenReturn(dtos);

        List<UserDto> users = userService.getAll();
        System.out.println(users);
        assertEquals(5, users.size());
        //        verify(userRepository, times(1)).findAll();
    }
}