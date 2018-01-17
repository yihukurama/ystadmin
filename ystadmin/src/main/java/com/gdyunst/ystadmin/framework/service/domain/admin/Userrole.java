package com.gdyunst.ystadmin.framework.service.domain.admin;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.application.utils.LogUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.RoleEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserroleEntity;

import java.util.List;

/**
 * @Description: 用户角色关联业务类
 * @Author: dengshuai
 * @Date: Created in 17:57 2018/1/3
 * @Modified: by autor in 17:57 2018/1/3
 */
public class Userrole extends UserroleEntity {


    @Override
    public int creates(List<Object> list, Class<?> clazz) throws TipsException {

        if(!EmptyUtil.isEmpty(list)){
            //绑定角色时把原有角色删除并更新员工表上的角色字段
            LogUtil.DebugLog(this,"绑定角色时把原有角色删除并更新员工表上的角色字段");
            String json = JSON.toJSONString(list.get(0));
            UserroleEntity userroleEntity = JSON.parseObject(json,UserroleEntity.class);
            UserroleEntity removeUserRole = new UserroleEntity();
            removeUserRole.setUserId(userroleEntity.getUserId());
            removeUserRole.remove();

            String roleNames = "";
            for (int i = 0; i < list.size(); i++) {
                String createjson = JSON.toJSONString(list.get(i));
                UserroleEntity createUserrole = JSON.parseObject(createjson,UserroleEntity.class);
                RoleEntity roleEntity = new Role();
                roleEntity.setId(createUserrole.getRoleId());
                roleEntity.load();
                roleNames += roleEntity.getText()+";";
            }
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userroleEntity.getUserId());
            userEntity.load();
            userEntity.setRolelist(roleNames);
            userEntity.update();
        }



        return super.creates(list,clazz);
    }
}
