//package com.microservice.productService.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoder;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtService {
//
//    private static final String SECRET="792F423F4528482B4D6250655368566D597133743677397A24432646294A404E" ;
//
//    public String generateToke(String username){
//
//        Map<String,Object> claims=new HashMap<>();
//        return createToken(claims,username);
//
//    }
//
//    private String createToken(Map<String, Object> claims, String username) {
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    private Key getSignKey() {
//        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String extractUserName(String token){
//         return  extractClaim(token, Claims::getSubject);
//
//    }
//
//    public  Date extractExpiration(String token){
//        return extractClaim(token,Claims::getExpiration);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
//        final Claims claims=extracAllClaims(token);
//        return claimsResolver.apply(claims);
//
//    }
//
//    private Claims extracAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private boolean isTokenExpired(String token){
//        return extractExpiration(token).before(new Date());
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails){
//
//        final String username=extractUserName(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//}
