package com.taotao.service.impl;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamItemService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-8-24.
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{

    @Autowired
    TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public String getItemParamItem(Long itemId) {
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria=example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> jsonList=tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (jsonList==null||jsonList.size()==0) return "";
        TbItemParamItem itemParamItem=jsonList.get(0);
        String paramData=itemParamItem.getParamData();
        //创建html页面，填充规格参数
        StringBuilder sb=new StringBuilder();
        List<Map> list=JsonUtils.jsonToList(paramData,Map.class);
        sb.append("<div display=\"block\"  class=\"Ptable-item\">\n" );
        for (Map m1:list) {
            sb.append("    <h3>"+m1.get("group")+"</h3>\n");
            List<Map> m2= (List<Map>) m1.get("params");
            for (Map mm:m2) {
                sb.append("       <dl>\n");
                sb.append("          <dt>" + mm.get("k") + "</dt>      <dd>" + mm.get("v") + "</dd> \n");
                sb.append("		  </dl>\n");
            }
        }
        sb.append("</div>");
        return sb.toString();
    }

    @Override
    public TaotaoResult getItemParamItem_(Long itemId) {
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria=example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItem=tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        return TaotaoResult.ok(tbItemParamItem.get(0));
    }
}
