package org.ExpenseTracker.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields as parameters
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode methods
@Table(name = "user_roles") // Assuming the table name is user_roles
public class UserRoles {
    @Id // JPA: Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // JPA: Auto-generates primary key values using provider's default strategy (AUTO, IDENTITY, SEQUENCE, or TABLE)
    @Column(name = "role_id") // Assuming role_id is the primary key for RefreshToken// JPA: Maps this field to the "role_id" column in the database
    private Long roleId;
    private String name;
}
