package com.lcp.learn.spring.cloud.consumer.impl;

import com.lcp.learn.spring.cloud.api.facade.CloudAction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/7/14-16:50
 */
@FeignClient(value = "spring-cloud-provider")
@Service("CloudActionProviderClient")
public interface CloudActionProviderClient extends CloudAction {

}
