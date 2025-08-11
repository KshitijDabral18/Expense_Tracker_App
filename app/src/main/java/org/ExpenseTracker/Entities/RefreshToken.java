package org.ExpenseTracker.Entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "refresh_token") // Assuming the table name is refresh_token
public class RefreshToken {
    @Id
    @Column(name = "id") // Assuming id is the primary key for RefreshToken
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Adjust strategy as necessary
    private int id;
    private String token; // Assuming userId is a String, adjust if necessary
    private Instant expiryDate; // Assuming expiryDate is a timestamp in milliseconds

}
