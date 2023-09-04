import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class bookingservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Booking> bookings = new ArrayList<>();
        // 예매 정보를 데이터베이스에서 가져오거나 가상의 데이터로 생성하여 리스트에 추가

        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/WEB-INF/my_bookings.jsp").forward(request, response);
    }
}
