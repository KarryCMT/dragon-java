package com.dragon.client;

import com.dragon.req.oss.OssReq;
import com.dragon.vo.oss.SdFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author liaoshen
 */
@Component
@FeignClient(name = "rabbit-oss",path = "",url = "http://localhost:8002/api/v1/oss")
public interface OssFeignClient {

    /**
     * 查询多个文件
     * @param req
     * @return
     */
    @PostMapping("/file/list/by/ids")
    List<SdFile> getFileListByIds(OssReq req);
}
