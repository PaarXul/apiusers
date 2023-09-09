package cl.api.users.usersapi;


import cl.api.users.usersapi.model.Address;
import cl.api.users.usersapi.model.Users;
import cl.api.users.usersapi.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createdusersTest() throws Exception {

        var user = getUsers();

        Users savedUser= new Users();
            BeanUtils.copyProperties(user, savedUser);
            savedUser.setId(2L); // Assume that system assigned new id

            when(usersService.createUser(user)).thenReturn(savedUser);

            this.mockMvc.perform(post("/createUsers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk());

        }

    @Test
    public void  getusersTest() throws Exception {
        var user = getUsers();
        when(usersService.createUser(user)).thenReturn(user);
        this.mockMvc.perform(post("/createUsers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/getUsers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

    }







    private static Users getUsers() {
        Users user = new Users();
        user.setId(1L);
        user.setName("testUser");
        user.setEmail("test@example.com");
        user.setBirthdate(String.valueOf(LocalDate.parse("1990-01-01")));

        Address address = new Address();
        address.setId(1L);
        address.setState("Region Metropolitana");
        address.setStreet("Calle 1");
        address.setCity("Santiago");
        address.setCountry("Chile");
        address.setZip("123456");


        user.setAddress(address);
        return user;
    }
}
