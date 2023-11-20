package com.udea.conductores.dao;

import com.udea.conductores.model.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IDriverDAO extends CrudRepository<Driver, Long> {
    @Query("from Driver d where d.rating>=4 AND d.available=true")
    public List<Driver> viewBestDriver();

}