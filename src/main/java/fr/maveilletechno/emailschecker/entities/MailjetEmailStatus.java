package fr.maveilletechno.emailschecker.entities;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * https://fr.mailjet.com/docs/api/report/emailsent
 * 
 * @author fab_boulot
 *
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailjetEmailStatus {
	
	private Integer id;
	private String subject;
	private Long sendtime_start;
	private Long sendtime_end;
	private String from_email;
	private String from_name;
	private String status;
	private String cnt_recipients;
	private String email_id;
	private String from_id;
	private String to_email;
	private Integer to_id;
	
	public enum statusValues {
		queued, sent, opened, clicked, bounce, blocked, spam, unsub;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Long getSendtime_start() {
		return sendtime_start;
	}
	public void setSendtime_start(Long sendtime_start) {
		this.sendtime_start = sendtime_start;
	}
	public Long getSendtime_end() {
		return sendtime_end;
	}
	public void setSendtime_end(Long sendtime_end) {
		this.sendtime_end = sendtime_end;
	}
	public String getFrom_email() {
		return from_email;
	}
	public void setFrom_email(String from_email) {
		this.from_email = from_email;
	}
	public String getFrom_name() {
		return from_name;
	}
	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCnt_recipients() {
		return cnt_recipients;
	}
	public void setCnt_recipients(String cnt_recipients) {
		this.cnt_recipients = cnt_recipients;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getFrom_id() {
		return from_id;
	}
	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}
	public String getTo_email() {
		return to_email;
	}
	public void setTo_email(String to_email) {
		this.to_email = to_email;
	}
	public Integer getTo_id() {
		return to_id;
	}
	public void setTo_id(Integer to_id) {
		this.to_id = to_id;
	}
	@Override
	public String toString() {
		return "MailjetEmailStatus [id=" + id + ", subject=" + subject
				+ ", status=" + status + ", to_email=" + to_email + "]";
	}

}
