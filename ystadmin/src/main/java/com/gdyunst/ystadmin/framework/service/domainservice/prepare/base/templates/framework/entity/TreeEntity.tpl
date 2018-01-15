package ${package};

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.${project}.framework.domain.repository.TreeCrud;

/**
 * 功能描述:${entDesc}
 */
@Table(name="${tableName}")
public class ${entityName} extends TreeCrud<${entityName}>
{
	${entColumns}
	
	//get  set 方法
	${entGetSet}
	

	${toString}
	
}
