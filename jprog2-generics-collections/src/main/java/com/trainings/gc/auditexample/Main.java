package com.trainings.gc.auditexample;

import com.trainings.gc.auditexample.pojos.DescriptionPojo;
import com.trainings.gc.auditexample.service.AuditService;
import com.trainings.gc.auditexample.service.AuditServiceImpl;

/**
 * 
 * @author Pavel Seda
 *
 */
public class Main {

	public static void main(String[] args) {
		DescriptionPojo dp = new DescriptionPojo();
		dp.setId(1L);
		dp.setDescription("test1");
		dp.setNote("some note");

		AuditService auditService = new AuditServiceImpl();
		auditService.<DescriptionPojo>save(dp);
	}
}
