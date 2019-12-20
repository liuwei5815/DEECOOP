package ${classPath};

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：${TITLE} 实体类
 * @author ${author} 自动生成
 * @date ${nowDate?string("yyyy-MM-dd")}
 */
@TableName("${table}")
public class ${objectName} implements Serializable {
	private static final long serialVersionUID = 1L;

<#list fieldList as var>
<#if var[4] == 'Integer'>
	//${var[3]}
	@TableField("${var[0]}")
	private Integer ${var[1]};
<#elseif var[4] == 'BigDecimal'>
	//${var[3]}
	@TableField("${var[0]}")
	private BigDecimal ${var[1]};
<#elseif var[4] == 'Date'>
	//${var[3]}
	@TableField("${var[0]}")
	private Date ${var[1]};
<#else>
	//${var[3]}
	@TableField("${var[0]}")
	private String ${var[1]};
</#if>
</#list>



<#list fieldList as var>
	<#if var[4] == 'Integer'>
	public void set${var[2]}(Integer ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	public Integer get${var[2]}() {
		return ${var[1]};
	}
	<#elseif var[4] == 'BigDecimal'>
	public void set${var[2]}(BigDecimal ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	public BigDecimal get${var[2]}() {
		return ${var[1]};
	}
	<#elseif var[4] == 'Date'>
	public void set${var[2]}(Date ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	public Date get${var[2]}() {
		return ${var[1]};
	}
	<#else>
	public void set${var[2]}(String ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	public String get${var[2]}() {
		return ${var[1]};
	}
	</#if>
</#list>

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
