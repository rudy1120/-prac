package reservation.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.frontController.ActionForward;
import reservation.util.ModalUtil;
import reservatoin.command.Command;

public class UserLogoutCommand implements Command {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		boolean isRedirect = true;
		String viewPage = "mainView.reservation";
		HttpSession session = request.getSession();
		session.removeAttribute("userID");
		session.removeAttribute("userType");
		session.setAttribute("modal", new ModalUtil("���� �޽���", "�α׾ƿ��� �����߽��ϴ�.", ModalUtil.SUCCESS));
		return new ActionForward(isRedirect, viewPage);
	}
	
}