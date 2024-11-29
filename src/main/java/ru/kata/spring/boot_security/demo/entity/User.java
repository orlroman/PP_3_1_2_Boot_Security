package ru.kata.spring.boot_security.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    
    @NotBlank(message = "First name should not be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]+$", message = "First name must contain only letters")
    @Size(max = 128, message = "maximum length 128")
    @Column(name = "first_name")
    private String firstName;
    
    @NotBlank(message = "Last name should not be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]+$", message = "Last name must contain only letters")
    @Size(max = 128, message = "maximum length 128")
    @Column(name = "last_name")
    private String lastName;
    
    @NotNull(message = "Age should not be empty")
    @Digits(integer = 2, fraction = 0, message = "Age must be a number with up to 3 digits")
    @PositiveOrZero(message = "Age must be positive or zero")
    @Column(name = "age")
    private Integer age;
    
    @NotBlank(message = "Email should not be empty")
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @NotBlank(message = "Password should not be empty")
    @Column(name = "password")
    private String password;
    
    
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    
    public User() {
    }
    
    public User(String firstName, String lastName, Integer age,
                String email, String password, Set<Role> roles) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    
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
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
