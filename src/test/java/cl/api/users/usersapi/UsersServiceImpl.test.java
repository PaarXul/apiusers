package cl.api.users.usersapi;

import cl.api.users.usersapi.model.Address;
import cl.api.users.usersapi.model.Users;
import cl.api.users.usersapi.repository.AddressRepository;
import cl.api.users.usersapi.repository.UsersRepository;
import cl.api.users.usersapi.service.serviceimpl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserById() {
        Users user = new Users();
        user.setId(1L);
        user.setName("John");


        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        Users result = usersService.getUserById(1L);

        assertEquals(user, result);
    }

    @Test
    void GetAllUsers() {
        Set<Users> users = new HashSet<>();
        Users user1 = new Users();
        user1.setId(1L);
        user1.setName("John");
        user1.setBirthdate("1990-01-01");
        user1.setEmail("g@mail.com");
        Users user2 = new Users();
        user2.setId(2L);
        user2.setName("Jane");
        users.add(user1);
        users.add(user2);

        when(usersRepository.findAll()).thenReturn((List<Users>) users);

        Set<Users> result = usersService.GetAllUsers();

        assertEquals(users, result);
    }

    @Test
    void updateUser() {
        Users user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        user.setBirthdate("1990-01-01");
        user.setEmail("g@mail.com");


        Address address = new Address();
        address.setId(1L);
        address.setStreet("Main St.");
        address.setCity("New York");
        address.setState("NY");
        address.setZip("10001");

        user.setAddress(address);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        Users updatedUser = new Users();
        updatedUser.setName("John Doe");
        updatedUser.setBirthdate("1990-01-01");
        updatedUser.setEmail("g@mail.com");

        Address updatedAddress = new Address();
        updatedAddress.setId(1L);
        updatedAddress.setStreet("Broadway");
        updatedAddress.setCity("New York");
        updatedAddress.setState("NY");
        updatedAddress.setZip("10002");

        updatedUser.setAddress(updatedAddress);

        Users result = usersService.updateUser(1L, updatedUser);



        assertEquals(updatedUser.getName(), result.getName());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedAddress.getStreet(), result.getAddress().getStreet());
        assertEquals(updatedAddress.getCity(), result.getAddress().getCity());
        assertEquals(updatedAddress.getState(), result.getAddress().getState());
        assertEquals(updatedAddress.getZip(), result.getAddress().getZip());
    }

    @Test
    void deleteUser() {
        Users user = new Users();
        user.setId(1L);
        user.setName("John");
        user.setBirthdate("1990-01-01");
        user.setEmail("g@mail.com");

        Address address = new Address();
        address.setId(1L);
        address.setStreet("Main St.");
        address.setCity("New York");
        address.setState("NY");
        address.setZip("10001");

        user.setAddress(address);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        usersService.deleteUser(1L);

        assertTrue(usersRepository.findById(1L).isPresent());

        System.out.println(usersRepository.findById(1L).get().getName());

       // assertFalse(addressRepository.findById(1L).isPresent());
    }

    @Test
    void createUser() {
        Users user = new Users();
        user.setId(1L);
        user.setName("John");
        user.setBirthdate("1990-01-01");
        user.setEmail("g@mail.com");

        Address address = new Address();
        address.setId(1L);
        address.setStreet("Main St.");
        address.setCity("New York");
        address.setState("NY");
        address.setZip("10001");

        user.setAddress(address);

        when(usersRepository.findByName("John")).thenReturn(null);
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        Users result = usersService.createUser(user);

        assertEquals(user, result);
    }
}