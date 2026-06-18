package trace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trace.dto.DashboardResponse;
import trace.entity.User;
import trace.service.interfaces.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping()
    public DashboardResponse getDashboard(@AuthenticationPrincipal User user){
        return dashboardService.getDashboard(user);
    }
}
