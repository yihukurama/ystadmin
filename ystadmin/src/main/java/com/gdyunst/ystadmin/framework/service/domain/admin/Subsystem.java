package com.gdyunst.ystadmin.framework.service.domain.admin;

import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.SubsystemEntity;

import java.util.List;

/**
 * @Description: 子系统业务类
 * @Author: dengshuai
 * @Date: Created in 17:03 2018/1/3
 * @Modified: by autor in 17:03 2018/1/3
 */
public class Subsystem extends SubsystemEntity{

    /**
     * @Description: 根据code查询子系统
     * @Author: dengshuai
     * @Date: Created in 17:10 2018/1/3
     * @Modified: by autor in 17:10 2018/1/3
     */
	public SubsystemEntity findByCode() {
        if (EmptyUtil.isEmpty(this.getCode())) {
            return null;
        }
        List<SubsystemEntity> subsystemEntityList = this.list();
        if (EmptyUtil.isEmpty(subsystemEntityList)) {
            return null;
        }
        return  subsystemEntityList.get(0);
    }
	
	
}
