package com.guo.javaconcurrencyinpractice.sevencancel.saverunable;

import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//必须是幂等任务,不太懂 TODO P132
public  class WebCrawler {
    private volatile TrackingExecutor exec;
    private final Set<String> urls = new HashSet<>();
    private final List<String> okUrltest = Collections.synchronizedList(new ArrayList<>());


    public void addUrl(String string){
        urls.add(string);
    }

    public List<String> getUrls(){
        return new ArrayList<>(urls);
    }

    public List<String> getOkUrltest() {
        return new ArrayList<>(okUrltest);
    }

    public synchronized void start(){
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (String url : urls) {
            submitCrawTask(url);
        }
        urls.clear();
    }

    public synchronized void stop(){
        System.out.println("start stop");
        try {
            saveUnCrawl(exec.shutdownNow());
            if (exec.awaitTermination(2, TimeUnit.SECONDS)){
                saveUnCrawl(exec.getTasksCancellAtShutDown());
            }
        } finally {
            exec = null;
        }
    }

    private void saveUnCrawl(List<Runnable> runnables){
//        System.out.println("saveUnCrawl" + runnables);
        for (Runnable runnable : runnables) {
            urls.add(((CrawlTAsk)runnable).getUrl());
        }
    }


    private void submitCrawTask(String url){
        exec.execute(new CrawlTAsk(url));
    }

    private class CrawlTAsk implements Runnable{
        private final String url;

        public CrawlTAsk(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10);
                okUrltest.add(url);
            } catch (InterruptedException e) {
                System.out.println("打断");
            }
//            System.out.println(url);
        }

        public String getUrl() {
            return url;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WebCrawler webCrawler = new WebCrawler();
        for (int i = 0; i <1000 ; i++) {
            webCrawler.addUrl(String.valueOf(i));
        }
        webCrawler.start();

//        Thread.sleep(100);

        webCrawler.stop();

        //未完成的url
        List<String> urls = webCrawler.getUrls();
        //已经完成的url
        List<String> okUrltest = webCrawler.getOkUrltest();
        //有无重复运行的url

        for (String s : okUrltest) {
            for (String url : urls) {
                if (s.equals(url)){
                    System.out.println("重复");
                    break;
                }
            }
        }
//        System.out.println("有无重复运行的url"+okUrltest.containsAll(urls));

    }
}
