package reservation.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.dao.UserDAO;
import reservation.dto.UserDTO;
import reservation.frontController.ActionForward;
import reservation.util.ModalUtil;
import reservatoin.command.Command;

public class UserJoinCommand implements Command {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		boolean isRedirect = true;
		String viewPage = "userConfirmView.reservation";
		HttpSession session = request.getSession();
		String userName = null;
		String userResidentID = null;
		String userID = null;
		String userPassword = null;
		String userPasswordConfirm = null;
		String userPhone = null;
		String userAddress = null;
		String userEmail = null;
		int userType = 1;
		
		if(session.getAttribute("userNameForJoin") != null) {
			userName = (String) session.getAttribute("userNameForJoin");
		}
		if(session.getAttribute("userResidentIDForJoin") != null) {
			userResidentID = (String) session.getAttribute("userResidentIDForJoin");
		}
		if(userName == null || userResidentID == null ||
		   userName.equals("") || userResidentID.equals("")) {
			session.setAttribute("modal", new ModalUtil("���� �޽���", "�Ǹ� �� �ֹε�Ϲ�ȣ ������ ���� ���ּ���.", ModalUtil.ERROR));
		}
		if(request.getParameter("userID") != null) {
			userID = (String) request.getParameter("userID");
		}
		if(request.getParameter("userPassword") != null) {
			userPassword = (String) request.getParameter("userPassword");
		}
		if(request.getParameter("userPasswordConfirm") != null) {
			userPasswordConfirm = (String) request.getParameter("userPasswordConfirm");
		}
		if(request.getParameter("userPhone") != null) {
			userPhone = (String) request.getParameter("userPhone");
		}
		if(request.getParameter("userAddress") != null) {
			userAddress = (String) request.getParameter("userAddress");
		}
		if(request.getParameter("userEmail") != null) {
			userEmail = (String) request.getParameter("userEmail");
		}
		System.out.print(userID);
		System.out.print(userPassword);
		System.out.print(userPhone);
		System.out.print(userAddress);
		System.out.print(userEmail);
		if(userID == null || userPassword == null ||
		   userPhone == null || userAddress == null ||
		   userEmail == null || userID.equals("") ||
		   userPassword.equals("") || userPhone.equals("") ||
		   userAddress.equals("") || userEmail.equals("")) {
			session.setAttribute("modal", new ModalUtil("���� �޽���", "��� ������ �������ּ���.", ModalUtil.ERROR));
		} else if(!userPassword.equals(userPasswordConfirm)) {
			session.setAttribute("modal", new ModalUtil("���� �޽���", "��й�ȣ�� ��й�ȣ Ȯ���� �ٸ��� �ʽ��ϴ�.", ModalUtil.ERROR));
		} else {
			UserDAO userDAO = new UserDAO();
			UserDTO user = new UserDTO(
				userID,
				userPassword,
				userResidentID,
				userName,
				userPhone,
				userAddress,
				userEmail,
				userType
			);
			int result = userDAO.join(user);
			if(result == 1) {
				session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ�����Կ� �����߽��ϴ�.", ModalUtil.SUCCESS));
				viewPage = "userLoginView.reservation";
			} else {
				session.setAttribute("modal", new ModalUtil("���� �޽���", "ȸ�����Կ� �����߽��ϴ�.", ModalUtil.ERROR));
			}
		}
		return new ActionForward(isRedirect, viewPage);
	}
	
}
