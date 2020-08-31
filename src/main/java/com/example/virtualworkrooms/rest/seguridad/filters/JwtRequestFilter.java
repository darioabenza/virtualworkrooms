package com.example.virtualworkrooms.rest.seguridad.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.virtualworkrooms.modelo.Usuario;
import com.example.virtualworkrooms.rest.seguridad.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String idUsuario = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            // extrae el nombre solo si el token es válido, en otro caso saltará una excepción
            try {
                idUsuario = jwtUtil.extractUserId(jwt);
            } catch (Exception e) {
                System.err.println("Error al procesar JWT, probablemente debido a un token no válido");
                //e.printStackTrace();
            }
        }

        if(idUsuario != null){
            Usuario usuarioMock = new Usuario();
            usuarioMock.setId(idUsuario);
            //Comportamiento de Spring security por defecto. Se ejecuta si el token es válido
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                idUsuario, null, usuarioMock.getAuthorities());
            //Almacena el mock autenticado en el contexto. Si fuese necesario se podría recuperar el usuario real para su uso en la aplicación
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            
        }
        //Pasa el relevo al siguiente filtro si lo hay
        chain.doFilter(request, response);
    }
}