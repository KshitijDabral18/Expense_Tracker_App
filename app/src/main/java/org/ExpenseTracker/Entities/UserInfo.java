package org.ExpenseTracker.Entities;
import org.ExpenseTracker.Entities.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_info") // Assuming the table name is user_info
public class UserInfo {
    @Id
    @Column(name = "user_id") // Assuming user_id is the primary key for UserInfo
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
