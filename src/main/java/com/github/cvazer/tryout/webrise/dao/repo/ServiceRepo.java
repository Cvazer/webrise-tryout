package com.github.cvazer.tryout.webrise.dao.repo;

import com.github.cvazer.tryout.webrise.dao.entity.ServiceEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ServiceRepo extends JpaRepository<ServiceEntity, String> {

    @Query("select e from ServiceEntity sub " +
            "join ServiceEntity e on sub.id = e.id " +
            "group by e.id " +
            "order by count(*) desc")
    Set<ServiceEntity> findTop(Limit limit);

}
