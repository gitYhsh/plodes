package com.xlcxx.config.quartz.domain;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_job_log")
public class JobLog implements Serializable {

	private static final long serialVersionUID = -7114915445674333148L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "LOG_ID")
	private Long logId;

	@Column(name = "JOB_ID")
	private Long jobId;

	@Column(name = "BEAN_NAME")
	private String beanName;

	@Column(name = "METHOD_NAME")
	private String methodName;

	@Column(name = "PARAMS")
	private String params;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "ERROR")
	private String error;

	@Column(name = "TIMES")
	private Long times;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * @return LOG_ID
	 */
	public Long getLogId() {
		return logId;
	}

	/**
	 * @param logId
	 */
	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**
	 * @return JOB_ID
	 */
	public Long getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return BEAN_NAME
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName == null ? null : beanName.trim();
	}

	/**
	 * @return METHOD_NAME
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

	/**
	 * @return PARAMS
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params
	 */
	public void setParams(String params) {
		this.params = params == null ? null : params.trim();
	}

	/**
	 * @return STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * @return ERROR
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 */
	public void setError(String error) {
		this.error = error == null ? null : error.trim();
	}

	/**
	 * @return TIMES
	 */
	public Long getTimes() {
		return times;
	}

	/**
	 * @param times
	 */
	public void setTimes(Long times) {
		this.times = times;
	}

	/**
	 * @return CREATE_TIME
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}