package cl.api.users.usersapi;

import cl.api.users.usersapi.model.Address;
import cl.api.users.usersapi.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test //Pruebas unitarias para el metodo createUser
    public void createUser() {
        Users users = new Users();
        users.setName("Juan");
        users.setEmail("juan@gmail.com");
        users.setBirthdate("1990-01-01");
        users.setAddress(null);
        Address address = new Address();
        address.setStreet("Calle 1");
        address.setCity("Santiago");
        address.setCountry("Chile");
        address.setZip("123456");
        address.setUsers(users);
        users.setAddress(address);


    }
}
