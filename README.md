# schedule-service   
主要功能：  
1.对quartz项目进行封装，为其他微服务提供定时任务调度功能。  
2.为前端提供对任务和触发器的管理接口。  
3：简化新建定时任务流程，自动扫描任务并启动任务等。 

如果想新建定时任务，在 com.cizing.job.jobs 包下新建任务类并继承 InitializationJob抽象类即可。
