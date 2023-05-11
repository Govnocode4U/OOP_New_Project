package org.saloon_management.system_models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
//    @ManyToMany()
//    private List<Date> dates;

    public String checkMasterWorkload(String masterName, Date startDate, Date endDate) {
        return null;
    }

    public String checkWorkShedule(Date date) {
        return null;
    }
}

