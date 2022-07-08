package gintautassimkus.studentai;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
@Transactional
public class CustomUserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private WebSecurityConfig webSecurityConfig;

    public void registerNewUserAccount(UserDto userDto) throws UserAlreadyExistsException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: "
              + userDto.getEmail());
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(webSecurityConfig.encoder().encode(userDto.getPassword()));
        usersRepository.save(user);
    }

    private boolean emailExists(String email) {
        return usersRepository.findByEmail(email) != null;
    }
}