package fr.maveilletechno.emailschecker.entities;

import java.util.HashSet;
import java.util.Set;

public class MailJetAccountStatus {

	private String status;
	private Integer total_cnt;
	
	private Set<MailjetEmailStatus> result = new HashSet<>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotal_cnt() {
		return total_cnt;
	}

	public void setTotal_cnt(Integer total_cnt) {
		this.total_cnt = total_cnt;
	}

	public Set<MailjetEmailStatus> getResult() {
		return result;
	}

	public void setResult(Set<MailjetEmailStatus> result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((total_cnt == null) ? 0 : total_cnt.hashCode());
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
		MailJetAccountStatus other = (MailJetAccountStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (total_cnt == null) {
			if (other.total_cnt != null)
				return false;
		} else if (!total_cnt.equals(other.total_cnt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailStatus [status=" + status + ", total_cnt=" + total_cnt + "]";
	}
	
}
