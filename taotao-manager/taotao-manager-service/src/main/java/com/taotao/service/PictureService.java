package com.taotao.service;

import com.taotao.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017-8-21.
 */
public interface PictureService {
    PictureResult uploadPicture(MultipartFile uploadFile);
}
