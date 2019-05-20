package converter.database.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converter.database.model.User;
import converter.database.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public Optional<User> findUserByUsername(String username) {
      return repository.findUserByUsername(username);
    }
}
