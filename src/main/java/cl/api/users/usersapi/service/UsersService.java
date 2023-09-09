package cl.api.users.usersapi.service;


import cl.api.users.usersapi.model.Address;
import cl.api.users.usersapi.model.Users;

import java.util.Set;

public interface UsersService {

    Users createUser(Users userDetails);

    Users getUserById(Long id);

    Set<Users> GetAllUsers();

    Users updateUser(Long id, Users userDetails);

    void deleteUser(Long id);


}
