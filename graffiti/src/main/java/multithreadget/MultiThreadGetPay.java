package multithreadget;


import org.apache.http.util.Asserts;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 多线程限时获取有效支付方式
 */
public class MultiThreadGetPay {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        List<String> payType = Arrays.asList("alipay", "wechat", "union", "master", "pinduoduo");
        long start = System.currentTimeMillis();
        List<String> validType = getValidPay(payType);
        System.out.println("used : " + (System.currentTimeMillis() - start));
        System.out.println(validType);
        executor.shutdown();
        Asserts.check(validType != null && validType.size() == 3, "result error");
    }

    public static List<String> getValidPay(List<String> payType) {
        return payType.parallelStream().filter(type -> {
            // 这里最好不要用ForkJoinPool，因为parallelStream用的就是ForkJoinPool，如果这里调用ForkJoinPool的话，可能会导致这里面的逻辑和valid逻辑分配到同一条线程，那过时策略就有可能会不起效
            Future<Boolean> task = executor.submit(() -> valid(type));
            try {
                return task.get(1, TimeUnit.SECONDS);
            } catch (TimeoutException ignored) {

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
    }

    public static boolean valid(String payType) {
        if (payType.equals("master")) {
            return false;
        }
        if (payType.equals("pinduoduo")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
