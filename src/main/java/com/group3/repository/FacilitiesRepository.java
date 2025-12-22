package com.group3.repository;

import com.group3.model.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Integer>{
    @Query("""
        SELECT f FROM Facilities f
        WHERE (:swimmingPool IS NULL OR f.swimmingPool = :swimmingPool)
          AND (:gymnasium   IS NULL OR f.gymnasium   = :gymnasium)
          AND (:wifi        IS NULL OR f.wifi        = :wifi)
          AND (:roomService IS NULL OR f.roomService = :roomService)
          AND (:airCondition IS NULL OR f.airCondition = :airCondition)
          AND (:breakfast   IS NULL OR f.breakfast   = :breakfast)
    """)
    List<Facilities> searchFacilities(
            Boolean swimmingPool,
            Boolean gymnasium,
            Boolean wifi,
            Boolean roomService,
            Boolean airCondition,
            Boolean breakfast
    );

    boolean existsById(Integer hotelId);
}
