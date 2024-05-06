package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Получаем роли аутентифицированного пользователя
        authentication.getAuthorities().forEach(authority -> {
            try {
                // Перенаправляем на разные страницы в зависимости от роли
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    redirectStrategy.sendRedirect(request, response, "/admin/admin");
                } else if (authority.getAuthority().equals("ROLE_USER")) {
                    redirectStrategy.sendRedirect(request, response, "/profile");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}