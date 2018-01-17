package com.gdyunst.ystadmin.framework.service.domain.admin;

import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.application.utils.EncrUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.FuncEntity;
/**
 * @Description: 功能业务类
 * @Author: dengshuai
 * @Date: Created in 17:54 2018/1/3
 * @Modified: by autor in 17:54 2018/1/3
 */
public class Func extends FuncEntity {


    @Override
    public FuncEntity create() throws TipsException {
        if(EmptyUtil.isEmpty(this.getCode())){//若无功能编码则自动生成
            String funcCode = EncrUtil.gen16BitMD5String();
            this.setCode(funcCode);
        }
        return super.create();
    }
}
