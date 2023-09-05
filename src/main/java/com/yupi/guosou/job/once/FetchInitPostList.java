package com.yupi.guosou.job.once;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yupi.guosou.esdao.PostEsDao;
import com.yupi.guosou.model.dto.post.PostEsDTO;
import com.yupi.guosou.model.entity.Post;
import com.yupi.guosou.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取初始帖子
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
// todo 取消注释开启任务,每次启动项目会执行一次
//@Component
@Slf4j
public class FetchInitPostList implements CommandLineRunner {

    @Resource
    private PostService postService;


    @Override
    public void run(String... args) {
        //获取数据
        String json = "{\"current\":1,\"pageSize\":8,\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"category\":\"文章\",\"reviewStatus\":1}";
        String url = "https://www.code-nav.cn/api/post/search/page/vo";
        String result = HttpRequest.post(url)
                .body(json)
                .execute().body();
        System.out.println(result);

        //json转对象
        Map<String,Object> map= JSONUtil.toBean(result,Map.class);
        JSONObject data =(JSONObject) map.get("data");
        JSONArray records = (JSONArray) data.get("records");
        List<Post> postList =new ArrayList<>();
        for (Object record : records) {
            JSONObject tempRecord =(JSONObject) record;
            Post post=new Post();
            post.setTitle(tempRecord.getStr("title"));
            post.setContent(tempRecord.getStr("content"));
            JSONArray tags=(JSONArray) tempRecord.get("tags");
            List<String> tagList = tags.toList(String.class);
            post.setTags(JSONUtil.toJsonStr(tagList));
            post.setUserId(1L);
            postList.add(post);


        }
        boolean b = postService.saveBatch(postList);
        if(b){
            log.info("初始化帖子列表成功,条数 = {}",postList.size());
        }else {
            log.error("初始化帖子列表失败");
        }

    }
}
