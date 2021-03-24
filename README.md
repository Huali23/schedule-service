# schedule-service
该服务基于quartz进行封装，可以简便的创建定时任务，并提供管理任务和定时器相关接口。  
如果想新建定时任务，在 com.cizing.job.jobs 包下新建任务类并继承 InitializationJob抽象类即可。
