package com.sofrecom.sn3.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "group_sn3")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_sequence")
    @SequenceGenerator(name = "group_sequence", sequenceName = "group_sequence", allocationSize = 1, initialValue = 1)
    private long groupPkId;

    @Column(unique = true, nullable = false)
    private String groupName;
    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "group_pk_id"),
            inverseJoinColumns = @JoinColumn(name = "user_pk_id")
    )
    private List<User> membres;
    private UUID uuid;


    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Application> applications;

    public Group() {
    }

    public Group(long groupPkId, String groupName, List<User> membres) {
        this.groupPkId = groupPkId;
        this.groupName = groupName;
        this.membres = membres;
    }

    public Group(long groupPkId, String groupName, List<User> membres, UUID uuid) {
        this.groupPkId = groupPkId;
        this.groupName = groupName;
        this.membres = membres;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public long getGroupPkId() {
        return groupPkId;
    }

    public void setGroupPkId(long groupPkId) {
        this.groupPkId = groupPkId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getMembres() {
        return membres;
    }

    public void setMembres(List<User> membres) {
        this.membres = membres;
    }
}
