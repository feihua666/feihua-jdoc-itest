package dto;

import java.io.Serializable;

/**
 * 实体类的基类
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8683452581622892189L;
    /**
     * 系统初始化默认用户id
     */
    public static final Integer SYSTEM_USER_ID = 1;
    /**
     * 删除标记（0：正常；1：删除；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    /**
     * 主键id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
}
