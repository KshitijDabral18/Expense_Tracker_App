package org.ExpenseTracker.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.github.cdimascio.dotenv.Dotenv;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT Service for handling JSON Web Token operations
 * This service provides functionality for creating, parsing, and validating JWT tokens
 * Used for authentication and authorization in the Expense Tracker application
 */
@Service
public class JWTService {

    // Load JWT secret key from environment variables for security
    // The secret key is used to sign and verify JWT tokens
    public static final String SECRET_KEY = Dotenv.load().get("JWT_SECRET_KEY");

    /**
     * Extracts the username (subject) from a JWT token
     * @param token The JWT token as a string
     * @return The username stored in the token's subject claim
     */
    public String extractUsername(String token) {
        // Use the generic extractClaim method with Claims::getSubject method reference
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from a JWT token
     * @param token The JWT token as a string
     * @return The expiration date of the token
     */
    public Date extractExpiration(String token) {
        // Use the generic extractClaim method with Claims::getExpiration method reference
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generic method to extract any claim from a JWT token
     * This method uses functional programming to allow flexible claim extraction
     * @param token The JWT token as a string
     * @param claimsResolver A function that takes Claims and returns the desired type T
     * @param <T> The type of the claim value to be extracted
     * @return The extracted claim value of type T
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // First extract all claims from the token
        final Claims claims = extractAllClaims(token);
        // Apply the provided function to get the specific claim
        return claimsResolver.apply(claims);
    }

    /**
     * Parses a JWT token and extracts all claims from its payload
     * This method verifies the token signature using the secret key
     * @param token The JWT token as a string
     * @return Claims object containing all the token's payload data
     * @throws io.jsonwebtoken.JwtException if the token is invalid or expired
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                // Set the signing key used to verify the JWT token's signature
                .setSigningKey(getSignKey())
                // Build the JWT parser with the configured settings
                .build()
                // Parse and validate the JWT token string, verifying signature and structure
                .parseClaimsJws(token)
                // Extract and return the claims (payload data) from the token
                .getBody();
    }

    /**
     * Creates a cryptographic key from the BASE64-encoded secret key
     * This key is used for signing and verifying JWT tokens
     * @return Key object for HMAC-SHA256 algorithm
     */
    private Key getSignKey() {
        // Decode the BASE64-encoded secret key string into bytes
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        // Create an HMAC-SHA key for JWT signing/verification
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Checks if a JWT token has expired
     * @param token The JWT token as a string
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        // Compare the token's expiration date with the current date
        return extractExpiration(token).before(new Date());
    }

    /**
     * Creates a new JWT token with specified claims and username
     * @param claims Additional claims to include in the token payload
     * @param username The username to set as the subject of the token
     * @return A signed JWT token as a string
     */
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)                                              // Add custom claims to payload
                .setSubject(username)                                          // Set username as subject
                .setIssuedAt(new Date(System.currentTimeMillis()))            // Set token creation time
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 1)) // Set expiration (100000 minutes)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)             // Sign with HMAC-SHA256
                .compact();                                                    // Generate the final token string
    }
}