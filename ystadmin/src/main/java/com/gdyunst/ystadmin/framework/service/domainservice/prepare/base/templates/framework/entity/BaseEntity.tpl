package ${package};

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.${project}.framework.domain.repository.BaseCrud;

/**
 * 功能描述:${entDesc}
 */
@Table(name="${tableName}")
public class ${entityName} extends BaseCrud<${entityName}>
{
	${entColumns}
	
	//get  set 方法
	${entGetSet}
	

	${toString}
	
}
