package com.pelgray.repository;

import com.pelgray.domain.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    @Query("SELECT cl FROM #{#entityName} cl WHERE cl.name LIKE %:substring%")
    List<ClientEntity> findBySubstringInName(@Param("substring") String substring);
}
