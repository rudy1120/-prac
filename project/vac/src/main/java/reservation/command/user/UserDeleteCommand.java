package reservation.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.dao.UserDAO;
import reservation.frontController.ActionForward;
import reservation.util.ModalUtil;
import reservatoin.command.Command;

public class UserDeleteCommand implements Command {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		boolean isRedirect = true;
		String viewPage = "mainView.reservation";
		HttpSession session = request.getSession();
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if(userID == null || userID.equals("")) {
			session.setAttribute("modal", new ModalUtil("���� �޽���", "������ ����Ǿ����ϴ�.", ModalUtil.ERROR));
		} else {
			UserDAO userDAO = new UserDAO();
			int result = userDAO.delete(userID);
			if (result == 1) {
				session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ��Ż�� �����߽��ϴ�.", ModalUtil.ERROR));
			} else {
				session.removeAttribute("userID");
				session.removeAttribute("userType");
				session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ��Ż�� �����߽��ϴ�.", ModalUtil.SUCCESS));
				viewPage = "mainView.reservation";
			}
		}
		return new ActionForward(isRedirect, viewPage);
	}
	
}
