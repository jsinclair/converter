package converter.database.service;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

import converter.database.model.User;

@Service
public interface IUserService {

    List<User> findAll();

    Optional<User> findUserByUsername(String username);
}
