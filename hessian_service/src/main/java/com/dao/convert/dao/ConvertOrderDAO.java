package com.dao.convert.dao;



import java.util.List;
import java.util.Map;

import com.dao.base.BaseIbatisDAO;
import com.pojo.convert.ConvertOrder;
import org.springframework.stereotype.Repository;


@Repository("convertOrderDao")
public class ConvertOrderDAO extends BaseIbatisDAO {

    public ConvertOrderDAO() {
        super("CONVERT_ORDER");  //此处需跟实体xml中的namespace保持一致
    }

    public ConvertOrder insertSelective(ConvertOrder record){
    	return (ConvertOrder)super.insert("insertSelective", record);
    }

	public List<ConvertOrder> selectOrderByParams(Map<String,Object> params) {
    	return super.getList("selectListByParams", params);
    }


    public Integer updateByParams(Map<String,Object> param) {
    	return super.update("updateOrderStatus", param);
    }

    public ConvertOrder getOrderByOrderNum(ConvertOrder record){
        try {
          return  super.getOne("selectOrderbyOrderId",record);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Map> queryMap(){
		return super.getList("queryMap");
	}
}