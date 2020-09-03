package com.example.virtualworkrooms.rest.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import com.example.virtualworkrooms.controlador.VirtualWorkRoomsException;
import com.example.virtualworkrooms.modelo.Usuario;

@Service
public class JwtUtil {

    private static final int EXPIRACION = 1000 * 60 * 60 * 10;
    private final String SECRET_KEY_ROUTE = "./src/main/resources/secret";
    private String secretKey;

    public JwtUtil() throws VirtualWorkRoomsException {
        secretKey = "";
        File secret = new File(SECRET_KEY_ROUTE);
        try {
            Scanner scanner = new Scanner(secret);
            while(scanner.hasNext()){
                secretKey +=scanner.next();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new VirtualWorkRoomsException("Error al abrir el archivo secret", e);
        }

    }

    public String extractUserId(String token) throws VirtualWorkRoomsException {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws VirtualWorkRoomsException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) throws VirtualWorkRoomsException{
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, usuario.getId());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACION))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

}