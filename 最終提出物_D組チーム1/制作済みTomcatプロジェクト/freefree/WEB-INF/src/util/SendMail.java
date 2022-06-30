package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	/**
	 * 引数で指定されたメールアドレスにメールを送信するメソッド
	 *
	 * @param mailAddress 送信先のメールアドレス
	 * @param subject     メールの件名
	 * @param mainMessage メール本文
	 */
	public static void sendEmail(String mailAddress, String subject, String mainMessage) {
		try {
			Properties properties = System.getProperties();

			// SMTPサーバのアドレス設定 (xserver)
			properties.put("mail.smtp.host", "sv5215.xserver.jp");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// メールサーバログイン用のメールアドレスとパスワード設定
					return new PasswordAuthentication("system.project.team33@kanda-it-school-system.com", "g9mANICyNX");
				}
			});

			MimeMessage mimeMessage = new MimeMessage(session);

			// 送信元メールアドレスと送信者名設定
			mimeMessage.setFrom(
					new InternetAddress("system.project.team33@kanda-it-school-system.com", "フリフリ", "iso-2022-jp"));

			// 送信先メールアドレス設定
			mimeMessage.setRecipients(Message.RecipientType.TO, mailAddress);

			// メール件名設定
			mimeMessage.setSubject(subject, "iso-2022-jp");

			// メール本文設定
			mimeMessage.setText(mainMessage, "iso-2022-jp");

			// メール形式設定
			mimeMessage.setHeader("Content-Type", "text/plain; charset=iso-2022-jp");

			// 送信日付設定
			mimeMessage.setSentDate(new Date());

			// 送信
			Transport.send(mimeMessage);

			// メール送信成功
			System.out.println("送信に成功しました。");
		} catch (Exception e) {
			// メール送信失敗
			System.out.println("メールの送信に失敗しました。\n" + e);
		}
	}

}
