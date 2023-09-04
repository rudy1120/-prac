package reservation.command.user;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.dao.UserDAO;
import reservation.dto.UserDTO;
import reservation.frontController.ActionForward;
import reservation.util.MathUtil;
import reservation.util.ModalUtil;
import reservation.util.SMTPUtil;
import reservatoin.command.Command;

public class UserPasswordFindCommand implements Command {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		boolean isRedirect = true;
	    String viewPage = "userFindView.reservation";
		HttpSession session = request.getSession();
	    String userID = null;
	    String userEmail = null;
		String userPassword = MathUtil.getPasswordCode();
	    if(request.getParameter("userID") != null) {
	    	userID = request.getParameter("userID");
	    } if(request.getParameter("userEmail") != null) {
	    	userEmail = request.getParameter("userEmail");
	    }
	    if(userID == null || userEmail == null ||
	       userID.equals("") || userEmail.equals("")) {
	    	session.setAttribute("modal", new ModalUtil("���� �޽���", "��� ������ �� ĭ�� �� �����ϴ�.", ModalUtil.ERROR));
	    } else if(!userEmail.contains("@")) {
	    	session.setAttribute("modal", new ModalUtil("���� �޽���", "�̸��� ������ �߸��Ǿ����ϴ�.", ModalUtil.ERROR));
	    } else if(userEmail.length() < 5 || userEmail.length() > 50) {
	    	session.setAttribute("modal", new ModalUtil("���� �޽���", "�̸��� �ּ��� ���̰� ���������Դϴ�.", ModalUtil.ERROR));
	    } else { // �Է��� �̸��� �ּҰ� ������ ��� ó�����ݴϴ�.
		    UserDAO userDAO = new UserDAO();
		    UserDTO user = userDAO.getUser(userID);
		    if(user == null) {
		    	session.setAttribute("modal", new ModalUtil("���� �޽���", "�������� �ʴ� ���̵��Դϴ�.", ModalUtil.ERROR));
		    } else if(!userDAO.getUser(userID).getUserEmail().equals(userEmail)) {
		    	session.setAttribute("modal", new ModalUtil("���� �޽���", "�̸��� �ּҰ� �ٸ��� �ʽ��ϴ�.", ModalUtil.ERROR));
		    } else {
				String from = SMTPUtil.getAdminEmail();
				String to = userEmail;
				String subject = "[ ��ȭ ���� ����Ʈ ] �� ��й�ȣ�� �����ص帳�ϴ�.";
				String content = "<p style='font-size: 14px; font-family: Nanum Gothic;'>ȸ������ �� ��й�ȣ�� <strong>" +
						userPassword + "</strong> �Դϴ�.</p>";
				Properties p = new Properties();
				p.put("mail.smtp.user", from);
				p.put("mail.smtp.host", "smtp.googlemail.com");
				p.put("mail.smtp.port", "465");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.debug", "true");
				p.put("mail.smtp.socketFactory.port", "465");
				p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				p.put("mail.smtp.socketFactory.fallback", "false");
				try {
					Authenticator auth = new SMTPUtil();
					Session ses = Session.getInstance(p, auth);
					ses.setDebug(true);
					MimeMessage msg = new MimeMessage(ses);
					msg.setSubject(subject);
					Address fromAddr = new InternetAddress(from);
					msg.setFrom(fromAddr);
					Address toAddr = new InternetAddress(to);
					msg.addRecipient(Message.RecipientType.TO, toAddr);
					msg.setContent(content, "text/html;charset=UTF8");
					Transport.send(msg);
					int result = userDAO.changePassword(userID, userPassword);
					if(result == 1) {
						session.setAttribute("modal", new ModalUtil("���� �޽���", "�� ��й�ȣ�� �̸��Ϸ� ���۵Ǿ����ϴ�.", ModalUtil.SUCCESS));
						viewPage = "userFindResultView.reservation";
					} else {
						session.setAttribute("modal", new ModalUtil("���� �޽���", "��й�ȣ �缳�� ������ �߻��߽��ϴ�.", ModalUtil.ERROR));
					}
				} catch (Exception e) {
					session.setAttribute("modal", new ModalUtil("���� �޽���", "�̸��� ���� ������ �߻��߽��ϴ�.", ModalUtil.ERROR));
				}
		    }
	    }
		return new ActionForward(isRedirect, viewPage);
	}
}
