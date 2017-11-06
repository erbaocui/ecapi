package com.cn.service.impl;

import com.cn.constant.EsIndex;
import com.cn.es.ESTransportClient;
import com.cn.service.IEsService;
import com.cn.util.DateUtil;
import com.cn.util.IdGenerator;
import com.cn.vo.ActionLog;
import com.cn.vo.ActionLogEx;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.*;

/**
 * Created by home on 2017/7/7.
 */
@Service("esService")
public class EsService implements IEsService {

    @Autowired
    private ESTransportClient esTransportClient;

    @Override
    public void  createActionLog(ActionLog log){

        try {

            //TransportClient esClient = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.95.1.213"), 9300));

            //TransportClient esClient = new PreBuiltTransportClient(settings ).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.95.1.213"), 9300));
            Map<String, Object> json = new HashMap<String,Object>();
            json.put("method", log.getMethod());
            json.put("loginName",log.getLoginName());
            json.put("module",log.getModule());
            json.put("actionTime", log.getActionTime());
            json.put("actionUrl", log.getActionUrl());
            json.put("description",log.getDescription());
            json.put("requestParam",log.getRequestParam());
            json.put("token", log.getToken());
            json.put("loginIp", log.getLoginIp());
            json.put("loginId", log.getLoginId());
            json.put("token",log.getToken());
            json.put("loginIp", log.getLoginIp());
            json.put("responseParam", log.getResponseParam());
            json.put("status",log.getStatus());
            json.put("executeTime", log.getExecuteTime());
            json.put("errorStack", log.getErrorStack());

            IndexResponse response = esTransportClient.getObject().prepareIndex(EsIndex.INDEX_ACTION_LOG, EsIndex.TYPE_ACTION_LOG ,IdGenerator.getId()).setSource(json).execute().actionGet();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public Map  getActionLogPageByEntity(ActionLogEx queryLog, Integer pageNo, Integer pageSize) throws Exception{
       //Settings esSettings = Settings.builder().put("cluster.name", "my-application").build();
       // TransportClient client=new PreBuiltTransportClient(esSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.95.1.213"), 9300));;
        //SearchResponse response =esTransportClient.getObject().prepareSearch(EsIndex.INDEX_ACTION_LOG)
        TimeZone srcTimeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone destTimeZone = TimeZone.getTimeZone("GMT");

       // SearchRequestBuilder searchRequestBuilder =client.prepareSearch(EsIndex.INDEX_ACTION_LOG)
        SearchRequestBuilder searchRequestBuilder=esTransportClient.getObject().prepareSearch(EsIndex.INDEX_ACTION_LOG)
                .setTypes(EsIndex.TYPE_ACTION_LOG);

       /* if(queryLog.getBeginTime()!=null&&queryLog.getEndTime()!=null){

            RangeQueryBuilder rangequerybuilder = QueryBuilders
                    .rangeQuery("actionTime")
                    .from(DateUtil.convert2String(DateUtil.dateTransformBetweenTimeZone(queryLog.getBeginTime(), srcTimeZone, destTimeZone), "yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'")).
                            to(DateUtil.convert2String(DateUtil.dateTransformBetweenTimeZone(queryLog.getEndTime(), srcTimeZone, destTimeZone),"yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'"));

            searchRequestBuilder=searchRequestBuilder.setQuery(rangequerybuilder);
        }*/

        if(queryLog.getBeginTime()!=null){

            searchRequestBuilder=searchRequestBuilder.setQuery(QueryBuilders
                    .rangeQuery("actionTime").gte(DateUtil.convert2String(DateUtil.dateTransformBetweenTimeZone(queryLog.getBeginTime(), srcTimeZone, destTimeZone), "yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'")));
        }
        if(queryLog.getEndTime()!=null){

            searchRequestBuilder=searchRequestBuilder.setQuery(QueryBuilders
                    .rangeQuery("actionTime").lte(DateUtil.convert2String(DateUtil.dateTransformBetweenTimeZone(queryLog.getBeginTime(), srcTimeZone, destTimeZone), "yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'")));
        }
       // String token="9738b530d91049bea1bb00b222ae0118";
        if(queryLog.getToken()!=null&&(!queryLog.getToken().equals(""))){

            searchRequestBuilder=searchRequestBuilder.setQuery(QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("token", queryLog.getToken())));

        }
        if(queryLog.getLoginId()!=null&&(!queryLog.getLoginId().equals(""))){

            searchRequestBuilder=searchRequestBuilder.setQuery(QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("loginId", queryLog.getLoginId())));

        }
        if(queryLog.getStatus()!=null&&(!queryLog.getStatus().equals(-1))){
            searchRequestBuilder=searchRequestBuilder.setQuery(QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("status", queryLog.getStatus())));

        }

        SearchResponse response =searchRequestBuilder
                        //.setQuery(QueryBuilders.matchQuery("position", "technique"))
                 //       .setPostFilter(QueryBuilders.rangeQuery("actionTime").from("2017-11-04 23:55:20"(").to(40)) ")

                .setFrom(pageNo*pageSize).setSize(pageSize)
                .addSort("actionTime", SortOrder.DESC)
                .get();

        SearchHit[] searchHits = response.getHits().getHits();
        Long total = response.getHits().getTotalHits();
        List<ActionLog> list=new ArrayList<ActionLog>();
       /* long i=0;
        if(total<(pageNo+1)* pageSize){
            i=total%pageSize;
        }else{
            i=
        }*/

        for(SearchHit hit:searchHits){
            Map document= hit.getSource();
            ActionLog log=new ActionLog();
            log.setMethod((String) document.get("method"));
            log.setLoginName((String) document.get("loginName"));
            log.setModule((String) document.get("module"));
            System.out.println((String)document.get("actionTime"));
            log.setActionTime( DateUtil.setTimeZone(DateUtil.convert2Date((String)document.get("actionTime"),"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
            srcTimeZone = TimeZone.getTimeZone("GMT");
            destTimeZone = TimeZone.getTimeZone("GMT+8");
            log.setActionTime(DateUtil.dateTransformBetweenTimeZone(log.getActionTime(),srcTimeZone ,destTimeZone));
            log.setActionUrl((String) document.get("actionUrl"));
            log.setDescription((String) document.get("description"));
            log.setRequestParam((String) document.get("requestParam"));
            log.setToken((String)document.get("token"));
            log.setLoginIp((String) document.get("loginIp"));
            log.setLoginId((String) document.get("loginId"));
            log.setToken((String)document.get("token"));
            log.setResponseParam((String) document.get("responseParam"));
            log.setId((String) document.get("id"));
            log.setStatus((Integer) document.get("status"));
            log.setExecuteTime((String) document.get("executeTime"));
            log.setErrorStack((String) document.get("errorStack"));
            list.add(log);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("list",list);
        result.put("total",total);
        return result;
    }

    public ESTransportClient getEsTransportClient() {
        return esTransportClient;
    }

    public void setEsTransportClient(ESTransportClient esTransportClient) {
        this.esTransportClient = esTransportClient;
    }

    public static void main(String[] args) throws Exception {
        ActionLogEx actionLog=new ActionLogEx();

     new EsService().getActionLogPageByEntity(actionLog, new Integer(0), new Integer(10));

    }
}
