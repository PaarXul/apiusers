package cl.api.users.usersapi.repository;


import cl.api.users.usersapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AddressRepository extends JpaRepository<Address, Long> {

}
