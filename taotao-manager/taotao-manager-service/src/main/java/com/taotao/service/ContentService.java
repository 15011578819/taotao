package com.taotao.service;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * Created by Administrator on 2017-9-27.
 */
public interface ContentService {
    EUDataGridResult getContentList(long categoryId,int page,int rows);

    TaotaoResult insertContent(TbContent tbContent);

    TaotaoResult deleteContent(Long[] ids);

    TaotaoResult updateContent(TbContent content);
}
