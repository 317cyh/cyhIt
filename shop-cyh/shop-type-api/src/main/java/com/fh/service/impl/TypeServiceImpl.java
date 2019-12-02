package com.fh.service.impl;

import com.fh.dao.TypeDao;
import com.fh.model.ShopInfo;
import com.fh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;


    @Override
    public List<Map<String, Object>>  queryTypeInfo() {
       List<ShopInfo>list= typeDao.queryTypeInfo();
        return getCategory(1,list);
    }

    private List<Map<String, Object>> getCategory(Integer pid, List<ShopInfo> categoryBeans) {
        List<Map<String, Object>> list = new ArrayList<>();
        categoryBeans.forEach(cate -> {
            Map<String, Object> map = null;
            if (pid.equals(cate.getPid())) {
                map = new HashMap<>();
                map.put("id", cate.getId());
                map.put("name", cate.getName());
                map.put("pid", cate.getPid());
                map.put("children", getCategory(cate.getId(), categoryBeans));
            }
            if (map != null) {
                list.add(map);
            }
        });
        return list;
    }
}
