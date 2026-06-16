package trace.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trace.entity.User;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    @GetMapping
    public String healthCheck(){
        return "Healthy";
    }

    @GetMapping("/me")
    public String me() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
    @GetMapping("/me2")
    public String me2(
            @AuthenticationPrincipal User user
            ){
        return "Email id : " +  user.getEmail() + "\n" + "Username : " + user.getDisplayUsername();
    }

}
