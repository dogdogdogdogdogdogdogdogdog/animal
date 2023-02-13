package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.ForumDao;
import com.lovepet.animal.dto.ForumData;
import com.lovepet.animal.dto.UserForumQueryParams;
import com.lovepet.animal.model.UserForum;
import com.lovepet.animal.rowmapper.UserForumRowmapper;
import com.lovepet.animal.util.DiscussPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ForumDaoImpl implements ForumDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createForum(ForumData forumData) {
        for(int i=0;i<1000;i++) {
            String sql = "INSERT INTO user_forum (user_id,title,description,create_date) VALUES (:userId,:title,:description,:createDate)";
            Map<String, Object> map = new HashMap<>();
            map.put("userId", forumData.getUserId());
            map.put("title", forumData.getTitle());
            map.put("description", forumData.getContent());
            map.put("createDate", new Date());
            namedParameterJdbcTemplate.update(sql, map);
        }
    }

    @Override
    public DiscussPage<UserForum> getForums(UserForumQueryParams userForumQueryParams) {
        String sql="select title,description,t_id from user_forum ";
        Map<String,Object> map=new HashMap<>();

        sql+="order by"+" "+userForumQueryParams.getOrderBy()+" "+userForumQueryParams.getSort()+" limit :limit offset :offset";
        map.put("limit",userForumQueryParams.getLimit());
        map.put("offset",(userForumQueryParams.getPage()-1)*userForumQueryParams.getLimit());

        List<UserForum>  userForumList=       namedParameterJdbcTemplate.query(sql,map,new UserForumRowmapper());

       List<String> pageList= showPage(userForumQueryParams.getPage(),getPages(userForumQueryParams));

DiscussPage<UserForum> discussPage=new DiscussPage<>();
discussPage.setPersonalForums(userForumList);
discussPage.setPage(pageList);
discussPage.setTotal(getPages(userForumQueryParams));
return discussPage;
    }


    private Integer getPages(UserForumQueryParams userForumQueryParams) {
        String sql = "SELECT count(*) FROM user_forum WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        Integer pages = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        if (pages % userForumQueryParams.getLimit() != 0) {
            return (pages / userForumQueryParams.getLimit()) + 1;
        }

        return (pages / userForumQueryParams.getLimit());


    }
    private  List<String> showPage(int curPage, int pages){
        ArrayList<String> ls=new ArrayList<>();
        for(int i=1;i<pages+1;i++){
            ls.add(String.valueOf(i));
        }
        int p1=0;
        int p2=6;

        if(pages>6){
            if(curPage>1){
                p1=curPage-1;
                p2=p2+p1;
                int subLine=pages/2;
                if(p1>subLine) {
                    if (p2 > pages - 1) {
                        p2 = pages;
                        if(p2-p1<6){
                            int gap=p2-p1;
                            p1=p1-(6-gap);
                        }
                    }

                }else {
                    if (p1 <=0) {
                        p1 = 0;
                    }
                }
            }

        }else{
            if(pages>0){
                p2=pages;
            }
        }




        return  ls.subList(p1,p2);
    }
}
