package trace.service.interfaces;

import trace.dto.DashboardResponse;
import trace.entity.User;

public interface DashboardService {
    DashboardResponse getDashboard(User user);
}
