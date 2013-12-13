package fr.maveilletechno.emailschecker.entities;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailJetEmailSentListStatus {

	private String status;
	private Integer cnt;
	
	private Set<MailjetEmailStatus> emails = new HashSet<>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<MailjetEmailStatus> getEmails() {
		return emails;
	}

	public void setEmails(Set<MailjetEmailStatus> emails) {
		this.emails = emails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((cnt == null) ? 0 : cnt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailJetEmailSentListStatus other = (MailJetEmailSentListStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (cnt == null) {
			if (other.cnt != null)
				return false;
		} else if (!cnt.equals(other.cnt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailStatus [status=" + status + ", cnt=" + cnt + "]";
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
}
