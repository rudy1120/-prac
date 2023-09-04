package egovframework.portal.unit.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.common.SessionKey;
import egovframework.portal.unit.common.UserType;
import egovframework.portal.util.TUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Facebook, Twitter oauth 관련 CONTROLLER
 *
 * @author J.Ryeon Lee
 * @since 2016.06.22
 */
@Controller
public class OAuthController {

	private final String fbAppSecret = EgovProperties.getProperty("facebook.appSecret");
	private final String twConsumerKey = EgovProperties.getProperty("twitter.consumer.key");
	private final String twConsumerSecret = EgovProperties.getProperty("twitter.consumer.secret");

	@ResponseBody
	@RequestMapping("/fb/login.do")
	public String fbLogin(@RequestParam String accessToken, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, fbAppSecret, Version.VERSION_2_6);
		User user = facebookClient.fetchObject("me", User.class);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute(SessionKey.USER_TYPE.getKey(), UserType.FACEBOOK.getCode());
			session.setAttribute(SessionKey.FB_USER_ID.getKey(), user.getId());
			session.setAttribute(SessionKey.FB_USER_NAME.getKey(), user.getName());
			rtn.put("success", Boolean.TRUE);
		}

		return rtn.toString();
	}

	@RequestMapping("/fb/logout.do")
	public String fbLogout(@RequestParam String backUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionKey.USER_TYPE.getKey());
		session.removeAttribute(SessionKey.FB_USER_ID.getKey());
		session.removeAttribute(SessionKey.FB_USER_NAME.getKey());

		return "redirect:" + TUtil.covertXSS(backUrl);
	}

	@RequestMapping("/tw/login.do")
	public String twitterLogin(@RequestParam String backUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(twConsumerKey, twConsumerSecret);

		RequestToken requestToken = twitter.getOAuthRequestToken();
		request.getSession().setAttribute(SessionKey.TW_REQ_TOKEN.getKey(), requestToken);
		request.getSession().setAttribute(SessionKey.TW_OAUTH_URL.getKey(), requestToken.getAuthorizationURL());
		request.getSession().setAttribute(SessionKey.TW_BACK_URL.getKey(), TUtil.covertXSS(backUrl));

		response.sendRedirect(requestToken.getAuthorizationURL());

		return null;
	}

	@RequestMapping("/tw/timelins.do")
	public String twitterTimeline(@RequestParam String backUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AccessToken accesstoken = new AccessToken("Access Token", "Access Token Secret");
		Twitter twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer("Consumer Key (API Key)", "Consumer Secret (API Secret)");
		twitter.setOAuthAccessToken(accesstoken);
		twitter4j.User user = twitter.verifyCredentials();

		List<Status> list = twitter.getUserTimeline();
		System.out.println("타임라인 계정:" + user.getScreenName());
		for (Status status : list) {
			System.out.println("작성자:" + status.getUser().getScreenName());
			System.out.println("타임라인내용:" + status.getText());
		}

		return null;
	}

	@RequestMapping("/tw/save/user/info.do")
	public String twitterCallback(@RequestParam("oauth_verifier") String oauthVerifier, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(twConsumerKey, twConsumerSecret);

		RequestToken requestToken = (RequestToken) request.getSession().getAttribute(SessionKey.TW_REQ_TOKEN.getKey());
		AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, oauthVerifier);
		twitter.setOAuthAccessToken(accessToken);
		request.getSession().removeAttribute(SessionKey.TW_REQ_TOKEN.getKey());

		twitter4j.User user = twitter.showUser(accessToken.getUserId());

		HttpSession session = request.getSession();
		session.setAttribute(SessionKey.USER_TYPE.getKey(), UserType.TWITTER.getCode());
		session.setAttribute(SessionKey.TW_USER_ID.getKey(), String.valueOf(accessToken.getUserId()));
		session.setAttribute(SessionKey.TW_USER_NAME.getKey(), accessToken.getScreenName());
		session.setAttribute(SessionKey.TW_PROFILE_IMG_URL.getKey(), user.getProfileImageURL());

		String backUrl = (String) session.getAttribute(SessionKey.TW_BACK_URL.getKey());
		session.removeAttribute(SessionKey.TW_BACK_URL.getKey());
		return "redirect:" + TUtil.covertXSS(backUrl);
	}

	@RequestMapping("/tw/logout.do")
	public String twitterLogout(@RequestParam String backUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionKey.USER_TYPE.getKey());
		session.removeAttribute(SessionKey.TW_USER_ID.getKey());
		session.removeAttribute(SessionKey.TW_USER_NAME.getKey());
		session.removeAttribute(SessionKey.TW_PROFILE_IMG_URL.getKey());

		return "redirect:" + TUtil.covertXSS(backUrl);
	}
}
