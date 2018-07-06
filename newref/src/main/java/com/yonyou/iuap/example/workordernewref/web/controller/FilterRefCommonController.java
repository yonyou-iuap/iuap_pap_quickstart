package com.yonyou.iuap.example.workordernewref.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.iuap.example.workordernewref.entity.RefParamVO;
import com.yonyou.iuap.example.workordernewref.service.NewRefCommonService;
import com.yonyou.iuap.example.workordernewref.utils.SimpleParseXML;
import com.yonyou.iuap.example.workordernewref.utils.ValueConvertor;

@Controller
@RequestMapping(value = "/common")
public class FilterRefCommonController {

	@Autowired
	private NewRefCommonService service;
	
	@RequestMapping(value = "/filterRef",method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,String>> filterRef(@RequestBody List<Map<String,String>> list) {
		String refCode = "";
		List<String> ids = new ArrayList<String>();
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		
		for(Map<String,String> map:list){
			refCode = map.get("refCode");
			String id = map.get("id");
			ids.add(id);
		}
		
		RefParamVO refParamVO = SimpleParseXML.getInstance().getMSConfig(refCode);
		String idField = refParamVO.getIdfield();
		String tableName = refParamVO.getTablename();
		List<Map<String, Object>> obj = service.getFilterRef(tableName,idField,refParamVO.getExtcol(),ids);
		if (CollectionUtils.isNotEmpty(obj)) {
			results = buildRtnValsOfRef(obj);
		}
		return results;
	}
	
	/**
	 * 过滤完的数据组装--表格
	 * 
	 * @param peoplelist
	 * @return
	 */
	private List<Map<String, String>> buildRtnValsOfRef(
			List<Map<String, Object>> headVOs) {
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		if ((headVOs != null) && (!headVOs.isEmpty())) {
			ValueConvertor convertor = new ValueConvertor();
			for (Map<String, Object> entity : headVOs) {
				Map<String, String> refDataMap = new HashMap<String, String>();
				for (String key : entity.keySet()) {
					if(key.equalsIgnoreCase("id")){
						refDataMap.put("refpk", entity.get(key).toString());
						refDataMap.put(key.toLowerCase(), entity.get(key).toString());
					}else{
						refDataMap.put(key.toLowerCase(), 
								convertor.convertToJsonType(entity.get(key)).toString());
					}
				}
				results.add(refDataMap);
			}
		}
		return results;
	}
}
