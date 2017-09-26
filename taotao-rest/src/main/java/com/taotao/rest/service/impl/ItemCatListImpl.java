package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.pojo.ItemCat;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-9-6.
 */
@Service
public class ItemCatListImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper tbItemCatMapper;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return catResult;
    }

    /**
     * 查询分类列表
     *
     * @param parentId
     * @return
     */
    private List<?> getCatList(long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List dataList = new ArrayList();
        int count=0;
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                ItemCat itemCat = new ItemCat();
                if (parentId==0) {
                    itemCat.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                }else{
                    itemCat.setName(tbItemCat.getName());
                }
                itemCat.setUrl("/products/"+tbItemCat.getId()+".html");
                //递归调用
                itemCat.setItem(getCatList(tbItemCat.getId()));
                //添加到列表
                dataList.add(itemCat);
                count++;
                if (parentId==0&&count>=14){
                    break;
                }
            } else {
                String catItem = "/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
                dataList.add(catItem);
            }

        }
        return dataList;
    }
}
