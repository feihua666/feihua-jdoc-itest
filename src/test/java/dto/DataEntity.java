package dto;

import java.util.Date;

/**
 * 数据Entity类
 */
public abstract class DataEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 备注
	 */
	protected String remarks;
	/**
	 * 创建者Id
	 */
	protected Integer createBy;
	/**
	 * 创建时间
	 */
	protected Date createAt;
	/**
	 * 更新者Id
	 */
	protected Integer updateBy;
	/**
	 * 更新时间
	 */
	protected Date updateAt;
	/**
	 * 删除标记（0：正常；1：删除；）
	 */
	protected String delFlag = DEL_FLAG_NORMAL;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
