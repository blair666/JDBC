package model;
//❑ Using Hibernate, create a Department Model with the following attributes:
//        ➢ int did
//        ➢ String name
//        ➢ String state
//❑ Using Hibernate, create the relevant services for this Model (create, update name/state, find,and delete).
//❑ Do not forget to add your model to the configuration file (hibernate.cfg.xml).


import jakarta.persistence.*;


@Entity
@Table(name = "Department")

public class Department {
    @Column(name = "Department_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int did;

    private String name;
    private String state;

    public Department() {

    }

    public Department(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}


