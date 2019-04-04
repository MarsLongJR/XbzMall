package com.macro.mall.dao.product;

import com.macro.mall.model.XbzProductVertifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 商品审核日志自定义dao
 * @Description 商品审核日志
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductVertifyRecordDao {
    int insertList(@Param("list") List<XbzProductVertifyRecord> list);
}
