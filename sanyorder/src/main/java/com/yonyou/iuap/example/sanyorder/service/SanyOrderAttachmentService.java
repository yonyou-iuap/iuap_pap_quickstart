package com.yonyou.iuap.example.sanyorder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.iuap.baseservice.service.GenericExService;
import com.yonyou.iuap.example.sanyorder.dao.SanyOrderAttachmentMapper;
import com.yonyou.iuap.example.sanyorder.entity.AttachmentEntity;

@Service
public class SanyOrderAttachmentService extends GenericExService<AttachmentEntity>{

	/**
	 * 新增保存工单信息
	 */
	@Override
	public AttachmentEntity insert(AttachmentEntity AttachmentEntity) {
		return super.insert(AttachmentEntity);
	}
	

	private SanyOrderAttachmentMapper sanyOrderAttachmentMapper;

	@Autowired
	public void setSanyOrderMapper(SanyOrderAttachmentMapper sanyOrderAttachmentMapper) {
		this.sanyOrderAttachmentMapper = sanyOrderAttachmentMapper;
		super.setIbatisMapperEx(this.sanyOrderAttachmentMapper);
	}

	public List<AttachmentEntity> getRefId(String id) {
		// TODO Auto-generated method stub
		List<AttachmentEntity> AttachmentEntitys = sanyOrderAttachmentMapper.getRefId(id);
		return AttachmentEntitys;
	}
}