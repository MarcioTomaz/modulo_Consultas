package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.DTO.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value =
            "SELECT name" +
            "   FROM customers " +
            "       WHERE UPPER(state) = UPPER(:state) ")
    List<CustomerMinProjection> search1(String state);

    @Query( "SELECT new com.devsuperior.uri2602.DTO.CustomerMinDTO(obj.name)" +
                    "   FROM Customer obj" +// tem q ser o nome da classe e dar um apelido
                    "       WHERE UPPER(obj.state) = UPPER(:state) ")
    List<CustomerMinDTO> search2(String state);

}
