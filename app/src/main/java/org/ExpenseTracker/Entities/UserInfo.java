package org.ExpenseTracker.Entities;
import org.ExpenseTracker.Entities.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields as parameters
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode methods
@ToString // Lombok: Generates a toString() method that includes all fields
@Table(name = "user_info") // Assuming the table name is user_info
public class UserInfo {
    @Id // JPA: Marks this field as the primary key
    @Column(name = "user_id") // JPA: Maps this field to the "id" column in the database
    private String userId;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id") // Assuming role_id is the foreign key in user_roles
    )
    // Assuming the join table is user_roles
    private Set<UserRoles> roles= new HashSet<>();

}
