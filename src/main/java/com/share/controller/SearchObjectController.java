package com.share.controller;


import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: QuincySu
 * @Date: 2018/4/23
 */
@Controller
@RequestMapping("/night")
public class SearchObjectController {
    private static Logger logger= Logger.getLogger(SearchObjectController.class);

    @RequestMapping(value = "/SearchES.from", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object searchES(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            String GoodsName=request.getParameter("Search");
            //es获取连接
            Settings settings= ImmutableSettings.settingsBuilder()
                    .put("cluster.name","elasticsearch").build();
            TransportClient client=new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("101.132.64.173",9300));
            //模糊查询
            SearchRequestBuilder responseBuilder = client.prepareSearch("share").setTypes("objects");
            SearchResponse res=responseBuilder.setQuery(QueryBuilders.wildcardQuery("GoodsName","*"+GoodsName.toLowerCase()+"*"))
                    .setFrom(0).setSize(10).setExplain(true).execute().actionGet();
            //查询结果
            SearchHits hits=res.getHits();
            String n="";
            if (hits.totalHits()>0){
                for (int i=0;i<hits.totalHits();i++){
                    n+= (hits.getAt(i).getSource().get("GoodsName").toString()+",");
                }
                return n;
            }else{
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
