package com.trainings.gc.auditexample.service;

import com.trainings.gc.auditexample.pojos.AbstractAuditPojo;

/**
 * 
 * @author Pavel Seda
 *
 */
public interface AuditService {

	<T extends AbstractAuditPojo> void save(T t);
}
