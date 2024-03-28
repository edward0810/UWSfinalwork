package com.szaiot.njg.config.listener;

import com.szaiot.njg.utils.QueueExcutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SystemInitListener implements ServletContextListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	QueueExcutor exec = new QueueExcutor();
	
	@Value(value = "${project}")
    private String project;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())  
        .getAutowireCapableBeanFactory().autowireBean(this);
		logger.info("----系统初始化开始--" + project + "---");
		logger.info("----系统初始化结束--" + project + "---");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("服务开始销毁");
		if (exec != null) {
			exec.shutdown();
		}
	}

}
