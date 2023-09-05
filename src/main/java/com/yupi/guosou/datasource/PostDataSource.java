package com.yupi.guosou.datasource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.yupi.guosou.common.ErrorCode;
import com.yupi.guosou.constant.CommonConstant;
import com.yupi.guosou.exception.BusinessException;
import com.yupi.guosou.exception.ThrowUtils;
import com.yupi.guosou.mapper.PostFavourMapper;
import com.yupi.guosou.mapper.PostMapper;
import com.yupi.guosou.mapper.PostThumbMapper;
import com.yupi.guosou.model.dto.post.PostEsDTO;
import com.yupi.guosou.model.dto.post.PostQueryRequest;
import com.yupi.guosou.model.entity.Post;
import com.yupi.guosou.model.entity.PostFavour;
import com.yupi.guosou.model.entity.PostThumb;
import com.yupi.guosou.model.entity.User;
import com.yupi.guosou.model.vo.PostVO;
import com.yupi.guosou.model.vo.UserVO;
import com.yupi.guosou.service.PostService;
import com.yupi.guosou.service.UserService;
import com.yupi.guosou.utils.SqlUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 帖子服务实现
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setPageSize(pageSize);
        postQueryRequest.setCurrent(pageNum);
        postQueryRequest.setSearchText(searchText);

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return postService.getPostVOPage(postPage,request);
    }

}




