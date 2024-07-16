package com.bx.imserver.task;

import com.bx.imcommon.util.ThreadPoolExecutorFactory;
import com.bx.imserver.netty.IMServerGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractPullMessageTask implements CommandLineRunner {

    private static final ScheduledThreadPoolExecutor EXECUTOR_SERVICE = ThreadPoolExecutorFactory.getThreadPoolExecutor();

    @Autowired
    private IMServerGroup serverGroup;

    @Override
    public void run(String... args) {
        EXECUTOR_SERVICE.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                try {
                    if (serverGroup.isReady()) {
                        pullMessage();
                    }
                } catch (Exception e) {
                    log.error("任务调度异常", e);
                }
                if (!EXECUTOR_SERVICE.isShutdown()) {
                    EXECUTOR_SERVICE.schedule(this,100, TimeUnit.MICROSECONDS);
                }
            }
        });
    }


    public abstract void pullMessage() throws InterruptedException;
}
