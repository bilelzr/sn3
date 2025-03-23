package com.sofrecom.sn3.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_sequence")
    @SequenceGenerator(name = "application_sequence", sequenceName = "application_sequence", allocationSize = 1, initialValue = 1)
    private long applicationPkId;

    private String applicationName;
    private String applicationDescription;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;


    public Application(long applicationPkId, String applicationName, String applicationDescription, Group group) {
        this.applicationPkId = applicationPkId;
        this.applicationName = applicationName;
        this.applicationDescription = applicationDescription;
        this.group = group;
    }

    public Application() {
    }

    public long getApplicationPkId() {
        return applicationPkId;
    }

    public void setApplicationPkId(long applicationPkId) {
        this.applicationPkId = applicationPkId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


}



