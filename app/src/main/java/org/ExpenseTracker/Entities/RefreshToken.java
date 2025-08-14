package org.ExpenseTracker.Entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields as parameters
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode methods
@Builder // Lombok: Enables the Builder pattern for object creation
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // Jackson: Converts camelCase fields to snake_case in JSON
@Table(name = "refresh_token") // Assuming the table name is refresh_token JPA: Specifies the database table name this entity maps to
public class RefreshToken {
    @Id // JPA: Marks this field as the primary key
    @Column(name = "id") // JPA: Maps this field to the "id" column in the database
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Adjust strategy as necessary
    private int id;
    private String token; // Assuming userId is a String, adjust if necessary
    private Instant expiryDate; // Assuming expiryDate is a timestamp in milliseconds
    @OneToOne(fetch = FetchType.LAZY) // JPA: Defines a one-to-one relationship with UserInfo
//    @JoinTable(
//            name = "user_id",
//            joinColumns = @JoinColumn(name="id"),
//            inverseJoinColumns=@JoinColumn(name="user_id")) // JPA: Maps this field to the "user_id" column in the database
    @JoinColumn(name = "id", referencedColumnName = "user_id") // JPA: Maps this field to the "user_id" column in the database
    private UserInfo userInfo; // Assuming userInfo is a UserInfo object, adjust if necessary

}
