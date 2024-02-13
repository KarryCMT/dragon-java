package com.coco.dragon.client;

import com.coco.dragon.req.oss.OssReq;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.resp.oss.SdFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
/**
 * @author liaoshen
 */
@Component
@FeignClient(name = "oss-service",path = "",url = "http://localhost:8004/api/v1/oss")
public interface OssFeignClient {

    /**
     * 查询多个文件
     * @param req
     * @return
     */
    @PostMapping("/file/list/by/ids")
    List<SdFile> getFileListByIds(OssReq req);
}
