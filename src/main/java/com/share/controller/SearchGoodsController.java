package com.share.controller;

import com.share.pojo.Object_1;
import com.share.service.ObjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by su on 17-8-24.
 */
@Controller
@RequestMapping("/night")
public class SearchGoodsController {
    private static Logger log = Logger.getLogger(SearchGoodsController.class);
    @Autowired
    private ObjectService ObjectService;
    @RequestMapping(value="/Search.from", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object GetGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String GoodsName=request.getParameter("Search");//获取前段的搜索内容
//        System.out.println(GoodsName);
//        Goods goods =new Goods();

        Object_1[] res= ObjectService.searchGoodsName(GoodsName);//用数组存搜索出来的结果

//        JSONArray jsonArray = new JSONArray();
//        System.out.println(res.length);

        if(res.length!=0){
            String temp="";
        for (int i=0;i<res.length;i++){
            temp+=(res[i].getObject()+",");//将搜索的结果的用逗号分开，加入到temp中
        }
            return temp;//将搜索结果发给前段
        }

        else {
            return "0";
        }
    }
}
