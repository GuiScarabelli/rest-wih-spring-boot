package org.example.contentnegotiation.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;


@Entity // Notação que indica que essa classe é uma entidade no banco
@Table(name = "person") // Indica qual tabela essa entidade pertence

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // Declara o id e o deixa como AutoIncrement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A notação serve pra indicar a coluna, caso o nome do atributo for diferente
    // do nome da coluna a propriedade "name" especifica o nome da mesma
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 6)
    private String gender;

    // Construtor
    public Person(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender);
    }
}
