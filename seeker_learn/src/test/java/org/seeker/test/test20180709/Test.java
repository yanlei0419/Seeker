package seeker.test.test20180709;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Test {
    @Value("${alert.in.time:2}")
    private int time ;
    @Bean
    public LoadingCache buildCache(){
        return CacheBuilder.newBuilder()
                .expireAfterWrite(time, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long key) throws Exception {
                        return new AtomicLong(0);
                    }
                });
    }
    /**
     * 判断是否需要报警
     */
    public void checkAlert() {
//        try {
//            if (counter.get(KEY).incrementAndGet() >= limit) {
//                LOGGER.info("***********报警***********");
////将缓存清空
//                counter.get(KEY).getAndSet(0L);
//            }
//        } catch (ExecutionException e) {
//            LOGGER.error("Exception", e);
//        }
    }
}
