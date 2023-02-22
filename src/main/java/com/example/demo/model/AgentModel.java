package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "agent")
public class AgentModel {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;

    // Constructor
    public AgentModel (){

    }

    //Overloaded Constructor
    public AgentModel(Long id, String name){
        this.id = id;
        this.name = name;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
