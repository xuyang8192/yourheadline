package com.yourheadline.ajaxapi;

import com.yourheadline.dao.ArticleDAO;
import com.yourheadline.dao.ArticleInfoDAO;
import com.yourheadline.dao.HistoryDAO;
import com.yourheadline.entity.ArticleEntity;
import com.yourheadline.entity.ViewedEntity;
import com.yourheadline.model.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServletArticleHome {
    //    @Autowired
//    ArticleDAO articleDAO;
    @Autowired
    ArticleInfoDAO articleInfoDAO;
    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    HistoryDAO historyDAO;

    @GetMapping("/article/home")
    @ResponseBody
    public Map<String, Object> getData() {

        Map<String, Object> map = new HashMap<String, Object>();

        List<ArticleInfo> aiList = articleInfoDAO.findAll();

//        老的用法，用@OneToMany和@ManyToOne的方法，注释掉不要了
//        List<ArticleEntity> aList = articleDAO.findAll();
//        List<ArticleInfo> aiList = new ArrayList<ArticleInfo>();
//
//        for (ArticleEntity a: aList){
//            ArticleInfo ai = new ArticleInfo();
//            ai.articleId = a.getArticleId();
//            ai.authorId = a.getAuthorId();
//            ai.editorId = a.getEditorId();
//            ai.moduleId = a.getModuleId();
//            ai.articleTitle = a.getArticleTitle();
//            ai.articleText = a.getArticleText();
//            ai.addTime = a.getAddTime();
//            ai.likeNum = a.getLikeNum();
//
//            ai.authorName = a.getAuthor().getAuthorName();
//            ai.editorName = "sb";
//
//            aiList.add(ai);
//        }
        map.put("article_list", aiList);

        return map;
    }

    @GetMapping("/article/module")
    @ResponseBody
    public Map<String, Object> findModuleid(@RequestParam int id) {

        Map<String, Object> map = new HashMap<String, Object>();

        List<ArticleInfo> mlist = articleInfoDAO.findArticleInfoByModuleId(id);

        map.put("article_list", mlist);

        return map;
    }

    @GetMapping("/article/collect")
    @ResponseBody
    public Map<String, Object> findArticleid(@RequestParam int id) {

        Map<String, Object> map = new HashMap<String, Object>();

        List<ArticleInfo> article = articleInfoDAO.selectCollectionByUserId(id);
        map.put("article_list", article);

        return map;
    }

    @GetMapping("/article/detail")
    @ResponseBody
    public  Map<String, Object> findArticle(@RequestParam int id){

        Map<String, Object> map = new HashMap<String, Object>();

        List<ArticleEntity> aList = articleDAO.findByArticleId(id);
        if (aList.isEmpty()){
            return null;
        }
        else {
            map.put("articleEntity", aList.get(0));
            return map;
        }
    }

    @PostMapping("/article/authorpage")
    @ResponseBody
    public  Map<String, Object> findarticlebyauthor(@RequestBody Map<String, String> inMap){

        Map<String, Object> map = new HashMap<String, Object>();

        if (inMap.containsKey("authorId")) {
            int authorId = Integer.parseInt(inMap.get("authorId"));

            List<ArticleEntity> aList = new ArrayList<>();
            aList=articleDAO.findByAuthorId(authorId);
            if (aList.isEmpty()){
                return null;
            }
            else {
                map.put("article_list", aList);
                return map;
            }
        }
        return  null;
    }

    @PostMapping("/article/addView")
    @ResponseBody
    public Map<String, Object> addView(@RequestBody Map<String, String> inMap){
        Date addDate = new Date(Calendar.getInstance().getTimeInMillis());

        String status = "";
        Map<String, Object> map = new HashMap<>();

        if(inMap.containsKey("articleId") && inMap.containsKey("userId")){
            ViewedEntity v = new ViewedEntity();
            v.setAddTime(addDate);
            v.setArticleId(Integer.parseInt(inMap.get("articleId")));
            v.setUserId(Integer.parseInt(inMap.get("userId")));
            v = historyDAO.save(v);

            if (v!=null) {
                status = "Succeed";
            }
            else{
                status = "DatabaseInnerError";
            }
        }

        map.put("addStatus", status);
        return map;
    }
}
