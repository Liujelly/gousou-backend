package com.yupi.guosou.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yupi.guosou.annotation.AuthCheck;
import com.yupi.guosou.common.BaseResponse;
import com.yupi.guosou.common.DeleteRequest;
import com.yupi.guosou.common.ErrorCode;
import com.yupi.guosou.common.ResultUtils;
import com.yupi.guosou.constant.UserConstant;
import com.yupi.guosou.exception.BusinessException;
import com.yupi.guosou.exception.ThrowUtils;
import com.yupi.guosou.model.dto.picture.PictureQueryRequest;
import com.yupi.guosou.model.dto.post.PostAddRequest;
import com.yupi.guosou.model.dto.post.PostEditRequest;
import com.yupi.guosou.model.dto.post.PostQueryRequest;
import com.yupi.guosou.model.dto.post.PostUpdateRequest;
import com.yupi.guosou.model.entity.Picture;
import com.yupi.guosou.model.entity.Post;
import com.yupi.guosou.model.entity.User;
import com.yupi.guosou.model.vo.PostVO;
import com.yupi.guosou.service.PictureService;
import com.yupi.guosou.service.PostService;
import com.yupi.guosou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帖子接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;


    // region 增删改查


    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                        HttpServletRequest request) {
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        String searchText = pictureQueryRequest.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);

        return ResultUtils.success(picturePage);
    }

}
