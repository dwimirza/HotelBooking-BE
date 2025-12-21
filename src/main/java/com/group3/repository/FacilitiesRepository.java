package com.group3.repository;

import com.group3.model.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Integer>{
    List<Facilities> findBySwimmingPoolAndGymnasiumAndWifiAndRoomServiceAndAirConditionAndBreakfast(
            Boolean swimmingPool,
            Boolean gymnasium,
            Boolean wifi,
            Boolean roomService,
            Boolean airCondition,
            Boolean breakfast
    );

    boolean existsById(Integer hotelId);
}
