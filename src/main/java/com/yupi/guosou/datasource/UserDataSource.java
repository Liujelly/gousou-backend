package com.yupi.guosou.datasource;

import static com.yupi.guosou.constant.UserConstant.USER_LOGIN_STATE;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.guosou.common.ErrorCode;
import com.yupi.guosou.constant.CommonConstant;
import com.yupi.guosou.exception.BusinessException;
import com.yupi.guosou.mapper.UserMapper;
import com.yupi.guosou.model.dto.user.UserQueryRequest;
import com.yupi.guosou.model.entity.User;
import com.yupi.guosou.model.enums.UserRoleEnum;
import com.yupi.guosou.model.vo.LoginUserVO;
import com.yupi.guosou.model.vo.UserVO;
import com.yupi.guosou.service.UserService;
import com.yupi.guosou.utils.SqlUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务实现
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest =new UserQueryRequest();
        userQueryRequest.setPageSize(pageSize);
        userQueryRequest.setCurrent(pageNum);
        userQueryRequest.setUserName(searchText);

        Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);

        return userVOPage;
    }
}
