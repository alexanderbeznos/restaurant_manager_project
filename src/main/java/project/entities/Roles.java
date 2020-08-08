package project.entities;

import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Entity
@Table(name = "roles")
public class Roles {
    private Long id;
    private String name;
    private Set<User> users;

    public Roles() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "roles_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> getUsers() {
        return users;
    }
}
