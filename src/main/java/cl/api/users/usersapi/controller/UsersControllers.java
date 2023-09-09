package cl.api.users.usersapi.controller;


import cl.api.users.usersapi.model.Address;
import cl.api.users.usersapi.model.Users;
import cl.api.users.usersapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UsersControllers {

    private final UsersService usersService;

    @Autowired
    public UsersControllers(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("createUsers")
    public ResponseEntity<Users> getusers(@RequestBody Users users)  {
        return  ResponseEntity.ok(usersService.createUser(users));
    }

    @GetMapping("getUsers")
    public ResponseEntity<Set<Users>> getUsers()  {
        return  ResponseEntity.ok( new HashSet<>(usersService.GetAllUsers()));
    }

    @GetMapping("getusersById/{userId}")
    public ResponseEntity<Users> getUsersById(@PathVariable Long userId)  {
        return  ResponseEntity.ok(usersService.getUserById(userId));
    }

    @DeleteMapping("deleteUsersById/{userId}")
    public ResponseEntity<Void> deleteUsersById(@PathVariable Long userId)  {
        usersService.deleteUser(userId);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("updateUsersById/{userId}")
    public ResponseEntity<Users> updateUsersById(@PathVariable Long userId, @RequestBody Users users )  {
        return  ResponseEntity.ok(usersService.updateUser( userId, users));
    }

}
