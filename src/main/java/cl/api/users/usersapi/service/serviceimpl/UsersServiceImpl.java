package cl.api.users.usersapi.service.serviceimpl;

import cl.api.users.usersapi.model.Address;
import cl.api.users.usersapi.model.Users;
import cl.api.users.usersapi.repository.AddressRepository;
import cl.api.users.usersapi.repository.UsersRepository;
import cl.api.users.usersapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, AddressRepository addressRepository) {
        this.usersRepository = usersRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Users createUser(Users users) {
        Users usersLocal = usersRepository.findByName(users.getName());

        if (usersLocal != null) {

            throw new RuntimeException("The user is already present.");
        } else {

            Address adde = addressRepository.save(users.getAddress());
            users.setAddress(adde);

            usersLocal = usersRepository.save(users);
        }
        return usersLocal;
    }

    @Override
    @Transactional(readOnly = true)
    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Users> GetAllUsers() {
        return new HashSet<Users>(usersRepository.findAll());
    }

    @Override
    public Users updateUser(Long id, Users users) {

        Users usersLocal = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if (usersLocal == null) {
            throw new RuntimeException("User not found");
        }else{

            Address ADD = addressRepository.findById(users.getAddress().getId()).orElseThrow(() -> new RuntimeException("Address not found"));
            ADD=users.getAddress();
                  ADD  = addressRepository.save(ADD);

            usersLocal = users;
            usersLocal = usersRepository.save(usersLocal);
        }


        return usersLocal;
    }

    @Override
    public void deleteUser(Long id) {
        Users usersLocal = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        usersRepository.deleteById(usersLocal.getId());
        Address addressLocal = addressRepository.findById(usersLocal.getAddress().getId()).orElseThrow(() -> new RuntimeException("Address not found"));
        addressRepository.deleteById(addressLocal.getId());
    }
}
