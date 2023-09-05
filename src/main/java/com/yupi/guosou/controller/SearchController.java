package com.yupi.guosou.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.guosou.common.BaseResponse;
import com.yupi.guosou.common.ErrorCode;
import com.yupi.guosou.common.ResultUtils;
import com.yupi.guosou.exception.BusinessException;
import com.yupi.guosou.exception.ThrowUtils;
import com.yupi.guosou.manager.SearchFacade;
import com.yupi.guosou.model.dto.picture.PictureQueryRequest;
import com.yupi.guosou.model.dto.post.PostQueryRequest;
import com.yupi.guosou.model.dto.search.SearchRequest;
import com.yupi.guosou.model.dto.user.UserQueryRequest;
import com.yupi.guosou.model.entity.Picture;
import com.yupi.guosou.model.entity.User;
import com.yupi.guosou.model.enums.SearchTypeEnum;
import com.yupi.guosou.model.vo.PostVO;
import com.yupi.guosou.model.vo.SearchVO;
import com.yupi.guosou.model.vo.UserVO;
import com.yupi.guosou.service.PictureService;
import com.yupi.guosou.service.PostService;
import com.yupi.guosou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 帖子接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private PictureService pictureService;

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> SearchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.SearchAll(searchRequest,request));

    }
}
