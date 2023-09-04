package reservation.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.dao.UserDAO;
import reservation.dto.UserDTO;
import reservation.frontController.ActionForward;
import reservation.util.ModalUtil;
import reservatoin.command.Command;

public class UserEditCommand implements Command {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		boolean isRedirect = true;
		String viewPage = "userEditView.reservation";
		HttpSession session = request.getSession();
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			session.setAttribute("modal", new ModalUtil("���� �޽���", "�α����� ���� ���ּ���.", ModalUtil.ERROR));
			viewPage = "userLoginView.reservation";
		} else {
			String userPassword = null;
			String userPasswordConfirm = null;
			String userPhone = null;
			String userAddress = null;
			String userEmail = null;
			if (request.getParameter("userPassword") != null) {
				userPassword = (String) request.getParameter("userPassword");
			}
			if (request.getParameter("userPasswordConfirm") != null) {
				userPasswordConfirm = (String) request.getParameter("userPasswordConfirm");
			}
			if (request.getParameter("userPhone") != null) {
				userPhone = (String) request.getParameter("userPhone");
			}
			if (request.getParameter("userAddress") != null) {
				userAddress = (String) request.getParameter("userAddress");
			}
			if (request.getParameter("userEmail") != null) {
				userEmail = (String) request.getParameter("userEmail");
			}
			if (userID == null || userPassword == null ||
				userPhone == null || userAddress == null ||
				userEmail == null || userID.equals("") ||
				userPassword.equals("") || userPhone.equals("") ||
				userAddress.equals("") || userEmail.equals("")) {
				session.setAttribute("modal", new ModalUtil("���� �޽���", "��� ������ �������ּ���.", ModalUtil.ERROR));
			} else if (!userPassword.equals(userPasswordConfirm)) {
				session.setAttribute("modal", new ModalUtil("���� �޽���", "��й�ȣ�� ��й�ȣ Ȯ���� �ٸ��� �ʽ��ϴ�.", ModalUtil.ERROR));
			} else {
				UserDAO userDAO = new UserDAO();
				UserDTO user = userDAO.getUser(userID);
				int result = userDAO.edit(userID, userPassword, userPhone, userAddress, userEmail);
				if (result == 1) {
					session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ�� ���� ������ �����߽��ϴ�. �ٽ� �α������ּ���.", ModalUtil.SUCCESS));
					viewPage = "userLoginView.reservation";
					session.removeAttribute("userID");
					session.removeAttribute("userType");
				} else {
					session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ�� ���� ������ �����߽��ϴ�.", ModalUtil.ERROR));
				}
			}
		}
		return new ActionForward(isRedirect, viewPage);
	}

}
