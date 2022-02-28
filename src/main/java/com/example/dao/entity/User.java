package com.example.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "password", unique = true, nullable = false)
    @Size(min = 2, max = 50)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @Size(min = 6, max = 50)
    private String email;

    @Column(name = "age", unique = true, nullable = false)
    @Min(1)
    @Max(150)
    private Long age;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> role;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public User() {
    }

    public User(Long id, @Size(min = 3, max = 50) String name, @Size(min = 2, max = 50) String password, @Size(min = 6, max = 50) String email, @Min(1) @Max(150) Long age, List<Role> role, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
        this.orders = orders;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(age, user.age) &&
                Objects.equals(role, user.role) &&
                Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, age, role, orders);
    }
}