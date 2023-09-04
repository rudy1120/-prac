package reservation.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.dao.UserDAO;
import reservation.dto.UserDTO;
import reservation.frontController.ActionForward;
import reservation.util.ModalUtil;
import reservatoin.command.Command;

public class UserManageDeleteCommand implements Command {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		boolean isRedirect = true;
		String viewPage = "mainView.reservation";
		HttpSession session = request.getSession();
		String userID = null;
		if(request.getParameter("userID") != null) {
			userID = (String) request.getParameter("userID");
		}
		String admin = null;
		if(session.getAttribute("userID") != null) {
			admin = (String) session.getAttribute("userID");
		}
		if(admin == null || admin.equals("")) {
			session.setAttribute("modal", new ModalUtil("���� �޽���", "������ ����Ǿ����ϴ�.", ModalUtil.ERROR));
		} else {
			UserDAO userDAO = new UserDAO();
			UserDTO user = userDAO.getUser(admin);
			if(user.getUserType() != 0) {
				session.setAttribute("modal", new ModalUtil("���� �޽���", "�����ڰ� �ƴմϴ�.", ModalUtil.ERROR));
				viewPage = "userMainView.reservation";
				isRedirect = true;
			} else {
				int result = userDAO.delete(userID);
				if (result == 1) {
					session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ��Ż�� �����߽��ϴ�.", ModalUtil.SUCCESS));
					viewPage = "mainView.reservation";
				} else {
					session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ��Ż�� �����߽��ϴ�.", ModalUtil.ERROR));
				}
			}
		}
		return new ActionForward(isRedirect, viewPage);
	}
	
}
