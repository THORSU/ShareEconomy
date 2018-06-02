package com.share.controller;


import com.share.pojo.Object_1;
import com.share.service.ObjectService;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: QuincySu
 * @Date: 2018/4/23
 */
@Controller
@RequestMapping("/night")
public class SearchObjectController {
    private static Logger logger= Logger.getLogger(SearchObjectController.class);
    @Autowired
    private ObjectService objectService;

    @RequestMapping(value = "/SearchES.from", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object searchES(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            String GoodsName = new String(request.getParameter("Search").getBytes("iso-8859-1"), "utf-8");
            //es获取连接
            Settings settings= ImmutableSettings.settingsBuilder()
                    .put("cluster.name","elasticsearch").build();
            TransportClient client=new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("101.132.64.173",9300));
            //模糊查询
            SearchRequestBuilder responseBuilder = client.prepareSearch("share").setTypes("objects");
            SearchResponse res = responseBuilder.setQuery(QueryBuilders.wildcardQuery("objectName", "*" + GoodsName.toLowerCase() + "*"))
                    .setFrom(0).setSize(10).setExplain(true).execute().actionGet();
            //查询结果
            SearchHits hits=res.getHits();
            String n="";
            if (hits.totalHits()>0){
                for (int i=0;i<hits.totalHits();i++){
                    String objectName = hits.getAt(i).getSource().get("objectName").toString();
                    Object_1 object_1 = objectService.getObjectStatusByName(objectName);
                    String objectId = "";
                    if (!Objects.isNull(object_1)) {
                        objectId = object_1.getId();
                    }
                    n += (objectName + "&" + objectId + ",");
                }
                System.out.println(n);
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
