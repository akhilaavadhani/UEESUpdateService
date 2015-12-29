package com.sakha.services.esupdater;

import com.sakha.services.util.ElasticSearchUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by welcome on 27/12/15.
 */
@Component("pppESUpdater")
public class PPPESUpdater {

        @Autowired
        private ElasticSearchUtil elasticsearch;
        @Value("${PPP_DB}")
        private String elasticSearchIndex;
        @Value("${PPP_DATA_COLL}")
        private String elasticSearchType;

        public void updateES(Exchange content) {

                Map<String, Object> record = new HashMap<String, Object>();
                try {
                        record = (Map<String, Object>)content.getIn().getBody();
                        String _id = record.get("_id").toString();
                        Date date = new Date((Long)record.get("increment_date"));

                        record.put("increment_date", date);

                        //Update ElasticSearch
                        elasticsearch.updateRequest(elasticSearchIndex,elasticSearchType, record, _id);

                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

}
