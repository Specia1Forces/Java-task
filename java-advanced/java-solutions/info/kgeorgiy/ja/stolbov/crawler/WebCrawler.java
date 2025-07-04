package info.kgeorgiy.ja.stolbov.crawler;

import info.kgeorgiy.java.advanced.crawler.*;

import java.util.*;
import java.util.concurrent.*;
import java.io.IOException;
import java.net.MalformedURLException;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class WebCrawler implements Crawler {

    @Override
    public Result download(String url, int depth) {
        return null;
    }

    @Override
    public void close() {

    }
}